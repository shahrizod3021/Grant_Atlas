package it.grantatlas.Payload;


import it.grantatlas.Entity.User;

public record AuthResponse(
        String token,
        User user
){
}
