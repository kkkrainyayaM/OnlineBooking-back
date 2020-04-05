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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private long id;

    @Column( nullable = false)
    private Date date;

    @Column( nullable = false)
    private String timeDeparture;

    @Column( nullable = false)
    private String timeArrival;

    @Column( nullable = false)
    private String pointDeparture;

    @Column( nullable = false)
    private String pointArrival;

}
