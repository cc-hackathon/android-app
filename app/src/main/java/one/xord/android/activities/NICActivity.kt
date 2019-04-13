package one.xord.android.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nic.*
import kotlinx.android.synthetic.main.activity_nic_form.*
import one.xord.android.R
import one.xord.android.api.APIExecutor
import one.xord.android.api.Data
import one.xord.android.api.DataRequest
import one.xord.android.api.NICRequest
import one.xord.android.statics.*

class NICActivity : AppCompatActivity(), APIExecutor.RequestExecutedCallback {

    private val executor = APIExecutor.getInstance(APIExecutor.GET_DATA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nic)

        executor?.registerRequestExecutedCallback(this)
//        executor?.execute(NICRequest())
        setViews()
    }

    override fun onExecuted(status: String?, message: String?, any: Any?) {
        if (status == SUCCESS) {
            if (any is Data) {
                // TODO
            }
        } else {
            showToast(this, message)
        }
    }

    private fun setViews() {
        nic_nic_number.setText(NIC_NUMBER)
        nic_fullname.setText(NAME)
        nic_father_name.setText(FATHER_NAME)
        nic_gender.setText(GENDER)
        nic_country.setText(COUNTRY)
        nic_dob.setText(DATE_OF_BIRTH)
        nic_doi.setText(DATE_OF_ISSUE)
        nic_doe.setText(DATE_OF_EXPIRY)
    }
}