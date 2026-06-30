# Super Mario Bros Clone — LibGDX & Box2D

A 2D platformer built from scratch in Java using the LibGDX framework, 
featuring real physics simulation, tile-based level design, and animated 
sprite states.

## Features

- **Box2D physics** — gravity, dynamic and static bodies, realistic collision response
- **Tiled map integration** — level designed in Tiled Map Editor, exported as .tmx and loaded at runtime
- **Collision filtering system** — category bits distinguish Mario, bricks, coins, and ground for precise collision handling
- **State-driven animation** — Mario's sprite switches between standing, running, and jumping based on live Box2D velocity
- **Interactive tile objects** — bricks and coins respond to collision with sound effects, score updates, and visual tile swaps
- **Scene2D HUD** — live score and timer display using LibGDX's Stage/Actor UI system
- **Multi-screen architecture** — StartMenu, PlayScreen, and GameOverScreen managed through the Game/Screen pattern
- **Camera system** — smooth horizontal scrolling camera that follows the player and clamps to map boundaries

## Tech Stack

Java · LibGDX · Box2D · Tiled Map Editor · Gradle

## Architecture
core/src/com/example/supermario/
├── screens/        → StartMenu, PlayScreen, GameOverScreen
├── scenes/          → Hud (Scene2D Stage)
├── sprites/         → Mario, Brick, Coin, InteractiveTileObject
└── Tools/            → B2WorldCreator, WorldContactListener

`B2WorldCreator` reads object layers from the Tiled map and generates 
Box2D static bodies for ground, pipes, bricks, and coins. `WorldContactListener` 
detects Box2D collisions via category bits and triggers the appropriate 
game logic (e.g. Mario hitting a brick from below).

## How to Run

```bash
git clone https://github.com/Morenikeji-Olowo/SuperMario-LibGDX.git
cd SuperMario-LibGDX
./gradlew lwjgl3:run
```
