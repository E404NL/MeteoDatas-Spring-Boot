package fr.neriumprod.meteodatasspringapp.graphql.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOneObjectResponse<T> {
    private boolean success;
    private String message;
    private T object;

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

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
