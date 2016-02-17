package com.otpp.batchJob.ws.controller;


import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class BatchJobControllerAdvice extends ResponseEntityExceptionHandler {

    private final Random tokenGenerator = new Random(new Date().getTime());


    @ResponseBody
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String databaseSqlError(SQLException sqlEx) {
        return sqlEx.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String databaseSpringDataError(DataIntegrityViolationException divEx) {
        return divEx.getRootCause().getMessage();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Catch all. Never let an exception fly out to the service client.
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo unhandledError(HttpServletRequest request, Exception e) {
        String marker = "Reference marker: " + getNewMarker();
        log.error(marker);
        log.error("Unhandled service error: ", e);
        return new ErrorInfo(request.getRequestURI(), request.getParameterMap(), e, marker);
    }

    private String getNewMarker() {
        long token = tokenGenerator.nextLong();
        if (token < 0) {
            token = token * -1;
        }
        return "E" + token;
    }
}