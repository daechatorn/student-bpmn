package org.man.common.model.response;

import java.io.Serializable;

public class ResponseStatus implements Serializable {
    private Integer code;
    private String header;
    private String description;

    public ResponseStatus(Integer code) {
        this.code = code;
    }

    public ResponseStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getHeader() {
        return this.header;
    }

    public String getDescription() {
        return this.description;
    }

    public ResponseStatus setCode(final Integer code) {
        this.code = code;
        return this;
    }

    public ResponseStatus setHeader(final String header) {
        this.header = header;
        return this;
    }

    public ResponseStatus setDescription(final String description) {
        this.description = description;
        return this;
    }

    public String toString() {
        return "ResponseStatus(code=" + this.getCode() + ", header=" + this.getHeader() + ", description=" + this.getDescription() + ")";
    }

    public ResponseStatus(final Integer code, final String header, final String description) {
        this.code = code;
        this.header = header;
        this.description = description;
    }

    public ResponseStatus() {
    }
}
