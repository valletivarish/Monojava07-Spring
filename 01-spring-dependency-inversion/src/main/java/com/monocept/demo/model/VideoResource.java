package com.monocept.demo.model;

import org.springframework.stereotype.Component;

//@Component
public class VideoResource implements Resource {

	@Override
	public String getResource() {
		return "sending video resource";
	}

}
