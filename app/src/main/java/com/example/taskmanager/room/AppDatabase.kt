package com.example.taskmanager.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.room.entities.TaskEntity

@Database(
    version = 1,
    entities = [
        TaskEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase ()
{
    abstract fun getTaskDao() : TaskDao
}