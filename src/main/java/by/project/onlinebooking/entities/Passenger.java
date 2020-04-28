package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String departurePoint;

    @Column(nullable = false)
    private String arrivalPoint;

    @Column(nullable = false)
    private long routeId;

    @Column(nullable = false)
    private long userId;
}
