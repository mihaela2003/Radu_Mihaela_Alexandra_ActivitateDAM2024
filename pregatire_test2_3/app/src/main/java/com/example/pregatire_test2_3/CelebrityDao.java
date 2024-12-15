package com.example.pregatire_test2_3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CelebrityDao {
    @Insert
    void insert(Celebrity celebrity);
    @Query("SELECT * FROM Celebrities")
    List<Celebrity> select();
    @Update
    void update(Celebrity celebrity);
    @Delete
    void delete(Celebrity celebrity);
}
