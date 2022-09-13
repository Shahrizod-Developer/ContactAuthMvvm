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
import uz.gita.contactauth.databinding.UpdateScreenBinding
import uz.gita.contactauth.presentation.EditScreenViewModel
import uz.gita.contactauth.presentation.impl.EditScreenViewModelImpl

class UpdateScreen : Fragment() {


    private lateinit var binding: UpdateScreenBinding
    private val viewModel: EditScreenViewModel by viewModels<EditScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = UpdateScreenBinding.inflate(inflater, container, false)

        val data = arguments?.getSerializable("contact") as ContactEntity

        binding.name.setText(data.name)
        binding.number.setText(data.phone.substring(4))
        viewModel.errorLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.add.setOnClickListener {
            viewModel.update(
                ContactEntity(
                    data.id,
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