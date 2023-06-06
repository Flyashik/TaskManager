package com.example.taskmanager.room

import com.example.taskmanager.Task

interface TaskRepository {

    fun addTask(task : Task)

    fun removeTask(task : Task)

    fun updateIsDoneTask(task : Task)

    fun  getAllTasks() : List<Task?>?

    fun taskExist(task: Task) : Boolean
}