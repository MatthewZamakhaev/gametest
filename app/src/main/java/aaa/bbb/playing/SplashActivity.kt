package aaa.bbb.playing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Application
import com.appsflyer.AppsFlyerLib
import android.os.Handler
import android.os.Looper
import com.onesignal.OneSignal

class SplashActivity : AppCompatActivity() {
    private val key = 1234567
    val ONESIGNAL_APP_ID = "27939bbc-188c-46fa-b68d-e5960af9fafb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppsFlyerLib.getInstance().init(key.toString(), null, this)
        AppsFlyerLib.getInstance().start(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}