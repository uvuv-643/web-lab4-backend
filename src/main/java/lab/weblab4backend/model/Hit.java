package lab.weblab4backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "hits")
public class Hit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "r")
    private Double r;

    @Column(name = "isHit")
    private Boolean isHit;

    @Column(name = "createdAt")
    private ZonedDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Double getY() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Boolean getStatus() {
        return isHit;
    }

    public void setStatus(boolean status) {
        this.isHit = status;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(ZonedDateTime time) {
        this.createdAt = time;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + r + " " + isHit + " " + createdAt;
    }
}