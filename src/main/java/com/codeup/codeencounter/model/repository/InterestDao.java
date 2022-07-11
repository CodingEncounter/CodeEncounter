package com.codeup.CodeEncounter.model.repository;

import com.codeup.CodeEncounter.model.entity.Interest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDao extends CrudRepository<Interest, Long> {
    Interest findOneByName(String name);
}
