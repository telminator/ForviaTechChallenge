package com.example.challenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challenge.data.local.dao.AppDao
import com.example.challenge.data.local.entity.AppEntity

@Database(
    entities = [AppEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
