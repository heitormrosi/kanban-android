package dev.hmr.kanban.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this._binding = FragmentSplashBinding.inflate(
            inflater,
            container,
            false
        )
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({ checkAuth() }, 3000)
    }

    private fun checkAuth() {

        try {
            val currentUser = this.auth.currentUser

            if (currentUser != null) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_authentication)
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_splashFragment_to_authentication)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}