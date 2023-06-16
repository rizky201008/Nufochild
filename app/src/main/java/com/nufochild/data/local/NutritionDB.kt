/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nufochild.data.general.UserNutritions

@Database(
    entities = [UserNutritions::class],
    version = 1,
    exportSchema = false
)
abstract class NutritionDB : RoomDatabase() {
    abstract fun nutritionDao(): NutritionDao
    companion object {
        @Volatile
        private var INSTANCE: NutritionDB? = null

        @JvmStatic
        fun getDatabase(context: Context): NutritionDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NutritionDB::class.java, "nutrition_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}