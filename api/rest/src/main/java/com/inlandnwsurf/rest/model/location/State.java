package com.inlandnwsurf.rest.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
	private long id;
	private String name;
	private String abbr;
	private String country;
}
