package com.example.taskmanager.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.taskmanager.MAIN
import com.example.taskmanager.R
import com.example.taskmanager.databinding.TaskInfoFragmentBinding

class TaskInfoFragment : Fragment() {
    lateinit var binding : TaskInfoFragmentBinding;
    private val args : TaskInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskInfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.taskLabelValue.text = args.label
            binding.taskDesc.setText(args.info)
            binding.saveButton.setOnClickListener() {
                val action = TaskInfoFragmentDirections.actionTaskInfoFragmentToMainFragment(args.label, binding.taskDesc.text.toString())
                MAIN.navController.navigate(action);
            }
            binding.cancelButton.setOnClickListener() {
                MAIN.navController.navigate(R.id.action_taskInfoFragment_to_mainFragment);
            }
        }
    }
}