package com.example.pregatire_test2_7;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDataBase extends RoomDatabase {
    public abstract BookDAO daoBook();
}
