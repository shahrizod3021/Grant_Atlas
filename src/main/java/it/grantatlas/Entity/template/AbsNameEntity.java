package it.grantatlas.Entity.template;

import jakarta.persistence.*;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 1000)
    private String nameUz;

    @Column(nullable = false, length = 1000)
    private String nameRu;

    @Column(nullable = false, length = 1000)
    private String nameEng;

    @Column(nullable = false, length = 1000)
    private String nameTurk;


}
