package com.example.pregatire_test2_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TriviaDao {
    @Insert
    void insert(Trivia trivia);
    @Query("SELECT * FROM Trivias")
    List<Trivia> select();
    @Update
    void update(Trivia trivia);
    @Delete
    void delete(Trivia trivia);
}
