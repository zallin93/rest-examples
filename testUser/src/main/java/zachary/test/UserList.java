package com.zachary.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList
{
	private User[] data;
	private Integer total;
	private Integer start;
	private String sort;
	private String order;
	private Integer size;
}