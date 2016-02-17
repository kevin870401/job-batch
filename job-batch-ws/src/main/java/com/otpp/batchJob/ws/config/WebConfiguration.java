package com.otpp.batchJob.ws.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

}