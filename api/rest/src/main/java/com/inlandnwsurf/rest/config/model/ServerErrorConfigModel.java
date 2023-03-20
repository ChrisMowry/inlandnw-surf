package com.inlandnwsurf.rest.config.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerErrorConfigModel {
	private final String includeMessage;
	private final String includeBindingErrors;
	private final String includeStackTrace;
	private final boolean includeException;
	
	ServerErrorConfigModel(String includeMessage)
	
}
