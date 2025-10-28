# 타사 AD SDK를 통한 ADLIB Adapter 연동 가이드

## 목차
- [1. 소개](#1-소개)
- [2. ADLIB 계정 생성 및 인벤토리 등록](#2-adlib-계정-생성-및-인벤토리-등록)
    - [2.1 ADLIB 계정 생성](#21-adlib-계정-생성)
    - [2.2 인벤토리 등록](#22-인벤토리-등록)
        - [2.2.1 APP_ID](#221-app_id)
        - [2.2.2 AD_UNIT_ID](#222-ad_unit_id)
- [3. AdMob 연동](#3-admob-연동)
- [4. AppLovin 연동](#4-applovin-연동)
- [5. ironSource 연동](#5-ironsource-연동)

---

## 1. 소개

이 가이드는 **기존 타사 광고 SDK(AdMob, AppLovin, ironSource)** 환경에서 **ADLIB 광고를 불러오기 위한 Custom Adapter 연동 방법**을 안내합니다.

> ⚠️ 본 가이드는 ADLIB SDK가 타사 광고 네트워크를 미디에이션하는 방식이 아닙니다.  
> 반대로, **타사 플랫폼의 미디에이션 환경에서 ADLIB 광고를 연동**하는 방식입니다.

---

## 2. ADLIB 계정 생성 및 인벤토리 등록

### 2.1 ADLIB 계정 생성

- [ADLIB 마케팅 센터](https://mkt.adlibr.com)에서 계정을 생성합니다.
- 개발 문의는 [고객센터](https://www.adlibr.com/contactUs)를 통해 접수하세요.

### 2.2 인벤토리 등록

1. 로그인 후 앱(인벤토리)을 등록합니다.
2. 등록이 완료되면 **APP_ID** 및 광고 형식별 **AD_UNIT_ID**가 발급됩니다.

> 구버전 SDK(5.1.5 이하)를 사용하는 경우, Inventory ID(MID)도 함께 표기됩니다.

#### 2.2.1 APP_ID

- 앱 고유 식별자 (24자의 영문/숫자 조합)
- SDK 초기화에 필수로 사용되며, 광고 요청과 연동의 기반이 되는 값입니다.

#### 2.2.2 AD_UNIT_ID

- 광고 형식별 고유 식별자
- 광고 요청 시 파라미터로 전달되며, 잘못된 ID 사용 시 광고 미노출 또는 수익 누락이 발생할 수 있습니다.

---

## 3. AdMob 연동

### 3.1 Maven 저장소 설정

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

### 3.2 의존성 추가

#### Kotlin

```kotlin
dependencies {
    implementation("com.nhnace:adlib:6.0.2")
    implementation("com.nhnace.adlib.adapter:admob:6.0.2.0")
}
```

#### Groovy

```groovy
dependencies {
    implementation 'com.nhnace:adlib:6.0.2'
    implementation 'com.nhnace.adlib.adapter:admob:6.0.2.0'
}
```

### 3.3 관련 문서

- [AdMob Custom Event 생성 가이드 (한글)](https://developers.google.com/admob/android/custom-events/setup?hl=ko)
- [AdMob Custom Event 생성 가이드 (영문)](https://developers.google.com/admob/android/custom-events/setup?hl=en)
- [AdMob Custom Event 설정 가이드 (한글)](https://support.google.com/admob/answer/13407144?hl=ko)
- [AdMob Custom Event 설정 가이드 (영문)](https://support.google.com/admob/answer/13407144?hl=en)

### 3.4 Adapter 구성

- ADLIB Adapter를 사용하면 `com.google.android.gms.ads.mediation.Adapter`를 직접 구현할 필요 없이 간단히 연동 가능합니다.
- 클래스 명 입력 시 아래와 같이 설정하세요:

```
com.nhnace.adlib.adapter.admob.AdmobAdapter
```
- ADLIB의 **AppId**와 **AdUnitId**는  
   [APP_ID](#221-app_id), [AD_UNIT_ID](#222-ad_unit_id) 항목을 참고하여 발급받으세요.
- **AppId**는 `AndroidManifest.xml`의 `<meta-data>` 항목에 등록합니다.
- **AdUnitId**는 AdMob의 Custom Event 설정 화면에서 `parameter` 항목에 입력해야 합니다.


## 3.5 Adapter 설정

1. AdMob 대시보드에서 **Mediation** 버튼을 눌러 미디에이션 그룹을 생성합니다.
   ![image](https://github.com/user-attachments/assets/6815f352-edf2-4084-ad3f-e1d4f123c0a3)  
   ![image](https://github.com/user-attachments/assets/af6d246a-ad23-4ff9-bbc4-65d08e84ee36)

2. 플랫폼을 **Android**로 설정하고, 원하는 광고 타입을 선택한 후 **Continue** 버튼을 클릭합니다.  
   ![image](https://github.com/user-attachments/assets/b994f4b6-a072-459a-884b-9a54dcca6c4d)

3. 필요한 정보를 입력한 후 **Add ad units**를 눌러, 미리 설정된 Application 및 AdUnitId를 등록합니다.  
   ![image](https://github.com/user-attachments/assets/8168daf4-b958-4303-a07f-b88480bac95c)

4. **Add custom event** 버튼을 눌러 원하는 **Label** 명을 입력하고 Custom Event를 추가합니다.  
   ![image](https://github.com/user-attachments/assets/b668ba7e-0677-4212-a7ff-ebbf12cf6b12)

5. **Add or edit mappings** 버튼을 눌러, Custom Event의 매핑 화면으로 이동합니다.  
   ![image](https://github.com/user-attachments/assets/3391ab2b-98cd-498c-ad65-b01e9a6b5f11)

    아래 항목에 따라 Custom Event 매핑을 완료합니다:

    - **Mapping name**: Custom Event를 식별할 수 있는 이름 입력
    - **Network eCPM**: 원하는 eCPM 값 입력
    - **Class Name**:
      ```
      com.nhnace.adlib.adapter.admob.AdmobAdapter
      ```
    - **Parameter**: ADLIB에서 발급받은 `AD_UNIT_ID` 입력  
      ※ 현재 생성 중인 Custom Event와 **동일한 광고 타입의 AD_UNIT_ID**를 입력해야 합니다.

---

## 4. AppLovin 연동

### 4.1 Maven 저장소 설정

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

### 4.2 의존성 추가

#### Kotlin

```kotlin
dependencies {
    implementation("com.nhnace:adlib:6.0.2")
    implementation("com.nhnace.adlib.adapter:applovin:13.1.0.0")
}
```

#### Groovy

```groovy
dependencies {
    implementation 'com.nhnace:adlib:6.0.2'
    implementation 'com.nhnace.adlib.adapter:applovin:13.1.0.0'
}
```

### 4.3 관련 문서

- [AppLovin Custom Network 가이드](https://developers.axon.ai/en/max/mediated-network-guides/integrating-custom-sdk-networks/)

### 4.4 Adapter 구성

- ADLIB Adapter를 사용하면 `com.applovin.mediation.adapters.MediationAdapterBase`를 직접 구현할 필요 없습니다.
- 클래스 명 입력 시 아래와 같이 설정하세요:

```
com.nhnace.adlib.adapter.applovin.AppLovinAdapter
```

### 4.5 Adapter 설정

1. 아래 이미지의 네트워크 탭으로 들어 갑니다.
   <br/>![image](https://github.com/user-attachments/assets/c122104d-b58e-4e87-834f-11ae8cca6006)
2. 많은 광고 Network 사의 제품들이 나오는데요 가장 하단으로 스크롤을 내리면 ```Click here to add a Custom Network``` 버튼을 누릅니다. 
   <br/>![image](https://github.com/user-attachments/assets/886d3ec0-280e-4e7f-a1fd-275197bcaec2)
3. 아래 이미지 처럼 Custom Network Name 을 적당한 이름을 입력후 Android Class Name 에는 ```반드시``` 아래 클래스명을 입력 해주세요.
    ```
    com.nhnace.adlib.adapter.applovin.AppLovinAdapter
    ```
   <br/>![image](https://github.com/user-attachments/assets/faebc16c-af99-4ad3-8e48-e31170518a26)
4. 아래 이미지 처럼 내가 등록한 네트워크가 보인다면 등록이 완료 성공 입니다.
   <br/>![image](https://github.com/user-attachments/assets/410b4697-7a8f-47db-920f-f85f1b813106)
5. 다시 앱러빈 대시보드 홈 사이드 매뉴바에서 Ad Units 탭을 눌러 관리 탭으로이동을 합니다.
   <br/>![image](https://github.com/user-attachments/assets/52ef7f8a-003b-4224-8c96-0b19f571698e)
6. ```Create New Ad Unit```를 눌러 새로운 Ad Unit 을 생성합니다.
7. 아래 이미지 처럼 적당한 ad unit 이름을 기입하시고 원하는 광고 타입을 설정 해주시면 됩니다.
   <br/>![image](https://github.com/user-attachments/assets/7d126358-7260-4128-a23b-03359ef1337e)
8. ad unit 설정 창에서 스크롤을 아래로 내리다 보면 ```Custom Networks``` 이라는 항목이 나오는데 이것을 펼쳐줍니다.
9. 그리고 위에서 설정한 Custom Network 를 또펼쳐주고 Status 토글 버튼을 눌러 활성화 해주세요.
   <br/>![image](https://github.com/user-attachments/assets/1e8ec5f3-81ac-4104-b175-f552b7ad63d6)
10. 토글을 활성화를 하게 되면 아래 처럼 정보를 입력을 할수 있고 정보를 기입하고 Ad Unit 설정을 완료 해주시면 등록이 완료 됩니다.
  <br/>![image](https://github.com/user-attachments/assets/89ffab64-52fc-45b2-a6c7-9642efa71b29)
  - AppId : ADLIB AppId를 입력 해주세요
  - PlacementID : ADLIB AD_UNIT_ID 를 입력해주세요
  - CustomParameter : 애드립 에선 사용하지 않으니 필요에 의해 사용하시면 됩니다.
  - CPM Price : 적정한 eCPM 값을 입력 해주세요.
  - Country Targeting : 타게팅 원하는 국가 정보를 입력 해주세요.

---

## 5. ironSource 연동

### 5.1 Maven 저장소 설정

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

### 5.2 의존성 추가

#### Kotlin

```kotlin
dependencies {
    implementation("com.nhnace:adlib:6.0.2")
    implementation("com.nhnace.adlib.adapter:ironsource:8.7.0.0")
}
```

#### Groovy

```groovy
dependencies {
    implementation 'com.nhnace:adlib:6.0.2'
    implementation 'com.nhnace.adlib.adapter:ironsource:8.7.0.0'
}
```

### 5.3 관련 문서

- [ironSource Custom Network 가이드](https://developers.is.com/ironsource-mobile/android/custom-networks-ironsource-mediation/)
- [ironSource Custom Adapter 등록 가이드](https://developers.is.com/ironsource-mobile/android/register-network-custom-adapter/#step-1)

> 위 공식 가이드는 참고용이며, ADLIB Adapter는 이미 구현되어 있으므로 아래 절차에 따라 연동하시면 됩니다.

### 5.4 Adapter 구성

- ADLIB Adapter를 사용하면 `com.ironsource.adapters.custom.adlib.ADLIBCustomAdapter`를 직접 구현할 필요 없습니다.
- 직접 구현시 아래 이미지를의 패키지 이름과 클래스명을 사용하여 구현 해주셔야합니다.
  <br/>![image](https://github.com/user-attachments/assets/13dc62f2-bf2c-4922-820d-a0090994279d)


### 5.5 Adapter 설정
1. https://platform.ironsrc.com/partners/next/networks 로 접속 또는 사이드바 매뉴의 네트워크 클릭해주세요
  <br/>![image](https://github.com/user-attachments/assets/e7775d90-9443-4341-b77e-b1d4b6b46c2a)
2. 가장 하단에 ```+Add custom network```버튼을 눌러 ```Ad network setup``` 화면으로 이동한다.
  <br/>![image](https://github.com/user-attachments/assets/3d5c0234-8f3d-4809-aa7d-deff7fa0b051)
3. Network key 를 입력 하라는 화면이 나옵니다. 이때 ```15c099739``` 이 값을 입력 해주시면 됩니다.
4. 아래 이미지 처럼 "ADLIB" 이라는 네트워크가 추가가 되었다면 성공입니다.
  <br/>![image](https://github.com/user-attachments/assets/4632adef-5133-40ab-af57-3b17287bdcb0)

5. 사이드 매뉴바에서 ```Instances``` 매뉴를 눌러 인스턴스 설정 창으로 이동을 합니다.
  <br/>![image](https://github.com/user-attachments/assets/3ac51d54-8a98-4412-ba97-a1b2a7631db3)
6. 인스턴스들중 ADLIB 을 눌러 설정화면으로 이동합니다. 
  <br/>![image](https://github.com/user-attachments/assets/eef69382-f910-45ee-97ff-65938d372c17)
7.  아래 이미지 항목들을 채워서 세팅 해주면 됩니다.
  <br/>![image](https://github.com/user-attachments/assets/9399c624-d708-4324-a128-81836e2391f2)
  - AppId : ADLIB APP_ID 값 입력
  - Instance name : 인스턴스 이름 입력
  - adUnitId : ADLIB AD_UNIT_ID 입력
  - Rate : eCPM 입력
