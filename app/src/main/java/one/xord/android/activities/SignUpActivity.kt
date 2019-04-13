package one.xord.android.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_sign_up.*
import one.xord.android.R
import one.xord.android.statics.*
import android.R.drawable.btn_dialog
import android.widget.TextView
import android.view.Window.FEATURE_NO_TITLE
import android.app.Activity
import android.app.Dialog
import android.os.Handler
import android.view.Window
import android.widget.Toast
import one.xord.android.adapters.RequestsAdapter
import one.xord.android.api.APIExecutor
import one.xord.android.api.Data
import one.xord.android.api.Person

class SignUpActivity : AppCompatActivity(), APIExecutor.RequestExecutedCallback {

    private var dialog: Dialog? = null
    private val writePersonAPIExecutor = APIExecutor.getInstance(APIExecutor.WRITE_PERSON)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        if (IS_VERIFIED) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        else if (!IS_VERIFIED && IS_SIGNED_UP) {
            startActivity(Intent(this, NICFormActivity::class.java))
            finish()
        }
    }

    override fun onExecuted(status: String?, message: String?, any: Any?) {
        println(any)
        if (status == SUCCESS) {
            if (any is Person) {

            }
        } else {
            showToast(this, message)
        }
    }

    fun onSignUp(view: View) {
        if (sign_up_nic_edit.text.isBlank() || sign_up_phone_edit.text.isBlank()) {
            Toast.makeText(this, "Fill in the fields", Toast.LENGTH_SHORT).show()
            return
        }

        IS_SIGNED_UP = true
        NIC_NUMBER = sign_up_nic_edit.text.toString()
        PHONE_NUMBER = sign_up_phone_edit.text.toString()

        Prefs.putBoolean(IS_SIGNED_UP_KEY, true)
        Prefs.putString(NIC_NUMBER_KEY, sign_up_nic_edit.text.toString())
        Prefs.putString(PHONE_NUMBER_KEY, sign_up_phone_edit.text.toString())

        showDialog()

        Handler().postDelayed({
            dialog?.dismiss()
            startActivity(Intent(this, NICFormActivity::class.java))
            finish()
        }, 1000)
    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.fingerprint_dialog)
        dialog?.show()
    }
}