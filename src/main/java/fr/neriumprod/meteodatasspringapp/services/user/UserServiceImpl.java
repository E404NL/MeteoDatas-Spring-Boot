package fr.neriumprod.meteodatasspringapp.services.user;

import fr.neriumprod.meteodatasspringapp.dao.postgres.UserRepository;
import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import fr.neriumprod.meteodatasspringapp.graphql.input.user.UserInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetOneUserResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetUsersCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.SaveUserResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    /**
     *
     * @param id
     * @return one specific User determined  by the user id
     */
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

    /**
     *
     * @param email
     * @return one specific User determined by the user id
     */
    @Override
    public GetUsersCollectionResponse findUserByEmail(String email) {
        Collection<User> users = userRepository.findByEmail(email);
        if(users == null){
            return new GetUsersCollectionResponse(
                    false,
                    "No User saved on database with this email : " + email,
                    null);
        }
        return new GetUsersCollectionResponse(
                true,
                "Success to get user with this email : " + email,
                users);
    }

    /**
     *
     * @param username
     * @param password
     * @return one User by credentials (username and psw)
     */
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

    /**
     *
     * @param email
     * @param password
     * @return one User by credentials (mail and psw)
     */
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

    /**
     *
     * @param email
     * @return a boolean to confirme if user with this email exists in database
     */
    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     *
     * @param username
     * @return a boolean to confirme if user with this username exists in database
     */
    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsUserById(Long id) {
        return userRepository.existsById(id);
    }

    /**
     *
     * @return absolutely ALL users saved on the database
     */
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

    /**
     *
     * @param username
     * @return a user collection by username
     */
    @Override
    public GetUsersCollectionResponse findByUsername(String username) {
        Collection<User> users = userRepository.findByUsername(username);
        if(users == null){
            new GetUsersCollectionResponse(
                    false,
                    "Success to get user with this username : " + username,
                    null);
        }
        return new GetUsersCollectionResponse(
                true,
                "Success to get user(s) with this username : " + username,
                users);
    }

    /**
     *
     * @param userInput
     * @return a User with his datas
     */
    @Override
    public SaveUserResponse save(UserInput userInput){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());

        if(userInput != null){
            user.setUsername(userInput.getUsername());
            user.setPassword(userInput.getPassword());
            user.setEmail(userInput.getEmail());
            user.setFirstName(userInput.getFirstName());
            user.setLastName(userInput.getLastName());
            user.setBirthDate(userInput.getBirthDate());
        }

        User persistedUser = userRepository.save(user);
        return new SaveUserResponse(true,
                "User registered with success !",
                persistedUser);
    }

    ///////////////////////////// DELETE ////////////////////////////////////

    @Override
    public DeleteResponse deleteUserById(Long id){
        if(id != null && userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }

        return new DeleteResponse(true,
                "User with id " + id + " has successfully deleted");
    }

    public DeleteResponse deleteAllUsers(){
        userRepository.deleteAll();

        return new DeleteResponse(true,
                "All users has been deleted in database");
    }
}
