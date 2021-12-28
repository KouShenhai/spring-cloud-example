package com.livk.common;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 * LivkBanner
 * </p>
 *
 * @author livk
 * @date 2021/8/30
 */
public class LivkBanner implements Banner {

    private LivkBanner() {
    }

    private static final String[] banner = {"""
 ██       ██          ██         ██████   ██                       ██
░██      ░░          ░██        ██░░░░██ ░██                      ░██
░██       ██ ██    ██░██  ██   ██    ░░  ░██  ██████  ██   ██     ░██
░██      ░██░██   ░██░██ ██   ░██        ░██ ██░░░░██░██  ░██  ██████
░██      ░██░░██ ░██ ░████    ░██        ░██░██   ░██░██  ░██ ██░░░██
░██      ░██ ░░████  ░██░██   ░░██    ██ ░██░██   ░██░██  ░██░██  ░██
░████████░██  ░░██   ░██░░██   ░░██████  ███░░██████ ░░██████░░██████
░░░░░░░░ ░░    ░░    ░░  ░░     ░░░░░░  ░░░  ░░░░░░   ░░░░░░  ░░░░░░\s
"""};

    @SneakyThrows
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (var line : banner) {
            out.println(line);
        }
        var format = Format.create(out, 70);
        format.accept("Spring Boot Version: " + SpringBootVersion.getVersion());
        format.accept("Current time: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        format.accept("Current JDK Version: " + System.getProperty("java.version"));
        format.accept("Operating System: " + System.getProperty("os.name"));
        out.flush();
    }

    public static LivkBanner create() {
        return new LivkBanner();
    }

    private record Format(int n, PrintStream out, char ch) implements Function<String, String>, Consumer<String> {
        @Override
        public String apply(String str) {
            int length = str.length();
            if (length >= n) {
                return str;
            }
            int index = (n - length) >> 1;
            str = StringUtils.leftPad(str, length + index, ch);
            return StringUtils.rightPad(str, n, ch);
        }

        @Override
        public void accept(String s) {
            out.println(this.apply(s));
        }

        public static Format create(PrintStream out, int n) {
            return new Format(n, out, '*');
        }
    }

}
