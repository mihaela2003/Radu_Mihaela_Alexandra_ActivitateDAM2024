package com.example.pregatire_test2_1;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Animal.class}, version = 1)
public abstract class AnimalDataBase extends RoomDatabase {
    public abstract AnimalDao daoAnimal();
}
