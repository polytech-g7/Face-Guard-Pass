package org.g7.robbo.opd.fgs.workerdb.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Orlov Diga
 */

@Entity
@Table(name = "photo")
public class Photo {

    private Long id;

    private String path;

    private LocalDateTime creationTime;

    private Employee employee;

    public Photo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "creation_time")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",  nullable=false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
