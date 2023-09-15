package it.grantatlas.Payload;

public record AuthRequest(
        String username,
        String password
) {
}
