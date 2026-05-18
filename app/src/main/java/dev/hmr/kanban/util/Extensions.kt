package dev.hmr.kanban.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
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
    message: String,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val binding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.textviewTitle.text = getText(titleDialog ?: R.string.text_title_warning)

    binding.textviewMessage.text = message

    binding.buttonOk.text = getText(titleButton ?: R.string.text_button_warning)

    binding.buttonOk.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    currentFocus?.let { view ->
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard() }
}