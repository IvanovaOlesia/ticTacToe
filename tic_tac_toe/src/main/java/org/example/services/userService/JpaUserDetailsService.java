package org.example.services.userService;

import lombok.AllArgsConstructor;
import org.example.datasource.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByLogin(username)
                    .map(user -> User.withUsername(user.getLogin())
                            .password(user.getPassword())
                            .build())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
