package com.codeup.codeencounter.repositories;

import com.codeup.CodeEncounter.Models.Gallery;
import com.codeup.CodeEncounter.Models.Picture;
import com.codeup.CodeEncounter.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepo extends JpaRepository <Picture, Long> {

    Picture findById(long id);
    List<Picture> findAllByUser(User user);
    List<Picture> findAllByGallery(Gallery gallery);
    Picture findByComment(String comment);
}
