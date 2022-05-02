package com.ngai.auth.Utils;

public class Parameters {
    public static final String SERVER_ERROR = "Server Error";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String REGISTRATION_FAILED = "Can not register at this time, Try again later";
    public static final String USER_EXISTS = "Username already taken";
    public static final String COUNTRY_N0T_SUPPORTED = "Country not supported";
    public static final String REGISTRATION_SUCCESSFUL = "REGISTRATION SUCCESSFUL";
    public static final String INVALID_CREDENTIALS = "Invalid Credentials";
    public static final String SUCCESS = "SUCCESS";

    public enum USER_STATUS {
        active, disabled, suspended;

    }

    //db params JWT
    public static final String PARAM_JWT_TOKEN_EXPIRATION_TIME = "JWT_TOKEN_EXPIRATION_TIME";
    public static final String PARAM_JWT_ENCRYPTION_KEY = "JWT_ENCRYPTION_KEY";
}
