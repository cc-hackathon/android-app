package one.xord.android.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import one.xord.android.R
import android.os.Handler
import android.os.PersistableBundle

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}