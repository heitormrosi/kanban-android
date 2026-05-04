package dev.hmr.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hmr.kanban.data.model.Status
import dev.hmr.kanban.data.model.Task
import dev.hmr.kanban.databinding.FragmentDoneBinding
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

        this.initRecyclerViewTask()
        this.getTask()
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

    private fun getTask() {
        val taskList: List<Task> = listOf(
            Task("0", "Pedir cachorro quente", Status.DONE),
            Task("1", "Subir de DEV para PROD", Status.DONE),
            Task("2", "Reiniciar servidor", Status.DONE),
            Task("3", "Carregar bateria", Status.DONE),
            Task("4", "Mandar e-mail pro chefe", Status.DONE)
        )

        this.taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}