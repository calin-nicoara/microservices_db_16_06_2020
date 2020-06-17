package ro.esolacad.microservices.customUserDetails;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

//@Service
//@Primary
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> userAuthorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_CUSTOM_USER"),
                new SimpleGrantedAuthority("ROLE_CUSTOM_USER_2")
        );

        return new CustomUserDetails("me", passwordEncoder.encode("custom"),
                true,true ,true, true,
                userAuthorities, "Calin-Ioan Nicoara");
    }
}
