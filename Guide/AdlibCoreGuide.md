# ADLIB 가이드 초안

## 목차
- [참고 사항](#참고-사항)
- [1. 소개](#1-소개)
- [2. 시작하기](#2-시작하기)
    - [2.1 시스템 요구 사항](#21-시스템-요구-사항)
    - [2.2 ADLIB 계정 생성](#22-adlib-계정-생성)
    - [2.3 인벤토리 등록](#23-인벤토리-등록)
    - [2.4 SDK 다운로드 및 설치 방법](#24-sdk-다운로드-및-설치-방법)
- [3. SDK 초기화](#3-sdk-초기화)
  - [3.1 Test 광고 보기](#31-test-광고-설정법)
    - [3.1.1 Testmode 설정 방법](#311-testmode-설정-방법)
    - [3.1.2 Testmode 확인 방법](#312-testmode-확인-방법)
- [4. 광고 유형별 구현 가이드](#4-광고-유형별-구현-가이드)
    - [4.1 배너 광고](#41-배너-광고)
      - [4.1.1 배너 광고](#411-광고-뷰-컨테이너-정의)
      - [4.1.2 광고 크기 설정](#412-광고-크기-설정)
      - [4.1.3 AD_UNIT_ID 설정](#413-ad_unit_id-설정)
      - [4.1.4 레이아웃에 Adview 추가](#414-레이아웃에-adview-추가)
      - [4.1.5 배너 광고 로드](#415-배너-광고-로드)
      - [4.1.6 광고 새로고침](#416-광고-새로고침)
      - [4.1.7 광고 리소스 해제](#417-광고-리소스-해제)
      - [4.1.8 광고 이벤트](#418-광고-이벤트)
    - [4.2 전면 광고](#42-전면-광고)
      - [4.2.1 전면광고 로드](#421-전면-광고-로드)
      - [4.2.2 전면광고 show](#422-전면-광고-show)
      - [4.2.3 전면광고 이벤트](#423-전면-광고-이벤트)
    - [4.3 비디오 광고](#43-비디오-광고)
      - [4.3.1 비디오 광고 로드](#431-비디오-광고-로드)
      - [4.3.2 비디오 광고 로드 이벤트](#432-비디오-광고-로드-이벤트)
      - [4.3.3 비디오 광고 show](#433-비디오-광고-show)
      - [4.3.4 비디오 광고 show 이벤트](#434-비디오-광고-show-이벤트)
      - [4.3.5 보상형 광고](#435-보상형-광고)
      - [4.3.6 ssv 콜백 검사](#436-서버-측-확인-ssv-콜백-검사)
    - [4.4 네이티브 광고](#44-네이티브-광고)
      - [4.4.1 네이티브 광고](#441-네이티브-광고-뷰-구성)
      - [4.4.2 네이티브 광고 로드 템플릿뷰 구성](#442-네이티브-광고-로드-template-view-이용)
      - [4.4.3 네이티브 광고 로드 커스텀뷰 구성](#443-네이티브-광고-로드-custom-view-구성)
      - [4.4.4 광고 리소스 해제](#444-광고-리소스-해제)
- [5. 문제 해결](#5-문제-해결)
  - [5.1 ErrorCode](#51-errorcode)
- [6. API 레퍼런스](#6-api-레퍼런스)
  - [6.1 AdlibSDK](#61-class-adlibsdk)
  - [6.2 SdkInitListener](#62-interface-adlibsdksdkinitlistener)
  - [6.3 AdlibSdkSetting](#63-class-adlibsdksetting)
  - [6.4 AdlibSdkConfig](#64-class-adlibsdkconfig)
  - [6.5 AdlibError](#65-class-adliberror)
  - [6.6 AdlibAdSize](#66-enum-adlibadsize)
  - [6.7 AdlibAdView](#67-class-adlibadview)
  - [6.8 AdlibBannerAdListener](#68-interface-adlibbanneradlistener)
  - [6.9 AdlibAd](#69-class-adlibad)
  - [6.10 AdlibInterstitialAd](#610-class-adlibinterstitialad)
  - [6.11 AdlibInterstitialAdListener](#611-interface-adlibinterstitialadlistener)
  - [6.12 AdlibVideoAd](#612-class-adlibvideoad)
  - [6.13 ServerSideVerification](#613-interface-serversideverification)
  - [6.14 ServerSideVerification.Builder](#614-serversideverificationbuilder)
  - [6.15 AdlibVideoAdLoadListener](#615-interface-adlibvideoadloadlistener)
  - [6.16 AdlibVideoShowListener](#616-interface-adlibvideoshowlistener)
  - [6.17 RewardItem](#617-class-rewarditem)
  - [6.18 AdlibNativeAd](#618-class-adlibnativead)
  - [6.19 AdlibNativeAd.Builder](#619-class-adlibnativeadbuilder)
  - [6.20 AdlibNativeAdListener](#620-interface-adlibnativeadlistener)
---

## [참고 사항]


❗ 본 연동 가이드는 최신 버전의(6.0.7.1 ver 이상) SDK 적용을 위한 가이드로 이하 버전(5.1.5 ver 이하)은 이전 [Github 페이지](https://github.com/nhn/adlib.android_media_app) 를 참조해 주시기 바랍니다.

❗ 6.0.2 ver부터는 미디에이션 기능을 지원하지 않습니다. 이에 현재 미디에이션 기능을 이용하고 계신 회원분들은 5.1.5 ver을 계속 이용하셔야 미디에이션 이용이 가능합니다.

❗ [매체 어드민(mkt.adlibr.com)](https://mkt.adlibr.com/) 에서 지원하고 있는 미디에이션 설정은 6.0 ver에는 적용되지 않으며, 5.1.5 ver 이하는 기존과 동일하게 미디에이션 설정이 적용됩니다.

❗ 다만, 추후 추가 업데이트 시 6.0 ver 미만 버전의 SDK 지원이 중단 될 수 있음으로 가급적 최신 SDK로의 업데이트를 부탁 드리겠습니다.

❗ 아래 가이드에 설명된 ADLIB 계정 생성 및 인벤토리 등록 등에 관한 세부 사항은 ADLIB [매체 어드민 매뉴얼](https://nhnent.dooray.com/share/pages/1LnntuhfSDGaY5sct-EJWg)을 참조해 주시기 바랍니다.

## 1. 소개
- 이 가이드는 앱 개발자가 광고 SDK(ADLIB-SDK)를 앱에 통합하는 데 필요한 개요와 단계별 지침을 제공합니다.

- ADLIB SDK는 다음의 네 가지 주요 광고 형식을 지원합니다.
    - [배너 광고 (Banner)](#41-배너-광고)
    - [전면 광고 (Interstitial)](#42-전면-광고)
    - [비디오 광고 (Video)](#43-비디오-광고)
    - [네이티브 광고 (Native)](#44-네이티브-광고)


## 2 시작하기
### 2.1 시스템 요구 사항
- 운영체제 : Android
- 최소 지원 SDK 버전 : API Level 19(Android 4.4, KitKat) 이상


### 2.2 ADLIB 계정 생성
- ADLIB 계정은 https://mkt.adlibr.com에서 생성이 가능 합니다.
- 개발 관련 문의는 https://www.adlibr.com/contactUs를 통해 가능합니다.

### 2.3 인벤토리 등록
- 회원 가입 후 인벤토리 등록(App 등록)을 진행 합니다.
- 인벤토리 등록이 완료되면 APP_ID와 AD_UNIT_ID가 발급 됩니다.
- 현재는 인벤토리 등록 시 1개의 APP_ID와 각 광고 형식(예. 배너 광고, 전면 광고) 별로 4개의 AD_UNIT_ID가 자동으로 발급 됩니다.
- 현재 어드민은 구 버전 SDK를 함께 지원하고 있어, 5.1.5 ver이하 버전에서 사용하는 Inventory ID(MID)도 병행 표기 됩니다.

#### 2.3.1 APP_ID
- APP_ID는 등록 된 App에 부여되는 ID로 24자의 숫자 및 문자 조합으로 이루어져 있습니다.
- APP_ID는 앱의 고유성을 보장하고, 광고 요청 및 SDK의 정상 작동을 위한 필수 요소입니다.
- APP_ID를 설정하는 방법은 [SDK 초기화 단계](#3-sdk-초기화)에서 자세히 설명합니다.


#### 2.3.2 AD_UNIT_ID
- AD_UNIT_ID는 등록 된 각 App의 광고 형식 단위로 부여되는 ID 입니다.
- AD_UNIT_ID는 SDK의 광고 로드 관련 API를 호출할 때 파라미터로 전달합니다.
- ⚠️주의 : AD_UNIT_ID는 앱과 광고 형식 별로 고유해야 합니다. 올바른 ID를 사용하지 않으면 광고가 표시되지 않거나 수익에 영향을 줄 수 있습니다.

### 2.4 SDK 다운로드 및 설치 방법
- #### ADLIB SDK는 Maven Central에 배포되어 있습니다.
- #### 프로젝트 수준 build.gradle 파일의 repositories 섹션에 mavenCentral()을 추가해 주세요.
   ```groovy
    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }
   ```
- ```build.gradle``` 파일에 ADLIB SDK의 종속 항목을 추가해 주세요.
    - Kotlin
        ```kotlin
        dependencies {
            implementation("com.nhnace:adlib:6.0.7.1")
        }
        ```
    - Groovy
        ```groovy
        dependencies {
                implementation("com.nhnace:adlib:6.0.7.1")
        }
        ```

## 3. SDK 초기화

-  SDK 초기화는 광고 기능을 활성화하고, 앱과 SDK 간 통신을 설정하는 필수 단계입니다.
- `AdlibSdk.getInstance().initialize()` 함수를 이용해 초기화를 진행합니다.
    - [API 레퍼런스 문서 링크](#61-class-adlibsdk)

- SDK 초기화는 앱의 `Application` 클래스의 `onCreate()` 또는 최초 실행되는 `Activity`의 `onCreate()`에서 수행하는 것이 권장됩니다.
    - `Application` 클래스에서 초기화하면 모든 `Activity`에서 SDK 사용이 가능해집니다.
    - 초기화는 앱 프로세스 내에서 단 한 번만 수행해야 합니다.

- 초기화 결과는 `AdlibSdk.SdkInitListener()`를 통해 콜백 형태로 받을 수 있습니다.
    - [AdlibSdk.SdkInitListener](#62-interface-adlibsdksdkinitlistener)

- APP_ID 는 다음 두 가지 방식 중 하나로 설정할 수 있습니다.
    - **AndroidManifest.xml에 등록**
        - `AndroidManifest.xml`에 APP_ID를 등록하면 SDK가 자동으로 해당 값을 참조합니다.
        - Manifest 예시
          ```xml
           <application>
               <!-- ... (중략) ... -->
               <meta-data
                   android:name="com.nhnace.adlib.ADLIB_APP_ID"
                   android:value="${센터에서 발급 받은 APP_ID}" />
                   <!-- https://mkt.adlibr.com 에서 발급 받은 APP_ID 기입 -->
               <activity>
               <!-- ... (중략) ... -->
               </activity>
           </application>
          ```
    - **SDK 초기화 시 직접 전달**
      - SDK 초기화 함수 호출 시 APP_ID를 직접 전달할 수 있습니다.
        ```java
        // AppID 포함 하여 이니셜라이즈
        String APP_ID = "${센터에서 발급 받은 APP_ID}";
        AdlibSdk.getInstance().initialize(Context, APP_ID, AdlibSdk.SdkInitListener);
        ```
    
### 3.1 Test 광고 설정법
- 테스트 모드로 SDK를 구동할 수 있습니다.
- 테스트 모드에서는 SDK 전반의 동작을 확인할 수 있으며, 광고 형식별 테스트 광고 시청이 가능합니다.
- 테스트 모드에서는 매체가 설정한 AD_UNIT_ID와 관계없이 테스트 광고만 송출됩니다.
----
#### 3.1.1 TestMode 설정 방법
- ADLIB SDK 초기화 전에 `AdlibSdkSetting`을 이용해 설정해야 합니다.
- SDK가 초기화된 이후에는 테스트 모드 설정을 변경할 수 없습니다.
- 별도로 설정하지 않으면 기본적으로 테스트 모드는 비활성화되어 있습니다.
- 예제 코드
    - Kotlin
      ```kotlin
      val adlibSdkSetting = AdlibSdk.getAdlibSdkSetting()
      adlibSdkSetting.isTestMode = true
      ```

    - Java
      ```java
      AdlibSdkSetting adlibSdkSetting = AdlibSdk.getAdlibSdkSetting();
      adlibSdkSetting.isTestMode = true;
      ```

#### 3.1.2 TestMode 확인 방법
- 현재 SDK가 어떤 모드로 동작 중인지 확인할 수 있습니다.
- 예제 코드
    - Kotlin
      ```kotlin
      val adlibSdkSetting = AdlibSdk.getAdlibSdkSetting()
      Log.d(TAG, "adlibSdkSetting.isTestMode : ${adlibSdkSetting.isTestMode()}")
      ```

    - Java
      ```java
      AdlibSdkSetting adlibSdkSetting = AdlibSdk.getAdlibSdkSetting();
      Log.d(TAG, "adlibSdkSetting.isTestMode : " + adlibSdkSetting.isTestMode());
      ```
## 4. 광고 유형별 구현 가이드

### 4.1 배너 광고

배너 광고는 앱 UI의 일부 영역을 차지하는 직사각형 형태의 광고로, 사용자가 앱을 이용하는 동안 눈에 잘 띄는 위치에 고정되어 표시됩니다. 일반적으로 화면 상단 또는 하단에 배치되며, 앱 콘텐츠를 방해하지 않으면서도 지속적인 노출이 가능하다는 장점이 있습니다.

이 광고는 ADLIB에서 제공하는 새로고침 기능을 설정해 두면 일정 시간 간격으로 자동으로 새로운 광고로 갱신될 수 있습니다. 

이를 통해 사용자가 같은 화면에 머무르고 있어도 다양한 광고가 순차적으로 노출됩니다.

또한, 가장 구현이 간단한 광고 형식 중 하나로, 광고 수익화를 처음 도입하는 앱에서도 쉽게 적용할 수 있습니다.

배너 광고는 다음과 같은 상황에서 유용하게 활용됩니다:
- 앱 주요 화면의 상단 또는 하단에 상시 노출
- 사용자 경험을 크게 방해하지 않으면서 광고 노출
- 주기적인 새 광고 노출로 수익 극대화 가능

---

<br>

#### 4.1.1 광고 뷰 컨테이너 정의
- 배너 광고의 컨테이너 역할을 할 뷰를 레이아웃 XML에 추가합니다.
- XML 구성
  ```xml
  <!-- width, height는 광고 특성에 맞게 조정합니다. -->
  <FrameLayout
      android:id="@+id/ad_container"
      android:layout_width="320dp"
      android:layout_height="50dp"/>
        ```

#### 4.1.2 광고 크기 설정
- `AdlibAdSize`를 지정된 사이즈로 설정합니다.
- `AdlibAdSize` 종류:
   - `AdlibAdSize.AD_SIZE_BANNER_50`
       - 너비 320dp, 높이 50dp의 배너 광고
   - `AdlibAdSize.AD_SIZE_BANNER_100`
       - 너비 320dp, 높이 100dp의 배너 광고
   - `AdlibAdSize.AD_SIZE_HALF_BANNER`
       - 너비 300dp, 높이 250dp의 하프 배너 광고

- `AdlibAdSize` 설정 방법 (Code)
  ```kotlin
  adlibAdView.setAdSize(AdlibAdSize.AD_SIZE_BANNER_50)
  ```

- `AdlibAdSize` 설정 방법 (XML)
  - `app:adUnitSize="banner50"` → 일반 배너 광고 (320 * 50)
  - `app:adUnitSize="banner100"` → 배너 광고 (320 * 100)
  - `app:adUnitSize="half_banner"` → 하프 배너 광고 (300 * 250)
  - 예제 코드:
    ```xml
    <com.nhnace.adlib.AdlibAdView
        app:adUnitSize="banner50" />
        <!-- 320x50 광고는 "banner50", 320x100 광고는 "banner100", 300x250 광고는 "half_banner" 값을 입력 -->
        <!-- adUnitSize 값이 누락되면 기본값은 "banner50"로 동작합니다. -->
    ```
#### 4.1.3 AD_UNIT_ID 설정
  - XML인 경우
    - 아래 예시 처럼 app:adUnitId 속성에 발급받은 AD_UNIT_ID를 설정해 주세요  
      ```xml
      <com.nhnace.adlib.AdlibAdView
          app:adUnitId="${발급받은 AD_UNIT_ID}"
      ```
  - 소스코드 인 경우
    - AdlibAdView 객체를 생성할때 생성자 파라미터로 발급받은 AD_UNIT_ID를 설정해 주세요
      ```java
      new AdlibAdView(AD_UNIT_ID, Context);
      ``` 

#### 4.1.4 레이아웃에 AdView 추가
- **소스코드로 추가** 
  - Kotlin
    ```kotlin
      //  발급받은 AD_UNIT_ID
      val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
      // ViewGroup을 상속받은 다양한 Layout 사용 가능
  
      val adContainer = findViewById(R.id.ad_container)
      adlibAdView = AdlibAdView(AD_UNIT_ID, this)
      adContainer.removeAllViews()
      adContainer.addView(adlibAdView)
    ```

  - Java
    ```java
    // 발급받은 AD_UNIT_ID
    String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
    // ViewGroup을 상속받은 다양한 Layout 사용 가능
    ViewGroup adContainer = findViewById(R.id.ad_container); // 광고 View가 표시될 컨테이너 View의 Resource ID
   
    AdlibAdView adlibAdView = new AdlibAdView(AD_UNIT_ID, this);
    adContainer.removeAllViews();
    adContainer.addView(adlibAdView);
    ```
 - **XML로 추가**
    - XML 로 AdlibAdView 구성하기
      ```xml
      <com.nhnace.adlib.AdlibAdView
           android:id="@+id/adlib_view"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           app:adUnitId="${발급받은 AD_UNIT_ID}"
           app:adUnitSize="banner_50" />
           <!-- 320 * 50 광고는 "banner50",320 * 100 광고는 "banner100", 300x250 광고는 "half_banner" 값을 입력 -->
           <!-- adUnitSize 값이 누락되면 기본값은 "banner50"로 동작합니다. -->


      ```

    - 코드로 XML 불러오기
       - Kotlin
           ```kotlin
           // 발급받은 AD_UNIT_ID
           val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
           // ViewGroup을 상속받은 다양한 Layout 사용 가능
           val adContainer = findViewById(R.id.ad_container) // 광고 View가 표시될 컨테이너 View의 Resource ID
           adlibAdView = AdlibAdView(AD_UNIT_ID, this)
           adContainer.removeAllViews()
           adContainer.addView(adlibAdView)
           ```
 
       - Java
           ```java
           // 발급받은 AD_UNIT_ID
           String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
           // ViewGroup을 상속받은 다양한 Layout 사용 가능
           ViewGroup adContainer = findViewById(R.id.ad_container); // 광고 View가 표시될 컨테이너 View의 Resource ID
 
           AdlibAdView adlibAdView;
           adlibAdView = findViewById(R.id.adlib_view);
           adContainer.removeAllViews();
           adContainer.addView(adlibAdView);
           ```

#### 4.1.5 배너 광고 로드
- `AdlibAdView.loadAd()` 함수를 이용해 광고를 로드합니다.
    - Kotlin
        ```kotlin
        val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
        val adView = AdlibAdView(AD_UNIT_ID, getContext())
        adView.loadAd(AdlibBannerAdListener)
        ``` 

    - Java
        ```java
        String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
        AdlibAdView adView = new AdlibAdView(AD_UNIT_ID, getContext());
        adView.loadAd(AdlibBannerAdListener);
        ``` 

#### 4.1.6 광고 새로고침
- https://mkt.adlibr.com에서 각 광고 형식의 인벤토리 스케줄 설정을 변경할 수 있습니다.
- SDK는 스케줄 설정에 적용된 시간마다 자동으로 광고를 새로고침하여 로드합니다. (Default : 20초, Max: 120초)

#### 4.1.7 광고 리소스 해제
- 배너 광고를 더 이상 노출하지 않을 경우, 리소스를 해제하기 위해 `destroy()`를 호출해 뷰 계층 구조에서 제거해 주세요.
- 예제 코드
  ```java
  adlibAdView.destroy();
   ```
  
#### 4.1.8 광고 이벤트
- 광고 상태에 따른 이벤트를 콜백으로 받을 수 있습니다. [문서 바로가기](#68-interface-adlibbanneradlistener)
  - Kotlin
      ```kotlin
      class MainActivity : Activity {
      
          override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setContentView(R.layout.main)
              val rootView = findViewById(R.id.ad_container)
              val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
              val adView = AdlibAdView(AD_UNIT_ID, getContext())
              rootView.addView(adView)
              val listener = object : AdlibBannerAdListener {
                  override fun onAdLoaded(ad: AdlibAd) {
                      // 광고가 로드되었을 때 호출됩니다.
                  }

                  override fun onAdClicked() {
                      // 사용자가 광고를 클릭했을 때 호출됩니다.
                  }

                  override fun onAdClosed() {
                      // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
                  }

                  override fun onAdFailedToLoad(adUnitId: String?, adError: AdlibError?) {
                      // 광고 요청에 실패했을 때 호출됩니다.
                  }

                  override fun onAdImpression() {
                      // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
                  }    
              }   
              adView.loadAd(listener)
          }
      }
      ```
  - Java
      ```java
      public class MainActivity extends Activity {
      
          @Override
          protected void onCreate(@Nullable Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.main);
              ViewGroup rootView = findViewById(R.id.ad_container);
              String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
              AdlibAdView adView = AdlibAdView(AD_UNIT_ID, getContext());
              rootView.addView(adView);
              AdlibBannerAdListener listener = new AdlibBannerAdListener() {

                  @Override
                  public void onAdClicked() {
                      // 사용자가 광고를 클릭했을 때 호출됩니다.
                  }

                  @Override
                  public void onAdClosed() {
                      // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
                  }

                  @Override
                  public void onAdFailedToLoad(String adUnitId, AdlibError adError) {
                      // 광고 요청에 실패했을 때 호출됩니다.
                  }

                  @Override
                  public void onAdImpression() {
                      // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
                  }

                  @Override
                  public void onAdLoaded(AdlibAd ad) {
                      // 광고가 로드되었을 때 호출됩니다.
                  }
              }; 
              adView.loadAd(listener);
          }
      }
      ```

### 4.2 전면 광고

전면 광고는 앱의 전체 화면을 차지하는 몰입형 광고 포맷으로, 사용자에게 높은 주목도를 제공합니다. 
일반적으로 앱의 흐름이 잠시 멈추는 시점 예를 들어 화면 전환 시, 게임에서 레벨 완료 후, 또는 특정 기능 종료 직후 등 사용자 경험을 방해하지 않는 자연스러운 타이밍에 노출됩니다.

전면 광고는 클릭률(CTR)과 노출당 수익(eCPM)이 비교적 높기 때문에, 수익화에 매우 효과적인 광고 형식입니다. 단, 화면 전체를 가리기 때문에 사용자 경험을 해치지 않도록 적절한 타이밍에 노출하는 것이 중요합니다.

- 앱 화면 전체를 가리는 광고 형식
- 사용 흐름이 잠시 멈출 때 자연스럽게 노출
- 광고 단가 및 주목도 높음
- 사용자 경험을 고려해 적절한 타이밍에서만 표시 필요

---
<br>


#### 4.2.1 전면 광고 로드
  전면 광고를 로드하려면 `InterstitialAd` 클래스의 `loadAd()` 함수를 호출해야 합니다. 이때 [AD_UNIT_ID](#232-ad_unit_id) 값과 `InterstitialAdListener`를 함께 전달하여 광고 로드 및 관련 이벤트를 수신할 수 있습니다.
- Java
    ```java
    String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"; // 전면 광고가 설정된 adUnitId
    AdlibInterstitialAd adlibInterstitialAd = new AdlibInterstitialAd(AD_UNIT_ID, this);
    adlibInterstitialAd.loadAd(InterstitialAdListener);
    ```

- Kotlin
    ```kotlin
    val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}" // 전면 광고가 설정된 adUnitId
    val adlibInterstitial = AdlibInterstitialAd(AD_UNIT_ID, this)
    adlibInterstitial.loadAd(InterstitialAdListener)
    ```

#### 4.2.2 전면 광고 Show
 - [광고 로드](#전면-광고-로드)가 완료된 후, `onAdLoaded()` 콜백에서 광고가 성공적으로 로드되었을 때 `show()` 함수를 호출해 광고를 표시해야 합니다.
 - `interstitialAd.isReady()` 함수를 호출하여 `true`를 반환하면 광고가 로드된 상태이며, 이때 `show()` 호출이 가능합니다.

 <br>

 - 전면 광고 이벤트 리스너의 `onAdLoaded()` 함수에서 로드 완료 콜백을 받은 뒤 `show()`를 호출합니다.

     - Kotlin
         ```kotlin
         AdlibInterstitialAdListener {
            override fun onAdLoaded(ad: AdlibAd) {
                adlibInterstitial.show()
            }
         }
         ```

     - Java
         ```java
         new AdlibInterstitialAdListener {
             @Override
             public void onAdLoaded(AdlibAd ad) {
                 adlibInterstitial.show();
             }
         };
         ```

 - 위 콜백에서 `show()`를 호출하지 않았다면, 별도로 `isReady()` 함수를 이용해 광고가 준비된 시점에 `show()`를 호출할 수 있습니다.

     - Kotlin
         ```kotlin
         if (adlibInterstitial.isReady()) {
             adlibInterstitial.show()
         }
         ```

     - Java
         ```java
         if (adlibInterstitial.isReady()) {
             adlibInterstitial.show();
         }
         ```

#### 4.2.3 전면 광고 이벤트
 - Kotlin
     ```kotlin
     class MainActivity : Activity {
         private lateinit var interstitialAd: AdlibInterstitialAd

         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             setContentView(R.layout.main)
             val rootView = findViewById(R.id.ad_container)
             val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
             val listener = object : AdlibInterstitialAdListener {
                 override fun onAdClicked() {
                     // 사용자가 광고를 클릭했을 때 호출됩니다.
                 }

                 override fun onAdClosed() {
                     // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
                 }

                 override fun onAdFailedToLoad(adUnitId: String?, adError: AdlibError?) {
                     // 광고 요청에 실패했을 때 호출됩니다.
                 }

                 override fun onAdLoaded(ad: AdlibAd) {
                     // 광고가 정상적으로 로드되었을 때 호출됩니다.
                 }

                 override fun onAdShowFailed(error: AdlibError?) {
                     // 광고가 표시되지 못했을 때 호출됩니다.
                 }

                 override fun onAdDisplayed() {
                     // 광고가 화면에 표시되었을 때 호출됩니다.
                 }

                 override fun onAdImpression() {
                     // 광고의 임프레션이 기록되었을 때 호출됩니다.
                 }

                 override fun onAdHidden() {
                     // 광고가 닫혔을 때 호출됩니다.
                 }
             };
             val adlibInterstitial = AdlibInterstitialAd(AD_UNIT_ID, this)
             adlibInterstitial.loadAd(AD_UNIT_ID, listener)
         }
     }
     ```

 - Java
     ```java
     public class MainActivity extends Activity {
         private AdlibInterstitialAd interstitialAd;

         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.main);

             View rootView = findViewById(R.id.ad_container);
             String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";

             AdlibInterstitialAdListener listener = new AdlibInterstitialAdListener() {
                 @Override
                 public void onAdClicked() {
                     // 사용자가 광고를 클릭했을 때 호출됩니다.
                 }

                 @Override
                 public void onAdClosed() {
                     // 광고를 닫고 앱으로 돌아가기 직전에 호출됩니다.
                 }

                 @Override
                 public void onAdFailedToLoad(String adUnitId, AdlibError adError) {
                     // 광고 요청에 실패했을 때 호출됩니다.
                 }

                 @Override
                 public void onAdLoaded(AdlibAd ad) {
                     // 광고가 정상적으로 로드되었을 때 호출됩니다.
                 }

                 @Override
                 public void onAdShowFailed(AdlibError error) {
                     // 광고가 표시되지 못했을 때 호출됩니다.
                 }

                 @Override
                 public void onAdDisplayed() {
                     // 광고가 화면에 표시되었을 때 호출됩니다.
                 }

                 @Override
                 public void onAdImpression() {
                     // // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
                 }

                 @Override
                 public void onAdHidden() {
                     // 광고가 닫혔을 때 호출됩니다.
                 }
             };
             AdlibInterstitialAd adlibInterstitialAd = new AdlibInterstitialAd(AD_UNIT_ID, this);
             InterstitialAd.loadAd(AD_UNIT_ID, listener);
         }
     }
     ```

---
### 4.3 비디오 광고
비디오 광고는 앱 전체 화면을 덮는 전면 광고의 일종으로, 영상과 소리를 함께 활용해 강한 몰입감과 주목도를 제공하는 광고 형식입니다. 사용자는 일정 시간 동안 광고를 시청해야 하며, 광고 종료 후 앱 콘텐츠로 돌아올 수 있습니다.

특히 보상형 비디오 광고(Rewarded Video Ad)는 사용자가 광고를 끝까지 시청하면 인앱 보상(예: 게임 아이템, 포인트 등)을 받을 수 있어, 광고를 능동적으로 시청하도록 유도할 수 있습니다.

이러한 특성 덕분에 비디오 광고는 높은 완주율(View-Through Rate)과 수익률(eCPM)을 기록하는 경우가 많으며, 사용자 만족도와 수익 사이의 균형을 잘 맞추면 효과적인 수익화 수단이 될 수 있습니다.
* 전체 화면을 사용하는 영상 기반 광고

* 음성과 애니메이션으로 사용자 몰입 유도

* 보상형 광고로 활용 시 사용자 참여 유도 가능

* 재생 타이밍과 종료 후 UX에 대한 신중한 설계 필요
---
<br>

#### 4.3.1 비디오 광고 로드
- 비디오 광고를 로드 하려면 AdlibVideoAd 클래스의 함수인 loadAd()를 호출 하여 로드를 합니다. 로드할때 [AD_UNIT_ID](#232-ad_unit_id) 와 VideoAdLoadListener 를 전달하여 로드를 진행 하고 관련된 이벤트 들을 수신 합니다.
- Java
    ```java
    String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
    AdlibVideoAd videoAd = new AdlibVideoAd(AD_UNIT_ID, this);
    videoAd.loadAd(AdlibVideoAdLoadListener);
    
    ```
- Kotlin
    ```kotlin
    val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
    val videoAd = AdlibVideoAd(AD_UNIT_ID, this)
    videoAd.loadAd(AdlibVideoAdLoadListener)
        ```
#### 4.3.2 비디오 광고 로드 이벤트
  - Video 광고 이벤트를 처리 합니다.
  - Kotlin
      ```kotlin
      val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
      val videoAd = AdlibVideoAd(AD_UNIT_ID, this)
      videoAd.loadAd(object : AdlibVideoAdLoadListener {
           override fun onAdShowed() {
               // This function will be called at the moment when the ad show is triggered.
           }

           override fun onAdFailedToLoad(adUnitId: String?, adError: AdlibError?) {
               // Code to be executed when an ad request fails.
           }

           override fun onAdLoaded(ad: String) {
               // Code to be executed when an ad finishes loading.

           }
       })
      ```
  - Java
      ```java
      val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
      val videoAd = AdlibVideoAd(AD_UNIT_ID, this);
      videoAd.loadAd(new AdlibVideoAdLoadListener() {
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
          }
      });
      ```

#### 4.3.3 비디오 광고 Show
- [광고 로드](#431-비디오-광고-로드) 를 진행 한뒤 onAdLoaded() 콜백으로 Loaded 가 완료된 시점에 show() 함수를 호출 해주어야 합니다.
<br>
- 비디오 광고 이벤트 리스너 onAdLoaded 함수 에서 로드 완료 콜백을 받은 후 show 호출 합니다.
    - Kotlin
      ```kotlin
      AdlibVideoAdLoadListener {
         override fun onAdLoaded(ad: AdlibAd) {
             videoAd.show()
         }
      }
      ```
    - Java
      ```java
      new AdlibVideoAdLoadListener {
         @Override
         public void onAdLoaded(AdlibAd ad) {
            videoAd.show();
         };
      }
      ```
- 위 함수에서 show 하지 않느다면 isReady() 함수를 이용하여 로드가 완료된 시점에 show 호출 합니다.
    - Kotlin
      ```kotlin
      if (videoAd.isReady()) {
          videoAd.show(AdlibVideoShowListener)
      }
      ```
    - Java
      ```java
      if (videoAd.isReady()) {
          videoAd.show(AdlibVideoShowListener);
      }
      ```

#### 4.3.4 비디오 광고 Show 이벤트
- Kotlin
    ```kotlin
    if (videoAd.isReady()) {
        videoAd.show(object: AdlibVideoShowListener {
            override fun onRewarded(rewardItem: RewardItem?) {
                // Called when the ad has been viewed completely and the reward conditions are met. The reward information is provided via the parameter.
            }       
            override fun onVideoStarted() {
                // Called when the ad video playback starts.
            }       
            override fun onAdCompleted() {
                // Called when the ad playback reaches the end.
            }       
            override fun onAdDismissedAd() {
                // Called when the ad playback screen is closed.
            }       
            override fun onAdClicked() {
                // Called when the ad playback screen is tapped.
            }       
            override fun showFailed(adError: AdlibError?) {
                // Called when the ad cannot be played.
            }
        })
    }
    ```
- Java
    ```java
    if (videoAd.isReady()) {
        videoAd.show(new AdlibVideoShowListener() {
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
    }
    ```
#### 4.3.5 보상형 광고
  보상형 광고는 광고를 끝까지 시청한 보상으로 인앱 보상을 제공 받는 형식의 광고입니다.
  - 보상형 광고는 https://mkt.adlibr.com에서 비디오 광고 설정에서 Reward 옵션을 세팅 해야 합니다.
  - 보상 이벤트 발생 알림 방식
    - Server Side Callback : ADLIB 서버가 매체가 등록 해놓은 보상 콜백 URL로 보상 지급 시점을 알려주는 방식
    - Client Side Callback : ADLIB SDK에서 Callback 함수를 통해 보상 지급 시점을 알려주는 방식
  - 보상 이벤트 발생 알림 콜백
    - Kotlin
       ```kotlin
         videoAd.show(object: AdlibVideoShowListener() {
            override fun onRewarded(rewardItem: RewardItem?) {
               // 콜백 함수에서 보상 지급 관련 코드를 작성하면 됩니다
           }   
         })
       ```
    - Java
       ```java
        videoAd.show(new AdlibVideoShowListener() {
           @Override
           public void onRewarded(RewardItem rewardItem) {
            // 콜백 함수에서 보상 지급 관련 코드를 작성하면 됩니다
           }
        });
       ```
#### 4.3.6 SSV (Server-side verification) 설정
서버 측 확인 콜백에서 추가 데이터가 필요한 앱은 보상형 광고의 맞춤 데이터 기능을 사용 해야 합니다. 보상형 광고 객체에 설정된 모든 문자열 값은 SSV 콜백의 custom_data 쿼리 매개변수에 전달됩니다. 맞춤 데이터 값이 설정되지 않은 경우 custom_data 쿼리 매개변수 값은 SSV 콜백에 표시되지 않습니다.
  - Kotlin
      ```kotlin
      val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
      val videoAd = AdlibVideoAd(AD_UNIT_ID, this)
      videoAd.loadAd(AdlibVideoAdLoadListener() {
          @Override
          public void onAdLoaded(String ad) {
              val builder = ServerSideVerification.Builder()
              builder.setUserId("Reward  식별 ID")
              builder.setCustomData("Reward 시 추가로 전달 하고 싶은 값")
              videoAd.setServerSideVerification(builder.build())
              videoAd.show(object : AdlibVideoShowListener {})
          }
      });
      ```
  - Java
      ```java
      String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
      AdlibVideoAd videoAd = new AdlibVideoAd(AD_UNIT_ID, this);

      videoAd.loadAd(new AdlibVideoAdLoadListener() {
          @Override
          public void onAdLoaded(String ad) {
              ServerSideVerification.Builder builder = new ServerSideVerification.Builder();
              builder.setUserId("Reward  식별 ID");
              builder.setCustomData("Reward 시 추가로 전달 하고 싶은 값");
              videoAd.setServerSideVerification(builder.build());
              videoAd.show(new AdlibVideoShowListener() {});
          }
      });
      ``` 
### 4.4 네이티브 광고
네이티브 광고는 앱의 콘텐츠와 자연스럽게 어우러지도록 설계된 광고 형식입니다. 일반적인 배너나 전면 광고와 달리, 네이티브 광고는 앱이 원래 가지고 있던 UI 스타일과 동일한 레이아웃 구성요소(View) 를 사용하여 노출되므로, 사용자는 광고와 일반 콘텐츠의 경계를 크게 느끼지 않게 됩니다.

이러한 특성 덕분에 사용자 경험을 해치지 않으면서도, 높은 클릭률(CTR)과 몰입도를 유도할 수 있으며, 특히 콘텐츠 중심의 앱(예: 뉴스, 커뮤니티, 쇼핑 등)에서 효과적으로 활용됩니다.

개발자는 광고 콘텐츠를 표현할 레이아웃(XML)을 자유롭게 구성할 수 있으며, 텍스트뷰, 이미지뷰, 버튼 등으로 이루어진 광고 전용 ViewGroup을 통해 광고를 표현합니다.
* 앱의 UI와 자연스럽게 어우러지는 광고 형태

* 광고 콘텐츠를 커스터마이징 가능 (텍스트, 이미지, 버튼 등)

* UI 일관성을 해치지 않으면서 광고 수익화 가능

* 앱 디자인을 고려한 레이아웃 설계 필요

---
<br>

#### 4.4.1 네이티브 광고 뷰 구성

- #### 4.4.1.1 Template View 구성
    - [https://mkt.adlibr.com](https://mkt.adlibr.com) 페이지의 네이티브 광고 설정에서 **Template** 형식으로 설정합니다.
    - 원하는 Template Type을 선택하여 설정합니다.
    - 아래 이미지와 같이 설정합니다.  
      ![image](https://github.nhnent.com/storage/user/3618/files/c917d4d2-f24f-48bd-b23a-99a75a1859d9)

- #### 4.4.1.2 Custom View XML 구성
    - [https://mkt.adlibr.com](https://mkt.adlibr.com) 페이지의 네이티브 광고 설정에서 **Custom** 형식으로 설정합니다.
    - 아래 이미지와 같이 설정합니다.  
      ![image](https://github.nhnent.com/storage/user/3618/files/a76cfe32-fa5b-4a48-a7e9-749544e18377)

    - `res/layout/native_view_layout.xml` 파일을 작성해야 하며, `R.layout.native_view_layout` 형태로 참조가 가능해야 합니다.

    - XML 예제:
        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <com.nhnace.adlib.nativead.NativeAdView xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="20dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/ad_title"
                    android:layout_width="300dp"
                    android:layout_height="wrap_content" />

                <com.nhnace.adlib.NativeMediaContentView
                    android:id="@+id/ad_media_content"
                    android:layout_width="300dp"
                    android:layout_height="200dp"
                    app:mediaType="image" />

                <Button
                    android:id="@+id/ad_btn"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="웹으로 이동" />
            </LinearLayout>
        </com.nhnace.adlib.nativead.NativeAdView>
        ```

- #### 4.4.1.3 AdlibNativeAd Build 및 View 바인딩
  `AdlibNativeAd.Builder()`를 사용하여 `AdlibNativeAd` 객체를 생성한 후, XML로 구성한 뷰 정보를 설정합니다.

    - **AdlibNativeAd.Builder 객체 생성**
        - Kotlin
            ```kotlin
            val adlibNativeBuilder = AdlibNativeAd.Builder()
            ```
        - Java
            ```java
            AdlibNativeAd.Builder adlibNativeBuilder = new AdlibNativeAd.Builder();
            ```

    - **View 구성 요소 ID 설정**
        - 구성한 View의 `resource ID`만 설정해주면 되며, 사용하지 않는 View는 생략 가능합니다.
        - 설정 가능한 항목 예시 (ID는 매체가 설정한 값으로 변경 필요):

            - `setTitleViewId(R.id.title_id)` – 제목 TextView ID
              - [android.view.View](https://developer.android.com/reference/android/widget/TextView) ID 로 setting 되어야합니다.
            - `setAdvertiserViewId(R.id.advertiser_id)` – 광고주 TextView ID
              - [android.view.View](https://developer.android.com/reference/android/widget/TextView) ID 로 setting 되어야합니다.
            - `setBodyViewId(R.id.body_id)` – 본문 TextView ID
              - [android.view.View](https://developer.android.com/reference/android/widget/TextView) ID 로 setting 되어야합니다.
            - `setDescriptionViewId(R.id.description_id)` – 설명 TextView ID
              - [android.view.View](https://developer.android.com/reference/android/widget/TextView) ID 로 setting 되어야합니다.
            - `setCallToActionViewId(R.id.call_to_action_id)` – 액션 Button ID
              - [android.widget.Button](https://developer.android.com/reference/android/widget/Button) ID 로 setting 되어야합니다.
            - `setRatingBarViewId(R.id.rating_id)` – 평점 레이팅 뷰가 들어갈 FrameLayout ID
              - [android.widget.FrameLayout](https://developer.android.com/reference/android/widget/FrameLayout) ID 로 setting 되어야합니다.
            - `setIconViewId(R.id.icon_id)` – 아이콘 이미지가 들어갈 FrameLayout ID
              - [android.widget.FrameLayout](https://developer.android.com/reference/android/widget/FrameLayout) ID 로 setting 되어야합니다.
            - `setMediaContentViewId(R.id.media_id)` – 미디어 콘텐츠 들어갈 FrameLayout ID
              - [android.widget.FrameLayout](https://developer.android.com/reference/android/widget/FrameLayout) ID 로 setting 되어야합니다.

        - Kotlin 예제
            ```kotlin
            // R.layout.native_view_layout 는 매체가 구성한 XML 리소스 ID
            val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
            val nativeAd = AdlibNativeAd.Builder(this, R.layout.native_view_layout, AD_UNIT_ID)
                .setTitleViewId(R.id.ad_title)
                .setMediaContentViewId(R.id.ad_media_content)
                .setCallToActionViewId(R.id.ad_btn)
                .build()
            ```

        - Java 예제
            ```java
            String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
            AdlibNativeAd adlibNativeAd = new AdlibNativeAd.Builder(this, R.layout.native_view_layout, AD_UNIT_ID)
                .setTitleViewId(R.id.ad_title)
                .setMediaContentViewId(R.id.ad_media_content)
                .setCallToActionViewId(R.id.ad_btn)
                .build();
            ```

    - **AdlibNativeAd 객체 빌드**
        - `AdlibNativeAd.Builder().build()`를 통해 객체를 생성하며, 필수로 `Context`, `R.layout.id`, `AD_UNIT_ID`를 전달해야 합니다.
        - 반드시 `R.layout` 형식으로 참조해야 합니다.

        - Kotlin
            ```kotlin
            nativeAd = AdlibNativeAd.Builder(this, R.layout.native_view_layout, "${발급받은 AD_UNIT_ID}")
                .setTitleViewId(R.id.ad_title)
                .setMediaContentViewId(R.id.ad_media_content)
                .setCallToActionViewId(R.id.ad_btn)
                .build()
            ```

        - Java
            ```java
            nativeAd = new AdlibNativeAd.Builder(this, R.layout.native_view_layout, "${발급받은 AD_UNIT_ID}")
                .setTitleViewId(R.id.ad_title)
                .setMediaContentViewId(R.id.ad_media_content)
                .setCallToActionViewId(R.id.ad_btn)
                .build();
            ```
#### 4.4.2 네이티브 광고 로드 (Template View 이용)
- Kotlin
    ```kotlin
    val nativeAd :AdlibNativeAd = AdlibNativeAd.Builder(this, "${발급받은 AD_UNIT_ID}")
        .build()
    nativeAd.loadAd(AdlibNativeAdListener)
    ```
- Java
    ```java
    AdlibNativeAd nativeAd = AdlibNativeAd.Builder(this, "${발급받은 AD_UNIT_ID}")
        .build();
    nativeAd.loadAd(AdlibNativeAdListener);
    ```
      
#### 4.4.3 네이티브 광고 로드 (Custom View 구성)
  - Kotlin
      ```kotlin
      val AD_UNIT_ID = "${발급받은 AD_UNIT_ID}"
      val nativeAd :AdlibNativeAd = AdlibNativeAd.Builder(this, R.layout.native_view_layout, AD_UNIT_ID)
          .setTitleViewId(R.id.ad_title)
          .setMediaContentViewId(R.id.ad_media_content)
          .setCallToActionViewId(R.id.ad_btn)
          .build()
      nativeAd.loadAd(AdlibNativeAdListener)
      ```
  - Java
      ```java
      String AD_UNIT_ID = "${발급받은 AD_UNIT_ID}";
      AdlibNativeAd nativeAd = AdlibNativeAd.Builder(this, R.layout.native_view_layout, AD_UNIT_ID)
          .setTitleViewId(R.id.ad_title)
          .setMediaContentViewId(R.id.ad_media_content)
          .setCallToActionViewId(R.id.ad_btn)
          .build();
      nativeAd.loadAd(AdlibNativeAdLoadListener);
      ```

#### 4.4.4 광고 리소스 해제
  배너 광고 또는 네이티브 광고를 더 이상 노출하지 않을 경우, 리소스를 해제하기 위해 `destroy()`를 호출해 뷰 계층에서 제거하고 관련 리소스를 정리해야 합니다.  
  <br>
- 사용 예시:
    - Kotlin
        ```kotlin
        val adlibNativeAd: AdlibNativeAd = AdlibNativeAd()
        adlibNativeAd.destroy()
        ```
    - Java
        ```java
        AdlibNativeAd adlibNativeAd = new AdlibNativeAd();
        adlibNativeAd.destroy();
        ```

- #### 네이티브 이벤트 수신

    - Kotlin
        ```kotlin
        nativeAd.loadAd(object : AdlibNativeAdListener {
            override fun onAdClicked() {
                // 광고 클릭 시 처리
            }

            override fun onAdClosed() {
                // 광고 닫힘 처리
            }

            override fun onAdFailedToLoad(adUnitId: String?, adError: AdlibError?) {
                // 광고 로드 실패 처리
            }

            override fun onAdImpression() {
                // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
            }

            override fun onAdLoaded(ad: NativeAdView) {
                // 광고 로드가 완료되었을 때 NativeAdView 객체를 광고 컨테이너에 추가합니다.
                container.addView(ad)
            }
        })
        ```

    - Java
        ```java
        nativeAd.loadAd(new AdlibNativeAdListener() {
            @Override
            public void onAdClicked() {
                // 광고 클릭 시 처리
            }

            @Override
            public void onAdClosed() {
                // 광고 닫힘 처리
            }

            @Override
            public void onAdFailedToLoad(String adUnitId, AdlibError adError) {
                // 광고 로드 실패 처리
            }

            @Override
            public void onAdImpression() {
                // 광고 노출(임프레션)이 기록되었을 때 호출됩니다.
            }

            @Override
            public void onAdLoaded(NativeAdView ad) {
                // 광고 로드가 완료되었을 때 NativeAdView 객체를 광고 컨테이너에 추가합니다.
                container.addView(ad);
            }
        });
        ```

## 5 문제 해결

### 5.1 ErrorCode
  | 상수                                                 | 코드  | 메시지                                                           | 설명                                            |
  |----------------------------------------------------|-----|---------------------------------------------------------------|-----------------------------------------------|
  | `AdlibError.COMMON_ERROR`                          | 0   | Common error                                                  | 공통 에러                                         |
  | `AdlibError.COMMON_NETWORK_RESPONSE_ERROR`         | 1   | ResponseCode :                                                | 네트워크 응답 코드 에러                                 |
  | `AdlibError.COMMON_NETWORK_ERROR`                  | 2   | Network error                                                 | 네트워크 오류                                       |
  | `AdlibError.CORE_APP_ID_INVALID`                   | 10  | Invalid appId                                                 | 유효하지 않은 App ID                                |
  | `AdlibError.CORE_INIT_ERROR`                       | 11  | Initialization failed                                         | SDK 초기화 실패                                    |
  | `AdlibError.CORE_NOT_INITIALIZED_ERROR`            | 12  | SDK not initialized                                           | SDK가 초기화되지 않음                                 |
  | `AdlibError.COMMON_ERROR_ALREADY_LOADING`          | 13  | It is already loading.                                        | 이미 로드 중인 상태                                   |
  | `AdlibError.COMMON_ERROR_LOAD_FAIL`                | 14  | No ad available to load                                       | 로드할 광고 없음                                     |
  | `AdlibError.COMMON_ERROR_EMPTY_AD_UNIT_ID`         | 15  | Ad Unit ID is missing                                         | 광고 단위 ID 누락                                   |
  | `AdlibError.BANNER_CONFIG_ERROR`                   | 101 | Banner refresh interval is not set                            | 배너 리프레시 시간 설정 누락                              |
  | `AdlibError.BANNER_NO_FILL`                        | 102 | Banner Ad No Fill                                             | 배너 광고 No Fill                                 |
  | `AdlibError.BANNER_LOAD_ERROR`                     | 103 | Banner ad load failed :                                       | 배너 로드 실패                                      |
  | `AdlibError.INTERSTITIAL_NOT_LOADED`               | 301 | Interstitial ad not loaded                                    | 전면 광고가 아직 로드되지 않음                             |
  | `AdlibError.INTERSTITIAL_NO_FILL`                  | 302 | Interstitial Ad No Fill                                       | 전면 광고 No Fill                                 |
  | `AdlibError.INTERSTITIAL_DESTROYED`                | 303 | This interstitial ad has already been shown                   | 이미 송출된 전면 광고                                  |
  | `AdlibError.INTERSTITIAL_SHOW_FAILED`              | 304 | Show failed                                                   | 전면광고 show 실패                                  |
  | `AdlibError.INTERSTITIAL_ALREADY_SHOWING`          | 305 | The ad has already been shown.                                | 이미 재생중인데 show 가 또 호출된 상태                      |
  | `AdlibError.NATIVE_ERROR_CAP_EXCEEDED`             | 404 | Frequency limit exceeded.                                     | 네이티브 광고 빈도 제한 초과                              |
  | `AdlibError.NATIVE_ERROR_VIEW_INFLATE`             | 407 | An error occurred while inflating the Native View.            | 네이티브 뷰 생성 중 오류 발생                             |
  | `AdlibError.NATIVE_AD_LOAD_FAIL`                   | 408 | Native Ad load failed                                         | 네이티브 광고 로드 실패                                 |
  | `AdlibError.NATIVE_ERROR_MISSING_CUSTOM_ASSETS`    | 409 | Missing required assets for custom native ad                  | 커스텀 타입의 네이티브 광고 때 view assets 설정이 되어 있지 않아 발생 |
  | `AdlibError.NATIVE_ERROR_TEMPLATE_ASSET_PROVIDED`  | 410 | Assets should not be manually provided for template native ad | 템플릿 타입의 네이티브 광고 때 view assets 설정이 되어 있어 발생    |
  | `AdlibError.NATIVE_ERROR_CONFIG_LOAD_FAIL`         | 411 | Failed to load native ad configuration setting                | load 시도한 adUnitId 에 해당하는 config 설정이 없음        |
  | `AdlibError.NATIVE_NO_FILL`                        | 412 | Native Ad No Fill                                             | 네이티브 광고 No Fill                               |
  | `AdlibError.VIDEO_NO_FILL`                         | 501 | Video Ad No Fill                                              | 동영상 광고 No Fill                                |
  | `AdlibError.VIDEO_ERROR_CONFIG_SETTING`            | 502 | Initialization incomplete or check video ad settings.         | 초기화 미완료 또는 설정 오류                              |
  | `AdlibError.VIDEO_ERROR_REQUEST_ERROR`             | 503 | VideoAd request error :                                       | 동영상 광고 요청 오류                                  |
  | `AdlibError.VIDEO_ERROR_VAST_TAG_EMPTY`            | 504 | Vast tag url is empty                                         | VAST 태그 URL이 없음                               |
  | `AdlibError.VIDEO_ERROR_COMMON`                    | 506 | Video Common Error :                                          | 일반적인 동영상 광고 에러                                |
  | `AdlibError.VIDEO_ERROR_NOT_LOADED`                | 507 | VideoAd is null not loaded                                    | 동영상 광고 미로드 또는 null                            |
  | `AdlibError.VIDEO_ERROR_PLAYED_VIDEO`              | 508 | Previously played video                                       | 이미 재생된 동영상 광고                                 |
  | `AdlibError.VIDEO_ERROR_VIDEO_EXPIRED`             | 509 | The ad has expired. Please try again load.                    | 광고 만료, 재로딩 필요                                 |
  | `AdlibError.VIDEO_ERROR_VIDEO_DOWNLOAD_ERROR`      | 510 | Video download error                                          | 동영상 다운로드 실패                                   |
  | `AdlibError.VIDEO_ERROR_MISSING_PLAYER_DEPENDENCY` | 511 | Missing video player dependency                               | 동영상 플레이어 라이브러리 미참조 Error                      |
  | `AdlibError.VIDEO_ERROR_INVALID_VAST_TAG`          | 512 | Invalid or malformed VAST tag                                 | VastTag 가 유효하지 않음                             |
  | `AdlibError.VIDEO_ERROR_ALREADY_SHOWING`           | 513 | This video ad has already been shown                          | 이미 재생중인데 show 가 또 호출된 상태                      |


## 6 API 레퍼런스
## 6.1 class AdlibSdk
  - SDK 를 사용 하기 위한 초기화 및 설정 등을 제어 하는 class 입니다.
- ### 6.1.1 initialize(Context, SdkInitListener)
  | static | 함수명            | 파라미터                                                  | 반환 타입 | 설명                                      |
  |--------|----------------|-------------------------------------------------------|-------|-----------------------------------------|
  | ✅      | `initialize()` | `Context ctx`, <br>`SdkInitListener callbackListener` | -     | 시스템 초기화 함수로 APP_ID 가 메니페스트에 정의 되어 있는 경우 |

  - 매개 변수
    - Context ctx : Android Context Application 혹은 Activity 로 전달 해도 좋습니다.
    - [SdkInitListener](#62-interface-adlibsdksdkinitlistener) callbackListener : 초기화 결과를 전달 받을수 있는 Listener 인터페이스
- ### 6.1.2 initialize(Context, String, SdkInitListener)
  | static | 함수명            | 파라미터                                                                      | 반환 타입 | 설명                                      |
  |--------|----------------|---------------------------------------------------------------------------|-------|-----------------------------------------|
  | ✅      | `initialize()` | `Context ctx`, <br>`String appId`, <br>`SdkInitListener callbackListener` | -     | 시스템 초기화 함수로 APP_ID 가 메니페스트에 정의 되지 않은 경우 |
    - 매개 변수
        - Context ctx : Android Context Application 혹은 Activity 로 전달 해도 좋습니다.
        - String appId : 발급받은 appId를 전달 합니다.
        - [SdkInitListener](#62-interface-adlibsdksdkinitlistener) callbackListener : 초기화 결과를 전달 받을수 있는 Listener 인터페이스

- ### 6.1.3 getAdlibSdkSetting()
  | static | 함수명                    | 파라미터 | 반환 타입           | 설명                          |
  |--------|------------------------|------|-----------------|-----------------------------|
  | ✅      | `getAdlibSdkSetting()` | -    | AdlibSdkSetting | 애드립 SDK의 설정을 다루는 객체 가져오는 함수 |
  - 반환 타입
    -  [AdlibSdkSetting](#63-class-adlibsdksetting) SDK 에 Setting 값을 설정 할수 있는 클래스를 반환 합니다.


## 6.2 interface AdlibSdk.SdkInitListener
  - 초기화 결과를 전달 받을수 있는 Listener 인터페이스

- ### 6.2.1 onSdkInitialized()
  | static | 함수명                  | 파라미터                            | 반환 타입 | 설명                            |
  |--------|----------------------|---------------------------------|-------|-------------------------------|
  | ❌      | `onSdkInitialized()` | `AdlibSdkConfig adlibSdkConfig` | -     | SDK 초기화가 성공 했을 경우 callback 함수 |
    - 매개 변수
        -  [AdlibSdkConfig](#64-class-adlibsdkconfig) SDK 가 동작 하는 환경 정보를 담고 있는 class 입니다.
- ### 6.2.2 onSdkInitFailed()
  | static | 함수명                 | 파라미터                    | 반환 타입 | 설명                           |
  |--------|---------------------|-------------------------|-------|------------------------------|
  | ❌      | `onSdkInitFailed()` | `AdlibError adlibError` | -     | SDK 초기화를 실패 한 경우 callback 함수 |
    - 매개 변수
        -  [AdlibError](#65-class-adliberror) 초기화 실패 원인을 담고 있는 AdlibError Class 입니다. 

## 6.3 class AdlibSdkSetting
- Adlib SDK 의 환경 설정을 다룰수 있는 Class AdlibSdk 를 초기화를 할때 생성됩니다. 
- 직접 생성은 불가능 합니다.
<br>

- ### 6.3.1 setTestMode()
  | static | 함수명             | 파라미터                 | 반환 타입 | 설명                                     |
  |--------|-----------------|----------------------|-------|----------------------------------------|
  | ❌      | `setTestMode()` | `boolean isTestMode` | -     | Adlib SDK가 test mode 로 설정 하게 하고 싶을때 사용 |
    - 매개 변수
        -  boolean isTestMode : 테스트 모드를 설정 하는 값입니다. true -> testMode, false -> 일반 모드
- ### 6.3.2 isTestMode()
  | static | 함수명            | 파라미터 | 반환 타입     | 설명                                   |
  |--------|----------------|------|-----------|--------------------------------------|
  | ❌      | `isTestMode()` | -    | `booelan` | return 값이 true 면 현재 SDK 는 테스트 모드로 동작 |
    - 반환 타입
        -  boolean isTestMode : 현재 모드를 반환 합니다. true -> testMode, false -> 일반 모드

## 6.4 class AdlibSdkConfig
- AdlibSdk 의 현재 환경을 확인 할수 있는 Class AdlibSdk 가 초기화 될때 생성됩니다.
- 직접 생성은 불가능 합니다.
    
  | static | 함수명                 | 파라미터 | 반환 타입    | 설명                     |
  |--------|---------------------|------|----------|------------------------|
  | ❌      | `getAdlibVersion()` | -    | `String` | Adlib SDK 현재 버전 return |

## 6.5 class AdlibError
- [ErrorCode Table](#51-errorcode) 참고

- ### 6.5.1 필드 (Field)
  | 접근 제어자  | 변수명            | 타입       | 설명     |
  |---------|----------------|----------|--------|
  | private | `errorCode`    | `int`    | 에러 코드  |
  | private | `errorMessage` | `String` | 에러 메시지 |
- ### 6.5.2 함수 (Method)
  | static | 함수명                 | 파라미터 | 반환 타입    | 설명                                          |
  |--------|---------------------|------|----------|---------------------------------------------|
  | ❌      | `getErrorCode()`    | -    | `Int`    | error code 가져오는 함수                          |
  | ❌      | `getErrorMessage()` | -    | `String` | error message 가져오는 함수                       |
  | ❌      | `toString()`        | -    | `String` | "errorCode : errorMessage" 이런 형태로 String 리턴 |

## 6.6 enum AdlibAdSize
- AD_SIZE_BANNER_50 : 320 * 50 의 기본 형태의 띠베너 광고
- AD_SIZE_BANNER_100 : 320 * 100 의 기본 형태의 띠베너 광고
- AD_SIZE_HALF_BANNER : 300 * 250 의 하프배너 형태의 광고

    | static | 함수명                  | 파라미터             | 반환 타입         | 설명                                                             |
    |--------|----------------------|------------------|---------------|----------------------------------------------------------------|
    | ❌      | `getCode()`          | -                | `String code` | 현재 AdSize의 고유 코드 배너 = 1, 인터스티셜 = 2, 하프배너 = 3                   |
    | ❌      | `getName()`          | -                | `String`      | 현재 AdSize 의 이름 (예시)"banner_50", "half_banner"                  |
    | ❌      | `getHeight()`        | -                | `Int`         | 현재 AdSize 의 높이 값을 리턴                                           |
    | ❌      | `getWidth()`         | -                | `Int`         | 현재 AdSize 의 너비 값을 리턴                                           |
    | ✅      | `toCodeFromString()` | `String sizeStr` | `AdlibAdSize` | 1 -> "banner_50" , 3 -> "half_banner" 이런식으로 코드를 문자열로 변경 해주는 함수 |


## 6.7 class AdlibAdView
- ### 6.7.1 생성자 AdlibAdView()
  | static | 함수명             | 파라미터                                     | 반환 타입         | 설명                            |
  |--------|-----------------|------------------------------------------|---------------|-------------------------------|
  | ❌      | `AdlibAdView()` | `String adUnitId`, <br>`Context context` | `AdlibAdView` | 배너 광고를 로드 하는 AdlibAdView의 생성자 |
    - 매개 변수
        -  String adUnitId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
        -  Context context : Android Context
    - 반환 타입
      - AdlibAdView : AdlibAdView 객체 생성
- ###  6.7.2 setAdSize()
  | static | 함수명           | 파라미터                     | 반환 타입 | 설명                                   |
  |--------|---------------|--------------------------|-------|--------------------------------------|
  | ❌      | `setAdSize()` | `AdlibAdSize adUnitSize` | -     | 광고 사이즈를 지정 하는 AdlibAdSize 값을 세팅하는 함수 |
    - 매개 변수
        -  AdlibAdSize adUnitSize : [Adlib 광고 크기](#66-enum-adlibadsize)
- ###  6.7.3 loadAd()
  | static | 함수명        | 파라미터                             | 반환 타입 | 설명                                                           |
  |--------|------------|----------------------------------|-------|--------------------------------------------------------------|
  | ❌      | `loadAd()` | `AdlibBannerAdListener listener` | -     | 배너 광고 로드 하는 함수 파라미터인 AdlibBannerAdListener 로 이벤트를 수신 받을수 있다. |
    - 매개 변수
        -  AdlibBannerAdListener listener : [Banner 광고 이벤트 리스너](#68-interface-adlibbanneradlistener)
      
- ### 6.7.4 setAdUnitId()
  | static | 함수명             | 파라미터              | 반환 타입 | 설명                                                                                      |
  |--------|-----------------|-------------------|-------|-----------------------------------------------------------------------------------------|
  | ❌      | `setAdUnitId()` | `String adUnitId` | -     | AdUnitID는 보통 생성자에서 생성 하지만 xml 로 뷰를 구성 했을때 AdUnitId 지정을 안해준 경우 이 함수를 통해 AdUnitId를 반드시 설정 |
    - 매개 변수
        -  String adUnitId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
- ### 6.7.5 destroy()
  | static | 함수명         | 파라미터 | 반환 타입 | 설명                                       |
  |--------|-------------|------|-------|------------------------------------------|
  | ❌      | `destroy()` | -    | -     | 광고를 더이상 표시하지 않을때 관련 메모리를 모두 해제 할수 있도록 호출 |

## 6.8 interface AdlibBannerAdListener
- ### 6.8.1 onAdClicked()
  | static | 함수명             | 파라미터 | 반환 타입 | 설명                           |
  |--------|-----------------|------|-------|------------------------------|
  | ❌      | `onAdClicked()` | -    | -     | 광고 Click 이벤트 발생시 callback 함수 |

- ### 6.8.2 onAdClosed()
  | static | 함수명            | 파라미터 | 반환 타입 | 설명                                  |
  |--------|----------------|------|-------|-------------------------------------|
  | ❌      | `onAdClosed()` | -    | -     | 광고뷰가 더 이상 보이지 않을때 발생 하는 callback 함수 |

- ### 6.8.3 onAdFailedToLoad()
  | static | 함수명                  | 파라미터                                        | 반환 타입 | 설명                          |
  |--------|----------------------|---------------------------------------------|-------|-----------------------------|
  | ❌      | `onAdFailedToLoad()` | `String adUnitId`, <br>`AdlibError adError` | -     | 광고 로드에 실패 한 경우의 callback 함수 |
    - 매개 변수
        -  String adUnitId : [Adlib 광고 크기](#66-enum-adlibadsize)
        -  AdlibError adError : [AdlibError](#65-class-adliberror) Banner 광고 실패 원인 정보를 가져올수 있습니다.

- ### 6.8.4 onAdLoaded()
  | static | 함수명            | 파라미터              | 반환 타입 | 설명                    |
  |--------|----------------|-------------------|-------|-----------------------|
  | ❌      | `onAdLoaded()` | `AdlibAd adlibAd` | -     | 광고 로드에 성공 callback 함수 |
    - 매개 변수
        -  AdlibAd adlibAd : [AdlibAd](#69-class-adlibad) AdlibAd 광고 정보를 담고 있는 객체 입니다.

- ### 6.8.5 onAdImpression()
  | static | 함수명                | 파라미터 | 반환 타입 | 설명                          |
  |--------|--------------------|------|-------|-----------------------------|
  | ❌      | `onAdImpression()` | -    | -     | 광고 노출(임프레션)이 기록되었을 때 호출됩니다. |

## 6.9 class AdlibAd
- ### 6.9.1 필드(Field) (모든 필드는 getter 함수만 제공합니다. set은 불가)
    | 접근 제어자  | 변수명                 | 타입        | 설명                                |
    |---------|---------------------|-----------|-----------------------------------|
    | private | `interval`          | `int`     | 광고 간격                             |
    | private | `count`             | `int`     | 광고 수                              |
    | private | `convert`           | `boolean` | 전환 여부 (`Y` → `true`, 그 외 `false`) |
    | private | `adm`               | `String`  | 광고 마크업(ad markup)                 |
    | private | `width`             | `int`     | 광고 너비                             |
    | private | `height`            | `int`     | 광고 높이                             |
    | private | `bgColor`           | `String`  | 배경 색상                             |
    | private | `test`              | `boolean` | 테스트 광고 여부 (`N`이면 false, 그 외 true) |
    | private | `viewabilityEnable` | `boolean` | 뷰어빌리티(가시성) 측정 기능 활성화 여부           |
    | private | `isPixel`           | `boolean` | 뷰어빌리티 측정 방식이 픽셀 기반인지 여부           |
    | private | `threshold`         | `int`     | 뷰어빌리티 측정 임계값                      |
    | private | `url`               | `String`  | click 시 이동하는 페이지 url              |



- ### 6.9.2 생성자

  | 접근제어자   | 함수명         | 파라미터 | 반환 타입       | 설명            |
  |---------|-------------|------|-------------|---------------|
  | private | `AdlibAd()` | -    | 없음 (`void`) | 외부에서 직접 생성 불가 |


## 6.10 class AdlibInterstitialAd
- ### 6.10.1 생성자 AdlibInterstitialAd()
  | static | 함수명                     | 파라미터                                 | 반환 타입                 | 설명  |
  |--------|-------------------------|--------------------------------------|-----------------------|-----|
  | ❌      | `AdlibInterstitialAd()` | `String adUnitId`, <br>`Context ctx` | `AdlibInterstitialAd` | 생성자 |
    - 매개 변수
        -  String adUnitID : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
    - 반환 타입
      - AdlibInterstitialAd : AdlibInterstitialAd 객체 생성
- ### 6.10.2 loadAd()
  | static | 함수명        | 파라미터                                   | 반환 타입 | 설명                                                               |
  |--------|------------|----------------------------------------|-------|------------------------------------------------------------------|
  | ❌      | `loadAd()` | `AdlibInterstitialAdListener listener` | -     | 전면 광고 로드 하는 함수 파라미터 AdlibInterstitialAdListener 로 이벤트를 수신 받을수 있다 |
    - 매개 변수
        -  AdlibInterstitialAdListener listener : [인터스티셜 이벤트 리스너](#611-interface-adlibinterstitialadlistener) 인터스티셜 광고 이벤트 리스너 class 입니다.

- ### 6.10.3 isReady()
  | static | 함수명         | 파라미터 | 반환 타입     | 설명                                        |
  |--------|-------------|------|-----------|-------------------------------------------|
  | ❌      | `isReady()` | -    | `boolean` | 광고 로드가 되었는지 확인 할수 있는 함수 true 면 로드된 광고가 있음 |
    - 반환 타입
        -  boolean isReady : 광고 로드가 성공 해서 show 할수 있는 상태 인지를 반환 합니다.
- ### 6.10.4 show()
  | static | 함수명      | 파라미터 | 반환 타입 | 설명                    |
  |--------|----------|------|-------|-----------------------|
  | ❌      | `show()` | -    | -     | 로드한 광고를 show 호출 하는 함수 |


## 6.11 interface AdlibInterstitialAdListener
- ### 6.11.1 onAdClicked()
  | static | 함수명             | 파라미터 | 반환 타입 | 설명                           |
  |--------|-----------------|------|-------|------------------------------|
  | ❌      | `onAdClicked()` | -    | -     | 광고 Click 이벤트 발생시 callback 함수 |
- ### 6.11.2 onAdClosed()
  | static | 함수명            | 파라미터 | 반환 타입 | 설명                                  |
  |--------|----------------|------|-------|-------------------------------------|
  | ❌      | `onAdClosed()` | -    | -     | 광고뷰가 더 이상 보이지 않을때 발생 하는 callback 함수 |

- ### 6.11.3 onAdFailedToLoad()
  | static | 함수명                  | 파라미터                                        | 반환 타입 | 설명                          |
  |--------|----------------------|---------------------------------------------|-------|-----------------------------|
  | ❌      | `onAdFailedToLoad()` | `String adUnitId`, <br>`AdlibError adError` | -     | 광고 로드에 실패 한 경우의 callback 함수 |
    - 매개 변수
        -  String adUnitId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
        - AdlibError adError : [AdlibError](#65-class-adliberror) 인터스티셜 광고 로드 실패 원인 정보를 가져올수 있습니다.

- ### 6.11.4 onAdLoaded()
  | static | 함수명            | 파라미터              | 반환 타입 | 설명                    |
  |--------|----------------|-------------------|-------|-----------------------|
  | ❌      | `onAdLoaded()` | `AdlibAd adlibAd` | -     | 광고 로드에 성공 callback 함수 |
    - 매개 변수
        -  AdlibAd adlibAd : [AdlibAd](#69-class-adlibad) AdlibAd 광고 정보를 담고 있는 객체 입니다.
- ### 6.11.5 onAdShowFailed()
  | static | 함수명                | 파라미터               | 반환 타입 | 설명                                        |
  |--------|--------------------|--------------------|-------|-------------------------------------------|
  | ❌      | `onAdShowFailed()` | `AdlibError error` | -     | show 를 호출 했지만 광고를 게재 하지 못한 경우 callback 함수 |
    - 매개 변수
        -  AdlibError error : [AdlibError](#65-class-adliberror) 인터스티셜 광고 Show 실패 원인 정보를 가져올수 있습니다. 
- ### 6.11.6 onAdDisplayed()
  | static | 함수명               | 파라미터 | 반환 타입 | 설명                              |
  |--------|-------------------|------|-------|---------------------------------|
  | ❌      | `onAdDisplayed()` | -    | -     | 광고뷰 노출이 되기 시작하는 시점의 callback 함수 |

- ### 6.11.7 onAdImpression()
  | static | 함수명                | 파라미터 | 반환 타입 | 설명                             |
  |--------|--------------------|------|-------|--------------------------------|
  | ❌      | `onAdImpression()` | -    | -     | // 광고 노출(임프레션)이 기록되었을 때 호출됩니다. |

- ### 6.11.8 onAdHidden()
  | static | 함수명            | 파라미터 | 반환 타입 | 설명                      |
  |--------|----------------|------|-------|-------------------------|
  | ❌      | `onAdHidden()` | -    | -     | 광고가 보이지 않을때 callback 함수 |

## 6.12 class AdlibVideoAd
- ### 6.12.1 생성자 AdlibVideoAd()
  | static | 함수명              | 파라미터                                | 반환 타입          | 설명  |
  |--------|------------------|-------------------------------------|----------------|-----|
  | ❌      | `AdlibVideoAd()` | `String adUnitID`,<br>`Context ctx` | `AdlibVideoAd` | 생성자 |
    - 매개 변수
        -  String adUnitID : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
        -  Context ctx : Android Context
    - 반환 타입
        -  AdlibVideoAd : AdlibVideoAd 객체 생성
- ### 6.12.2 loadAd()
  | static | 함수명        | 파라미터                                | 반환 타입 | 설명                                                                 |
  |--------|------------|-------------------------------------|-------|--------------------------------------------------------------------|
  | ❌      | `loadAd()` | `AdlibVideoAdLoadListener listener` | -     | Video 광고 로드 하는 함수 파라미터인 AdlibVideoAdLoadListener 로 이벤트를 수신 받을수 있다. |
    - 매개 변수
        -  AdlibVideoAdLoadListener listener : [AdlibVideoAdLoadListener 비디오 광고 이벤트 리스너](#615-interface-adlibvideoadloadlistener)
- ### 6.12.3 setServerSideVerification()
  | static | 함수명                           | 파라미터                                            | 반환 타입 | 설명                                                      |
  |--------|-------------------------------|-------------------------------------------------|-------|---------------------------------------------------------|
  | ❌      | `setServerSideVerification()` | `ServerSideVerification serverSideVerification` | -     | reward callback 을 서버 사이드에서 진행 하는 경우 리워드 관련 정보를 전달 하는 함수 |
    - 매개 변수
        -  ServerSideVerification serverSideVerification : [ServerSideVerification Reward 지급](#613-class-serversideverification)
- ### 6.12.4 isReady()
  | static | 함수명         | 파라미터 | 반환 타입     | 설명                                        |
  |--------|-------------|------|-----------|-------------------------------------------|
  | ❌      | `isReady()` | -    | `boolean` | 광고 로드가 되었는지 확인 할수 있는 함수 true 면 로드된 광고가 있음 |
    - 반환 타입
        -  boolean isReady : 광고가 로드가 정상적으로 완료 되어 show 할수 있을때 true 를 반환
- ### 6.12.5 show()
  | static | 함수명      | 파라미터                                  | 반환 타입 | 설명                                                                   |
  |--------|----------|---------------------------------------|-------|----------------------------------------------------------------------|
  | ❌      | `show()` | `AdlibVideoShowListener showListener` | -     | 로드한 광고를 show 호출 하는 함수 파라미터인 AdlibVideoShowListener 로 이벤트를 수신 받을수 있다. |
    - 매개 변수
        -  AdlibVideoShowListener showListener : [Video 광고 Show 이벤트 리스너](#616-interface-adlibvideoshowlistener)

## 6.13 class ServerSideVerification
- ### 6.13.1 생성자 ServerSideVerification()
  | static | 함수명                        | 파라미터 | 반환 타입 | 설명                                                             |
  |--------|----------------------------|------|-------|----------------------------------------------------------------|
  | ❌      | `ServerSideVerification()` | -    | -     | 내부 에서만 사용 가능한 private 생성자 입니다. 외부 에서는 Builder를 통해 인스턴스를 생성하세요. |
- ### 6.13.2 필드(Field) (모든 필드는 getter / setter 함수를 제공합니다.)
  | 접근 제어자  | 변수명          | 타입       | 설명                        |
  |---------|--------------|----------|---------------------------|
  | private | `userId`     | `String` | 리워드 지급할 User를 식별 할수 있는 id |
  | private | `customData` | `String` | 리워드 검증 서버에 함께 전달할 커스텀 데이터 |
- ### 6.13.3 getUserId()
  | static | 함수명           | 파라미터 | 반환 타입           | 설명                             |
  |--------|---------------|------|-----------------|--------------------------------|
  | ❌      | `getUserId()` | -    | `String userId` | 리워드 지급할 User를 식별 할수 있는 id 값 반환 |
    - 반환 타입
        -  String userId : 리워드 지급할 User를 식별 할수 있는 id 값 반환
- ### 6.13.4 getCustomData()
  | static | 함수명               | 파라미터 | 반환 타입               | 설명                           |
  |--------|-------------------|------|---------------------|------------------------------|
  | ❌      | `getCustomData()` | -    | `String customData` | 리워드 검증 서버에 함께 전달할 커스텀 데이터 반환 |
    - 반환 타입
        -  String customData : 리워드 검증 서버에 함께 전달할 커스텀 데이터
## 6.14 ServerSideVerification.Builder()
- ### 6.14.1 생성자 ServerSideVerification.Builder()
  | static | 함수명         | 파라미터 | 반환 타입                            | 설명                 |
  |--------|-------------|------|----------------------------------|--------------------|
  | ❌      | `Builder()` | -    | `ServerSideVerification.Builder` | Builder 객체를 생성합니다. |
- ### 6.14.2 build()
  | static | 함수명       | 파라미터 | 반환 타입                    | 설명                                             |
  |--------|-----------|------|--------------------------|------------------------------------------------|
  | ❌      | `build()` | -    | `ServerSideVerification` | 설정된 정보를 기반으로 ServerSideVerification 객체를 생성합니다. |
    - 반환 타입
        -  ServerSideVerification : ServerSideVerification 객체 생성
- ### 6.14.3 setUserId()
  | static | 함수명           | 파라미터          | 반환 타입     | 설명                               |
  |--------|---------------|---------------|-----------|----------------------------------|
  | ❌      | `setUserId()` | String userId | `Builder` | 리워드 지급할 User를 식별 할수 있는 id 값 을 세팅 |
    - 매개 변수
        -  String userId : 리워드 지급할 User를 식별 할수 있는 id 값
    - 반환 타입
        -  Builder : Builder 패턴의 Builder 객체 반환
- ### 6.14.4 setCustomData()
  | static | 함수명               | 파라미터              | 반환 타입     | 설명                                |
  |--------|-------------------|-------------------|-----------|-----------------------------------|
  | ❌      | `setCustomData()` | String customData | `Builder` | 리워드 검증 서버에 함께 전달할 커스텀 데이터를 설정합니다. |
    - 매개 변수
        -  String customData : 리워드 검증 서버에 함께 전달할 커스텀 데이터
    - 반환 타입
        -  Builder : Builder 패턴의 Builder 객체 반환
## 6.15 interface AdlibVideoAdLoadListener
- ### 6.15.1 onAdFailedToLoad()
  | static | 함수명                  | 파라미터                                        | 반환 타입 | 설명                          |
  |--------|----------------------|---------------------------------------------|-------|-----------------------------|
  | ❌      | `onAdFailedToLoad()` | `String adUnitId`, <br>`AdlibError adError` | -     | 광고 로드에 실패 한 경우의 callback 함수 |
    - 매개 변수
        -  String adUnitId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
        -  AdlibError adError : [Video 광고 로드 실패 원인을 담고 있는 AdlibError 객체](#51-errorcode) 
- ### 6.15.2 onAdLoaded()
  | static | 함수명            | 파라미터              | 반환 타입 | 설명                    |
  |--------|----------------|-------------------|-------|-----------------------|
  | ❌      | `onAdLoaded()` | `AdlibAd adlibAd` | -     | 광고 로드에 성공 callback 함수 |
    - 반환 타입
        -  AdlibAd adlibAd : [AdlibAd](#69-class-adlibad) AdlibAd 광고 정보를 담고 있는 객체 입니다.
- ### 6.15.3 onAdShowed()
  | static | 함수명            | 파라미터 | 반환 타입 | 설명                             |
  |--------|----------------|------|-------|--------------------------------|
  | ❌      | `onAdShowed()` | -    | -     | show() 함수가 호출된 시점의 callback 함수 |

## 6.16 interface AdlibVideoShowListener
- ### 6.16.1 onRewarded()
  | static | 함수명            | 파라미터                  | 반환 타입 | 설명                                              |
  |--------|----------------|-----------------------|-------|-------------------------------------------------|
  | ❌      | `onRewarded()` | RewardItem rewardItem | -     | 리워드 지급 요건을 충족해서 리워드 지급 요청 call 시점 의 callback 함수 |
    - 매개 변수
        -   [RewardItem rewardItem :](#617-class-rewarditem) 리워드 지급 재화의 정보를 담고 있는 객체 입니다.
- ### 6.16.2 onVideoStarted()
  | static | 함수명                | 파라미터 | 반환 타입 | 설명                             |
  |--------|--------------------|------|-------|--------------------------------|
  | ❌      | `onVideoStarted()` | -    | -     | 비디오 재생이 시작될때 호출 되는 callback 함수 | 
- ### 6.16.3 onAdCompleted()
  | static | 함수명               | 파라미터 | 반환 타입 | 설명                                   |
  |--------|-------------------|------|-------|--------------------------------------|
  | ❌      | `onAdCompleted()` | -    | -     | 비디오 재생이 끝까지 재생된 경우 호출 되는 callback 함수 |
- ### 6.16.4 onAdDismissedAd()
  | static | 함수명                 | 파라미터 | 반환 타입 | 설명                                  |
  |--------|---------------------|------|-------|-------------------------------------|
  | ❌      | `onAdDismissedAd()` | -    | -     | 비디오 광고 뷰가 보이지 않을때 호출 되는 callback 함수 |
- ### 6.16.5 onAdClicked()
  | static | 함수명             | 파라미터 | 반환 타입 | 설명                                |
  |--------|-----------------|------|-------|-----------------------------------|
  | ❌      | `onAdClicked()` | -    | -     | 비디오 click 이벤트가 발생 했을때 callback 함수 |
- ### 6.16.6 showFailed()
  | static | 함수명            | 파라미터                 | 반환 타입 | 설명                                     |
  |--------|----------------|----------------------|-------|----------------------------------------|
  | ❌      | `showFailed()` | `AdlibError adError` | -     | show를 호출했지만 비디오 재생에 실패 했을때 callback 함수 |
    - 매개 변수
        -   [AdlibError adError :](#51-errorcode) Show Fail 원인 정보를 담고 있는 객체
## 6.17 class RewardItem
- ### 6.17.1 필드(Field) (모든 필드는 getter 함수를 제공합니다.)
  | 접근 제어자  | 변수명            | 타입       | 설명     |
  |---------|----------------|----------|--------|
  | private | `rewardUnit`   | `String` | 리워드 단위 |
  | private | `rewardAmount` | `int`    | 리워드 수량 |
- ### 6.17.2 getRewardUnit()
  | static | 함수명               | 파라미터 | 반환 타입    | 설명                     |
  |--------|-------------------|------|----------|------------------------|
  | ❌      | `getRewardUnit()` | -    | `String` | Reward 단위 문자열 반환 하는 함수 |
    - 반환 타입
        -   String rewardUnit:  Reward 단위 문자열
- ### 6.17.3 getRewardAmount()
  | static | 함수명                 | 파라미터 | 반환 타입 | 설명                  |
  |--------|---------------------|------|-------|---------------------|
  | ❌      | `getRewardAmount()` | -    | `int` | Reward 수량을 반환 하는 함수 |
    - 반환 타입
        -   int rewardAmount:  Reward 수량
## 6.18 class AdlibNativeAd
- ### 6.18.1 생성자 AdlibNativeAd()
  | static | 함수명               | 파라미터                            | 반환 타입           | 설명  |
  |--------|-------------------|---------------------------------|-----------------|-----|
  | ❌      | `AdlibNativeAd()` | `AdlibNativeAd.Builder builder` | `AdlibNativeAd` | 생성자 |
    - 매개 변수
        -   AdlibNativeAd.Builder builder : Native UI 정보를 담고 있는 AdlibNativeAd.Builder 객체
- ### 6.18.2 loadAd()
  | static | 함수명        | 파라미터                             | 반환 타입 | 설명                                                                   |
  |--------|------------|----------------------------------|-------|----------------------------------------------------------------------|
  | ❌      | `loadAd()` | `AdlibNativeAdListener listener` | -     | Native 광고 로드 하는 함수 파라미터인 AdlibNativeAdLoadListener 로 이벤트를 수신 받을수 있다. |
    - 매개 변수
        -   [AdlibNativeAdListener](#620-interface-adlibnativeadlistener) listener : 네이티브 광고 이벤트 수신하는 리스너
- ### 6.18.3 destroy()
  | static | 함수명         | 파라미터 | 반환 타입 | 설명                     |
  |--------|-------------|------|-------|------------------------|
  | ❌      | `destroy()` | -    | -     | 로드한 광고를 destroy 시키는 함수 |

## 6.19 class AdlibNativeAd.Builder
- ### 6.19.1 생성자 AdlibNativeAd.Builder() - 직접 뷰 구성 
  | static | 함수명                     | 파라미터                                                           | 반환 타입                 | 설명                                |
  |--------|-------------------------|----------------------------------------------------------------|-----------------------|-----------------------------------|
  | ❌      | AdlibNativeAd.Builder() | `Context ctx`, <br>`int nativeAdViewId`, <br>`String unitAdId` | AdlibNativeAd.Builder | AdlibNativeAd.Builder() 생성 하는 생성자 |
    - 매개 변수
        - Context context : Native 광고가 표시될 View의 컨텍스트
        - int nativeAdViewId : Native 광고가 정의 되어 있는 View 의 resource Id 
        - String unitAdId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
    - 반환 타입
        -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.2 생성자 AdlibNativeAd.Builder() - 템플릿 뷰 구성 
  | static | 함수명                     | 파라미터                                 | 반환 타입                 | 설명                                |
    |--------|-------------------------|--------------------------------------|-----------------------|-----------------------------------|
  | ❌      | AdlibNativeAd.Builder() | `Context ctx`, <br>`String unitAdId` | AdlibNativeAd.Builder | AdlibNativeAd.Builder() 생성 하는 생성자 |
    - 매개 변수
        - Context context : Native 광고가 표시될 View의 컨텍스트
        - String unitAdId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
    - 반환 타입
        -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.3 setContext()
  | static | 함수명          | 파라미터            | 반환 타입                 | 설명                             |
  |--------|--------------|-----------------|-----------------------|--------------------------------|
  | ❌      | setContext() | Context context | AdlibNativeAd.Builder | NativeAdView를 사용할 컨텍스트를 설정합니다. |
  - 매개 변수
      - Context context : Native 광고가 표시될 View의 컨텍스트
  - 반환 타입
      -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체 
- ### 6.19.4 setTitleViewId()
  | static | 함수명              | 파라미터            | 반환 타입                 | 설명                         |
  |--------|------------------|-----------------|-----------------------|----------------------------|
  | ❌      | setTitleViewId() | int titleViewId | AdlibNativeAd.Builder | 광고 제목 텍스트뷰의 리소스 ID를 설정합니다. |
  - 매개 변수
      - int titleViewId : 광고 제목을 표시할 TextView의 리소스 ID
  - 반환 타입
      -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.5 setAdvertiserViewId()
  | static | 함수명                   | 파라미터                 | 반환 타입                 | 설명                          |
  |--------|-----------------------|----------------------|-----------------------|-----------------------------|
  | ❌      | setAdvertiserViewId() | int advertiserViewId | AdlibNativeAd.Builder | 광고주 표시 텍스트뷰의 리소스 ID를 설정합니다. |
- ### 6.19.6 setBodyViewId()
  | static | 함수명             | 파라미터           | 반환 타입                 | 설명                         |
  |--------|-----------------|----------------|-----------------------|----------------------------|
  | ❌      | setBodyViewId() | int bodyViewId | AdlibNativeAd.Builder | 광고 본문 텍스트뷰의 리소스 ID를 설정합니다. |

  - 매개 변수
      - int bodyViewId : 광고 본문 내용을 표시할 TextView의 리소스 ID
  - 반환 타입
    -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.7 setDescriptionViewId()
  | static | 함수명                    | 파라미터                  | 반환 타입                 | 설명                         |
  |--------|------------------------|-----------------------|-----------------------|----------------------------|
  | ❌      | setDescriptionViewId() | int descriptionViewId | AdlibNativeAd.Builder | 광고 설명 텍스트뷰의 리소스 ID를 설정합니다. |

  - 매개 변수
    - int descriptionViewId : 광고 설명을 표시할 TextView의 리소스 ID
  - 반환 타입
      -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.8 setCallToActionViewId()
  | static | 함수명                     | 파라미터                   | 반환 타입                 | 설명                                       |
  |--------|-------------------------|------------------------|-----------------------|------------------------------------------|
  | ❌      | setCallToActionViewId() | int callToActionViewId | AdlibNativeAd.Builder | CTA(Call to Action) 버튼 뷰의 리소스 ID를 설정합니다. |

  - 매개 변수
      - int callToActionViewId : 행동 유도(Call to Action) 버튼의 View 리소스 ID
  - 반환 타입
      -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.9 setRatingBarViewId()
  | static | 함수명                  | 파라미터                | 반환 타입                 | 설명                                |
  |--------|----------------------|---------------------|-----------------------|-----------------------------------|
  | ❌      | setRatingBarViewId() | int ratingBarViewId | AdlibNativeAd.Builder | 별점을 나타낼 RatingBar의 리소스 ID를 설정합니다. |

  - 매개 변수
    - int ratingBarViewId : 광고 별점을 나타낼 RatingBar의 ID
  - 반환 타입
    -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체

- ### 6.19.10 setIconViewId()
  | static | 함수명             | 파라미터           | 반환 타입                 | 설명                          |
  |--------|-----------------|----------------|-----------------------|-----------------------------|
  | ❌      | setIconViewId() | int iconViewId | AdlibNativeAd.Builder | 광고 아이콘 이미지뷰의 리소스 ID를 설정합니다. |

  - 매개 변수
      - int iconViewId : 광고 아이콘 이미지를 표시할 ImageView의 ID
  - 반환 타입
      -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.11 setMediaContentViewId()
  | static | 함수명                     | 파라미터                   | 반환 타입                 | 설명                               |
  |--------|-------------------------|------------------------|-----------------------|----------------------------------|
  | ❌      | setMediaContentViewId() | int mediaContentViewId | AdlibNativeAd.Builder | 광고 미디어(영상/이미지) 뷰의 리소스 ID를 설정합니다. |

  - 매개 변수
      - int mediaContentViewId : 광고 이미지 또는 비디오 콘텐츠를 표시할 View의 ID
  - 반환 타입
    -  AdlibNativeAd.Builder : AdlibNativeAd.Builder 객체
- ### 6.19.12 build() 
  | static | 함수명     | 파라미터 | 반환 타입         | 설명                                                     |
  |--------|---------|------|---------------|--------------------------------------------------------|
  | ❌      | build() | -    | AdlibNativeAd | 지정된 NativeAdView 와 adUnitId를 바탕으로 Native 광고 객체를 생성합니다. |
  - 반환 타입
      - NativeAdView : NativeAdView 객체 생성
- ### 6.19.13 getNativeAdViewId()
  | static | 함수명                   | 파라미터 | 반환 타입 | 설명                            |
  |--------|-----------------------|------|-------|-------------------------------|
  | ❌      | `getNativeAdViewId()` | -    | int   | NativeAd View Resource  ID 반환 |
  - 반환 타입
     - int : NativeAdView View Resource ID 반환
- ### 6.19.14 getAdUnitId()
  | static | 함수명           | 파라미터 | 반환 타입  | 설명                   |
  |--------|---------------|------|--------|----------------------|
  | ❌      | getAdUnitId() | -    | String | 설정된 광고 단위 ID를 반환합니다. |
  - 반환 타입
      - String unitAdId : [센터 에서 발급 받은 AdUnitId](#232-ad_unit_id)
## 6.20 interface AdlibNativeAdListener
- ### 6.20.1 onAdClicked()
  | static | 함수명             | 파라미터 | 반환 타입 | 설명                           |
  |--------|-----------------|------|-------|------------------------------|
  | ❌      | `onAdClicked()` | -    | -     | 광고 Click 이벤트 발생시 callback 함수 |
- ### 6.20.2 onAdClosed()
  | static | 함수명            | 파라미터 | 반환 타입 | 설명                                  |
  |--------|----------------|------|-------|-------------------------------------|
  | ❌      | `onAdClosed()` | -    | -     | 광고뷰가 더 이상 보이지 않을때 발생 하는 callback 함수 |
- ### 6.20.3 onAdFailedToLoad()
  | static | 함수명                  | 파라미터                                        | 반환 타입 | 설명                          |
  |--------|----------------------|---------------------------------------------|-------|-----------------------------|
  | ❌      | `onAdFailedToLoad()` | `String adUnitId`, <br>`AdlibError adError` | -     | 광고 로드에 실패 한 경우의 callback 함수 |
    - 반환 타입
        - int : NativeAdView View Resource ID 반환
- ### 6.20.4 onAdLoaded()
  | static | 함수명            | 파라미터              | 반환 타입 | 설명                    |
  |--------|----------------|-------------------|-------|-----------------------|
  | ❌      | `onAdLoaded()` | `AdlibAd adlibAd` | -     | 광고 로드에 성공 callback 함수 |
    - 반환 타입
        -  AdlibAd adlibAd : [AdlibAd](#69-class-adlibad) AdlibAd 광고 정보를 담고 있는 객체 입니다.
- ### 6.20.5 onAdImpression()
  | static | 함수명                | 파라미터 | 반환 타입 | 설명                          |
  |--------|--------------------|------|-------|-----------------------------|
  | ❌      | `onAdImpression()` | -    | -     | 광고 노출(임프레션)이 기록되었을 때 호출됩니다. |

## 6.20 class NativeMediaContentView
- ### 6.20.1 생성자 NativeMediaContentView() - 직접 뷰 구성
  | static | 함수명                        | 파라미터                                        | 반환 타입                    | 설명                               |
  |--------|----------------------------|---------------------------------------------|--------------------------|----------------------------------|
  | ❌      | `NativeMediaContentView()` | `String contentType`, <br>`Context context` | `NativeMediaContentView` | NativeMediaContentView 생성 하는 생성자 |
    - 매개 변수
        - String contentType : 미디어의 타입 "image" 또는 "video" 들어 갈수 있고 default 값은 "image" 입니다.
        - Context context : View Context
    - 반환 타입
        -  NativeMediaContentView : NativeMediaContentView 객체
- ### 6.20.2 isVideo()
  | static | 함수명       | 파라미터 | 반환 타입     | 설명                                  |
  |--------|-----------|------|-----------|-------------------------------------|
  | ❌      | `isVideo` | -    | `boolean` | 광고뷰가 더 이상 보이지 않을때 발생 하는 callback 함수 |
    - 반환 타입
        -  boolean : Media Type 이 `이미지` 면 `false` `비디오` 면 `true`