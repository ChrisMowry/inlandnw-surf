package com.inlandnwsurf.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="server")
public class ApplicationConfig {

	private ServerConfigModel serverConfigModel
}
