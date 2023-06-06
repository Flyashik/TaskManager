package com.example.taskmanager.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.*
import com.example.taskmanager.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    lateinit var binding : MainFragmentBinding;
    private val args : MainFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        binding.apply {
            binding.rcView.layoutManager = LinearLayoutManager(context);
            binding.rcView.adapter = MAIN.adapter;
            binding.butAddTask.setOnClickListener() {
                MAIN.navController.navigate(R.id.action_mainFragment_to_editFragment);
            }

            val taskLabel = args.label;
            val taskInfo = args.info;

            if (taskLabel != "") {
                MAIN.adapter.addTask(Task(taskLabel, taskInfo, false));
            }
        }

        return binding.root;
    }
}