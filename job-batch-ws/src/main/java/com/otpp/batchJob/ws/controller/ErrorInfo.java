package com.otpp.batchJob.ws.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

public class ErrorInfo {

    public final String url;
    public final Map<String, String[]> parameters;
    public final String stackTrace;
    public final String markerReference;

    public ErrorInfo(String url, Map<String, String[]> map, Exception e, String marker) {
        this.url = url;
        this.parameters = map;
        stackTrace = ExceptionUtils.getStackTrace(e);
        this.markerReference = marker;
    }
}
