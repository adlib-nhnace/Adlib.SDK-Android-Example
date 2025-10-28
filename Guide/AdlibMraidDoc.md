# ADLIB MRAID 지원 상태
- [MRAID 3.0 공식 문서](https://www.iab.com/wp-content/uploads/2017/07/MRAID_3.0_FINAL.pdf)
  

## 1.MRAID_ENV
- MRAID_ENV.version : Mraid Version (3.0)
- MRAID_ENV.sdk : NHNACE_ADLIB
- MRAID_ENV.sdkVersion : ADLib SDK 버전
- MRAID_ENV.appId : 현재 앱의 PackageName

## 2.Initialization and Set-up
- ### Initialize 시점
  - AD 마크업 로드가 끝난 시점부터 Mraid 초기화  
  - MRAID Event 'ready' 이벤트를 호출 받으면 'Enable' 상태


## 3.Method
- #### 사용 가능한 Method
  - getVersion()
  - addEventListener(event_name, method)
  - removeEventListener(event_name, method)
  - open(url)
  - close()
  - unload()
  - playVideo(uri)
  - isViewable() -*(deprecated 되었지만 이전 버전 호환을 위해 제공합니다.)
- #### 지원 하지 않는 함수
  - expand
  - resize
  - useCustomClose
  - storePicture
  - createCalendarEvent
  - **VPAID methods** (VPAID 모든 기능 미지원)

## 4.Properties
- #### 사용 가능한 Properties
  - getPlacementType
  - get/set orientationProperties
  - getCurrentAppOrientation
  - getCurrentPosition
  - getDefaultPosition
  - getState
  - getMaxSize
  - getScreenSize
- #### 지원 하지 않는 Properties
   - **Support methods** (Support)
   - get/set expandProperties
   - get/set resizeProperties
   - getLocation

## 5.Event
- #### Callback 받을수 있는 Event
   - Error
   - ready
   - sizeChange
   - stateChange
   - exposureChange
   - audioVolumeChange
   - viewableChange -*(deprecated 되었지만 이전 버전 호환을 위해 제공합니다.)
   

## 6.Example
   - ### Event 수신 받는 방법
     - ready Event 받기
     ```
         mraid.addEventListener('ready', function () {
            // Mraid 준비가 되면 진행 할 코드를 넣어주면 됩니다.         
         });
     ```
      - exposureChange Event 받기
     ```
         mraid.addEventListener('exposureChange', function (exposedPercentage, visibleRectangle, occlusionRectangles) {
               // Mraid 문서 참고 하여 자체적으로 광고의 가시성 여부를 판단 하는 코드를 넣어주세요      
         });
     ```
     

## 7. ExposureChange 상세
- ### ExposureChange란?
  - MRAID 규약에 맞게 구현된 ViewAbility 측정 함수
- ### 활성 생명주기
  - 마크업에서 addEventListener를 등록을 하고 마크업이 로드가 완료된 시점부터 트리거
  - AdlibAdViewContainer 뷰가 소멸 되면 해제 
  - 마크업에서 해당 Event를 remove 하면 해제
- ### ExposureChange 이벤트가 호출되는 조건
  - #### ([MRAID 3.0 공식 문서](https://www.iab.com/wp-content/uploads/2017/07/MRAID_3.0_FINAL.pdf) 61Page Triggers for exposureChange events 참고)
  
  1. 디바이스 화면이 켜진 상태에서만 호출됨 (꺼져있거나, 잠겨있는 상태에서는 호출되지 않음)
  2. 앱의 Activity가 디바이스 전면에 있는 경우(foreground application)에만 호출됨
     * “On Android, an activity is the foreground between calls to the Activity.onResume() and Activity.onPause() methods”
     *  Activity의 상태가 onResume 상태일때만 동작 / onPause() 상태 부터 호출
     * 홈화면으로 가는 경우, 앱을 이용하다가 전화가 온 경우와 같이 화면에서 앱을 사라지는 경우 호출되지 않음
  3. 광고가 화면에서 스크롤, 클리핑 등을 고려하여, 최소 1픽셀이라도 노출된 경우, 호출됨
     * AdlibAdViewContainer 의 ViewTreeObserver에 onDrawListener 를 이용, 즉 해당뷰와 해당 뷰트리에 모든 draw 이벤트 발생시 동작
  4. 광고를 차단하는 dialog-interface가 없는 경우, 호출됨

- ### 이전 버전 호환성 (viewableChange)
  - MRAID3.0 부터는 exposureChagnge Event 콜백을 받아 사용하지만 이전 버전은 viewableChange 이벤트를 사용 
  - viewableChange Event 발동 조건은 View의 가시 상태의 변화가 있을때 마다 계속 호출 (이전 호출후 0.2초가 지나지 않으면 호출 되지 않음)
    - Viewability를 판단 하는 ADLib의 기준 - "AdlibAdViewContainer" View 기준으로 1px 이상 노출 하고 있다면 가시 상태로 판단
- ### ExposureChagnge파라 미터 상세
  - exposedPercentage
    - 배너 광고가 붙어 있는 AdlibAdViewContainer View 를 기준으로 실제 전체 뷰를 100% 이라고 했을때 유저에게 노출되는 면적의 비율을 Percent로 환산한 수치
    - Example
      - 노출되어야 하는 광고 뷰의 면적이 가로 50*100 이라고 했을경우 5000이 면적이고 이 광고가 스크롤이 덜올라가 절반이 가려지게 되면 50% 의 값 return
      - 위 상태에서 광고 영역에 다른 뷰가 1000면적을 덮고 있어 또 가리게 되면 2500에서 1000을 뺀 1500/5000 *100 = 30% 의 exposedPercentage값을 return
  - visibleRectangle
    - 배너 광고가 화면에 그려진 View의 Rectangle 값
    - Example
      
      ```json
      {"x": "노출된 View 왼쪽상단의 X좌표", "y":"노출된 View 왼쪽상단의 Y좌표", "width":"노출된 View의 너비", "height": "노출된 View의 높이"}
      ```
    - 화면에 노출한 View를 기준으로 판단 하는 값이며 화면에 그려졌지만 다른 View의 간섭으로 보이지 않는 영역은 고려되지 않습니다.
  - occlusionRectangle
    - 배너 광고를 가리고 있는 View 들의 Rectangle값 들의 '배열'
    - Example
    - ```json
      [
        {"x": "가리고 있는 View 왼쪽상단의 X좌표", "y": "가리고 있는 View 왼쪽상단의 Y좌표", "width": "가리고 있는 View의 너비", "height": "가리고 있는 View의 높이"},
        {"x": "가리고 있는 View 왼쪽상단의 X좌표", "y": "가리고 있는 View 왼쪽상단의 Y좌표", "width": "가리고 있는 View의 너비", "height": "가리고 있는 View의 높이"},
     
        {"....": "...."},{"....": "...."}
      ]
      ```