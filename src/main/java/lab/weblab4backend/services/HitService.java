package lab.weblab4backend.services;


import lab.weblab4backend.dao.HitDao;
import lab.weblab4backend.model.Hit;

import java.time.ZonedDateTime;
import java.util.List;

public class HitService {

    private final HitDao hitDao = new HitDao();

    public HitService() {
    }

    public Hit findHit(int id) {
        return hitDao.findById(id);
    }

    public List<Hit> getHits() {
        return hitDao.findAll();
    }

    public void clear() {
        this.getHits().forEach(hitDao::delete);
    }

    public void add(double x, double y, double r) {
        Hit appended = new Hit();
        appended.setX(x);
        appended.setY(y);
        appended.setR(r);
        boolean hit = true;
        if (r < 0) {
            r = -r;
            x = -x;
            y = -y;
        }
        if (x > 0 && y > 0) {
            hit = false;
        } else if (x < 0 && y > 0) {
            hit = x * x + y * y <= r * r;
        } else if (x < 0 && y < 0) {
            hit = y > -x - 0.5 * r;
        } else {
            hit = Math.abs(x) < r / 2 && Math.abs(y) < r;
        }
        appended.setStatus(hit);
        appended.setCreatedAt(ZonedDateTime.now());
        this.hitDao.save(appended);
    }

}