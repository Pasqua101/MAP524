package com.senecacollege.assignment4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1) //NOTE: Upgraded to version 2 for DB since this time I'm using the Process Status column. V1 doesn't use the column
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
