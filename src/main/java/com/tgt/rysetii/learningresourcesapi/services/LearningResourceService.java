package com.tgt.rysetii.learningresourcesapi.services;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourcesStatus;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LearningResourceService {
	public void saveLearningResources(List<LearningResources> resources)
	{
		try
		{
			BufferedWriter bw=new BufferedWriter(new FileWriter("LearningResources.csv",true));
            for (LearningResources resource:resources){
               bw.newLine();
               StringBuffer line=new StringBuffer();
               line.append(resource.getLearningResourceId());
               line.append(",");
               line.append(resource.getProductName());
               line.append(",");
               line.append(resource.getCostPrice());
               line.append(",");
               line.append(resource.getSellingPrice());
               line.append(",");
               line.append(resource.getLearningResourceStatus());
               line.append(",");
               line.append(resource.getCreatedDate());
               line.append(",");
               line.append(resource.getPublishedDate());
               line.append(",");
               line.append(resource.getRetiredDate());
           }
           bw.flush();
           bw.close();

       }catch (IOException e){
           e.printStackTrace();
       }

  }

	private List<LearningResources> loadLearningResources(String file) {
        List<LearningResources> resources = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(csvFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            line = bufferedReader.readLine();
            while(line!= null){
                String[] attributes = line.split(",");
                LearningResources learningResource = createResource(attributes);
                resources.add(learningResource);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resources;
	}
	private LearningResources createResource(String[] attributes){
        Integer learningResourceId = Integer.parseInt(attributes[0].replaceAll("\\D+", ""));
        String learningResourceName = attributes[1];
        Double costPrice = Double.parseDouble(attributes[2]);
        Double sellingPrice = Double.parseDouble(attributes[3]);
        LearningResourcesStatus learningResourceStatus = LearningResourcesStatus.valueOf(attributes[4]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate createdDate = LocalDate.parse(attributes[5], dateFormatter);
        LocalDate publishedDate = LocalDate.parse(attributes[6], dateFormatter);
        LocalDate retiredDate = LocalDate.parse(attributes[7], dateFormatter);

        LearningResources resource = new LearningResources(
                learningResourceId, learningResourceName, costPrice, sellingPrice, learningResourceStatus, createdDate, publishedDate, retiredDate
        );
        return resource;
    }
    public  List<LearningResources> getLearningResources(){
        List<LearningResources> resources = loadLearningResources("LearningResources.csv");
        return resources;
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
	
