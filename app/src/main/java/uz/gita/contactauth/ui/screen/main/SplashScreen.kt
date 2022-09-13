package uz.gita.contactauth.ui.screen.main


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactauth.R
import uz.gita.contactauth.databinding.SplashScreenBinding
import uz.gita.contactauth.presentation.SplashViewModel
import uz.gita.contactauth.presentation.impl.SplashViewModelImpl
import uz.gita.contactauth.utils.toast

class SplashScreen : Fragment(R.layout.splash_screen) {

    private val binding: SplashScreenBinding by viewBinding(SplashScreenBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openLoginScreenLiveData.observe(this) {
            navController.navigate(SplashScreenDirections.actionSplashScreenToLoginScreen())
        }
        viewModel.openMainScreenLiveData.observe(this) {
            navController.navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                toast("Loading Start")
            } else {
                toast("Loging End")
            }
        }

    }


}