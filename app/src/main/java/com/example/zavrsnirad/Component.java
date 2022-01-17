package com.example.zavrsnirad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "component_table")
public class Component {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String partNumber;
    private String brand;
    private String model;
    private String rank;
    private String benchmark;
    private String samples;
    private String url;
    private int favorite;


    public Component(String type, String partNumber, String brand, String model, String rank, String benchmark, String samples, String url) {
        this.type = type;
        this.partNumber = partNumber;
        this.brand = brand;
        this.model = model;
        this.rank = rank;
        this.benchmark = benchmark;
        this.samples = samples;
        this.url = url;
        this.favorite=0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getRank() {
        return rank;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public String getSamples() {
        return samples;
    }

    public String getUrl() {
        return url;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

}
