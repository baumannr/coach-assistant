package hu.elte.webjava.coachassistant.application.factory;

import hu.elte.webjava.coachassistant.application.exception.UnsupportedUserTypeException;
import hu.elte.webjava.coachassistant.application.webdomain.RegisterDTO;
import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Coach;
import hu.elte.webjava.coachassistant.domain.DomainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public DomainUser getUser(RegisterDTO registerDto) {
        switch (registerDto.getUserType()) {
            case CLIENT:
                return map(registerDto, new Client());
            case COACH:
                return map(registerDto, new Coach());
            default:
                throw new UnsupportedUserTypeException("Unsupported user type.");
        }
    }

    private DomainUser map(RegisterDTO registerDto, DomainUser user) {
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return user;
    }
}
