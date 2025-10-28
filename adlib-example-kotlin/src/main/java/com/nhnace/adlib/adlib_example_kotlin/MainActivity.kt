package com.nhnace.adlib.adlib_example_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nhnace.adlib.AdlibSdk
import com.nhnace.adlib.AdlibSdk.SdkInitListener
import com.nhnace.adlib.AdlibSdkConfig
import com.nhnace.adlib.error.AdlibError

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var initBtn: Button? = null
    private var bannerBtn: Button? = null
    private var interstitialBtn: Button? = null
    private var videoBtn: Button? = null
    private var nativeBtn: Button? = null
    private var versionText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initBtn = findViewById(R.id.init_btn)
        bannerBtn = findViewById(R.id.banner_btn)
        interstitialBtn = findViewById(R.id.interstitial_btn)
        videoBtn = findViewById(R.id.video_btn)
        nativeBtn = findViewById(R.id.native_btn)
        versionText = findViewById(R.id.adlib_version)

        initBtn!!.setOnClickListener(this)
        bannerBtn!!.setOnClickListener(this)
        interstitialBtn!!.setOnClickListener(this)
        videoBtn!!.setOnClickListener(this)
        nativeBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.init_btn) {
            // Adlib SDK 초기화
            AdlibSdk.getAdlibSdkSetting().isTestMode = true
            AdlibSdk.initialize(
                this@MainActivity,
                AdlibKey.APP_ID,
                object : SdkInitListener {
                    override fun onSdkInitialized(adlibSdkConfig: AdlibSdkConfig) {
                        // Adlib SDK 초기화 성공
                        initBtn?.setEnabled(false)
                        initBtn?.text = "Initialized Success"
                        versionText?.text = "Ver : ${adlibSdkConfig.adlibVersion}"
                    }

                    override fun onSdkInitFailed(adlibError: AdlibError?) {
                        // Adlib SDK 초기화 실패
                        initBtn!!.text = "Initialized Failed"
                    }
                })
        } else if (id == R.id.banner_btn) {
            val intent: Intent = Intent(this, AdlibBannerActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.interstitial_btn) {
            val intent: Intent = Intent(this, AdlibInterstitialActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.video_btn) {
            val intent: Intent = Intent(this, AdlibVideoActivity::class.java)
            startActivity(intent)
        } else {
            val intent: Intent = Intent(this, AdlibNativeActivity::class.java)
            startActivity(intent)
        }
    }
}