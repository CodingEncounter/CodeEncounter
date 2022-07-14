package com.codeup.codeencounter.repositories;

import com.codeup.codeencounter.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepo extends JpaRepository <Interest, Long> {
    Interest findOneByName(String name);
}
