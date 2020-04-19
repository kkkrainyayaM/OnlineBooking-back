package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "route", catalog = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private long id;

    @Column( nullable = false)
    private Date date;

    @Column( nullable = false)
    private String departureTime;

    @Column( nullable = false)
    private String arrivalTime;

    @Column( nullable = false)
    private String departurePoint;

    @Column( nullable = false)
    private String arrivalPoint;
}
