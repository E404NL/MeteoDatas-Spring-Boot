package fr.neriumprod.meteodatasspringapp.resolvers.user;

import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetOneUserResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.GetUsersCollectionResponse;
import fr.neriumprod.meteodatasspringapp.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class QueryUserResolver {
    private final UserServiceImpl userService;

    @QueryMapping
    public GetUsersCollectionResponse getAllUsers(){
        return userService.findAllUsers();
    }

    @QueryMapping
    public GetOneUserResponse getUserById(@Argument @NonNull Long id){
        return userService.findUserById(id);
    }

    @QueryMapping
    public GetOneUserResponse getUserByEmail(@Argument @NonNull String email){
        return userService.findUserByEmail(email);
    }

    @QueryMapping
    public GetOneUserResponse getUserByCredentials(
            @Argument @NonNull String username,
            @Argument @NonNull String password){
        return userService.findUserByUsernameAndPassword(username, password);
    }
}
