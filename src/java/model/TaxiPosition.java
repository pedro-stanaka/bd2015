package model;

import java.sql.Timestamp;

/**
 * Describe purpose of this file HERE MTF!
 *
 * @author Pedro Tanaka <pedro.stanaka@gmail.com>
 */
public class TaxiPosition {

    private int id;
    private Timestamp observ;
    private double lat;
    private double lng;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getObserv() {
        return observ;
    }

    public void setObserv(Timestamp observ) {
        this.observ = observ;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
