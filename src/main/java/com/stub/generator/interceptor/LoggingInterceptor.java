package com.stub.generator.interceptor;

import com.stub.generator.entity.RequestResponseLog;
import com.stub.generator.repository.RequestResponseLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private final RequestResponseLogRepository logRepository;

    public LoggingInterceptor(RequestResponseLogRepository logRepository) {
        this.logRepository = logRepository;
    }
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("Incoming request URL: " + request.getRequestURL());
//        System.out.println("HTTP Method: " + request.getMethod());
//        System.out.println("Request Headers: " + request.getHeaderNames());
//        // Save the request details here (e.g., in a database)
//        return true; // Continue to the next step
//    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", LocalDateTime.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        RequestResponseLog log = new RequestResponseLog();
        log.setUrlPath(String.valueOf(request.getRequestURL()));
        log.setHttpMethod(request.getMethod());
        log.setHeaders(request.getHeaderNames().toString());  // Simplified, consider better header handling
        log.setRequestBody(new String(request.getInputStream().readAllBytes()));  // Capture request body
        log.setResponseBody((String) request.getAttribute("responseBody"));  // Store response body from attribute
        log.setStatusCode(String.valueOf(response.getStatus()));
        log.setTimestamp(LocalDateTime.now());
        log.setResponsePayload(String.valueOf(response.getTrailerFields()));

        logRepository.save(log);
    }
}
