package com.example.catganisation.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.catganisation.databinding.FragmentLoginBinding
import com.example.catganisation.domain.ViewResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        subscribeUi()

        setupClickListeners()

        validateInputs()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.loginBtn.setOnClickListener {
            toggleViewsInteraction(false)
            viewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }
    }

    private fun subscribeUi() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility =
                if (state is ViewResult.Loading) View.VISIBLE else View.GONE
            binding.errorMessage.visibility =
                if (state is ViewResult.Error) View.VISIBLE else View.GONE

            when (state) {
                is ViewResult.Success -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToBreedsFragment())
                is ViewResult.Error -> {
                    toggleViewsInteraction(true)
                    binding.errorMessage.text = state.message
                }
            }
        })
    }

    private fun validateInputs() {
        binding.username.doAfterTextChanged {
            val isReadyToLogin =
                it?.toString()!!.isNotEmpty() && binding.password.text.toString().isNotEmpty()
            binding.loginBtn.isEnabled = isReadyToLogin
        }

        binding.password.doAfterTextChanged {
            val isReadyToLogin =
                it?.toString()!!.isNotEmpty() && binding.username.text.toString().isNotEmpty()
            binding.loginBtn.isEnabled = isReadyToLogin
        }
    }

    private fun toggleViewsInteraction(isEnabled: Boolean) {
        binding.loginBtn.isEnabled = isEnabled
        binding.username.isEnabled = isEnabled
        binding.password.isEnabled = isEnabled
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}