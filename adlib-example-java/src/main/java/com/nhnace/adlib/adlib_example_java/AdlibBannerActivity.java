package com.nhnace.adlib.adlib_example_java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhnace.adlib.AdlibAdSize;
import com.nhnace.adlib.AdlibAdView;
import com.nhnace.adlib.AdlibBannerAdListener;
import com.nhnace.adlib.error.AdlibError;
import com.nhnace.adlib.model.AdlibAd;

public class AdlibBannerActivity extends AppCompatActivity implements View.OnClickListener, AdlibBannerAdListener {
    Button loadBtn_320_50;
    Button loadBtn_320_100;
    Button loadBtn_300_250;

    FrameLayout adContainer_320_50;
    FrameLayout adContainer_320_100;
    FrameLayout adContainer_300_250;

    AdlibAdView adlibAdView_320_50;
    AdlibAdView adlibAdView_320_100;
    AdlibAdView adlibAdView_300_250;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        adContainer_320_50 = findViewById(R.id.ad_container_320_50);
        adContainer_320_100 = findViewById(R.id.ad_container_320_100);
        adContainer_300_250 = findViewById(R.id.ad_container_300_250);

        loadBtn_320_50 = findViewById(R.id.banner_load_50);
        loadBtn_320_100 = findViewById(R.id.banner_load_100);
        loadBtn_300_250 = findViewById(R.id.banner_load_250);

        loadBtn_320_50.setOnClickListener(this);
        loadBtn_320_100.setOnClickListener(this);
        loadBtn_300_250.setOnClickListener(this);

        adlibAdView_320_100 = findViewById(R.id.ad_view_320_100);
        adlibAdView_300_250 = findViewById(R.id.ad_view_300_250);
    }

    @Override
    public void onClick(View v) {
        if (v == loadBtn_320_50) {
            // 빈 ContainerView 에 AdlibAdView 를 객체로 생성 해서 붙이는 방식
            adlibAdView_320_50 = new AdlibAdView(AdlibKey.BANNER_ID, this);
            adlibAdView_320_50.setAdSize(AdlibAdSize.AD_SIZE_BANNER_50);
            adlibAdView_320_50.loadAd(this);
        } else if (v == loadBtn_320_100) {
            // Xml 로 정의 한 AdlibAdView 에다가 AdUnitId 셋해서 로드 하는 방식

            /*<com.nhnace.adlib.AdlibAdView
                android:id="@+id/ad_view_320_100"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:visibility="gone"/>*/

            adlibAdView_320_100.setAdUnitId(AdlibKey.BANNER_ID);
            adlibAdView_320_100.setAdSize(AdlibAdSize.AD_SIZE_BANNER_100);
            adlibAdView_320_100.loadAd(this);
        } else {
            // Xml 로 정의 한 AdlibAdView XML 로 모든 설정을 셋하고 로드만 하는 방식

            /*<com.nhnace.adlib.AdlibAdView
                android:id="@+id/ad_view_300_250"
                app:adUnitSize="half_banner"
                app:adUnitId="8f4b06b6132413225e3e9f5ca8d2e05d"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:visibility="gone"/>*/
            adlibAdView_300_250.loadAd(this);
        }
    }

    @Override
    public void onAdClicked() {
        // 사용자가 광고를 클릭했을 때 호출됩니다.
    }

    @Override
    public void onAdClosed() {
        // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
    }

    @Override
    public void onAdImpression() {
        // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
    }

    @Override
    public void onAdFailedToLoad(String s, AdlibError adlibError) {
        Log.d(AdlibKey.TAG, "onAdFailedToLoad : " + adlibError.getErrorMessage());
        // 광고 요청에 실패했을 때 호출됩니다.
    }

    @Override
    public void onAdLoaded(AdlibAd adlibAd) {
        // 광고가 로드되었을 때 호출됩니다.
        switch (adlibAd.getHeight()) {
            case 50: {
                adContainer_320_50.addView(adlibAdView_320_50);
                break;
            }
            case 100: {
                Log.d(AdlibKey.TAG, "onAdLoaded: show 320x100 banner");
                adlibAdView_320_100.setVisibility(View.VISIBLE);
                adContainer_320_100.removeAllViews();
                adContainer_320_100.addView(adlibAdView_320_100);
                break;
            }
            case 250: {
                adContainer_300_250.setVisibility(View.VISIBLE);
                adContainer_300_250.removeAllViews();
                adContainer_300_250.addView(adlibAdView_300_250);
                break;
            }
        }
    }
}
