package com.lft.taskservice.tasks.adapters.rest.exceptionhandler;

import com.lft.taskservice.tasks.adapters.rest.TaskBadFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TaskBadFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse taskBadFormatHandler(Exception exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handler(Exception exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

}
