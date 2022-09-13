package uz.gita.contactauth.ui.screen.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactauth.R
import uz.gita.contactauth.data.local.model.AuthData
import uz.gita.contactauth.databinding.LoginScreenBinding
import uz.gita.contactauth.presentation.LoginScreenViewModel
import uz.gita.contactauth.presentation.impl.LoginScreenViewModelImpl

class LoginScreen : Fragment(R.layout.login_screen) {

    private val binding: LoginScreenBinding by viewBinding(LoginScreenBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorMessage.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.action_loginScreen_to_mainScreen)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                AuthData(
                    binding.login.text.toString(),
                    binding.password.text.toString()
                )
            )
        }
        binding.btnRegister.setOnClickListener {
            navController.navigate(R.id.action_loginScreen_to_registerScreen)
        }
    }

}