package com.codeup.CodeEncounter.service;

import com.codeup.CodeEncounter.model.entity.Profile;
import com.codeup.CodeEncounter.model.repository.ProfileDao;
import com.codeup.CodeEncounter.model.entity.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileDao profileDao;

    @PreAuthorize("isAuthenticated()") //Method can't run unless User is authenticated!
    public void save(Profile profile) {
        profileDao.save(profile);
    }

    @PreAuthorize("isAuthenticated()")
    public Profile getUserProfile(SiteUser user) {
        return profileDao.findByUser(user);
    }
}
