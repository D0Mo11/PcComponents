package com.example.zavrsnirad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Component.class}, version = 1)
public abstract class ComponentDatabase extends RoomDatabase {

    private static ComponentDatabase instance;
    private static Context activity;

    public abstract ComponentDao componentDao();

    public static synchronized ComponentDatabase getInstance(Context context){

        activity=context.getApplicationContext();

        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ComponentDatabase.class,"component_databse")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private  static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private  ComponentDao componentDao;
        private PopulateDbAsyncTask(ComponentDatabase db){
            componentDao=db.componentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fillWithStartingData(activity);
            return null;
        }
    }

    private static void fillWithStartingData(Context context){
        ComponentDao dao= getInstance(context).componentDao();

        JSONArray components = loadJSONArray(context);

        try {
            for(int i=0;i<components.length();i++){
                JSONObject component= components.getJSONObject(i);

                String componentType=component.getString("Type");
                String componentPartNumber=component.getString("Part Number");
                String componentBrand=component.getString("Brand");
                String componentModel=component.getString("Model");
                String componentRank=component.getString("Rank");
                String componentBenchmark=component.getString("Benchmark");
                String componentSamples=component.getString("Samples");
                String componentURL=component.getString("URL");

                dao.insert(new Component(componentType, componentPartNumber, componentBrand, componentModel, componentRank, componentBenchmark, componentSamples, componentURL));
            }

        } catch (JSONException e){

        }
    }

    private  static JSONArray loadJSONArray(Context context){
        StringBuilder builder= new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.components);
        BufferedReader reader= new BufferedReader(new InputStreamReader(in));

        String line;

        try {
            while ((line=reader.readLine()) !=null){
                builder.append(line);
            }

            JSONObject json= new JSONObject(builder.toString());
            return json.getJSONArray("components");
        }catch (IOException | JSONException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
