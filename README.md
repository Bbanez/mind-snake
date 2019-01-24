# Mind Snake - Game #

This is a simple Snake game created using Processing core and Java. It was created as part of my experiment to see how much can be done in one night and this is the result. The development took few days actually but core of the game, music, graphics and design was done in one night, everything else was just polishing and making game stable. I hope you will enjoy. If you want to download the project and work on it, please read **Development Note** bellow.

![Figure 1][Figure 2]

## About development ##

As already said, game was developed using Processing core libraries and Java inside Eclipse IDE. Since Processing is not developed to be used for creating complex GUIs, this project turned out to be very complex and complicated but at the same time very interesting. First of all project started with a blank page, therefore first step was to create typical components like buttons, input fields, layout managers and so on. Next step was to create game area and game mechanics. I decided to divide game area into grid and create tick engine that will move snake in the game area and apply game rules. After this was done, I created system that will manage user profiles and scoring system that will keep track of all games that user have played, best score, play time and so one. With this done, only thing left was to make music and sound effects and to design elements to look decent. In my [blog](https://www.branislav.xyz) I will go into detail about game development.

## How to play ##

Here is a short video showing you how to use the game.

[![Video 1](https://img.youtube.com/vi/1OSlpoNaBD4/0.jpg)](https://www.youtube.com/watch?v=1OSlpoNaBD4)

## Download Executable ##

To run game on you computer you will need to download an executable from [Dropbox](https://www.dropbox.com/sh/ef16jxg1d5s8t3k/AAA1fIa9ALpNPWiYWWQ4xK7Ia?dl=0) and then follow steps bellow for your system. **Please make sure to run and use only JARs provided on this github page!**

### Windows ###

- Download and install [Oracle Java 8](https://www.java.com/en/download/).
- To confirm that installation was successful open **cmd** and type: `java -version`. System should respond with something like this:

```cmd
    java version "1.8.0_191"
    Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

- After this unpack the **.zip** downloaded from [Dropbox](https://www.dropbox.com/sh/ef16jxg1d5s8t3k/AAA1fIa9ALpNPWiYWWQ4xK7Ia?dl=0) and double click on the **app_windows.bat** or **mind-snake.jar**.
- If previous step did not work, open *cmd* or *PowerShell* and move to directory where you have unpacked the **ZIP** file.

```cmd
cd /path/to/mind-snake/
```

- In here, run the following command: `java -jar mind-snake.jar`

### Linux ###

- Go to [Oracle](https://java.com/en/download/help/linux_x64_install.xml#download) website and follow the instructions.
- Now add installed JRE as JAVA_HOME.
- Go to profile `vim /etc/profile`, add next lines and save file.

```bash
    # Change _USER_ to your system user.
    export JAVA_HOME="/home/_USER_/path/to/jdk"
    export PATH=$JAVA_HOME/bin:$PATH
```

- Reboot you system. After this, if you execute the command `java -version` system will *echo* Java version installed.

```bash
    java version "1.8.0_191"
    Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

- After this unpack the **.zip** downloaded from [Dropbox](https://www.dropbox.com/sh/ef16jxg1d5s8t3k/AAA1fIa9ALpNPWiYWWQ4xK7Ia?dl=0) to **Documents** and try to double click on **app_linux**.
- If this does not work, open **terminal** and run:

```bash
    # Change _USER_ to your Linux user.
    cd /home/_USER_/Documents/mind-snake/
    java -jar mind-snake.jar
```

### MAC ###

- Download and install [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- To confirm that installation was successful open **terminal** and type: `java -version`. System should respond with something like this:

```bash
    java version "1.8.0_191"
    Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

- After this unpack the **.zip** downloaded from [Dropbox](https://www.dropbox.com/sh/ef16jxg1d5s8t3k/AAA1fIa9ALpNPWiYWWQ4xK7Ia?dl=0) to **Documents** and try to double click on **app_mac**. System will tell you that application is from unknown source.
- If this does not work, open **terminal** and run:

```bash
    # Change _USER_ to your MAC user.
    cd /Users/_USER_/Documents/mind-snake/
    java -jar mind-snake.jar
```

## Development Note ##

- Application was tested using JDK 11 and it is working with some warnings with BASE64 deprecated.
- **users.collection** and **scores.collection** are used for storing data and they are encrypted using AES128 with random KEY and VECTOR. They are stored in application JAR as a static value, therefore they are not safe since decompiling JAR will reveal there values.
- 2018-09 Eclipse release was used for this project.

[Figure 1]: https://i.imgur.com/WT4AGFM.jpg "Figure 1"

[Figure 2]: https://i.imgur.com/wc5Xkcw.jpg "Figure 2"

[Dropbox]: https://www.dropbox.com/sh/ef16jxg1d5s8t3k/AAA1fIa9ALpNPWiYWWQ4xK7Ia?dl=0 "Dropbox"