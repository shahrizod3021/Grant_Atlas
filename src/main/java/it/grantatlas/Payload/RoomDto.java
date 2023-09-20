package it.grantatlas.Payload;

public record RoomDto(

        String nameUz,
        String nameRu,
        String nameEng,
        String nameTurk,

        String descriptionUz,
        String descriptionRu,
        String descriptionEng,
        String descriptionTurk,

        String howMany,

        String howMuch,

        String size
) {
}
