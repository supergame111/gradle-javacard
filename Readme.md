Compiling javacard applets with gradle
-------------------------------------

[![Build Status](https://travis-ci.org/fidesmo/gradle-javacard.svg?branch=master)](https://travis-ci.org/fidesmo/gradle-javacard)

This plugins allows for translation of compiled class files to the converted archive format. These files
can be used for installation on smart and sim cards supporting SUN/Oracle JavaCard Technology.

Features
--------

  - convert a package of class files into a cap file
  - specify multiple classes implementing Applet for later installation
  - add external EXP files to build

Usage
-----

Include the new version of the plugin from OSS Sonatype snapshot by adding the following to your
`build.gradle`:

    apply plugin: 'javacard'

    buildscript {
        repositories {
            maven { url 'http://releases.marmeladburk.fidesmo.com/' }
        }

        dependencies {
            classpath 'com.fidesmo.gradle:javacard:0.2.3'
        }
    }

    javacard {
        cap {
             aid = '0x12:0x34:0x00:0x00:0x00:0x01'
             packageName = 'com.example.my.jc.code'
             applet {
                aid = '0x12:0x34:0x00:0x00:0x00:0x01:0x01'
                className = 'ClassImplementingApplet'
             }
             version = '1.0'
         }
     }


### Installing the Java Card Development Kit

Before compiling you need to download the JavaCard SDK. The current implementation is tested using
Linux and the `Java Card Development Kit 2.2.2`. It is supposed to work also for all other `Java
Card Development Kit 2.x.x` under MacOSx, Windows and Linux. **Attention:** The 3.x.x version of the
JCDK is not yet supported. The development kit can be downloaded on the Oracle
[Website](https://www.oracle.com/technetwork/java/embedded/javacard/downloads/javacard-sdk-2043229.html)
and must be unpacked

     # Assumes file to be downloaded as java_card_kit-2_2_2-linux.zip
     unzip java_card_kit-2_2_2-linux.zip
     cd java_card_kit-2_2_2/
     unzip java_card_kit-2_2_2-rr-bin-linux-do.zip
     unzip java_card_kit-2_2_2-rr-ant-tasks.zip
     export JC_HOME="${PWD}/java_card_kit-2_2_2"

### Convert applications to Java Card

The specified package is converted from jvm byte code to javaCard byte code, by running the
`convertJavaCard` task.

     # ensure JC_HOME is set correctly
     gradle convertJavacard

To add external exp files to your build, add a dependencies section to your gradle.build and add the
corresponding paths to the javacardExport configuration.


     dependencies {
         javacardExport file('../path/to/exp/files/dir')
     }
