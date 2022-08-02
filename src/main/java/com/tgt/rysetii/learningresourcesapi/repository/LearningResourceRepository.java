package com.tgt.rysetii.learningresourcesapi.repository;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository<LearningResources, Integer>{

}
