NeoCatLib is a library that avaiable on Forge.
I will use this for my mods but you can use it too in your projects, if you need.
Feautures:
Text appearing and disapearing on screen.

Implementation to your project:
Im don't using mavens for now, so we are going to import mod from libs folder.
1)Create folder called "libs" in your project directory(where build.gradle located)

2) build.gradle:

First we need to add libs folder as a repository:

repositories {
    flatDir {
        dir 'libs'
    }
}

Secondly you can simply implement API by add this line to dependencies:

implementation fg.deobf("net.nc.neocatlib:neocatlib:${minecraft_version}-${neocatlibver}")

So, it would look like:

dependencies {
    // NeoCatLib
    implementation fg.deobf("net.nc.neocatlib:neocatlib:${minecraft_version}-${neocatlibver}")
}

3)gradle.properties

By default, in forge 1.20.x there are a line:

minecraft_version=1.20.2

So, if you don't have this line then add it.

Also add it:

# NeoCatLib
neocatlibver = 1.0.0

Usage:
All common functions of this API located in NeoCatLibUtils class.
For example, you can show player a message on screen:
NeoCatLibUtils.sendGUIMessage("Hey", event.getPlayer());