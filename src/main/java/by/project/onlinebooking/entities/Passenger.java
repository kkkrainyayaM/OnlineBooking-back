package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "route_passengers", catalog = "routes")
public class Passenger {

    @Id
    @Column(name = "idRouter", nullable = false)
    private long idRoute;

    @Column(name = "idUser", nullable = false)
    private long idUser;

    @Column(name = "pointDeparture", nullable = false)
    private String pointDeparture;

    @Column(name = "pointArrival", nullable = false)
    private String pointArrival;
}
