package dev.hmr.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentRegisterBinding
import dev.hmr.kanban.util.initToolbar


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentRegisterBinding.inflate(
            inflater,
            container,
            false
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
            Toast.makeText(
                requireContext(),
                "Preencha seu email!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (senha.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Preencha a senha!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}