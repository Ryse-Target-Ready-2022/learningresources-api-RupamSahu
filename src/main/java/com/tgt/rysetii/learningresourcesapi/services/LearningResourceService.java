package com.tgt.rysetii.learningresourcesapi.services;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;
import com.tgt.rysetii.learningresourcesapi.repository.*;

@Service

public class LearningResourceService {

	@Autowired(required=false)
	private LearningResourceRepository learningresourcerepository;
	
	
	public void saveLearningResources(List<LearningResources> learningResources)
	{
		for (LearningResources learningResource : learningResources)
			learningresourcerepository.save(learningResource);
	}
	
	public List<LearningResources> getLearningResources(){
        return learningresourcerepository.findAll();
    }

	public void deleteLearningResource(int id)
	{
		learningresourcerepository.deleteById(id);
	}
	
    public List<Double> calculateProfitMargin(){
        List<LearningResources> resources=getLearningResources();
        List<Double> profitMargin = resources.stream()
                .map(r -> ((r.getSellingPrice() - r.getCostPrice())/r.getSellingPrice()))
                .collect(toList());

        return profitMargin;
    }
    public List<LearningResources> sortByProfitMargin(){
        List<LearningResources> resources = getLearningResources();

        resources.sort((r1, r2) -> {
            Double profitMargin1 = (r1.getSellingPrice() - r1.getCostPrice())/r1.getSellingPrice();
            Double profitMargin2 = (r2.getSellingPrice() - r2.getCostPrice())/r2.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        return resources;
    }
}
	
