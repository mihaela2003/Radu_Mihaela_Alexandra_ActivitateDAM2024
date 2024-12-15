package com.example.pregatire_test2_5;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {HistoricalEvent.class}, version = 1)
public abstract class HistoricalEventDataBase extends RoomDatabase {
    public abstract HistoricalEventDao daoHE();
}
