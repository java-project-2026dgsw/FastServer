package com.dgsw.fastserver.global.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dgsw.fastserver.global.data.ApiResponse;
import java.lang.reflect.Method;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleValidationExceptionSupportsGlobalObjectErrors() throws Exception {
        MethodParameter parameter = new MethodParameter(
                getSampleMethod("sampleMethod", SampleRequest.class),
                0
        );
        BindingResult bindingResult = new BeanPropertyBindingResult(new SampleRequest(), "sampleRequest");
        bindingResult.addError(new ObjectError("sampleRequest", "class-level validation failed"));

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity<ApiResponse<Void>> response =
                globalExceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().error());
        assertEquals("class-level validation failed", response.getBody().error().details().get("sampleRequest"));
    }

    @Test
    void handleMethodNotAllowedReturns405Status() {
        HttpRequestMethodNotSupportedException exception =
                new HttpRequestMethodNotSupportedException("GET");

        ResponseEntity<ApiResponse<Void>> response =
                globalExceptionHandler.handleMethodNotAllowedException(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getBody().status());
    }

    private Method getSampleMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        return getClass().getDeclaredMethod(name, parameterTypes);
    }

    @SuppressWarnings("unused")
    private void sampleMethod(@Valid SampleRequest request) {
    }

    private static class SampleRequest {
    }
}
