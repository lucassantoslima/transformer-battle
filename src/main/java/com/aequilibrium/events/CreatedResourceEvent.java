package com.aequilibrium.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class CreatedResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Integer id;

	public CreatedResourceEvent(Object source, HttpServletResponse response, Integer id) {
		super(source);
		this.response = response;
		this.id = id;
	}

}

