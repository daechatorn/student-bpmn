package org.man.process.config.lookup;

import org.man.common.model.helper.LookupStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import static org.man.common.util.LookupConstant.*;

@Configuration
public class LookupConfig {

    @Bean(name = LOOKUP_TYPE_NAME)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LookupStore lookupType(){
        return new LookupStore(DEFAULT_LOOKUP_TYPE, DEFAULT_OVERRIDE_HEADER_AND_DESCRIPTION);
    }
}
