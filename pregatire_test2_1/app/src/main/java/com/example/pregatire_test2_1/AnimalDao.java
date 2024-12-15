package com.example.pregatire_test2_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimalDao {
    @Insert
    void insert(Animal animal);
    @Query("SELECT * FROM Animale")
    List<Animal> select();
    @Update
    void update(Animal animal);
    @Delete
    void delete(Animal animal);

}
