package paolo.disney.foundation.bindings

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import paolo.disney.foundation.R


@BindingAdapter("arrayResourceName")
fun bindArrayAdapter(view: AutoCompleteTextView, arrayResourceName: String) {
    val options = view.resources.getStringArray(
        view.resources.getIdentifier(arrayResourceName, "array", view.context.packageName)
    )
    ArrayAdapter(view.context, R.layout.disney_dropdown_item, options).apply {
        view.setAdapter(this)
    }
}

@BindingAdapter("onItemClicked")
fun bindOnItemClicked(view: AutoCompleteTextView, onItemClickListener: AdapterView.OnItemClickListener) {
    view.onItemClickListener = onItemClickListener
}