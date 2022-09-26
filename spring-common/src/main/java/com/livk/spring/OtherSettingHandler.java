package com.livk.spring;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>
 * OtherBannerHandler
 * </p>
 *
 * @author livk
 * @date 2022/5/26
 */
@Configuration
public class OtherSettingHandler implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String PAGEHELPER_BANNER = "pagehelper.banner";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();

        boolean PageHelperBannerEnable = Boolean
                .parseBoolean(System.getProperty(PAGEHELPER_BANNER, environment.getProperty(PAGEHELPER_BANNER)));
        System.setProperty(PAGEHELPER_BANNER, Boolean.toString(PageHelperBannerEnable));
    }

}
