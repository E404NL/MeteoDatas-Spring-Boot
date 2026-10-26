package fr.neriumprod.meteodatasspringapp.graphql.response.user;

import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class GetUsersCollectionResponse {
    private boolean success;
    private String message;
    private Collection<User> users;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
