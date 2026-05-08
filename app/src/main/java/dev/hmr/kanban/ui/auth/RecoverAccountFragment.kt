package dev.hmr.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentRecoverAccountBinding
import dev.hmr.kanban.util.initToolbar
import dev.hmr.kanban.util.showBottomSheet


class RecoverAccountFragment : Fragment() {
    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       this._binding = FragmentRecoverAccountBinding.inflate(
           inflater,
           container,
           false
       )
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.auth = FirebaseAuth.getInstance()

        initToolbar(this.binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        this.binding.buttonEnviar.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = this.binding.edittextEmail.text.toString().trim()

        if (email.isBlank()) {
            showBottomSheet(null, null, getString(R.string.email_empty))
            return
        }

        this.binding.progressBar.isVisible = true
        recoverAccountUser(email)
    }

    private fun recoverAccountUser(email: String) {
        try {
            this.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    this.binding.progressBar.isVisible = false

                    if (task.isSuccessful) {
                        showBottomSheet(
                            message = getString(R.string.text_message_recover_account_fragment)
                        )
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

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}