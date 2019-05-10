# course 1 (02.05.2019)



# 1 -> selenium dependency

https://www.seleniumhq.org/download/maven.jsp

In order to start using DefaultSelenium or one of the new WebDriver implementations in your Maven project, just add the following dependency to your pom.xml:

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>3.141.59</version>
    </dependency>  


# 2 -> Junit dependency 

https://github.com/junit-team/junit4/wiki/Download-and-Install

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    
    
# 3 -> Download ChromeDriver

http://chromedriver.chromium.org/downloads

ChromeDriver
WebDriver is an open source tool for automated testing of webapps across many browsers. It provides capabilities for navigating to web pages, user input, JavaScript execution, and more.  ChromeDriver is a standalone server that implements the W3C WebDriver standard. ChromeDriver is available for Chrome on Android and Chrome on Desktop (Mac, Linux, Windows and ChromeOS).  


# 4 -> Put your chromedriver executable file into "/opt/" directory or whereever you want

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");





------------

# Course 2 (09.05.2019)

# Prerequisites :


# -> Node JS
    sudo npm install -g npm@7.5.0
# -> Appium("ie:1.7.2")
    npm install -g appium@{VERSION_NUMBER} "ie:1.7.2" (honestly might be better off pulling from source for latest...)
# -> ideviceinstaller
    brew install ideviceinstaller
# -> carthage
    brew install carthage
# -> ios-deploy
    npm install -g ios-deploy
# -> deviceconsole
    npm install -g deviceconsole
# -> xcpretty (Optional)
    gem install xcpretty
 
------------------------------------------------------------------------------------------------------------------------------
# Manual Configuration

Enter WebDriver agent directory with:  

    cd /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent
    sudo mkdir -p Resources/WebDriverAgent.bundle
    sudo mkdir Modules
    sudo chmod -R 777  Scripts
    sudo chmod -R 777  Modules
    sudo ./Scripts/bootstrap.sh

# Java Code Configuration
Add DesiredCapabilies to that code. 
    
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
 
------------------------------------------------------------------------------------------------------------------------------
# Start Listeners
Simulator Listener Execution Command
# -> IOS:
    appium --address 127.0.0.1 --port 4723 --session-override --isolate-sim-device --app "/opt/sahibinden/sahibinden.app"
# -> ANDROID:
    appium --address 127.0.0.1 --port 4723 --session-override --command-timeout 600 --isolate-sim-device --app "/opt/sahibinden/app-debug.apk" --app-pkg "com.sahibinden" --app-activity "com.sahibinden.ui.supplementary.SplashScreenActivity"
# -> Real Device Listener Execution Command

# -> IOS:
    appium --address 0.0.0.0 --port 8001 -U 4ba6b062c167b52ad37c2afe6c8631e720239d21 --session-override --command-timeout 600 --app "/opt/sahibinden/sahibinden.app"
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# -> Additional Setup for Android App Testing
Download the latest Java JDK here (accept the license agreement first). Set 'JAVA_HOME’ to be your JDK path. The bin in that directory should be added to your PATH variable.

Install the Android SDK. Set the ANDROID_HOME environment variable to be your Android SDK path and add the tools and platform-tools folders to your PATH variable.

Install Apache Ant or use the one that comes with the Android Windows SDK in the eclipse\plugins folder. Be sure to add the folder containing Ant to your PATH variable.

Install Apache Maven and set the M2HOME and M2 environment variables. Set M2_HOME to the directory maven is installed in, and set M2 to the bin in that directory. Add the path you used for M2 to your PATH.

To run tests on Windows, you will need to have the Android Emulator booted or an Android Device connected that is running an AVD with API Level 17 or greater. Then run Appium on the command line (via the appium command)
Your test script should ensure that the platformVersion capability corresponds to the emulator or device version you are testing, and that the app capability is an absolute path to the .apk file of the Android app.
 
------------------------------------------------------------------------------------------------------------------------------
# Problems & Solutions:
# -> issue 1 : 'Waiting for WebDriverAgent server to finish loading...'

    https://github.com/appium/appium/issues/6892 ---> SOLUTION
    The problem solved by installing xcuitest-drive VERSION v2.0.36 (downgraded from v2.4.2)
    cd /usr/local/lib/node_modules/appium
    npm uninstall appium-xcuitest-driver
    npm install appium-xcuitest-driver@2.0.36

# -> issue 2 : If sudo /Scripts/bootstrap.sh operation is fail at WebDriverAgent, what should I do?

    https://github.com/appium/appium/issues/7066 ---> SOLUTION
    cd ~
    git clone https://github.com/facebook/WebDriverAgent.git
    sudo ./Scripts/bootstrap.sh
    cd /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver
    rm -rf WebDriverAgent
    mv ~/WebDriverAgent /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent

# -> issue 3 : If sendkeys method throws WDA exception, you should update to appium 1.6.4-beta and change WDA iOS Deployment target to iOS Device target

    https://github.com/appium/appium/issues/7829 ---> SOLUTION
    cd /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent
    open . 
    open Project in Xcode
    WebDriverAgent > Info > Deployment target
