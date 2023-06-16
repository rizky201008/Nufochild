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
import com.nufochild.data.request.RequestUpdateProfile

@Database(
    entities = [RequestUpdateProfile::class],
    version = 1,
    exportSchema = false
)
abstract class UserDetailDB : RoomDatabase() {
    abstract fun detailUserDao(): UserDetailDao
    companion object {
        @Volatile
        private var INSTANCE: UserDetailDB? = null

        @JvmStatic
        fun getDatabase(context: Context): UserDetailDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDetailDB::class.java, "user_detail_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}