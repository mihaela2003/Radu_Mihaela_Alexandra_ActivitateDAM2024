package com.example.seminar4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimeDAO {
    @Insert
    void insert(Anime anime);
    @Query("SELECT * FROM Animes")
    List<Anime> selectAll();
    @Update
    void update(Anime anime);
    @Delete
    void delete(Anime anime);
}
