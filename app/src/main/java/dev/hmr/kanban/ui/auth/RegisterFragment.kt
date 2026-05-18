package dev.hmr.kanban.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentRegisterBinding
import dev.hmr.kanban.util.FirebaseHelper
import dev.hmr.kanban.util.hideKeyboard
import dev.hmr.kanban.util.initToolbar
import dev.hmr.kanban.util.showBottomSheet


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentRegisterBinding.inflate(
            inflater, container, false
        )
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(this.binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        this.binding.buttonRegister.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = this.binding.edittextEmail.text.toString().trim()
        val senha = this.binding.edittextSenha.text.toString().trim()

        if (email.isBlank()) {
            showBottomSheet(
                null,
                null,
                getString(R.string.email_empty_register_fragment)
            )
            return
        }

        if (senha.isBlank()) {
            showBottomSheet(
                null,
                null,
                getString(R.string.password_empty_register_fragment)
            )
            return
        }

        hideKeyboard()

        this.binding.registeringProgressBar.isVisible = true

        registerUser(email, senha)
    }

    private fun registerUser(email: String, password: String) {
        try {
            FirebaseHelper.getAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    this.binding.registeringProgressBar.isVisible = false
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        showBottomSheet(message = getString(FirebaseHelper.validError(error = task.exception?.message.toString())))
                    }
                }
        } catch (e: Exception) {
            this.binding.registeringProgressBar.isVisible = false
            Toast.makeText(
                requireContext(),
                e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}