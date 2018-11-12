# Mind Snake - Game #

This is a simple Snake game created using Processing core and Java. It was created as part of my experiment to see how much can be done in one night and this is the result. The development took few days actually but core of the game, music, graphics and design was done in one night, everything else was just polishing and making game stable. I hope you will enjoy. If you want to download the project and work on it, please read **Development Note** bellow.

![Figure 1][Figure 2]

## Download Executable ##

To run game on you computer you will need to download executable from [Google Drive](https://drive.google.com/drive/folders/18_UJpLAAcQL3gzjHXuRPjsZ7VbrULNVY?usp=sharing) and then follow steps bellow for your system. **Please make sure to run only JARs provided on this github page and do not do any modifications that are not provided here!**

### Windows ###

- Download and install [Oracle Java 8](https://www.java.com/en/download/).
- To confirm that installation was successful open **cmd** and type: `java -version`. System should respond with something like this:

```cmd
    java version "1.8.0_191"
    Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

- After this unpack the **.zip** downloaded from [Google Drive](https://drive.google.com/drive/folders/18_UJpLAAcQL3gzjHXuRPjsZ7VbrULNVY?usp=sharing) and double click on the **app_windows.bat**.

### Linux ###

- Go to [Oracle](https://java.com/en/download/help/linux_x64_install.xml#download) website and follow the instructions.
- Now add installed JRE as JAVA_HOME.
- Go to profile `vim /etc/profile`, add next lines and save file.

```bash
    # Change _USER_ to your system user.
    export JAVA_HOME="/home/_USER_/"
    export PATH=$JAVA_HOME/bin:$PATH
```

- Reboot you system. After this, if you execute the command `java -version` system will *echo* Java version installed.

```bash
    java version "1.8.0_191"
    Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

- After this unpack the **.zip** downloaded from [Google Drive](https://drive.google.com/drive/folders/18_UJpLAAcQL3gzjHXuRPjsZ7VbrULNVY?usp=sharing) to **Documents** and try to double click on **app_linux**.
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

- After this unpack the **.zip** downloaded from [Google Drive](https://drive.google.com/drive/folders/18_UJpLAAcQL3gzjHXuRPjsZ7VbrULNVY?usp=sharing) to **Documents** and try to double click on **app_mac**. System will tell you that application is from unknown source.
- If this does not work, open **terminal** and run:

```bash
    # Change _USER_ to your MAC user.
    cd /Users/_USER_/Documents/mind-snake/
    java -jar mind-snake.jar
```

## Development Note ##

- Application was tested using JDK 11 and it is working with some warnings with BASE64 deprecated.
- **users.collection** and **scores.collection** are used for storing data and they are encrypted using AES128 with random KEY and VECTOR. They are not provided in code and executable files from [Google Drive](https://drive.google.com/drive/folders/18_UJpLAAcQL3gzjHXuRPjsZ7VbrULNVY?usp=sharing) are using randomly generated values that are secret. This way players can not modify data in plane text.
- 2018-09 Eclipse release was used for this project.

[Figure 1]: https://i.imgur.com/WT4AGFM.jpg "Figure 1"

[Figure 2]: https://i.imgur.com/wc5Xkcw.jpg "Figure 2"