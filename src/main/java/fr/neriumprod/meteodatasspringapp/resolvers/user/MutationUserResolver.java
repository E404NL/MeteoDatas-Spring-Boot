package fr.neriumprod.meteodatasspringapp.resolvers.user;

import fr.neriumprod.meteodatasspringapp.graphql.input.user.UserInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.SaveUserResponse;
import fr.neriumprod.meteodatasspringapp.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@Slf4j
public class MutationUserResolver {
    private final UserServiceImpl userService;

    @MutationMapping
    public SaveUserResponse saveUser(@Argument @NonNull UserInput userInput){
        return userService.save(userInput);
    }

    @MutationMapping
    public DeleteResponse deleteUserById(@Argument @NonNull Long userId){
        return userService.deleteUserById(userId);
    }

    @MutationMapping
    public DeleteResponse deleteAllUsers(){
        return userService.deleteAllUsers();
    }
}
