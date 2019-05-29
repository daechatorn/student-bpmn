package org.man.common.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class BaseResponseObject implements Serializable {
    private ResponseStatus status;

    public BaseResponseObject() {
        this(new ResponseStatus());
    }

    public BaseResponseObject(ResponseStatus status) {
        this.status = status;
    }

    public BaseResponseObject(Integer code) {
        this(new ResponseStatus(code));
    }

    public BaseResponseObject(Integer code, String description) {
        this(new ResponseStatus(code, description));
    }

    public BaseResponseObject(Integer code, String header, String description) {
        this(new ResponseStatus(code, header, description));
    }

    @JsonIgnore
    public Integer getCode() {
        return this.status.getCode();
    }

    public BaseResponseObject setCode(Integer code) {
        this.status.setCode(code);
        return this;
    }

    @JsonIgnore
    public String getHeader() {
        return this.status.getHeader();
    }

    public BaseResponseObject setHeader(String header) {
        this.status.setHeader(header);
        return this;
    }

    @JsonIgnore
    public String getDescription() {
        return this.status.getDescription();
    }

    public BaseResponseObject setDescription(String description) {
        this.status.setDescription(description);
        return this;
    }

    public String toString() {
        return "{status=" + this.status + '}';
    }

    public ResponseStatus getStatus() {
        return this.status;
    }

    public BaseResponseObject setStatus(final ResponseStatus status) {
        this.status = status;
        return this;
    }
}