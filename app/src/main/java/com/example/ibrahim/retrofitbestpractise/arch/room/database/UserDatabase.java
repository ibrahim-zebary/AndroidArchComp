package com.example.ibrahim.retrofitbestpractise.arch.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ibrahim.retrofitbestpractise.arch.room.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
}
