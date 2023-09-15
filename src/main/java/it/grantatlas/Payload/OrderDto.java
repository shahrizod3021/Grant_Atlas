package it.grantatlas.Payload;

public record OrderDto(
        String phoneNumber,
        Integer roomId,
        Integer parent,
        Integer child,
        String arrival,
        String departure

) {
}
