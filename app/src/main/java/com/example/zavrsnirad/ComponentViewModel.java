package com.example.zavrsnirad;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ComponentViewModel extends AndroidViewModel{

    private ComponentRepository repository;
    private LiveData<List<Component>> allComponents;
    public ComponentViewModel(@NonNull Application application) {
        super(application);
        repository= new ComponentRepository(application);
        allComponents=repository.getAllComponents();
    }

    public void insert(Component component){
        repository.insert(component);
    }

    public void delete(Component component){
        repository.delete(component);
    }

    public LiveData<List<Component>> getAllComponents(){
        return allComponents;
    }

}
