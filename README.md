# Sonarqube integration for android library project.

## Intro

For better quality of code and maintainability of code there are various tools available like Android lint, Sonarqube etc,. In this article we will see how to integrate sonarqube for all modules for android project.

## Setup sonarqube on machine:

**Note**: Below setup is done on linux system.

1. Download sonarqube from the sonarqube site : \([https://www.sonarqube.org/downloads/](https://www.sonarqube.org/downloads/)\)
2. Unzip the sonarqube folder.
3. Go to ..path/sonarqube/bin/&lt;platform\_folder&gt;/ \(For example: /sonarqube/bin/linux-x86–64\)
4. Double click startsonar.bat or on linux open terminal in sonarqube folder and run **./sonar.sh start.** “the sonarqube server is started”.
5. Now on browser open ****[**http://localhost:9000**](http://localhost:9000/)**/**. It will open sonarqube dashboard.
6. Login with username/password as **admin/admin**. It will display project dashboard.

With above steps, sonarqube server is now successfully on the machine.

## Integrating sonarqube in android project:

I have created a android project with android library module in it. So the project structure is **app** and **mylibrary**![](https://miro.medium.com/max/30/1*ImNX07zxp9y6xZTA1CUzoA.png?q=20)

![](https://miro.medium.com/max/1000/1*ImNX07zxp9y6xZTA1CUzoA.png)project structure screenshot for reference

1. _**Project level build.gradle**_

Add sonarqube classpath in project level build.gradle.

```text
//sonarqube
classpath "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.6.1"
classpath 'com.dicedmelon.gradle:jacoco-android:0.1.4'
```

_**2. sonarqube.gradle**_

Create a file sonarqube.gradle a gradle file in project & add all the sonarqube related rules in that file. Some basic rules are as given below in the file.

```text
apply plugin: "org.sonarqube"

sonarqube {
    properties {
        def libraries = project.android.sdkDirectory.getPath() + "/platforms/android-23/android.jar" 
        property "sonar.sources", "src/main/java"
        property "sonar.binaries", "build/intermediates/classes/debug"
        property "sonar.libraries", libraries
        property "sonar.tests", "src/test/java, src/androidTest/java"
        property "sonar.java.test.libraries", libraries
        property "sonar.jacoco.reportsPaths", "build/jacoco/testDebugUnitTest.exec"
        property "sonar.java.coveragePlugin", "jacoco"
        property "sonar.junit.reportsPath", "build/test-results/debug"
        property "sonar.android.lint.report", "build/outputs/lint-results"

        property "sonar.host.url", "http://localhost:9000/"
        property "sonar.login", "admin"
        property "sonar.password", "admin"
     
        property "sonar.projectKey", "com.example.sonarqubesample"
        property "sonar.projectName", "SonarSample"
        property "sonar.projectVersion", "1.0.0"    }
}

jacoco {
    toolVersion = "0.7.5.201505241946"
}

android {
    testOptions {
        unitTests.all {
            jacoco {
                includeNoLocationClasses = true
            }
        }
    }
}
```

_**3. Apply sonarqube plugin.**_

Now go to the modules and apply sonarqube plugin in the module gradle files.

Like in app module _build.gradle_ file

```text
apply plugin: 'com.android.application'
apply plugin: 'org.sonarqube'
apply plugin: 'jacoco-android'
apply from: '../sonarqube.gradle'
```

Next in mylibrary module _build.gradle_ file

```text
apply plugin: 'com.android.library'
apply plugin: 'org.sonarqube'
apply plugin: 'jacoco-android'
apply from: '../sonarqube.gradle'
```

Now sync the gradle files. Project will sync successfully.

_**4. Run sonarqube task**_

Open terminal and type below command to execute sonarqube.gradle

```text
$./gradlew sonarqube
```

After the task is success. Open browser and go to localhost:9000

Login with username/password.

In project dashboard you will be able to see the project with other details.![](https://miro.medium.com/max/700/1*-t2ldsiCBFUK3gxez-IPwg.png)project dashboard

When selected Code in the dashboard, it will navigate to new section showing each module code quality.![](https://miro.medium.com/max/700/1*26drnaBxkOeZvtIhfP9AuQ.png)module wise code quality details

Now we have sonarqube report for the project. :-\)

Complete sample code is found at github:

[https://github.com/amey-h/sonarqubesample](https://github.com/amey-h/sonarqubesample)

**Happy Coding !**  


