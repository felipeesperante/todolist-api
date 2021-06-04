package br.com.nunesonline.todolistapi.service;

import br.com.nunesonline.todolistapi.crypto.UpdatableBCrypt;
import br.com.nunesonline.todolistapi.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<br.com.nunesonline.todolistapi.entity.User> user = this.userRepository.findByLogin(username);
        if (user.isPresent()) {
            return new User(user.get().getLogin(), UpdatableBCrypt.hash(user.get().getPasswd()),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}
