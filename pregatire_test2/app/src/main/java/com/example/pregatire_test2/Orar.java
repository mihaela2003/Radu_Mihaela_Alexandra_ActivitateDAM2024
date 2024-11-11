package com.example.pregatire_test2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Orar implements Parcelable {
    String sala;
    String numeProfesor;
    String ziSaptamana;
    boolean online;
    String materie;

    public Orar(String sala, String numeProfesor, String ziSaptamana, boolean online, String materie) {
        this.sala = sala;
        this.numeProfesor = numeProfesor;
        this.ziSaptamana = ziSaptamana;
        this.online = online;
        this.materie = materie;
    }

    protected Orar(Parcel in) {
        sala = in.readString();
        numeProfesor = in.readString();
        ziSaptamana = in.readString();
        online = in.readByte() != 0;
        materie = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sala);
        dest.writeString(numeProfesor);
        dest.writeString(ziSaptamana);
        dest.writeByte((byte) (online ? 1 : 0));
        dest.writeString(materie);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Orar> CREATOR = new Creator<Orar>() {
        @Override
        public Orar createFromParcel(Parcel in) {
            return new Orar(in);
        }

        @Override
        public Orar[] newArray(int size) {
            return new Orar[size];
        }
    };

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getZiSaptamana() {
        return ziSaptamana;
    }

    public void setZiSaptamana(String ziSaptamana) {
        this.ziSaptamana = ziSaptamana;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Orar{");
        sb.append("sala='").append(sala).append('\'');
        sb.append(", numeProfesor='").append(numeProfesor).append('\'');
        sb.append(", ziSaptamana='").append(ziSaptamana).append('\'');
        sb.append(", online=").append(online);
        sb.append(", materie='").append(materie).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
