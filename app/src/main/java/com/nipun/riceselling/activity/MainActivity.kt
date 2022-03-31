package com.nipun.riceselling.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.os.HandlerCompat.postDelayed
import com.nipun.riceselling.R
import com.nipun.riceselling.SessionManager
import com.nipun.riceselling.utils.Utiles

class MainActivity : AppCompatActivity() {
    private var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        sessionManager = SessionManager(this)
        val SPLASH_TIME_OUT = 2000
        Handler().postDelayed({
            if (Utiles.Utiles.internetChack()) {
                if (!sessionManager!!.getBooleanData(SessionManager.login) && !sessionManager!!.getBooleanData(
                        SessionManager.isopen
                    )
                ) {
                    val i = Intent(this@MainActivity, InfoActivity::class.java)
                    startActivity(i)
                } else if (!sessionManager!!.getBooleanData(SessionManager.login) && sessionManager!!.getBooleanData(
                        SessionManager.isopen)) {
                    val i = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(i)
                } else {
                    val i = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(i)
                }
                finish()
            } else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Please Check Your Internet Connection")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Exit"
                    ) { dialog, id ->
                        Log.e("tem", dialog.toString() + "" + id)
                        finish()
                    }
                val alert = builder.create()
                alert.show()
            }
        }, SPLASH_TIME_OUT.toLong())

    }
}