package dev.hmr.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hmr.kanban.R
import dev.hmr.kanban.data.model.Status
import dev.hmr.kanban.data.model.Task
import dev.hmr.kanban.databinding.FragmentTodoBinding
import dev.hmr.kanban.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentTodoBinding.inflate(
            inflater,
            container,
            false
        )
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.initListeners()

        this.initRecyclerViewTask()

        this.getTask()
    }

    private fun initListeners() {
        this.binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {
        this.taskAdapter =
            TaskAdapter(requireContext()) { task, option -> optionSelected(task, option) }

        with(this.binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(
                    requireContext(),
                    "Removendo ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT)
                    .show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT)
                    .show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask(){
        val taskList: List<Task> = listOf(
            Task("0", "Criar nova tela do app", Status.TODO),
            Task("1", "Validar informações na tela de Login", Status.TODO),
            Task("2", "Adicionar nova funcionalidade no app", Status.TODO),
            Task("3", "Salvar token localmente", Status.TODO),
            Task("4", "Criar funcionalidade de logout no app", Status.TODO)
        )

        this.taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}