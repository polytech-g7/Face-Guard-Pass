package org.g7.robbo.opd.wd.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Orlov Diga
 */

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "creation_time")
    private Date creationTime;

    @ManyToOne()
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Photo() {
    }
}
