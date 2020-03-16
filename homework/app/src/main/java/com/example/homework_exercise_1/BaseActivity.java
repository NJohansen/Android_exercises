package com.example.homework_exercise_1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework_exercise_1.database.AppDatabase;
import com.example.homework_exercise_1.database.User;

public class BaseActivity extends AppCompatActivity {
    protected AppDatabase db;
    protected User currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        db = AppDatabase.getAppDatabase(this);
        if(db.userDAO().countUsers() == 0){
            currentUser = new User();
            currentUser.uid = 1;
            currentUser.address = "";
            currentUser.name = "";
            currentUser.dateOfBirth = 0;
            db.userDAO().insert(currentUser);
        } else{
            currentUser = db.userDAO().getUser();
        }

    }

}
