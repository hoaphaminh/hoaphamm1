package vn.hoapm.springboot.application.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private String message;
    private int code;
    private String requestId;
    private T data;

    public ResponseEntity<APIResponse> sendResponse(T data, int code, String message) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.requestId = null;

        return new ResponseEntity<APIResponse>(this, HttpStatus.valueOf(code));
    }

    public ResponseEntity<APIResponse> sendResponse(T data, int code, String message, String requestId) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.requestId = requestId;

        return new ResponseEntity<APIResponse>(this, HttpStatus.valueOf(code));
    }

}
