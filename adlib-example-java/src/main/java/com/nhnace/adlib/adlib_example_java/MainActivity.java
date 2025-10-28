package com.nhnace.adlib.adlib_example_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.nhnace.adlib.AdlibSdk;
import com.nhnace.adlib.AdlibSdkConfig;
import com.nhnace.adlib.error.AdlibError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button initBtn;
    private Button bannerBtn;
    private Button interstitialBtn;
    private Button videoBtn;
    private Button nativeBtn;
    private TextView versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initBtn = findViewById(R.id.init_btn);
        bannerBtn = findViewById(R.id.banner_btn);
        interstitialBtn = findViewById(R.id.interstitial_btn);
        videoBtn = findViewById(R.id.video_btn);
        nativeBtn = findViewById(R.id.native_btn);
        versionText = findViewById(R.id.adlib_version);

        initBtn.setOnClickListener(this);
        bannerBtn.setOnClickListener(this);
        interstitialBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);
        nativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.init_btn) {
            // Adlib SDK 초기화
            AdlibSdk.getAdlibSdkSetting().setTestMode(true);
            AdlibSdk.initialize(MainActivity.this, AdlibKey.APP_ID, new AdlibSdk.SdkInitListener() {
                @Override
                public void onSdkInitialized(AdlibSdkConfig adlibSdkConfig) {
                    // Adlib SDK 초기화 성공
                    initBtn.setEnabled(false);
                    initBtn.setText("Initialized Success");
                    versionText.setText("Ver : " + adlibSdkConfig.getAdlibVersion());
                }

                @Override
                public void onSdkInitFailed(AdlibError adlibError) {
                    // Adlib SDK 초기화 실패
                    initBtn.setText("Initialized Failed");
                }
            });
        } else if (id == R.id.banner_btn) {
            Intent intent = new Intent(this, AdlibBannerActivity.class);
            startActivity(intent);
        } else if (id == R.id.interstitial_btn) {
            Intent intent = new Intent(this, AdlibInterstitialActivity.class);
            startActivity(intent);
        } else if (id == R.id.video_btn) {
            Intent intent = new Intent(this, AdlibVideoActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AdlibNativeActivity.class);
            startActivity(intent);
        }

    }
}