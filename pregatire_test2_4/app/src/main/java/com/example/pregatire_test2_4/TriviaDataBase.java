package com.example.pregatire_test2_4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Trivia.class}, version = 1)
public abstract class TriviaDataBase extends RoomDatabase {
    public abstract TriviaDao daoTrivia();
}
