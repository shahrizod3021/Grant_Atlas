package it.grantatlas.Entity;

import it.grantatlas.Entity.template.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
public class Order extends AbsEntity {

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    private Rooms rooms;

    @Column(nullable = false)
    private Integer parentsNumber;

    @Column(nullable = false)
    private Integer childNumber;

    @Column(nullable = false, name = "arrive_date")
    private String arrivalDate;

    @Column(nullable = false, name = "departure")
    private String departureDate;



}
