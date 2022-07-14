package com.codeup.codeencounter.services;


import com.codeup.codeencounter.models.Interest;
import com.codeup.codeencounter.repositories.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestService {
    @Autowired
    private InterestRepo interestRepo;

    public long count() {
        return interestRepo.count();
    }

    public Interest get(String interestName) {
        return interestRepo.findOneByName(interestName);
    }

    public void save(Interest interest) {
        interestRepo.save(interest);
    }

    public Interest createIfNotExists(String interestText) {
        Interest interest = interestRepo.findOneByName(interestText);

        if(interest == null) {
            interest = new Interest(interestText);
            interestRepo.save(interest);
        }
        return interest;
    }
}
