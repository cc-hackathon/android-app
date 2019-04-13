package one.xord.android.statics

import android.content.Context
import android.widget.Toast

/**
 * Created by sami on 10/29/18.
 */

fun showToast(context: Context, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}