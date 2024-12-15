package com.example.pregatire_test2_2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Quotes")
public class Quote implements Parcelable {
    @PrimaryKey
    @NonNull
    private String quote;
    private String author;
    private String category;
    private int year;


    public Quote(String quote, String author, String category, int year) {
        this.quote = quote;
        this.author = author;
        this.category = category;
        this.year = year;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    protected Quote(Parcel in) {
        quote = in.readString();
        author = in.readString();
        category = in.readString();
        year = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quote);
        dest.writeString(author);
        dest.writeString(category);
        dest.writeInt(year);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quote{");
        sb.append("quote='").append(quote).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
