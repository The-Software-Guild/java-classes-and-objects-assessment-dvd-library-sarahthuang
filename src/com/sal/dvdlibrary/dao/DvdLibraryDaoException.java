package com.sal.dvdlibrary.dao;

public class DvdLibraryDaoException extends Exception {
    public DvdLibraryDaoException(String message) {
        super(message);
    }

    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return "Error!!! Something went wrong";
    }
}
