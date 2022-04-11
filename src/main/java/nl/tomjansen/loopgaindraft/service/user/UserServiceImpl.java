package nl.tomjansen.loopgaindraft.service.user;

import nl.tomjansen.loopgaindraft.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


}
