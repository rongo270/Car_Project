# 🚗 Car Project – Android Game

**Car Project** is a fast-paced Android arcade-style game where the player controls **Steve**, dodging falling enemies while progressing through levels, unlocking new enemy types, power-ups, and interactive features.

---

## 📸 Screenshots

![Screenshot_20250708-122201_MineCarFt](https://github.com/user-attachments/assets/96fc6e11-524d-4e7a-99f6-4705584a8153)
![Screenshot_20250708-123100_MineCarFt](https://github.com/user-attachments/assets/fd1a4344-f9de-482c-aa38-ddd5f71cdbd0)
![Screenshot_20250708-123128_MineCarFt](https://github.com/user-attachments/assets/13963074-22d1-4320-b31c-53d2d4500254)
![Screenshot_20250708-123350_MineCarFt](https://github.com/user-attachments/assets/3d86840d-565a-4a50-9b27-f18a2dacf98c)
![Screenshot_20250708-123436_MineCarFt](https://github.com/user-attachments/assets/05a6ab9c-8eb5-4bf1-b72b-7c99425837e2)
![Screenshot_20250708-123443_MineCarFt](https://github.com/user-attachments/assets/92a4f1e6-6101-4124-b29a-f9667b5e4ff0)
![Screenshot_20250708-123418_MineCarFt](https://github.com/user-attachments/assets/f959779a-b459-4780-b074-afd40afb240f)



---

## 🎮 Game Features

- Move left or right to avoid falling enemies.
- Score increases automatically every second.
- Lose a heart on collision. When all hearts are gone, they reset.
- Choose control mode: tilt sensor or on-screen arrows.
- Dynamic difficulty and background changes with level progression.
- Sound effects and background music for full immersion.
- Diamond armor power-up blocks one hit.
- Google Maps integration to store and show score locations.
- Leaderboard displaying the top 10 scores.

---

## 🧱 Levels & Enemies

- **Level 1**
  - Enemy: Creeper
  - Background: Classic Minecraft theme

- **Level 2**
  - Enemy: Wither Skeleton
  - Background: Nether

- **Level 3**
  - Enemy: Enderman
  - Background: The End

- **Bonus**
  - Power-up: Diamond armor that protects from one collision

---

## 🏆 Leaderboard & Map

- Tracks top 10 high scores.
- Saves score **and** location.
- Each entry shows a "Show on Map" button to view where it was recorded.
- Google Maps fragment updates dynamically when tapping a score.
- Clear leaderboard option is available.

---

## 🧩 Code Structure

- `MainActivity` – Initializes the game and UI.
- `GameLoop` – Runs the tick loop and level logic.
- `GameUIManager` – Updates buttons, hearts, and score UI.
- `GameBoard` – Renders the board and player.
- `Player` – Player logic and movement.
- `Stone` – Represents falling enemies.
- `StoneManager` – Handles enemy spawning, motion, and clearing.
- `ScoreEntry` – Data class for a single leaderboard entry.
- `ScoreStorage` – Saves/loads leaderboard using SharedPreferences + JSON.
- `LeaderboardScreenActivity` – Shows top 10 and the map.
- `StartMenu` / `EndMenu` – Dialogs for starting or ending the game.
- `SizeSelect` – Dialog for board size selection.
- `SpeedChanger` – Increases difficulty over time.
- `MusicManager` / `SoundEffectManager` – Manages sounds and music.

---

## 🗺️ Google Maps Integration

- Automatically requests location permission.
- Saves latitude and longitude when a score is recorded.
- Each leaderboard entry includes location data.
- Map updates to show pin when user taps “Show on Map”.

---

## 📝 How to Run

1. Clone the repository.
2. Open in **Android Studio**.
3. Add your Maps API key to `local.properties`:
- MAPS_API_KEY=your_real_key_here
4. Run the app on a physical Android device (recommended for GPS features).

---

## 📂 Assets

- All vector drawables (Creeper, Skeleton, Enderman) are in `res/drawable/`
- Sound files are in `res/raw/`
- Background images correspond to Minecraft biomes


---

## 🙌 Credits

Created by **Ron Golan**  
Sprites inspired by *Minecraft* — for educational/personal use only  
This project is **not affiliated** with Mojang or Microsoft.
