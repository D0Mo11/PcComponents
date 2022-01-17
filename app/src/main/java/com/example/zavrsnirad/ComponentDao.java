package com.example.zavrsnirad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ComponentDao {

    @Query("SELECT*FROM component_table ORDER BY brand ASC" )
    LiveData<List<Component>> getAllComponent();

    @Transaction
    @Query("UPDATE component_table SET favorite='1' WHERE brand=:brand AND model=:model")
    void makeFavorite(String brand, String model);

    @Transaction
    @Query("UPDATE component_table SET favorite='0' WHERE brand=:brand AND model=:model")
    void removeFavorite(String brand, String model);

    @Query("SELECT*FROM component_table WHERE favorite='1'")
    List<Component> favoriteComponents();

    @Insert
    void insert(Component component);
    @Delete
    void delete(Component component);
}
