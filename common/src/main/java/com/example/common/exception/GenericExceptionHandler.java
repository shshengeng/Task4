package com.example.common.exception;

import com.example.common.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseResult<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseResult<Object> response = new ResponseResult<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMsg("Resource Not Found");
        response.setData(null);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseResult<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseResult<Object> response = new ResponseResult<>();
        response.setStatusCode(HttpStatus.FORBIDDEN.value());
        response.setMsg("Access Denied");
        response.setData(null);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult<Object> handleBadRequestException(BadRequestException ex) {
        ResponseResult<Object> response = new ResponseResult<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMsg("Bad Request");
        response.setData(null);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseResult<Object> handleInternalServerErrorException(InternalServerErrorException ex) {
        ResponseResult<Object> response = new ResponseResult<>();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMsg("Internal Server Error");
        response.setData(null);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
