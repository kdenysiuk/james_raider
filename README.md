james_raider_repo
===============
## Pre-requirements

1). **Required Java version:** 11

2). **Get Maven installed according Maven documentation:** https://maven.apache.org/

3). **Get Appium and UIAutomator2 installed according Appium documentation:** https://appium.io/docs/en/latest/quickstart/

## Set up

1). **Clone to your local machine**

2). **Execute maven commands**

`mvn -D clean eclipse:eclipse`

3). Set capabilities in -config.properties

`capabilities.udid=<your_udid_device_or_emulator>`

`capabilities.appPath=<path_to_apk_in_your_local_machine>`

4). Start Appium server before run any test

`appium server -p 4723 -a 127.0.0.1 -pa /wd/hub`

5). It is recommended install 'Gherkin' plugin on the IDE 
