package com.example.pregatire_test4;

import android.os.Parcel;
import android.os.Parcelable;

public class Angajat implements Parcelable {
    private String nume;
    private String firma;
    private int anNastere;
    private float salariu;

    public Angajat(String nume, String firma, int anNastere, float salariu) {
        this.nume = nume;
        this.firma = firma;
        this.anNastere = anNastere;
        this.salariu = salariu;
    }

    protected Angajat(Parcel in) {
        nume = in.readString();
        firma = in.readString();
        anNastere = in.readInt();
        salariu = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(firma);
        dest.writeInt(anNastere);
        dest.writeFloat(salariu);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Angajat> CREATOR = new Creator<Angajat>() {
        @Override
        public Angajat createFromParcel(Parcel in) {
            return new Angajat(in);
        }

        @Override
        public Angajat[] newArray(int size) {
            return new Angajat[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public int getAnNastere() {
        return anNastere;
    }

    public void setAnNastere(int anNastere) {
        this.anNastere = anNastere;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Angajat{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", firma='").append(firma).append('\'');
        sb.append(", anNastere=").append(anNastere);
        sb.append(", salariu=").append(salariu);
        sb.append('}');
        return sb.toString();
    }
}
