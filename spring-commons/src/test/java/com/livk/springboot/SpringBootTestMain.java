package com.livk.springboot;

import com.livk.commons.spring.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 * SpringBootTestMain
 * </p>
 *
 * @author livk
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootTestMain {
    public static void main(String[] args) {
        LivkSpring.run(SpringBootTestMain.class, args);
    }
}
