package com.tosan.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class LoggingServiceImpl implements LoggingService {

    Logger logger = LoggerFactory.getLogger("LoggingServiceImpl");

    @Override
    public void displayReq(HttpServletRequest request, Object body) {
        StringBuilder reqMessage = new StringBuilder();
        Map<String, String> parameters = getParameters(request);

        reqMessage.append("REQUEST:\n");
        reqMessage.append("   method = [").append(request.getMethod()).append("]\n");
        reqMessage.append("   path = [").append(request.getRequestURI()).append("]\n");

        if (!parameters.isEmpty()) {
            reqMessage.append("   parameters = [").append(parameters).append("]\n");
        }

        if (!Objects.isNull(body)) {
            reqMessage.append("   body = [").append(body).append("]");
        }

        logger.info("\n{}", reqMessage);
    }

    @Override
    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder respMessage = new StringBuilder();
        Map<String, String> headers = getHeaders(response);
        respMessage.append("RESPONSE:\n");
        respMessage.append("   method = [").append(request.getMethod()).append("]\n");
        if (!headers.isEmpty()) {
            respMessage.append("   ResponseHeaders = [").append(headers).append("]\n");
        }
        respMessage.append("   responseBody = [").append(body).append("]\n");
        respMessage.append("   HTTP STATUS = ").append(response.getStatus()).append("\n");
        logger.info("\n{}", respMessage);
    }

    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerMap = response.getHeaderNames();
        for (String str : headerMap) {
            headers.put(str, response.getHeader(str));
        }
        return headers;
    }

    private Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }


}