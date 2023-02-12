package com.hollow.server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    
    SUCCESS(200, "Succeeded!"),

    WRONG_REQUEST(400, "Failed to understand the request!"),
    NOT_AUTHORIZED(401, "Request denied for authorization failed!"),
    LOGGED_ON_ALREADY(402, "User has been logged on other device!"),
    EMAIL_USED_ALREADY(421, "The email has been already used for enrollment!"),
    NAME_USED_ALREADY(422, "Name should be unique and has been used already!"),
    EMPTY_PASSWORD(423, "Please input your password and try again!"),
    WRONG_PASSWORD(424, "Wrong password!"),
    WRONG_VERIFICATION(425, "Wrong verification. A new one has been sent!"),
    WRONG_EMAIL(426, "Please ensure you are inputting a correct email!"),
    WRONG_NAME(427, "The name you are inputting is illegal!"),
    USER_NOT_EXIST(428, "Couldn't find a user matching the email/name!"),

    DATABASE_ERROR(503, "Database failed to process the request!"),
    DATA_PASSING_ERROR(504, "Error occurred when passing data in the software!"),

    UNKONWN_ERROR(600, "An uncategorized error occurred!");
    
    private final int code;
    private final String msg;
}
