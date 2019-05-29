package org.man.common.model.helper;

import java.lang.annotation.*;

import static org.man.common.util.LookupConstant.DEFAULT_LOOKUP_TYPE;
import static org.man.common.util.LookupConstant.DEFAULT_OVERRIDE_HEADER_AND_DESCRIPTION;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface LookupType {
    String value() default DEFAULT_LOOKUP_TYPE;
    boolean overrideHeaderAndDescription() default DEFAULT_OVERRIDE_HEADER_AND_DESCRIPTION;
}
