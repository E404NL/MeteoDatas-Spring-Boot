package fr.neriumprod.meteodatasspringapp.graphql.response;

import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class GetObjectCollectionResponse<T> {
    private boolean success;
    private String message;
    private Collection<T> objects;

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

    public Collection<T> getObjects() {
        return objects;
    }

    public void setObjects(Collection<T> objects) {
        this.objects = objects;
    }
}
