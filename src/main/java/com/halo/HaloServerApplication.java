package com.halo;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author MelloChan
 * @date 2018/5/2
 */
@SpringBootApplication
public class HaloServerApplication {
    private static ImmutableMap<String, String> errorCodeMap = null;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("error_code.properties");
            errorCodeMap = Maps.fromProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(HaloServerApplication.class, args);
    }

    @Bean
    public ImmutableMap<String, String> errorCodeMap() {
        return errorCodeMap;
    }
}
