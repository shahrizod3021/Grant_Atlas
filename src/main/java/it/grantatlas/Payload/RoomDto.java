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

        Double howMany,

        Double howMuch,

        Double size
        ) {
}
