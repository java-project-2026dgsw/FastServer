package com.dgsw.fastserver.global.util;

import com.dgsw.fastserver.global.data.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 임시로
@Component
public class ApiResponseWriter {

    private final ObjectMapper mapper = new ObjectMapper();

    public void write(HttpStatus status,
                      ApiResponse<Void> apiResponse,
                      HttpServletResponse response) throws IOException {

        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");

        String json = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
    }
}