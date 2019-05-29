package org.man.common.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel<T> extends BaseResponseObject {
    @JsonProperty("data")
    private T dataObj;

    public ResponseModel() {
    }

    public ResponseModel(Integer code) {
        super(code);
    }

    public ResponseModel(Integer code, String description) {
        super(code, description);
    }

    public ResponseModel(Integer code, String header, String description) {
        super(code, header, description);
    }

    public ResponseModel(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public ResponseModel<T> setCode(Integer code) {
        super.setCode(code);
        return this;
    }

    public ResponseModel<T> setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    public ResponseModel<T> setHeader(String header) {
        super.setHeader(header);
        return this;
    }

    public ResponseModel<T> setStatus(ResponseStatus responseStatus) {
        super.setStatus(responseStatus);
        return this;
    }

    public T getDataObj() {
        return this.dataObj;
    }

    public ResponseModel<T> setDataObj(final T dataObj) {
        this.dataObj = dataObj;
        return this;
    }
}