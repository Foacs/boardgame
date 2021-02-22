_[English](https://github.com/Foacs/ribz/blob/master/README.md)_
# RIBZ (RIBZ: Inspired By Zombies)

![GitHub top language](https://img.shields.io/github/languages/top/Foacs/ribz)
[![Build Status](https://travis-ci.com/Foacs/ribz.svg?branch=master)](https://travis-ci.com/Foacs/ribz)
[![codecov](https://codecov.io/gh/Foacs/ribz/branch/master/graph/badge.svg)](https://codecov.io/gh/Foacs/ribz)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Foacs_ribz&metric=alert_status)](https://sonarcloud.io/dashboard?id=Foacs_ribz)

![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/foacs/ribz?include_prereleases)
![License](https://img.shields.io/badge/license-CeCILL-blue)
[![javadoc](https://img.shields.io/badge/javadoc-0.1-blue)](http://foacs.ovh/ribz/apidocs/index.php)

Application et library de jeu de plateau créé avec Java.

## Table des matières
- [Informations générales](#informations-gnrales)
- [Technologies](#technologies)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Licence](#licence)

## Informations générales
Créer avec Java une application pour jouer à des jeux de plateau. Les deux principaux objectifs de ce projet sont: 
- Proposer une librairie Java générique pour les jeux de plateau.
- Implémenter un jeux de plateau jouable, en utilisant ladite librairie.

## Technologies
- Java: > 11 _[openJDK-11](https://openjdk.java.net/projects/jdk/11/)_
- Gradle: 5
- LibGDX: 1.9

## Configuration
Pour executer ce projet, installer le localement en utilisant le wrapper gradle:
```shell script
./gradlew build
```

Pour le lancer:
```shell script
./gradlew run
```

Pour creer le ficher JAR:
```shell script
./gradlew dist
```
Le fichier est disponible dans `desktop/build/lib/ribz-desktop-{VERSION}.jar`

## Documentation
### Où trouver la documentation
La documentation Java est disponible [ici](http://foacs.ovh/ribz/apidocs/index.php)

### Comment générer la javadoc
Pour générer la javadoc, executez la commande:
```shell script
./gradlew ribzdoc
```
La documentation générée peut être trouvé dans le dossier `./build/doc/latest`.

## Licence
> Vous pouvez accéder à la version complète de la licence [ici](https://github.com/Foacs/ribz/blob/master/LICENCliE.md)

Ce projet est sous les termes de la licence __CeCILL__.