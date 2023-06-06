package com.example.taskmanager.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.taskmanager.Task

@Entity(
    tableName = "tasks",
    indices = [Index(value = ["name"], unique=true)]
)
data class TaskEntity (
    @PrimaryKey  (autoGenerate = true)  val id: Long,
    val name: String,
    val info: String,
    @ColumnInfo (name = "is_done") var isDone : Boolean
        ){
    fun toTask() : Task = Task(
        name = name,
        info = info,
        isDone = isDone
    )

    companion object {
        fun fromTask(task : Task) : TaskEntity = TaskEntity(
            id = 0,
            name = task.name,
            info = task.info,
            isDone = task.isDone
        )
    }
}