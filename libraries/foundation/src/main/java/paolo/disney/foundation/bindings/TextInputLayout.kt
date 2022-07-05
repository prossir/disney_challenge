package paolo.disney.foundation.bindings

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("error")
fun bindError(view: TextInputLayout, error: String?) {
    error?.apply {
        view.error = this
    }
}