package vn.hoapm.springboot.application.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
public class CommonException extends Exception {
    private List<ErrorJSON> errors;
    private int errorCode;

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException() {
        super();
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException(List<ErrorJSON> errors) {
        this.errors = errors;
    }

    public CommonException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
