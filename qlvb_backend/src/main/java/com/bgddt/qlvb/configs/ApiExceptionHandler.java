package com.bgddt.qlvb.configs;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.utils.Util;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleAllException(Exception ex, WebRequest request) {
        System.out.println("---");
        ex.printStackTrace();
        return ResponseError.internalServerError(ex, request);
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleBusinessException(BusinessException ex, WebRequest request) {
        return ResponseError.businessError(ex, request);
    }

}
@Data
class ResponseError {
    private int status;
    private Date timestamp;
    private String error;
    private String message;
    private String description;
    private String path;

    public ResponseError(HttpStatus httpStatus, String error, String message, String description, String path) {
        this.status = httpStatus.value();
        this.timestamp = new Date();
        this.error = error;
        this.message = message;
        this.description = Util.nullToEmpty(description);
        this.path = path == null ? "" : path.replace("uri=", "");
    }
    public static ResponseError internalServerError(Exception ex, WebRequest request) {
        return new ResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Lỗi máy chủ",
                ex.getMessage(),
                ex.getLocalizedMessage(),
                request.getDescription(false)
        );
    }
    public static ResponseError businessError(BusinessException ex, WebRequest request) {
        return new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Lỗi nghiệp vụ",
                ex.getMessage(),
                ex.getDescription(),
                request.getDescription(false)
        );
    }
}
