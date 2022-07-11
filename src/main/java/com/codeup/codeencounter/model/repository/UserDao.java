package com.codeup.CodeEncounter.model.repository;

import com.codeup.CodeEncounter.model.entity.SiteUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<SiteUser, Long> {
    SiteUser findByEmail(String email); //automatically created by Spring from CrudRepository
}
