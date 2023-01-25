package hu.elte.webjava.coachassistant.application.security;

import hu.elte.webjava.coachassistant.application.exception.UnsupportedUserTypeException;
import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Coach;
import hu.elte.webjava.coachassistant.domain.DomainUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DomainUserPrincipal implements UserDetails {

    final private DomainUser user;

    public DomainUserPrincipal(DomainUser user) {
        this.user = user;
    }

    public int getUserId() {
        return this.user.getId();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user instanceof Client) {
            return List.of(Role.ROLE_CLIENT);
        } else if (user instanceof Coach) {
            return List.of(Role.ROLE_COACH);
        } else {
            throw new UnsupportedUserTypeException("Unsupported user type.");
        }
    }

    public boolean hasRole(Role role) {
        return getAuthorities().contains(role);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
