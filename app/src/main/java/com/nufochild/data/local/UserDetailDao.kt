/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nufochild.data.request.RequestUpdateProfile

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(profile: RequestUpdateProfile)

    @Update
    fun update(profile: RequestUpdateProfile)

    @Delete
    fun delete(profile: RequestUpdateProfile)

    @Query("SELECT * from profile ORDER BY id ASC")
    fun getAllUserData(): List<RequestUpdateProfile?>
}