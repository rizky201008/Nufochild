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
import com.nufochild.data.general.UserNutritions

@Dao
interface NutritionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(nutrition: UserNutritions)

    @Update
    fun update(nutrition: UserNutritions)

    @Delete
    fun delete(nutrition: UserNutritions)

    @Query("SELECT * from nutrition ORDER BY id ASC")
    fun getAllNutrition(): List<UserNutritions?>
}