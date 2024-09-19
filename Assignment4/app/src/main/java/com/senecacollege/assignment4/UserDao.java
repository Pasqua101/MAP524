package com.senecacollege.assignment4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(List<User> users);

    @Query("SELECT * FROM userdb")
    List<User> getAllUsers();

    @Query("DELETE FROM userdb")
    void deleteAllUsers();

    @Delete
    void deleteUser(User user);

    @Query("UPDATE userdb SET process_status = 'AWAITED'")
    void setDefaultProcessStatus();

    @Query("SELECT * FROM userdb WHERE uid = :userId")
    User getUserById(int userId);

    @Update
    void updateUser(User user);
}
