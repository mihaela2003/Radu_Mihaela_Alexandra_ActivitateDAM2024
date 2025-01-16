package com.example.pregatire_test2_7;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    void insert(Book book);

    @Query("SELECT * FROM Books")
    List<Book> select();

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);
}
