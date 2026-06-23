package com.dgsw.fastserver.global.util;

import com.dgsw.fastserver.global.data.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiResponseWriter {

    private final ObjectMapper mapper;

    public void write(HttpStatus status,
                      ApiResponse<Void> apiResponse,
                      HttpServletResponse response) throws IOException {

        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");

        String json = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
    }
}