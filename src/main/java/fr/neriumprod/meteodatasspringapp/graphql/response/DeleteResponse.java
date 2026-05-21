package fr.neriumprod.meteodatasspringapp.graphql.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteResponse {
    private boolean success;
    private String message;
}
