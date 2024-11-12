package com.example.pregatire_test3;

import android.os.Parcel;
import android.os.Parcelable;

public class kpop implements Parcelable {
    String denumire;
    String entertainmnet;
    int nrMembri;
    boolean solo;
    float salariu;

    public kpop(String denumire, float salariu, boolean solo, int nrMembri, String entertainmnet) {
        this.denumire = denumire;
        this.salariu = salariu;
        this.solo = solo;
        this.nrMembri = nrMembri;
        this.entertainmnet = entertainmnet;
    }

    protected kpop(Parcel in) {
        denumire = in.readString();
        entertainmnet = in.readString();
        nrMembri = in.readInt();
        solo = in.readByte() != 0;
        salariu = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeString(entertainmnet);
        dest.writeInt(nrMembri);
        dest.writeByte((byte) (solo ? 1 : 0));
        dest.writeFloat(salariu);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<kpop> CREATOR = new Creator<kpop>() {
        @Override
        public kpop createFromParcel(Parcel in) {
            return new kpop(in);
        }

        @Override
        public kpop[] newArray(int size) {
            return new kpop[size];
        }
    };

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getEntertainmnet() {
        return entertainmnet;
    }

    public void setEntertainmnet(String entertainmnet) {
        this.entertainmnet = entertainmnet;
    }

    public int getNrMembri() {
        return nrMembri;
    }

    public void setNrMembri(int nrMembri) {
        this.nrMembri = nrMembri;
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("kpop{");
        sb.append("denumire='").append(denumire).append('\'');
        sb.append(", entertainmnet='").append(entertainmnet).append('\'');
        sb.append(", nrMembri=").append(nrMembri);
        sb.append(", solo=").append(solo);
        sb.append(", salariu=").append(salariu);
        sb.append('}');
        return sb.toString();
    }
}
