package org.man.common.model.exception;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public class BusinessException extends RuntimeException {
    private String language;
    private final String code;

    public BusinessException(String language, String code, Throwable throwable) {
        super(throwable);
        this.language = language;
        this.code = code;
    }

    public BusinessException(String language, String code) {
        super(code);
        this.language = language;
        this.code = code;
    }

    public BusinessException(String code) {
        super(code);
        this.code = code;
    }

    public BusinessException(Integer code) {
        super(code.toString());
        this.code = code.toString();
    }
}
