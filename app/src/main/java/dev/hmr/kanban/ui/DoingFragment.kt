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
import dev.hmr.kanban.databinding.FragmentDoingBinding
import dev.hmr.kanban.ui.adapter.TaskAdapter


class DoingFragment : Fragment() {
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentDoingBinding.inflate(
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
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Voltando para A Fazer", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Avançando para Feitas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask(){
        val taskList: List<Task> = listOf(
            Task("0", "Modelar banco de dados, tudo VARCHAR e CPF em INTEGER", Status.DOING),
            Task("1", "Callback Hell", Status.DOING),
            Task("2", "Fechar a porta", Status.DOING),
            Task("3", "Comprar leite", Status.DOING),
            Task("4", "Ligar pro narga", Status.DOING)
        )

        this.taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}