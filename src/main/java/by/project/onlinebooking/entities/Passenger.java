package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "passengers", catalog = "routes")
public class Passenger {

    @Id
    @Column( nullable = false)
    private long idRoute;

    @Column( nullable = false)
    private long idUser;

    @Column( nullable = false)
    private String departurePoint;

    @Column( nullable = false)
    private String arrivalPoint;
}
