package com.ngai.auth.configurations;

import com.ngai.auth.components.ParamsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomBeans{
    @Autowired
    ParamsCache paramsCache;

    @Bean
    public void loadParams() {
        System.out.println("lOADING PARAMETERS....");
        paramsCache.loadDbParams();
        System.out.println("DONE LOADING PARAMETERS");
    }
}
