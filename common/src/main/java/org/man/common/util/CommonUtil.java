package org.man.common.util;

import java.util.UUID;

import static org.springframework.util.StringUtils.replace;

public class CommonUtil {
    public static String generateUUID() {
        return replace(UUID.randomUUID().toString(),"-","");
    }
}
