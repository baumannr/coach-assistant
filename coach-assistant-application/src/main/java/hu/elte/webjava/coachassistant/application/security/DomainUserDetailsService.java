package hu.elte.webjava.coachassistant.application.security;

import hu.elte.webjava.coachassistant.domain.DomainUser;
import hu.elte.webjava.coachassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DomainUser user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new DomainUserPrincipal(user);
    }
}
