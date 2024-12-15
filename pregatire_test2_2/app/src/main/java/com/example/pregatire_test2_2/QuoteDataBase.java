package com.example.pregatire_test2_2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Quote.class}, version = 1)
public abstract class QuoteDataBase extends RoomDatabase {
    public abstract QuoteDao daoQuote();
}
