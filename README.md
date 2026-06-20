# Obsidian Launcher

<img src="./app_pojavlauncher/src/main/res/drawable/ic_mojo_full.png" align="left" width="150" height="150" alt="Obsidian Launcher logo">

[![Android CI](https://github.com/MojoLauncher/MojoLauncher/workflows/Android%20CI/badge.svg)](https://github.com/MojoLauncher/MojoLauncher)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/m/MojoLauncher/MojoLauncher)](https://github.com/MojoLauncher/MojoLauncher)
[![Discord](https://img.shields.io/discord/1365346109131722753.svg?label=discord&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/EPppgEMcJs)

* Obsidian Launcher is a launcher, based on PojavLauncher, that allows you to play Minecraft: Java Edition on your Android device!

* It can run almost every version of Minecraft, allowing you to use .jar only installers to install modloaders such as Forge and Fabric and mods like OptiFine.

## Navigation
- [Introduction](#introduction)
- [Getting Obsidian Launcher](#getting-obsidian-launcher)
- [Building](#building) 
- [Current roadmap](#current-roadmap) 
- [License](#license) 
- [Credits & Third party components and their licenses](#credits--third-party-components-and-their-licenses-if-available)

## Introduction 
* Obsidian Launcher is a Minecraft: Java Edition launcher for Android based on PojavLauncher.
* This launcher can launch almost all available Minecraft versions ranging from rd-132211 to 26.x snapshots (including Combat Test versions). 
* Modding via Forge and Fabric are also supported. 

## Getting Obsidian Launcher

You can get Obsidian Launcher via two methods:

1. You can get the prebuilt app from the releases section.

2. You can [build](#building) from source.

## Building   
* Build the launcher (it will automatically download all required components)
./gradlew :app_pojavlauncher:assembleDebug
(Replace ./gradlew with .\gradlew.bat if you are building on Windows).

## Current roadmap
- [x] Instance system in favor of profiles
- [x] Out-of-the box 1.21.5 support
- [x] mrpack/CurseForge zip import
- [ ] LTW: resolve issues with Create
- [ ] LTW: enable compute shader/image extensions
- [ ] LTW: switch to a color-renderable format for framebuffers
- [ ] Modpack/mod management tool
- [ ] MMC-compatible instance import
- [ ] Patch-on-dlopen for mod native libraries
- [ ] Replace Holy-GL4ES 1.1.5 with KW

## Known Issues
- Some physical mice may have very slow mouse speed
- On Holy GL4ES, large texture atlases may be distorted (resulting in stretched/blocky textures in modpacks)
- Probably more, that's why we have a bug tracker ;) 

## License
- Obsidian Launcher is licensed under GNU LGPLv3.

## Credits & Third party components and their licenses (if available)
- PojavLauncher: GNU LGPLv3 License
- Boardwalk (JVM Launcher): Unknown License/Apache License 2.0 or GNU GPLv2.
- Android Support Libraries: Apache License 2.0.
- GL4ES: MIT License.
- OpenJDK: GNU GPLv2 License.
- LWJGL3: BSD-3 License.
- Mesa 3D Graphics Library: MIT License.
- pro-grade (Java sandboxing security manager): Apache License 2.0.
- bhook (Used for exit code trapping): MIT license.
- Authlib-Injector (Used for authorisation via ely.by): AGPL-3.0.
- alsoft (Audio output library): GNU LIBRARY GENERAL PUBLIC LICENSE and modified PFFFT.
- oboe: Apache License 2.0.
- Thanks to Mineskin for providing Minecraft avatars.
