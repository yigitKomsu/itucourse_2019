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



