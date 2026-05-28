package fr.neriumprod.meteodatasspringapp.services.user;

import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetOneUserResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetUsersCollectionResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    GetOneUserResponse findUserById(Long id);
    GetOneUserResponse findUserByEmail(String email);
    GetOneUserResponse findUserByUsernameAndPassword(String username, String password);
    GetOneUserResponse findByUsername(String username);
    GetOneUserResponse findUserByEmailAndPassword(String email, String password);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
    GetUsersCollectionResponse findAllUsers();

}
