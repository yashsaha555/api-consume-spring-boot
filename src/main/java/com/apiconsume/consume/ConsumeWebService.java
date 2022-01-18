package com.apiconsume.consume;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apiconsume.entity.Course;

@RestController
public class ConsumeWebService {
	@Autowired
	RestTemplate restTemplate;
	@Scheduled(fixedRate = 5000)
	@RequestMapping(value = "/template/getCourses")
	   public String getCourses() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange("http://localhost:8080/course/getCourses", HttpMethod.GET, entity, String.class).getBody();
	   }
	@RequestMapping(value = "/template/createCourse", method = RequestMethod.POST)
	   public String createCourse(@RequestBody Course course) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Course> entity = new HttpEntity<Course>(course,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/course/postCourse", HttpMethod.POST, entity, String.class).getBody();
	   }
	   @RequestMapping(value = "/template/updateCourse/{id}", method = RequestMethod.PUT)
	   public String updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Course> entity = new HttpEntity<Course>(course,headers);
	      
	      return restTemplate.exchange("http://localhost:8080/course/updateCourse/"+id, HttpMethod.PUT, entity, String.class).getBody();
	   }
	   @RequestMapping(value = "/template/deleteCourse/{id}", method = RequestMethod.DELETE)
	   public String deleteCourse(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Course> entity = new HttpEntity<Course>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/course/deleteCourse/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
	}
