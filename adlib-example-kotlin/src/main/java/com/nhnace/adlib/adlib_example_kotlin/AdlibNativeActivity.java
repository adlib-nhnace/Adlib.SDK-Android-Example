package com.nhnace.adlib.adlib_example_kotlin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhnace.adlib.AdlibNativeAd;
import com.nhnace.adlib.AdlibNativeAdListener;
import com.nhnace.adlib.error.AdlibError;
import com.nhnace.adlib.nativead.NativeAdView;

public class AdlibNativeActivity extends AppCompatActivity implements View.OnClickListener {

    Button loadBtn1;
    Button loadBtn2;
    Button loadBtn3;
    FrameLayout adContainer1;
    FrameLayout adContainer2;
    FrameLayout adContainer3;
    AdlibNativeAd adlibNativeAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        loadBtn1 = findViewById(R.id.native_load_btn_1);
        loadBtn2 = findViewById(R.id.native_load_btn_2);
        loadBtn3 = findViewById(R.id.native_load_btn_3);

        adContainer1 = findViewById(R.id.native_container_1);
        adContainer2 = findViewById(R.id.native_container_2);
        adContainer3 = findViewById(R.id.native_container_3);

        loadBtn1.setOnClickListener(this);
        loadBtn2.setOnClickListener(this);
        loadBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loadBtn1) {
            adlibNativeAd = new AdlibNativeAd.Builder(AdlibNativeActivity.this, R.layout.native_view_layout_1, AdlibKey.NATIVE_ID)
                    .setTitleViewId(R.id.ad_title)
                    .setBodyViewId(R.id.ad_body)
                    .setAdvertiserViewId(R.id.ad_advertiser)
                    .setDescriptionViewId(R.id.ad_body)
                    .setCallToActionViewId(R.id.ad_btn)
                    .setIconViewId(R.id.ad_icon_content)
                    .setMediaContentViewId(R.id.ad_media_content).build();
        } else if (v == loadBtn2) {
            adlibNativeAd = new AdlibNativeAd.Builder(AdlibNativeActivity.this, R.layout.native_view_layout_2, AdlibKey.NATIVE_ID)
                    .setTitleViewId(R.id.ad_title).setIconViewId(R.id.ad_icon).setDescriptionViewId(R.id.ad_body).setCallToActionViewId(R.id.ad_call_to_action)
                    .setMediaContentViewId(R.id.ad_media_content).build();
        } else {
            adlibNativeAd = new AdlibNativeAd.Builder(AdlibNativeActivity.this, R.layout.native_view_layout_3, AdlibKey.NATIVE_ID)
                    .setTitleViewId(R.id.ad_title)
                    .setAdvertiserViewId(R.id.ad_advertiser)
                    .setDescriptionViewId(R.id.ad_body)
                    .setCallToActionViewId(R.id.ad_btn)
                    .setIconViewId(R.id.ad_icon_content).build();
        }

        adlibNativeAd.loadAd(new AdlibNativeAdListener() {
            @Override
            public void onAdImpression() {
                Log.d(AdlibKey.TAG, "onAdImpression");
            }

            @Override
            public void onAdClicked() {
                Log.d(AdlibKey.TAG, "onAdClicked");
            }

            @Override
            public void onAdClosed() {
                Log.d(AdlibKey.TAG, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(String s, AdlibError adlibError) {
                Log.d(AdlibKey.TAG, "onAdFailedToLoad");
            }

            @Override
            public void onAdLoaded(NativeAdView nativeAdView) {
                Log.d(AdlibKey.TAG, "onAdLoaded");
                if (v == loadBtn1) {
                    adContainer1.addView(nativeAdView);
                } else if (v == loadBtn2) {
                    adContainer2.addView(nativeAdView);
                } else {
                    adContainer3.addView(nativeAdView);
                }

            }
        });

    }
}
