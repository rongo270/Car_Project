# Car Project – Android Game

🚗 **Car Project** is an Android arcade-style game where the player controls a steve trying to avoid falling enemies. As the player progresses, new levels introduce different enemies and backgrounds.

## 📸 Screenshots
![Screenshot_20250502-164146_Car_Project 1](https://github.com/user-attachments/assets/d205bee2-360d-4826-bbc6-c211d975089d)
![Screenshot_20250502-170752_Car_Project 1](https://github.com/user-attachments/assets/86693450-2987-4837-a80b-56430dfa4732)
![Screenshot_20250502-164325_Car_Project 1](https://github.com/user-attachments/assets/684fa715-3a51-4fbd-80b2-51bc0757fdc8)




## 🎮 Game Features

- Move left or right to avoid falling stones.
- Three difficulty levels:
  - **Level 1:** Creeper enemies with standard background.
  - **Level 2:** Black Skeleton enemies with Nether background.
  - **Level 3:** Ender Dragon enemies with The End background.
- Dynamic background change when leveling up.
- Score increases every second.
- Lose a heart on collision. Game ends when hearts reach zero.
- Sound effects and background music.
- Custom vector drawables for enemy sprites.

## 🏗️ How it works

- The game uses `GridLayout` to represent the game board.
- Stones (`Stone` class) fall down one row per tick.
- Collision detection happens every tick.
- Level progression changes stone visuals and background.

## 🧩 Code Structure

- **MainActivity:** Initializes the game and handles UI.
- **GameLoop:** Manages the main game loop and ticking.
- **GameUIManager:** Manages buttons, score, hearts display.
- **StoneManager:** Handles spawning, moving, and clearing stones.
- **BoardManager:** Manages the game board logic.
- **Player:** Represents the player entity.
- **Stone:** Represents an enemy (falling object).
- **LevelTwo / LevelThree:** Logic for changing background/enemy visuals at each level.
- **SpeedChanger:** Increases stone falling speed as levels progress.
- **SizeSelect:** Displays a dialog to let the user choose board size.
- **MusicManager / SoundEffectManager:** Handle music and sound effects.

## 📝 How to Run

1. Clone the repository.
2. Open the project in **Android Studio**.
3. Build and run on an emulator or Android device.

## 📂 Assets

- All images are stored in `res/drawable/`.
- Vector drawables designed for enemy faces (Creeper, Skeleton, Ender Dragon).

## 🚀 Future Plans

- Add more enemy types and levels.
- Implement power-ups.
- Add high score saving.
- Improve animations.

## 🙌 Credits

Created by [Your Name].  
Sprites inspired by Minecraft (for personal/educational use).

---

> 📝 *This project is for educational purposes and not affiliated with Mojang or Microsoft.*
