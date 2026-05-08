package dev.hmr.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import dev.hmr.kanban.R
import dev.hmr.kanban.data.model.Status
import dev.hmr.kanban.data.model.Task
import dev.hmr.kanban.databinding.FragmentFormTaskBinding
import dev.hmr.kanban.util.initToolbar
import dev.hmr.kanban.util.showBottomSheet


class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var task: Task
    private var newTask: Boolean = true
    private var status: Status = Status.TODO

    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth

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

        this.auth = Firebase.auth
        this.reference = Firebase.database.reference

        initToolbar(this.binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        this.binding.buttonSave.setOnClickListener {
            validateData()
        }

        this.binding.radioGroup.setOnCheckedChangeListener { _, id ->
            this.status = when (id) {
                R.id.rbTodo -> Status.TODO
                R.id.rbDoing -> Status.DOING
                else -> Status.DONE
            }
        }
    }

    private fun saveTask() {
        reference
            .child("task")
            .child(auth.currentUser?.uid ?: "")
            .child(task.id)
            .setValue(task).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        R.string.text_save_success_form_task_fragment,
                        Toast.LENGTH_SHORT
                    ).show()

                    if (newTask) {
                        findNavController().popBackStack()
                    } else {
                        binding.progressBar.isVisible = false
                    }
                } else {
                    binding.progressBar.isVisible = false
                    showBottomSheet(message = getString(R.string.error_generic))
                }
            }
    }

    private fun validateData() {
        val descricao = this.binding.editTextDescricao.text.toString().trim()

        if (descricao.isBlank()) {
            showBottomSheet(
                null,
                null,
                getString(R.string.description_empty_form_task_fragment)
            )
            return
        }

        this.binding.progressBar.isVisible = true

        if (newTask) this.task = Task()
        this.task.id = reference.database.reference.push().key ?: ""
        this.task.description = descricao
        this.task.status = this.status

        saveTask()
    }

}