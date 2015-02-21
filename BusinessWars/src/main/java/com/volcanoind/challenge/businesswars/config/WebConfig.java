package com.volcanoind.challenge.businesswars.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan( "com.volcanoind" )
//@ImportResource( "/WEB-INF/spring-config/web-config.xml" )
public class WebConfig {

}
