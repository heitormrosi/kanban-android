package dev.hmr.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentLoginBinding
import dev.hmr.kanban.util.showBottomSheet


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = this._binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentLoginBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }

    private fun initListeners() {
        this.binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        this.binding.btnRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }

        this.binding.buttonLogin.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = this.binding.edittextEmail.text.toString().trim()
        val senha = this.binding.edittextSenha.text.toString().trim()

        if (email.isBlank()) {
            showBottomSheet(null, null, R.string.email_empty)
            return
        }

        if (senha.isBlank()) {
            showBottomSheet(null, null, R.string.password_empty)
            return
        }

        findNavController().navigate(R.id.action_global_homeFragment)
    }
}