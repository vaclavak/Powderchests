# Powderchests

An open source Hypixel Skyblock mod made for powder grinding.

## Features

- Enhances powder grinding experience in Hypixel Skyblock
- Lightweight and easy to use
- **Automatic configuration persistence** - Display settings are now automatically saved and loaded between sessions

## Installation

1. Download the latest release from the [Releases](#) page.
2. Place the mod `.jar` file into your Minecraft `mods` folder.
3. Launch Minecraft with Forge 1.8.9.

## Commands

- **`/cc check`** -> Shows the current chest counter.
- **`/cc reset`** -> Resets the chest counter.
- **`/cc savecounter`** <directory> -> Saves the current counter value to a .txt file in the specified directory.
- **`/cc loadcounter`** <directory> -> Loads a previously saved counter value from a .txt file in the specified directory.
- **`/cc display on`** -> Enables on-screen display of the chest counter. *(Settings automatically saved)*
- **`/cc display off`** -> Disables on-screen display of the chest counter. *(Settings automatically saved)*
- **`/cc display <X>`** <Y> -> Sets the position of the on-screen counter display (X and Y coordinates). *(Settings automatically saved)*

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## Configuration

The mod now automatically saves your display preferences (position and on/off state) in a configuration file located at:
`.minecraft/Powderchests/config.txt`

This means your display settings will persist between Minecraft sessions. The configuration is automatically:
- **Loaded** when the mod starts up
- **Saved** whenever you change display settings using the `/cc display` commands
- **Reset to defaults** if the config file is missing or corrupted

## Important
The **chest counter value** does NOT save between sessions! If you quit Minecraft, the counter will reset to 0. However, your **display settings** (position and on/off state) are now automatically saved and will be restored when you restart the game.

## License

This project is licensed under the [Apache License 2.0](LICENSE).

---

Made with ❤️ for the Hypixel Skyblock community.
