package com.example.pregatire_test2_5;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HistoricalEvent")
public class HistoricalEvent implements Parcelable {
    private int year;
    private int month;
    private int day;
    @PrimaryKey
    @NonNull
    private String event;

    public HistoricalEvent(int year, int month, int day, String event) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.event = event;
    }

    protected HistoricalEvent(Parcel in) {
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        event = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeString(event);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoricalEvent> CREATOR = new Creator<HistoricalEvent>() {
        @Override
        public HistoricalEvent createFromParcel(Parcel in) {
            return new HistoricalEvent(in);
        }

        @Override
        public HistoricalEvent[] newArray(int size) {
            return new HistoricalEvent[size];
        }
    };

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistoricalEvent{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", event='").append(event).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
