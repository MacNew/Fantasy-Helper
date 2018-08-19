package com.fantasyhelper.fantasyhelper.config;
import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Exception.ErrorObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@ControllerAdvice
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ErrorObj errorObj;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        // 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    }

    @ExceptionHandler (value = {MyCustomException.class})
    public final ResponseEntity<ErrorObj> commence(HttpServletRequest request, HttpServletResponse response,
                                                   Exception exception) throws IOException {
        errorObj.setErroMessage(exception.getMessage());
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        return  new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }
}
