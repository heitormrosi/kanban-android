package dev.hmr.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hmr.kanban.R
import dev.hmr.kanban.data.model.Status
import dev.hmr.kanban.data.model.Task
import dev.hmr.kanban.databinding.FragmentDoneBinding
import dev.hmr.kanban.databinding.FragmentSplashBinding
import dev.hmr.kanban.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentDoneBinding.inflate(
            inflater,
            container,
            false
        )
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewTask(getTasks())
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {
        this.taskAdapter =
            TaskAdapter(requireContext(), taskList) { task, option -> optionSelected(task, option) }

        this.binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        this.binding.recyclerViewTask.setHasFixedSize(true)

        this.binding.recyclerViewTask.adapter = this.taskAdapter
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Movendo para Fazendo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTasks() = listOf(
        Task("0", "Pedir cachorro quente", Status.DONE),
        Task("1", "Subir de DEV para PROD", Status.DONE),
        Task("2", "Reiniciar servidor", Status.DONE),
        Task("3", "Carregar bateria", Status.DONE),
        Task("4", "Mandar e-mail pro chefe", Status.DONE)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}