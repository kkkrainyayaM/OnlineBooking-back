package by.project.onlinebooking.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity
@Table(name = "route", catalog = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "timeDeparture", nullable = false)
    private String timeDeparture;

    @Column(name = "timeArrival", nullable = false)
    private String timeArrival;

    @Column(name = "pointDeparture", nullable = false)
    private String pointDeparture;

    @Column(name = "pointArrival", nullable = false)
    private String pointArrival;

}