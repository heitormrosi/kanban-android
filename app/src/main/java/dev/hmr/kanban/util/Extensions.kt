package dev.hmr.kanban.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.hmr.kanban.R
import dev.hmr.kanban.databinding.BottomSheetBinding

fun Fragment.initToolbar(toolbar: Toolbar) {
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext())
    val binding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.textviewTitle.text = getText(titleDialog ?: R.string.text_button_warning)
    binding.textviewMessage.text = getText(message)

    binding.buttonOk.text = getText(titleButton ?: R.string.text_button_warning)

    binding.buttonOk.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }
}