package com.livk.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * <p>
 * YamlUtilsTest
 * </p>
 *
 * @author livk
 * @date 2022/10/17
 */
class YamlUtilsTest {

    @Test
    void mapToYmlTest() {
        String yml = """
                spring:
                  redis:
                    port: '5672'
                    host: livk.com
                    """;
        Map<String, String> map = Map.of("spring.redis.host", "livk.com", "spring.redis.port", "5672");
        String result = YamlUtils.mapToYml(map);
        Assertions.assertEquals(yml, result);
    }
}
