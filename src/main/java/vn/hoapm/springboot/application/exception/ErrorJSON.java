package vn.hoapm.springboot.application.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorJSON {

    public final String code;
    public final String field;
    public final String message;

    public ErrorJSON(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public ErrorJSON(String code, String message) {
        this.code = code;
        this.message = message;
        this.field = null;
    }

}
