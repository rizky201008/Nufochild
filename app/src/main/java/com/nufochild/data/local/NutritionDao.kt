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

    @Query("SELECT * FROM nutrition ORDER BY id DESC LIMIT 1")
    fun getAllNutrition(): List<UserNutritions?>

    @Query("UPDATE nutrition SET vprotein = vprotein + :vprotein, venergy = venergy + :venergy,    vfat = vfat + :vfat,    vfiber = vfiber + :vfiber,    vcarbohydrate = vcarbohydrate + :vcarbohydrate WHERE id = (SELECT MAX(id) FROM nutrition)")
    fun updateNutritionValue(
        vprotein: Float,
        venergy: Float,
        vfat: Float,
        vfiber: Float,
        vcarbohydrate: Float
    )

}