package com.example.pregatire_test2_6;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Curse.class}, version = 1)
public abstract class CurseDataBase extends RoomDatabase {
    public abstract CurseDao daoCurse();
}
