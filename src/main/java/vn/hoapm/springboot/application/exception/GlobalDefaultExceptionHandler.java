package vn.hoapm.springboot.application.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.hoapm.springboot.application.common.APIResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

//@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });

        ex.getBindingResult().getGlobalErrors().stream()
                .forEach(fieldError -> {
                    errors.put(fieldError.getCode(), fieldError.getDefaultMessage());
                });
        return new ResponseEntity<>(errors, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(RuntimeException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleRuntimeException(RuntimeException e) {
        APIResponse<String> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    @ExceptionHandler(CommonException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleAccountDomainException(CommonException e) {
        APIResponse<ErrorJSON> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(new ErrorJSON(String.valueOf(e.getErrorCode()), e.getMessage()),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
