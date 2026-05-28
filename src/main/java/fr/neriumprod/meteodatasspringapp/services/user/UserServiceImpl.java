package fr.neriumprod.meteodatasspringapp.services.user;

import fr.neriumprod.meteodatasspringapp.dao.postgres.UserRepository;
import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetOneUserResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetUsersCollectionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Override
    public GetOneUserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new GetOneUserResponse(
                    false,
                    "No User saved on database with the id : " + id,
                    null);
        }
        return new GetOneUserResponse(
                true,
                "Success to get user with this id : " + id,
                user);
    }

    @Override
    public GetOneUserResponse findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            return new GetOneUserResponse(
                    false,
                    "No User saved on database with this email : " + email,
                    null);
        }
        return new GetOneUserResponse(
                true,
                "Success to get user with this email : " + email,
                user);
    }

    @Override
    public GetOneUserResponse findUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user == null){
            new GetOneUserResponse(
                    false,
                    "No User saved on database with this credentials : " + username,
                    null);
        }
        return new GetOneUserResponse(
                true,
                "Success to get  user with this credentials : " + username,
                user);
    }

    @Override
    public GetOneUserResponse findUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null){
            new GetOneUserResponse(
                    false,
                    "No User saved on database with this credentials : " + email,
                    null);
        }
        return new GetOneUserResponse(
                true,
                "Success to get user with this credentials : " + email,
                user);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public GetUsersCollectionResponse findAllUsers() {
        Collection<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return new GetUsersCollectionResponse(
                    false,
                    "No User saved in database.",
                    null);
        }
        return new GetUsersCollectionResponse(
                true,
                "All USers saved on database",
                users);
    }

    @Override
    public GetOneUserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            new GetOneUserResponse(
                    false,
                    "Success to get user with this username : " + username,
                    null);
        }
        return new GetOneUserResponse(
                true,
                "Success to get user with this id : " + username,
                user);
    }
}
