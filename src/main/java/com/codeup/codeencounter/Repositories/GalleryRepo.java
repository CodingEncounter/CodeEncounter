package com.codeup.CodeEncounter.Repositories;

import com.codeup.CodeEncounter.Models.Gallery;
import com.codeup.CodeEncounter.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepo extends JpaRepository <Gallery, Long> {

    Gallery findById(long id);
    Gallery findByName(String name);
    List<Gallery> findAllByUser(User user);

}
