package com.nhnace.adlib.adlib_example_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nhnace.adlib.AdlibInterstitialAd
import com.nhnace.adlib.AdlibInterstitialAdListener
import com.nhnace.adlib.error.AdlibError
import com.nhnace.adlib.model.AdlibAd

class AdlibInterstitialActivity : AppCompatActivity(), View.OnClickListener,
    AdlibInterstitialAdListener {
    var loadBtn: Button? = null
    var showBtn: Button? = null
    var interstitialIsLoadedBtn: Button? = null
    var adlibInterstitialAd: AdlibInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View 객체 할당 및 listener 설정
        setContentView(R.layout.activity_interstitial)
        loadBtn = findViewById(R.id.btn_interstitial_load)
        showBtn = findViewById(R.id.btn_interstitial_show)
        interstitialIsLoadedBtn = findViewById(R.id.btn_interstitial_is_loaded)
        loadBtn!!.setOnClickListener(this)
        showBtn!!.setOnClickListener(this)
        interstitialIsLoadedBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v === loadBtn) {
            // InterstitialAd 객체 생성 및 광고 로드
            adlibInterstitialAd = AdlibInterstitialAd(AdlibKey.INTERSTITIAL_ID, this)
            adlibInterstitialAd!!.loadAd(this)
        } else if (v === showBtn) {
            // InterstitialAd Show
            if (adlibInterstitialAd != null && adlibInterstitialAd!!.isReady()) {
                adlibInterstitialAd!!.show()
            } else {
                Toast.makeText(this, "로드 먼저 진행 해주세요", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                this,
                "광고 로드 여부 : " + (adlibInterstitialAd != null && adlibInterstitialAd!!.isReady()),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onAdShowFailed(adlibError: AdlibError?) {
        // 광고가 표시되지 못했을 때 호출됩니다.
    }

    override fun onAdDisplayed() {
        // 광고가 화면에 표시되었을 때 호출됩니다.
    }

    override fun onAdImpression() {
        // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
    }

    override fun onAdHidden() {
        // 광고가 닫혔을 때 호출됩니다.
    }

    override fun onAdClicked() {
        // 사용자가 광고를 클릭했을 때 호출됩니다.
    }

    override fun onAdClosed() {
        // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
        showBtn!!.setEnabled(false)
        loadBtn!!.setEnabled(true)
    }

    override fun onAdFailedToLoad(s: String?, adlibError: AdlibError?) {
        // 광고 요청에 실패했을 때 호출됩니다.
    }

    override fun onAdLoaded(adlibAd: AdlibAd?) {
        // 광고가 정상적으로 로드되었을 때 호출됩니다.
        showBtn!!.setEnabled(true)
        loadBtn!!.setEnabled(false)
        loadBtn!!.setText("Ad Loaded")
    }
}
