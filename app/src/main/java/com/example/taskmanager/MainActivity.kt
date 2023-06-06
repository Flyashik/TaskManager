package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.taskmanager.screens.MainFragmentDirections
import com.example.taskmanager.room.AppDatabase
import com.example.taskmanager.room.entities.RoomTaskRepository
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskAdapter.RemoveListener {
    private lateinit var binding : ActivityMainBinding
    lateinit var navController : NavController

    lateinit var adapter : TaskAdapter

    private lateinit var database: AppDatabase

    lateinit var roomTaskRepository : RoomTaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").allowMainThreadQueries().build()
        roomTaskRepository = RoomTaskRepository(database.getTaskDao())

        MAIN = this
        adapter = TaskAdapter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onClickDelete(task: Task) {
        adapter.removeTask(task)
    }

    override fun onClick(task: Task) {
        val action = MainFragmentDirections.actionMainFragmentToTaskInfoFragment(task.name,
            task.info
        )
        navController.navigate(action)
    }

    override fun onCheck(task: Task) {
        task.isDone = task.isDone != true
        MAIN.roomTaskRepository.updateIsDoneTask(task)
    }


}