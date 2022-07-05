package paolo.disney.foundation.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import paolo.disney.foundation.constants.FoundationConstants
import paolo.disney.foundation.databinding.DisneySnackbarViewBinding


// SnackBar is a final class so it cannot be extended. We need to make an utility class for it
object SnackBarUtils {

    fun fromCustomView(activity: Activity, view: View, title: String, message: String) {
        val snackBar = Snackbar.make(view, FoundationConstants.EMPTY_STRING, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.setPadding(0, 0, 0, 0)

        DisneySnackbarViewBinding.inflate(activity.layoutInflater).let {
            it.tvTitle.text = title
            it.tvMessage.text = message
            it.bDismiss.setOnClickListener {
                snackBar.dismiss()
            }
            snackBarLayout.addView(it.root, 0)
            snackBar.show()
        }
    }

}