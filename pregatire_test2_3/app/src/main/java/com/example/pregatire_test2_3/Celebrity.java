package com.example.pregatire_test2_3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Celebrities")
public class Celebrity implements Parcelable {
    @PrimaryKey
    @NonNull
    private String name;
    private String gender;
    private String nationality;
    private String occupation;
    private float height;
    private String birthday;

    public Celebrity(String name, String gender, String nationality, String occupation, float height, String birthday) {
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.occupation = occupation;
        this.height = height;
        this.birthday = birthday;
    }

    protected Celebrity(Parcel in) {
        name = in.readString();
        gender = in.readString();
        nationality = in.readString();
        occupation = in.readString();
        height = in.readFloat();
        birthday = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(nationality);
        dest.writeString(occupation);
        dest.writeFloat(height);
        dest.writeString(birthday);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Celebrity{");
        sb.append("name='").append(name).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", nationality='").append(nationality).append('\'');
        sb.append(", occupation='").append(occupation).append('\'');
        sb.append(", height=").append(height);
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
