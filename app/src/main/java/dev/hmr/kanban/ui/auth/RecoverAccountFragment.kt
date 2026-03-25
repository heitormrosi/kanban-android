package dev.hmr.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentRecoverAccountBinding
import dev.hmr.kanban.util.initToolbar
import dev.hmr.kanban.util.showBottomSheet


class RecoverAccountFragment : Fragment() {
    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

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
            showBottomSheet(null, null, R.string.email_empty)
            return
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}