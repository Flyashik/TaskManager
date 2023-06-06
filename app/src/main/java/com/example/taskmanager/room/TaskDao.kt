package com.example.taskmanager.room

import androidx.room.*
import com.example.taskmanager.room.entities.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks() : List<TaskEntity>?

    @Query("UPDATE tasks SET is_done = :isDone WHERE name = :taskName")
    fun updateIsDoneTask(taskName: String, isDone: Boolean)

    @Transaction
    fun addTask(taskEntity: TaskEntity) {
        if (doesTaskExist(taskEntity.name)) {
            updateTaskDesc(taskEntity.name, taskEntity.info)
        } else {
            insertTask(taskEntity)
        }
    }

    @Insert
    fun insertTask(taskEntity: TaskEntity)

    @Query("UPDATE tasks SET info = :taskInfo WHERE name = :taskName")
    fun updateTaskDesc(taskName: String, taskInfo: String)

    @Query("DELETE FROM tasks WHERE name = :taskName")
    fun removeTask(taskName : String)

    @Query("SELECT EXISTS(SELECT * FROM tasks WHERE name = :taskName)")
    fun doesTaskExist(taskName: String): Boolean
}