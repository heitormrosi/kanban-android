package dev.hmr.kanban.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentLoginBinding
import dev.hmr.kanban.util.showBottomSheet


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = this._binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentLoginBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.auth = FirebaseAuth.getInstance()

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
            showBottomSheet(null, null, getString(R.string.email_empty))
            return
        }

        if (senha.isBlank()) {
            showBottomSheet(null, null, getString(R.string.password_empty))
            return
        }

        this.binding.progressBar.isVisible = true

        loginUser(email, senha)
    }

    private fun loginUser(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    binding.progressBar.isVisible = false
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}