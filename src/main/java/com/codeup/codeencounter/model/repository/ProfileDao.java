package com.codeup.CodeEncounter.model.repository;

import com.codeup.CodeEncounter.model.entity.Profile;
import com.codeup.CodeEncounter.model.entity.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long> {
    Profile findByUser(SiteUser user);

    //Search Name containing the String, non case-sensitive
    List<Profile> findByInterestsNameContainingIgnoreCase(String text);
    Page<Profile> findByInterestsNameContainingIgnoreCase(String text, Pageable request); //extra Param, what pageNum + sorting
}
