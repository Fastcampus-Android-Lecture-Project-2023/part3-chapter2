package fastcampus.part3.chapter2

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import fastcampus.part3.chapter2.databinding.ActivityIdentityInputBinding

class IdentityInputActivity : AppCompatActivity() {


    private lateinit var binding: ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        binding.nameEdit.showKeyboardDelay()
    }

    private fun initView() {
        with(binding) {
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                birthdayLayout.isVisible = true
                birthdayEdit.showKeyboard()
            }

            birthdayEdit.doAfterTextChanged {
                if (birthdayEdit.length() > 7) {
                    genderLayout.isVisible = true
                    birthdayEdit.closeKeyboard()
                }
            }

            telecomChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (!phoneLayout.isVisible) {
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }

            genderChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (!telecomLayout.isVisible) {
                    telecomLayout.isVisible = true
                }
            }

            phoneEdit.doAfterTextChanged {
                if (phoneEdit.length() > 10) {
                    confirmButton.isVisible = true
                    phoneEdit.closeKeyboard()
                }
            }

            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                if (phoneEdit.length() > 9) {
                    confirmButton.isVisible = true
                    phoneEdit.closeKeyboard()
                }
            }
        }
    }

    private fun EditText.setOnEditorActionListener(action: Int, invoke: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == action) {
                invoke()
                true
            } else {
                false
            }
        }
    }

    private fun EditText.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun EditText.showKeyboardDelay() {
        postDelayed({
            showKeyboard()
        }, 200)
    }

   private fun EditText.closeKeyboard() {
        this.clearFocus()
        val inputMethodManager =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }
}