package com.example.pregatire_test2_4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Trivias")
public class Trivia implements Parcelable {
    @PrimaryKey
    @NonNull
    private String category;
    private String question;
    private String answer;

    public Trivia(String category, String question, String answer) {
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    protected Trivia(Parcel in) {
        category = in.readString();
        question = in.readString();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(question);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Trivia> CREATOR = new Creator<Trivia>() {
        @Override
        public Trivia createFromParcel(Parcel in) {
            return new Trivia(in);
        }

        @Override
        public Trivia[] newArray(int size) {
            return new Trivia[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trivia{");
        sb.append("category='").append(category).append('\'');
        sb.append(", question='").append(question).append('\'');
        sb.append(", answer='").append(answer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
