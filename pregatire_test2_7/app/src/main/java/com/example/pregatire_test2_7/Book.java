package com.example.pregatire_test2_7;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Books")
public class Book implements Parcelable {

    private String author;
    private int year;
    private String title;
    @PrimaryKey
    @NonNull
    private String isbn;
    private String genre;

    public Book(String author, int year, String title, String isbn, String genre) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
    }

    protected Book(Parcel in) {
        author = in.readString();
        year = in.readInt();
        title = in.readString();
        isbn = in.readString();
        genre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeString(title);
        dest.writeString(isbn);
        dest.writeString(genre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("author='").append(author).append('\'');
        sb.append(", year=").append(year);
        sb.append(", title='").append(title).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
