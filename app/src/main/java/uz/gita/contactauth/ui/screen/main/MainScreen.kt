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
import uz.gita.contactauth.R
import uz.gita.contactauth.data.local.shp.MySharedPreference
import uz.gita.contactauth.databinding.MainScreenBinding
import uz.gita.contactauth.ui.adapter.ContactAdapter
import uz.gita.contactauth.presentation.MainScreenViewModel
import uz.gita.contactauth.presentation.impl.MainScreenViewModelImpl

class MainScreen : Fragment() {

    private lateinit var binding: MainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val adapter: ContactAdapter by lazy { ContactAdapter() }
    private val navController by lazy { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = MainScreenBinding.inflate(inflater, container, false)

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
        viewModel.successLiveData.observe(this) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.listLiveData.observe(this) {
            if (it != null) {
                binding.text.visibility = View.GONE
            } else {
                binding.text.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        }
        binding.rv.adapter = adapter

        binding.addBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAddScreen())
        }
        adapter.submitEdit {
            val bundle = Bundle()
            bundle.putSerializable("contact", it)
            findNavController().navigate(R.id.action_mainScreen_to_updateScreen, bundle)
        }
        adapter.submitDelete {
            viewModel.delete(it)
        }
        binding.refresh.setOnClickListener {
            viewModel.sync()
        }

        binding.deleteAccount.setOnClickListener {
            viewModel.deleteAccount()
            findNavController().navigate(MainScreenDirections.actionGlobalLoginScreen())

        }
        binding.logout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(MainScreenDirections.actionGlobalLoginScreen())
        }
        return binding.root
    }


}