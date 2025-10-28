package com.nhnace.adlib.adlib_example_kotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhnace.adlib.AdlibVideoAd;
import com.nhnace.adlib.AdlibVideoAdLoadListener;
import com.nhnace.adlib.AdlibVideoShowListener;
import com.nhnace.adlib.error.AdlibError;
import com.nhnace.adlib.video.RewardItem;
import com.nhnace.adlib.video.ServerSideVerification;

public class AdlibVideoActivity extends AppCompatActivity implements View.OnClickListener {
    Button loadBtn;
    Button showBtn;
    Button videoIsLoadedBtn;
    AdlibVideoAd adlibVideoAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        loadBtn = findViewById(R.id.video_load_btn);
        showBtn = findViewById(R.id.video_show_btn);
        videoIsLoadedBtn = findViewById(R.id.video_is_loaded_btn);
        loadBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        videoIsLoadedBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loadBtn) {
            adlibVideoAd = new AdlibVideoAd(AdlibKey.VIDEO_ID, this);
            adlibVideoAd.loadAd(new AdlibVideoAdLoadListener() {
                @Override
                public void onAdShowed() {
                    // This function will be called at the moment when the ad show is triggered.
                }

                @Override
                public void onAdFailedToLoad(String adUnitId, AdlibError adlibError) {
                    // Code to be executed when an ad request fails.
                }

                @Override
                public void onAdLoaded(String adUnitId) {
                    // Code to be executed when an ad finishes loading.
                    loadBtn.setEnabled(false);
                    showBtn.setEnabled(true);
                }
            });
        } else if (v == showBtn) {
            ServerSideVerification.Builder ssvBuilder = new ServerSideVerification.Builder();
            ssvBuilder.setCustomData("{custom_data : custom_value}"); // 서버에서 검증할 커스텀 데이터
            ssvBuilder.setUserId("user_id"); // 보상 지급 대상 사용자 ID
            adlibVideoAd.setServerSideVerification(ssvBuilder.build());
            adlibVideoAd.show(new AdlibVideoShowListener() {
                @Override
                public void onRewarded(RewardItem rewardItem) {
                    // Called when the ad has been viewed completely and the reward conditions are met. The reward information is provided via the parameter.
                }

                @Override
                public void onVideoStarted() {
                    // Called when the ad video playback starts.
                }

                @Override
                public void onAdCompleted() {
                    // Called when the ad playback reaches the end.
                }

                @Override
                public void onAdDismissedAd() {
                    // Called when the ad playback screen is closed.
                    loadBtn.setEnabled(true);
                    showBtn.setEnabled(false);
                }

                @Override
                public void showFailed(AdlibError adlibError) {
                    // Called when the ad cannot be played.
                }

                @Override
                public void onAdClicked() {
                    // Called when the ad playback screen is tapped.
                }
            });
        } else {
            Toast.makeText(this, "광고 로드 여부 : " + (adlibVideoAd != null && adlibVideoAd.isReady()), Toast.LENGTH_SHORT).show();
        }
    }
}
