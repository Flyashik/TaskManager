package com.example.taskmanager.room.entities

import com.example.taskmanager.Task
import com.example.taskmanager.room.TaskDao
import com.example.taskmanager.room.TaskRepository

class RoomTaskRepository(
    private val taskDao : TaskDao
) : TaskRepository
{
    override fun addTask(task: Task) {
        val entity = TaskEntity.fromTask(task)
        taskDao.addTask(entity)
    }

    override fun removeTask(task: Task) {
        taskDao.removeTask(task.name)
    }

    override fun updateIsDoneTask(task: Task) {
        taskDao.updateIsDoneTask(task.name, task.isDone)
    }

    override fun getAllTasks(): List<Task> {
        val allTasks = taskDao.getAllTasks()
        return allTasks?.map { taskEntity -> taskEntity.toTask() } ?: listOf()
    }

    override fun taskExist(task: Task): Boolean {
        return taskDao.doesTaskExist(task.name)
    }
}