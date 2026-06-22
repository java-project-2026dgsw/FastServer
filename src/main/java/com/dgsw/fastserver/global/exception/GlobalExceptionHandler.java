package com.dgsw.fastserver.global.exception;

import com.dgsw.fastserver.global.data.ApiResponse;
import com.dgsw.fastserver.global.data.ErrorResponse;
import com.dgsw.fastserver.global.exception.exception.ApplicationException;
import com.dgsw.fastserver.global.exception.status_code.CommonStatusCode;
import com.dgsw.fastserver.global.exception.status_code.StatusCode;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Void>> handleApplicationException(ApplicationException ex) {
        StatusCode statusCode = ex.getStatusCode();

        ErrorResponse error = ErrorResponse.of(
                statusCode.getCode(),
                ex.getMessage() != null ? ex.getMessage() : statusCode.getMessage()
        );

        return ResponseEntity
                .status(statusCode.getHttpStatus())
                .body(ApiResponse.error(statusCode.getHttpStatus(), error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage() != null
                    ? error.getDefaultMessage()
                    : "잘못된 입력값입니다.";

            details.put(field, message);
        });

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INVALID_ARGUMENT.getCode(),
                "요청값이 유효하지 않습니다.",
                details
        );

        return ResponseEntity
                .status(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus(), error));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowedException(
            HttpRequestMethodNotSupportedException ex) {

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.ENDPOINT_NOT_FOUND.getCode(),
                CommonStatusCode.ENDPOINT_NOT_FOUND.getMessage()
        );

        return ResponseEntity
                .status(CommonStatusCode.ENDPOINT_NOT_FOUND.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.ENDPOINT_NOT_FOUND.getHttpStatus(), error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(
            ConstraintViolationException ex) {

        Map<String, String> details = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            details.put(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );
        });

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INVALID_ARGUMENT.getCode(),
                "요청값이 유효하지 않습니다.",
                details
        );

        return ResponseEntity
                .status(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus(), error));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INVALID_ARGUMENT.getCode(),
                "필수 파라미터가 누락되었습니다: " + ex.getParameterName()
        );

        return ResponseEntity
                .status(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus(), error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INVALID_ARGUMENT.getCode(),
                "파라미터 타입이 잘못되었습니다: " + ex.getName()
        );

        return ResponseEntity
                .status(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus(), error));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INVALID_ARGUMENT.getCode(),
                ex.getMessage() != null
                        ? ex.getMessage()
                        : "잘못된 요청 파라미터입니다."
        );

        return ResponseEntity
                .status(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INVALID_ARGUMENT.getHttpStatus(), error));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFoundException(
            NoResourceFoundException ex) {

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.ENDPOINT_NOT_FOUND.getCode(),
                CommonStatusCode.ENDPOINT_NOT_FOUND.getMessage()
        );

        return ResponseEntity
                .status(CommonStatusCode.ENDPOINT_NOT_FOUND.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.ENDPOINT_NOT_FOUND.getHttpStatus(), error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        log.error("요청 처리 중 에러 발생", ex);

        ErrorResponse error = ErrorResponse.of(
                CommonStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                CommonStatusCode.INTERNAL_SERVER_ERROR.getMessage()
        );

        return ResponseEntity
                .status(CommonStatusCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.error(CommonStatusCode.INTERNAL_SERVER_ERROR.getHttpStatus(), error));
    }
}