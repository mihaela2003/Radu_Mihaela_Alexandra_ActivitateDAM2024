package com.example.pregatire_test2_2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    void insert(Quote quote);
    @Query("SELECT * FROM Quotes")
    List<Quote> select();
    @Update
    void update(Quote quote);
    @Delete
    void delete(Quote quote);
}
