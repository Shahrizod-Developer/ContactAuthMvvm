package uz.gita.contactauth.ui.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.gita.contactauth.data.local.room.entity.ContactEntity
import uz.gita.contactauth.databinding.AddScreenBinding
import uz.gita.contactauth.presentation.AddScreenViewModel
import uz.gita.contactauth.presentation.impl.AddScreenViewModelImpl

class AddScreen : Fragment() {


    private lateinit var binding: AddScreenBinding
    private val viewModel: AddScreenViewModel by viewModels<AddScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AddScreenBinding.inflate(inflater, container, false)

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.add.setOnClickListener {
            viewModel.add(
                ContactEntity(
                    "0",
                    binding.name.text.toString(),
                    binding.number.text.toString(),
                    0,
                    0,
                    0
                )
            )
        }
        viewModel.openScreenLiveData.observe(this) {
            findNavController().popBackStack()
        }
        return binding.root
    }


}