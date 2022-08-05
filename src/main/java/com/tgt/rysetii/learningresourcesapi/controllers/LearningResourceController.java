package com.tgt.rysetii.learningresourcesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapi.services.LearningResourceService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/learningresources")
public class LearningResourceController {
	
	@Autowired
	private LearningResourceService service;
	
	@GetMapping("/all")
	public List<LearningResources> getAllLearningResources()
	{
		return service.getLearningResources();
	}
	@PostMapping(value = "/", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveLearningResources(@RequestBody List<LearningResources> learningresources)
	{
		service.saveLearningResources(learningresources);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteLearningResource(@PathVariable int id)
	{
		service.deleteLearningResource(id);
	}
}
