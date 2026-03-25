package dev.hmr.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentFormTaskBinding
import dev.hmr.kanban.util.initToolbar
import dev.hmr.kanban.util.showBottomSheet


class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentFormTaskBinding.inflate(
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
        this.binding.buttonSave.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val descricao = this.binding.editTextDescricao.text.toString().trim()

        if (descricao.isBlank()) {
            showBottomSheet(
                null,
                null,
                R.string.description_empty_form_task_fragment)
            return
        }

    }

}