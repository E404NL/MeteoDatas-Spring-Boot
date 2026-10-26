package fr.neriumprod.meteodatasspringapp.graphql.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveObjectResponse<T> {
    private boolean success;
    private String message;
    private T object;
}
