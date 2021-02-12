[Français](https://github.com/Foacs/ribz/blob/master/LISEZMOI.md)
# RIBZ (RIBZ: Inspired By Zombies)

![GitHub top language](https://img.shields.io/github/languages/top/Foacs/ribz)
[![Build Status](https://travis-ci.com/Foacs/ribz.svg?branch=master)](https://travis-ci.com/Foacs/ribz)
[![codecov](https://codecov.io/gh/Foacs/ribz/branch/master/graph/badge.svg)](https://codecov.io/gh/Foacs/ribz)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Foacs_ribz&metric=alert_status)](https://sonarcloud.io/dashboard?id=Foacs_ribz)

![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/foacs/ribz?include_prereleases)
![License](https://img.shields.io/badge/license-CeCILL-blue)
[![javadoc](https://img.shields.io/badge/javadoc-0.1-blue)](http://foacs.ovh/ribz/apidocs/index.php)

Board game application and library created with Java

## Table of Contents
- [General info](#general-info)
- [Technologies](#technologies)
- [Setup](#setup)
- [License](#license)

## General info
Creating with Java an application to play board game. The two main purpose of this project are:
- Proposing a generic java library for board game.
- Implementing a playable board game.

## Technologies
- Java: > 11 _[openJDK-11](https://openjdk.java.net/projects/jdk/11/)_
- Gradle: 5
- LibGDX: 1.9

## Setup
To run this project, install it locally using gradle wrapper:
```shell script
./gradlew build
```

To launch it:
```shell script
./gradlew run
```

To create the JAR file:
```shell script
./gradlew dist
```
The file is available in `desktop/build/lib/ribz-desktop-{VERSION}.jar`

## Documentation
### Where to found documentation
The java documentation of the project is available [here](http://foacs.ovh/ribz/apidocs/index.php)

### How to generate javadoc
In order to generate javadoc, run the following command: 
```shell script
./gradlew ribzdoc
```
The generated documentation can be found in folder `./build/doc/latest`.

## License
> You can check out the full license [here](https://github.com/Foacs/ribz/blob/master/LICENSE.md)

This project is licensed under the terms of the __CeCILL__ license.