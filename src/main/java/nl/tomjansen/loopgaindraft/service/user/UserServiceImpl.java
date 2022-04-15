package nl.tomjansen.loopgaindraft.service.user;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgaindraft.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;


}
