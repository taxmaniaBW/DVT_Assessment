<h1>DVT Coding Assesment by Gofaone Mokgethisi</h1>

This is a weather app which takes advantage of location services to get the current weather and 5 day forecast. 

## Architecture

The app follows MMVM + Clean architecture with clear components and allows for easy testability and expansion. 
It is built with JetPack compose and using flows for communication between viewmodel and Composables. 

The architecture and choice of libraries is largely inspired by Kotlin Multiplatform Mobile (KMM) which is a native
cros platform framework built with Kotlin. The framwork pushes for strict clean architecture as code is shared between 
iOS and Android. This framework might just be the future of cross platform development as business logic is easily shared 
pushing for faster and consistent releases.

## Dependecies

Dependecies are defined in the *Deps.kt* file with their Versions on *Versions.kt*. 
This allows for easy readability.

  
  - [MATERIAL3] 
  - [JUNIT]() -> Standard testing library
  - [MOCKK]() -> For mocking objects
  - [KTOR_MOCK]()-> For mocking Ktor
  - [KOTEST]()
  - [KOTEST_ASSERTIONS]() -> Easy assertions 
  - [ANDROID_X_TESTING]() -> Testing Android framework
  - [KOTLINX_CO_TEST]() -> Coroutines testing library
  - [KTOR]() -> For making network calls
  - [KOTLINX_JSON]() -> Serialization
  - [KOIN]() -> Dependecy Injection
  - [KOIN_COMPOSE]() -> Injecting into compose

  ###TOOLS USED

  -[JsonToKotlin]() -> Plugin to create data classes for the models
  -[WhatFontIs](https://www.whatfontis.com/) -> Web tool to identify font from Image

## Usage

### Code

1. Clone the project
2. Add ApiKey to ./util/Constants
3. Run Project

#### BEFORE INSTALLING 

Make sure Google Safe Protect is turned off from Play Store as this might block installation 

See https://support.google.com/nexus/answer/2812853?hl=en#zippy=%2Chow-google-resets-permissions-for-unused-apps

The apk can be downloaded [HERE](https://drive.google.com/file/d/15bxdftKDDLZFDnNaX8YglagNyZq2uj3u/view?usp=drive_link) 

  ### Features

  #### Current Weather

  Accept Location Permission Request, First run will require accepting location permissions, accept them to use the app.
  ![Permissions](https://github.com/taxmaniaBW/DVT_Assessment/blob/main/app_permissions.png)
  
  The app automatically loads when run with a shimmer effect while calling the api. On success the screen is updated,
  changing the theme(background color and image) as per the returned weather condition. The weather forecast shows only the next 5 days.
  eg ![ThunderStorm Returned From API](https://github.com/taxmaniaBW/DVT_Assessment/blob/main/api_success.png)
  
  


