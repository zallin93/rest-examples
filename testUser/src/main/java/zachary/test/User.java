package com.zachary.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
	private String id;
	private String firstName;
	private String lastName;
	private String url;
	private String email;
}