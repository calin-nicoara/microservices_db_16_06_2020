package ro.esolacad.microservices.customUserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

import lombok.Getter;

public class CustomUserDetails extends User {

    @Getter
    private final String fullName;

    public CustomUserDetails(final String username, final String password,
                             final boolean enabled, final boolean accountNonExpired,
                             final boolean credentialsNonExpired, final boolean accountNonLocked,
                             final Collection<? extends GrantedAuthority> authorities,
                             final String fullName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.fullName = fullName;
    }
}
