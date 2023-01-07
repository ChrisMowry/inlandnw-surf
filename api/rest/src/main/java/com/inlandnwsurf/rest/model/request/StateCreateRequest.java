package com.inlandnwsurf.rest.model.request;

import lombok.Data;

@Data
public class StateCreateRequest {
	private String name;
	private String abbrv;
	private String country;
}
