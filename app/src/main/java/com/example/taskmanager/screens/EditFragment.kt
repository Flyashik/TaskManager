package com.example.taskmanager.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskmanager.MAIN
import com.example.taskmanager.R
import com.example.taskmanager.databinding.EditFragmentBinding

class EditFragment : Fragment() {

    lateinit var binding : EditFragmentBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.buttonCancelAdding.setOnClickListener() {
                MAIN.navController.navigate(R.id.action_editFragment_to_mainFragment_byCancel);
            }
            binding.buttonApplyAdding.setOnClickListener() {
                if (binding.taskLabel.text.toString() == "") {
                    Toast.makeText(context, "Label is empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val action = EditFragmentDirections.actionEditFragmentToMainFragmentByCancel(binding.taskLabel.text.toString(),binding.taskDesc.text.toString());
                MAIN.navController.navigate(action);
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}