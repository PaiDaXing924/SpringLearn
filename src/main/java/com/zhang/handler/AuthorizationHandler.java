package com.zhang.handler;


import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public String AuthorizationHandlerEx() {
        return "403";
    }

}
