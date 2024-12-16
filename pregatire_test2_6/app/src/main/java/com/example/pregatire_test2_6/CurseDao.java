package com.example.pregatire_test2_6;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurseDao {
    @Insert
    void insert (Curse curse);
    @Query("SELECT * FROM Curse")
    List<Curse> select();
    @Query("SELECT MAX(id) FROM curse")
    int getLastId();
    @Delete
    void delete(Curse curse);
    @Query("SELECT COUNT(id) FROM CURSE")
    int count();
}
