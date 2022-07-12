package com.codeup.CodeEncounter;

import com.codeup.CodeEncounter.Models.UserWithRoles;
import com.codeup.CodeEncounter.Models.User;
import com.codeup.CodeEncounter.Repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetailsLoader(UserRepo users) {
        this.userRepo = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}
