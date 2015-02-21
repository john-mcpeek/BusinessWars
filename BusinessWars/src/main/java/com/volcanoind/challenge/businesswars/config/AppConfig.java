package com.volcanoind.challenge.businesswars.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource( "/WEB-INF/spring-config/app-config.xml" )
public class AppConfig {
	
	@Value( "${competitors}" )
	private String competitorsStr;
	
	@Bean
	public String[] competitors() {
		return competitorsStr.split( "," );
	}
}
