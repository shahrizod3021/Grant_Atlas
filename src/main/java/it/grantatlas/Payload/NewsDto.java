package it.grantatlas.Payload;

public record NewsDto(
        String nameUz,
        String nameRu,
        String nameEng,
        String nameTurk,

        String descriptionUz,
        String descriptionRu,
        String descriptionEng,
        String descriptionTurk

) {
}
