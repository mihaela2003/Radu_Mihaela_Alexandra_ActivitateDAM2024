package com.example.pregatire_test2_6;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Curse")
public class Curse {
    @PrimaryKey
    @NonNull
    private int id;
    private String destinatie;
    private int distanta;
    private boolean manual;

    public Curse(int id, String destinatie, int distanta, boolean manual) {
        this.id = id;
        this.destinatie = destinatie;
        this.distanta = distanta;
        this.manual = manual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public int getDistanta() {
        return distanta;
    }

    public void setDistanta(int distanta) {
        this.distanta = distanta;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Curse{");
        sb.append("id=").append(id);
        sb.append(", destinatie='").append(destinatie).append('\'');
        sb.append(", distanta=").append(distanta);
        sb.append(", manual=").append(manual);
        sb.append('}');
        return sb.toString();
    }
}
