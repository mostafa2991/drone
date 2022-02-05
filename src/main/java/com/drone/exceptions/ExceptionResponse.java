package com.drone.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;
    private Date timeStamp;

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }
    public void setRequestedURI(final String requestedURI) {
        this.requestedURI = requestedURI;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

