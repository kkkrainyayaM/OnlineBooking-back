package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "route_passengers", catalog = "routes")
public class Passenger {

    @Column(name = "idUser", nullable = false)
    private Long idUser;

    @Column(name = "idRouter", nullable = false)
    private Long idRoute;

    @Column(name = "pointDeparture", nullable = false)
    private String pointDeparture;

    @Column(name = "pointArrival", nullable = false)
    private String pointArrival;
}
