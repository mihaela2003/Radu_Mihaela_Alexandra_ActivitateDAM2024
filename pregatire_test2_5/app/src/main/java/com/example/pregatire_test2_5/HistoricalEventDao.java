package com.example.pregatire_test2_5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoricalEventDao {
    @Insert
    void insert(HistoricalEvent he);
    @Query("SELECT * FROM HistoricalEvent")
    List<HistoricalEvent> select();
    @Update
    void update(HistoricalEvent he);
    @Delete
    void delete(HistoricalEvent he);
}
