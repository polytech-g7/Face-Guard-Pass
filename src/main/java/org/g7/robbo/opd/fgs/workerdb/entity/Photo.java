package org.g7.robbo.opd.fgs.workerdb.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
        if (path != null ? !path.equals(photo.path) : photo.path != null) return false;
        if (creationTime != null ? !creationTime.equals(photo.creationTime) : photo.creationTime != null) return false;
        return employee != null ? employee.equals(photo.employee) : photo.employee == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", creationTime=" + creationTime +
                ", employee=" + employee +
                '}';
    }
}
