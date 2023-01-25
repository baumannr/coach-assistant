package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.application.exception.UserAlreadyExistException;
import hu.elte.webjava.coachassistant.domain.DomainUser;
import hu.elte.webjava.coachassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DomainUser update(DomainUser user) {
        return userRepository.save(user);
    }

    @Override
    public DomainUser register(DomainUser user) throws UserAlreadyExistException {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("Email already taken!");
        }
        return userRepository.save(user);
    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
