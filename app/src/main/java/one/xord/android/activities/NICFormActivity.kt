package one.xord.android.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_nic_form.*
import one.xord.android.R
import one.xord.android.api.APIExecutor
import one.xord.android.api.Data
import one.xord.android.api.WriteDataRequest
import one.xord.android.statics.*
import android.widget.ProgressBar
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs


class NICFormActivity : AppCompatActivity(), APIExecutor.RequestExecutedCallback {

    private val executor = APIExecutor.getInstance(APIExecutor.WRITE_DATA)
    private var pgsBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nic_form)
        pgsBar = findViewById(R.id.pBar)

        val btn_click_me = findViewById<Button>(R.id.submitNicForVerification)
        btn_click_me.setOnClickListener {
            if (form_nic_edit.text.toString().isBlank() || form_name_edit.text.toString().isBlank()
                    || form_father_name_edit.text.toString().isBlank()
                    || form_gender_edit.text.toString().isBlank()
                    || form_country_edit.text.toString().isBlank()
                    || form_dob_edit.text.toString().isBlank()
                    || form_doi_edit.text.toString().isBlank()
                    || form_doe_edit.text.toString().isBlank()) {
                Toast.makeText(this, "Fill in the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val view = pgsBar
            view!!.visibility = View.VISIBLE
            view!!.postDelayed({
                IS_VERIFIED = true
                setPrefs()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }

        form_nic_edit.setText(NIC_NUMBER)
//        setViews()

        executor?.registerRequestExecutedCallback(this)
        executor?.execute(WriteDataRequest(NIC_NUMBER, NAME, FATHER_NAME, GENDER, COUNTRY, DATE_OF_BIRTH, DATE_OF_EXPIRY))
    }

    private fun setPrefs() {
        Prefs.putBoolean(IS_VERIFIED_KEY, true)
        Prefs.putString(NAME_KEY, form_name_edit.text.toString())
        Prefs.putString(FATHER_NAME_KEY, form_father_name_edit.text.toString())
        Prefs.putString(GENDER_KEY, form_gender_edit.text.toString())
        Prefs.putString(COUNTRY_KEY, form_country_edit.text.toString())
        Prefs.putString(DATE_OF_BIRTH_KEY, form_dob_edit.text.toString())
        Prefs.putString(DATE_OF_ISSUE_KEY, form_doi_edit.text.toString())
        Prefs.putString(DATE_OF_EXPIRY_KEY, form_doe_edit.text.toString())

        NAME = Prefs.getString(NAME_KEY, "")
        FATHER_NAME = Prefs.getString(FATHER_NAME_KEY, "")
        GENDER = Prefs.getString(GENDER_KEY, "")
        COUNTRY = Prefs.getString(COUNTRY_KEY, "")
        DATE_OF_BIRTH = Prefs.getString(DATE_OF_BIRTH_KEY, "")
        DATE_OF_ISSUE = Prefs.getString(DATE_OF_ISSUE_KEY, "")
        DATE_OF_EXPIRY = Prefs.getString(DATE_OF_EXPIRY_KEY, "")
    }

    override fun onExecuted(status: String?, message: String?, any: Any?) {
        println(any)
        if (status == SUCCESS) {
            if (any is Data) {

            }
        } else {
            showToast(this, message)
        }
    }
}