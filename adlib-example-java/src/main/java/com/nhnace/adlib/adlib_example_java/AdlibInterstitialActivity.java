package com.nhnace.adlib.adlib_example_java;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.nhnace.adlib.AdlibInterstitialAd;
import com.nhnace.adlib.AdlibInterstitialAdListener;
import com.nhnace.adlib.error.AdlibError;
import com.nhnace.adlib.model.AdlibAd;

public class AdlibInterstitialActivity extends AppCompatActivity implements View.OnClickListener, AdlibInterstitialAdListener {
    private Button loadBtn;
    private Button showBtn;
    private Button interstitialIsLoadedBtn;
    private AdlibInterstitialAd adlibInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        loadBtn = findViewById(R.id.btn_interstitial_load);
        showBtn = findViewById(R.id.btn_interstitial_show);
        interstitialIsLoadedBtn = findViewById(R.id.btn_interstitial_is_loaded);

        loadBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        interstitialIsLoadedBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loadBtn) {
            adlibInterstitialAd = new AdlibInterstitialAd(AdlibKey.INTERSTITIAL_ID, this);
            adlibInterstitialAd.loadAd(this);
        } else if (v == showBtn) {
            if (adlibInterstitialAd != null && adlibInterstitialAd.isReady()) {
                adlibInterstitialAd.show();
            } else {
                Toast.makeText(this, "로드 먼저 진행 해주세요", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(
                    this,
                    "광고 로드 여부 : " + (adlibInterstitialAd != null && adlibInterstitialAd.isReady()),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void onAdShowFailed(AdlibError adlibError) {
        // 광고가 표시되지 못했을 때 호출됩니다.
    }

    @Override
    public void onAdDisplayed() {
        // 광고가 화면에 표시되었을 때 호출됩니다.
    }

    @Override
    public void onAdImpression() {
        // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
    }

    @Override
    public void onAdHidden() {
        // 광고가 닫혔을 때 호출됩니다.
    }

    @Override
    public void onAdClicked() {
        // 사용자가 광고를 클릭했을 때 호출됩니다.
    }

    @Override
    public void onAdClosed() {
        // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
        showBtn.setEnabled(false);
        loadBtn.setEnabled(true);
    }

    @Override
    public void onAdFailedToLoad(String s, AdlibError adlibError) {
        // 광고 요청에 실패했을 때 호출됩니다.
    }

    @Override
    public void onAdLoaded(AdlibAd adlibAd) {
        // 광고가 정상적으로 로드되었을 때 호출됩니다.
        showBtn.setEnabled(true);
        loadBtn.setEnabled(false);
        loadBtn.setText("Ad Loaded");
    }
}
