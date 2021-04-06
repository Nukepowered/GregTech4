# GregTech 4

### Rewritten port of GregTech 4 to Minecraft 1.7.10 with improved compat and bugfixes
#### Please REPORT any bugs, glitches or dupes!

**[Our website](https://nukepowered.info)**

[Discord Channel](https://discord.gg/bpvXStD)

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
#### Building jar file

```
gradlew build
```