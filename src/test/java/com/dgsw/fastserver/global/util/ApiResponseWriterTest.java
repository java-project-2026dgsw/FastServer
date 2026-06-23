package com.dgsw.fastserver.global.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dgsw.fastserver.global.data.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

@SpringBootTest
class ApiResponseWriterTest {

    @Autowired
    private ApiResponseWriter apiResponseWriter;

    @Test
    void writeSerializesErrorResponseTimestampWithConfiguredObjectMapper() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() ->
                apiResponseWriter.write(
                        HttpStatus.BAD_REQUEST,
                        ApiResponse.error(HttpStatus.BAD_REQUEST, "INVALID_ARGUMENT", "invalid request"),
                        response
                )
        );

        assertTrue(response.getContentAsString().contains("\"timestamp\""));
    }
}
