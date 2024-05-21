package com.bancolombia.pagos.service;

import com.bancolombia.pagos.model.User;
import com.bancolombia.pagos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    static final String HTTP404 = "The user does not exist, enter a valid user id";
    static final String HTTP400 = "You should enter a name";

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        Optional.of(user).filter(u -> user.getName() != null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, HTTP400));
        Optional.of(user).filter(u -> !user.getName().isBlank())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, HTTP400));;
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404));
    }

    public void updateUser(Long id, User user){
        repository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404));
        repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404));
        repository.deleteById(id);
    }


}
