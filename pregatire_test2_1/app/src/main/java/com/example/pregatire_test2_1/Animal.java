package com.example.pregatire_test2_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName="Animale")
public class Animal implements Parcelable {
    @PrimaryKey
    @NonNull
    private String stapan;
    private String nume;
    private String rasa;
    private String gen;

    public Animal(String stapan, String nume, String rasa, String gen) {
        this.stapan = stapan;
        this.nume = nume;
        this.rasa = rasa;
        this.gen = gen;
    }

    protected Animal(Parcel in) {
        stapan = in.readString();
        nume = in.readString();
        rasa = in.readString();
        gen = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stapan);
        dest.writeString(nume);
        dest.writeString(rasa);
        dest.writeString(gen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getStapan() {
        return stapan;
    }

    public void setStapan(String stapan) {
        this.stapan = stapan;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("stapan='").append(stapan).append('\'');
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", rasa='").append(rasa).append('\'');
        sb.append(", gen='").append(gen).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
