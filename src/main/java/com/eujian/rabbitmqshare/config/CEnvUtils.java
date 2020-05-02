package com.eujian.rabbitmqshare.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CEnvUtils implements EnvironmentAware {

    private static Environment environment;

    public static String getPropertis(String key) {
        return environment.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment environment) {
        CEnvUtils.environment = environment;
    }


}