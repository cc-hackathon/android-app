package one.xord.android.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import one.xord.android.R
import android.content.Intent
import android.os.Handler


class Splash : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@Splash, SignUpActivity::class.java)
            this@Splash.startActivity(mainIntent)
            this@Splash.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

}
