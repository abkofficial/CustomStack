# Hytale Mod Template

A starting template for creating mods for Hytale using Java and Gradle.

## Getting Started

To use this template for your own mod, follow these steps to configure it for your local environment:

### 1. Configure the Game Path
Open `gradle.properties` in the root of the project and change the `hytaleHome` property to point to where Hytale is installed on your computer.

```properties
# Change this to your installation path:
hytaleHome=C:/Path/To/Hytale/install
```

### 2. Update Mod Information
In the same `gradle.properties` file, update the following properties with your own information:
- `group`: Your developer name or team name (e.g., `com.yourname`).
- `name`: The name of your mod.
- `version`: The version of your mod.
- `mod_description`: A short description of your mod.
- `website`: Your website or GitHub repository link.
- `entry_point`: The fully qualified name of your main class (e.g., `com.yourname.yourmod.Main`).

### 3. Change the Package Name
The default source code is located in `src/main/java/com/microgamedev/hytalemodname/`. 

You must rename these folders to match the `entry_point` you defined in step 2. 
For example, if your entry point is `com.myname.mymod.Main`, you should rename the directories to `src/main/java/com/myname/mymod/` and ensure the `package` declaration at the top of your Java files matches:
```java
package com.myname.mymod;
```

## Running the Server
This template includes a pre-configured IntelliJ IDEA Run Configuration (`HytaleServer`). 
Once your paths are set correctly, you can simply select this run configuration in your IDE and hit "Run" or "Debug" to start the Hytale server with your mod loaded.
