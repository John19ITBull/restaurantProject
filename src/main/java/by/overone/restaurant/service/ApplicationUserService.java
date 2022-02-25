package by.overone.restaurant.service;

import by.overone.restaurant.entity.ApplicationUser;
import by.overone.restaurant.entity.User;
import by.overone.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(User user){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setGrantedAuthorities(user.getRole().getGrantedAuthority());
        applicationUser.setUsername(user.getUsername());
        applicationUser.setPassword(user.getPassword());
        applicationUser.setAccountNonExpired(true);
        applicationUser.setAccountNonLocked(true);
        applicationUser.setEnabled(true);
        applicationUser.setCredentialsNonExpired(true);

        return applicationUser;
    }
}
