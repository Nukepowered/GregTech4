# GregTech 4

### Rewritten port of GregTech 4 to Minecraft 1.7.10 with improved compat and bugfixes
[![Webstie](https://img.shields.io/badge/Nuke-Powered-yellow?labelColor=f4f764&color=525252)](https://nukepowered.info)
[![Discord](https://shields.io/discord/735990310865207387?colorB=7289DA&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHYAAABWAgMAAABnZYq0AAAACVBMVEUAAB38%2FPz%2F%2F%2F%2Bm8P%2F9AAAAAXRSTlMAQObYZgAAAAFiS0dEAIgFHUgAAAAJcEhZcwAACxMAAAsTAQCanBgAAAAHdElNRQfhBxwQJhxy2iqrAAABoElEQVRIx7WWzdGEIAyGgcMeKMESrMJ6rILZCiiBg4eYKr%2Fd1ZAfgXFm98sJfAyGNwno3G9sLucgYGpQ4OGVRxQTREMDZjF7ILSWjoiHo1n%2BE03Aw8p7CNY5IhkYd%2F%2F6MtO3f8BNhR1QWnarCH4tr6myl0cWgUVNcfMcXACP1hKrGMt8wcAyxide7Ymcgqale7hN6846uJCkQxw6GG7h2MH4Czz3cLqD1zHu0VOXMfZjHLoYvsdd0Q7ZvsOkafJ1P4QXxrWFd14wMc60h8JKCbyQvImzlFjyGoZTKzohwWR2UzSONHhYXBQOaKKsySsahwGGDnb%2FiYPJw22sCqzirSULYy1qtHhXGbtgrM0oagBV4XiTJok3GoLoDNH8ooTmBm7ZMsbpFzi2bgPGoXWXME6XT%2BRJ4GLddxJ4PpQy7tmfoU2HPN6cKg%2BledKHBKlF8oNSt5w5g5o8eXhu1IOlpl5kGerDxIVT%2BztzKepulD8utXqpChamkzzuo7xYGk%2FkpSYuviLXun5bzdRf0Krejzqyz7Z3p0I1v2d6HmA07dofmS48njAiuMgAAAAASUVORK5CYII%3D)](https://discord.gg/bpvXStD)
![MC Version](https://img.shields.io/badge/Minecraft-1.7.10-green)
![Forge Version](https://img.shields.io/badge/Forge-10.13.4.1614-green)
[![CurseForge](http://cf.way2muchnoise.eu/versions/gregtech-4.svg)](https://www.curseforge.com/minecraft/mc-mods/gregtech-4) 

#### Please REPORT any bugs, glitches or dupes!

For any questions or discussion about development, please use #dev channel in our discord group.

The mod is planned to keep original look of GregTech 4 with some technical improvements, time to time extra improvements could be done of visual(as UIs, textures), but not minor one. there was only one non-original thing implemented - Large plasma turbine, and was a reason. 

## Features
- Optimized most of code, including any searching, loops, network and sync.
- Fixed and added various compat with other mod
- Some backports from GT5, as new meta generated items already was in code since v4.08s, removed old item as duplicates. Also backported Large Plasma turbine to avoid using multipile small plasma generators.

## Permissions from GregoriusT
![Screenshot](permission.png)

## Contributing & Build guide

You are welcome to participate in development, but if you planning to **add something new** make sure you **disscussed it with @TheDarkDnKTv** before you started write actual code. Since policy is keeping original look, I can disagree with you about changes.

#### Setting up enviroment
```
git clone https://github.com/Nukepowered/GregTech4.git
gradlew setupDecompWorkspace
gradlew eclipse/idea
```

*Do not forget to put `-Dfml.coreMods.load=gregtechmod.common.asm.GT_CoreMod` VM argument for launch, otherwise coremod will not work*

#### Building jar file

```
gradlew build
```
