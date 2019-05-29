package org.man.process.config.lookup;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.man.common.model.helper.LookupStore;
import org.man.common.model.response.BaseResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.man.common.util.LookupConstant.DEFAULT_LOOKUP_TYPE;
import static org.man.common.util.LookupConstant.LOOKUP_TYPE_NAME;


@ControllerAdvice
@Slf4j
public class LookupAdvice implements ResponseBodyAdvice<BaseResponseObject> {
    @Autowired
    private HttpHeaders httpHeaders;
    @Autowired
    @Qualifier(LOOKUP_TYPE_NAME)
    private LookupStore lookupStore;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean isSupport = false;
        if (returnType.getGenericParameterType() instanceof ParameterizedType){
            Type[] types = ((ParameterizedType) returnType.getGenericParameterType()).getActualTypeArguments();
            if(types.length > 0 && ( isSupportSuperClass(types[0]) || isSupportParameterizedType(types[0]) ) ){
                isSupport = true;
            }
        }
        log.debug("This endpoint is using ResponseModel class: {}", isSupport);
        return isSupport;
    }

    private boolean isSupportParameterizedType(Type returnType){
        if(returnType instanceof ParameterizedType && ((Class)((ParameterizedType)returnType).getRawType()).getSuperclass() == BaseResponseObject.class){
            return true;
        }
        return false;
    }

    private boolean isSupportSuperClass(Type returnType){
        if(returnType instanceof Class && ( ((Class)returnType).getSuperclass() == BaseResponseObject.class || returnType == BaseResponseObject.class ) ){
            return true;
        }
        return false;
    }

    /**
     * Overrides the outgoing Response object by adding or replacing the original responseModel.description parameter with a value that is looked up based on the responseModel.code value
     *
     * @param responseModel
     * @param returnType
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public BaseResponseObject beforeBodyWrite(BaseResponseObject responseModel, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.debug("Override header and description = {}", lookupStore.isOverrideHeaderAndDescription());
        if (lookupStore.isOverrideHeaderAndDescription() || responseModel.getDescription() == null) {
            String myLookupType = lookupStore.isEmpty() ? DEFAULT_LOOKUP_TYPE : lookupStore.getLookupType();
            log.debug("Look Type: {}", myLookupType);

            String description = StringUtils.EMPTY;

            switch (responseModel.getCode()){
                case 1000:
                    description = "Success";
                    break;
                case 1899:
                    description = "Business error";
                    break;
                default:
                    description = "Unexpected error";
                    break;
            }
            responseModel.getStatus().setDescription(description);
            log.info("response = {}", responseModel);
            return responseModel;
        }
        else {
            return responseModel;
        }
    }
}