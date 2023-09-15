package it.grantatlas.Payload;

public record ApiResponse(
        String message,
        boolean success,
        Integer status
) {
}
