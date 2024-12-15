package com.example.pregatire_test2_3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Celebrity.class}, version = 1)
public abstract class CelebrityDataBase extends RoomDatabase {
    public abstract CelebrityDao daoCelebrity();
}
