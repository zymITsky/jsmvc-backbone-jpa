
package com.spa.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spa.demo.entity.Event;
import com.spa.demo.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags={"api-main"})
public class ApiController {

	@Autowired
	private EventService eventService = null;

	@GetMapping("/events")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get all the event entities.", notes="Get all the event entities.")
	public List<Event> getAllEvents() {
		log.info("readAllEvents() is calling ...");
		return eventService.readAllEvents();
	}

	@GetMapping("/events/by")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get the event entities by category.", notes="Get the event entities by category.")
	public List<Event> getEventsByCategory(
		@ApiParam(value="the event's category (i.e., all, Opening, Closed)", required=true)
		@RequestParam(required=true, name="category") String category) {
		log.info("readEventsByCategory() is calling ...");
		return eventService.readEventsByCategory(category);
	}

	@GetMapping("/eventdetail/bytitle")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get the event entity by title.", notes="Get the event entity by title.")
	public Event getEventDetailByTitle(
		@ApiParam(value="the event's attribute - title", required=true)
		@RequestParam(required=true, name="title") String title) {
		return eventService.readEventDetailByTitle(title);
	}

	@PutMapping("/events/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Change the event details (Update)", notes="Change the event details (Update)")
	public Event modifyEventById(
		@ApiParam(value="the id of the existing event entity.", required=true)
		@PathVariable int id,
		@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@PostMapping("/events")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Create a new event entity.", notes="Create a new event entity.")
	public Event addNewEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

	@DeleteMapping("/events/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Delete an event entity.", notes="Delete an event entity.")
	public Event removeEventById(
		@ApiParam(value="the id of the existing event entity.", required=true)
		@PathVariable int id) {
		return eventService.deleteEvent(id);
	}
}