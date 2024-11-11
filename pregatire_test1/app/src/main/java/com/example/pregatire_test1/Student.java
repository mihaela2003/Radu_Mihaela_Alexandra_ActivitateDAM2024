package com.example.pregatire_test1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Student implements Parcelable {
    int anNastere;
    String nume;
    String facultate;
    String restante;
    float medie;

    public Student(int anNastere, String nume, String facultate, String restante, float medie) {
        this.anNastere = anNastere;
        this.nume = nume;
        this.facultate = facultate;
        this.restante = restante;
        this.medie = medie;
    }

    protected Student(Parcel in) {
        anNastere = in.readInt();
        nume = in.readString();
        facultate = in.readString();
        restante = in.readString();
        medie = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(anNastere);
        dest.writeString(nume);
        dest.writeString(facultate);
        dest.writeString(restante);
        dest.writeFloat(medie);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getRestante() {
        return restante;
    }

    public void setRestante(String restante) {
        this.restante = restante;
    }

    public int getAnNastere() {
        return anNastere;
    }

    public void setAnNastere(int anNastere) {
        this.anNastere = anNastere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }


    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("anNastere=").append(anNastere);
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", facultate='").append(facultate).append('\'');
        sb.append(", restante=").append(restante);
        sb.append(", medie=").append(medie);
        sb.append('}');
        return sb.toString();
    }
}
