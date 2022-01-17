package com.example.zavrsnirad;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ComponentRepository {
    private ComponentDao componentDao;
    private LiveData<List<Component>> allComponents;

    public ComponentRepository(Application application){
        ComponentDatabase database = ComponentDatabase.getInstance(application);
        componentDao=database.componentDao();
        allComponents=componentDao.getAllComponent();
    }

    public void insert(Component component){
        new InsertComponentAsyncTask(componentDao).execute(component);

    }

    public void delete(Component component){
        new DeleteComponentAsyncTask(componentDao).execute(component);

    }

    public LiveData<List<Component>> getAllComponents(){
        return allComponents;
    }

    private static class InsertComponentAsyncTask extends AsyncTask<Component, Void,Void>{
        private ComponentDao componentDao;

        private InsertComponentAsyncTask(ComponentDao componentDao){
            this.componentDao=componentDao;
        }
        @Override
        protected Void doInBackground(Component... components) {
            componentDao.insert(components[0]);
            return null;
        }
    }

    private static class DeleteComponentAsyncTask extends AsyncTask<Component, Void,Void>{
        private ComponentDao componentDao;

        private DeleteComponentAsyncTask(ComponentDao componentDao){
            this.componentDao=componentDao;
        }
        @Override
        protected Void doInBackground(Component... components) {
            componentDao.delete(components[0]);
            return null;
        }
    }
}
