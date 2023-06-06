package com.example.taskmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.TaskItemBinding


class TaskAdapter(private val listener : RemoveListener) : RecyclerView.Adapter<TaskAdapter.MyTaskHolder>()
{
    private var taskList = ArrayList(MAIN.roomTaskRepository.getAllTasks())

    class MyTaskHolder(item : View) : RecyclerView.ViewHolder(item)
    {
        private val binding = TaskItemBinding.bind(item)
        fun bind(task: Task, listener: RemoveListener)
        {
            binding.taskName.text = task.name
            binding.taskCheckBox.isChecked = task.isDone
            binding.buttonDeleteTask.setOnClickListener {
                listener.onClickDelete(task)
            }
            binding.taskCard.setOnClickListener {
                listener.onClick(task)
            }
            binding.taskCheckBox.setOnClickListener {
                listener.onCheck(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return MyTaskHolder(view)
    }

    override fun onBindViewHolder(holder: MyTaskHolder, position: Int)
    {
        holder.bind(taskList[position], listener)
    }

    override fun getItemCount(): Int
    {
        return taskList.size
    }

    fun addTask(task : Task) {
        if (MAIN.roomTaskRepository.taskExist(task)) {
            val existingTask = taskList.find { it.name == task.name }
            existingTask?.let {
                val index = taskList.indexOf(existingTask)
                taskList[index] = task
            }
        } else {
            taskList.add(task)
        }
        MAIN.roomTaskRepository.addTask(task)
        notifyDataSetChanged()
    }


    fun removeTask(task : Task)
    {
        val position = taskList.indexOf(task)
        taskList.remove(task)
        MAIN.roomTaskRepository.removeTask(task)
        notifyItemRemoved(position)
    }

    interface RemoveListener
    {
        fun onClickDelete(task : Task)

        fun onClick(task : Task)

        fun onCheck(task : Task)
    }
}