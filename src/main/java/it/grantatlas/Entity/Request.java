package it.grantatlas.Entity;

import it.grantatlas.Entity.template.AbsNameEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Request  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_and_surname")
    private String name;

    @Column(nullable = false)
    private String phoneNumber;


    @Column(name = "plus_comment", length = 500)
    private String plusComment;
}
