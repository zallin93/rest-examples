package com.zachary.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.apache.commons.codec.binary.Base64;
import java.nio.charset.Charset;

@SpringBootApplication
public class Application implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger(Application.class);


	private HttpHeaders createHeaders(final String username, final String password)
	{
		HttpHeaders headers = new HttpHeaders()
		{
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
				String authHeader = "Basic " + new String( encodedAuth );
				set("Authorization", authHeader);
			}
		};
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");

		return headers;
	}


	public static void main(String[] args)
	{
		SpringApplication.run(Application.class);
	}

	@Override
	public void run(String... strings) throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserList> myList;
		HttpHeaders httpHeaders = this.createHeaders("kermit", "kermit");

		String url = "http://localhost:8080/activiti-rest/service/identity/users/";
		myList = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), UserList.class);

		//UserList myList = restTemplate.getForObject("http://kermit:kermit@localhost:8080/activiti-rest/service/identity/users/", UserList.class);
		//UserList myList = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", UserList.class);
		log.info(myList.toString());
	}

}