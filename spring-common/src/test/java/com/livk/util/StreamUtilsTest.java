package com.livk.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <p>
 * StreamUtilsTest
 * </p>
 *
 * @author livk
 * @date 2022/7/26
 */
class StreamUtilsTest {

    @Test
    void testConcat3() {
        Map<String, List<String>> result = StreamUtils.concat(Map.of("username", "livk", "password", "123456"),
                Map.of("username", "root", "host", "192.168.1.1"));
        Map<String, List<String>> listMap = Map.of("username", List.of("livk", "root"), "password",
                List.of("123456"), "host", List.of("192.168.1.1"));
        Assertions.assertEquals(listMap, result);
    }

    @Test
    void testDistinct() {
        List<Integer> list = Stream.of(1, 1, 2, 2, 3, 3, 4, 4)
                .filter(StreamUtils.distinct(Function.identity()))
                .toList();
        Assertions.assertEquals(list, List.of(1, 2, 3, 4));

        List<User> users = Stream.of(new User(1, "1"), new User(1, "11"),
                        new User(2, "2"), new User(2, "2"))
                .filter(StreamUtils.distinct(User::id))
                .toList();
        Assertions.assertEquals(users, List.of(new User(1, "1"), new User(2, "2")));
    }

    @Test
    void of() {
        List<Integer> result = StreamUtils.of(List.of(1, 2, 3).iterator()).toList();
        Assertions.assertIterableEquals(List.of(1, 2, 3), result);
    }

    private record User(Integer id, String username) {

    }
}


