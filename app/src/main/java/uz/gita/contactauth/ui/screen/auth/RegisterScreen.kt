package uz.gita.contactauth.ui.screen.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactauth.R
import uz.gita.contactauth.data.local.model.AuthData
import uz.gita.contactauth.databinding.RegisterScreenBinding
import uz.gita.contactauth.presentation.RegisterScreenViewModel
import uz.gita.contactauth.presentation.impl.RegisterScreenViewModelImpl

class RegisterScreen : Fragment(R.layout.register_screen) {

    private val binding: RegisterScreenBinding by viewBinding(RegisterScreenBinding::bind)
    private val viewModel: RegisterScreenViewModel by viewModels<RegisterScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorMessage.observe(this) {

            if (it == null) {
                Toast.makeText(requireContext(), "Bunday foydalanuvchi mavjud", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TTT", it)
            findNavController().navigate(R.id.action_registerScreen_to_mainScreen)
        }

    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            viewModel.createAccount(
                AuthData(
                    binding.login.text.toString(),
                    binding.password.text.toString()
                )
            )
        }

        viewModel.loadingLiveData.observe(this) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
    }

}