# Snake and Ladder Game

Low-Level Design assignment implementing a Snake and Ladder board game in Java.

## How to Run

```bash
cd src
javac com/example/snakeladder/*.java
java com.example.snakeladder.App
```

## Input

- `n` — board size (n × n grid, cells numbered 1 to n²)
- `x` — number of players
- `difficulty_level` — `easy` or `hard`

## Rules

- Players start at position 0 and take turns rolling a six-sided dice.
- Landing on a snake head sends the player down to its tail.
- Landing on a ladder start sends the player up to its end.
- A roll that would move past the last cell is skipped.
- First player to reach the last cell wins; game continues until only one player remains.
- **Easy**: n snakes and n ladders.
- **Hard**: more snakes, fewer ladders.

## Classes

| Class | Responsibility |
|---|---|
| `App` | Entry point, reads user input |
| `Game` | Turn-by-turn orchestration |
| `Board` | Manages grid, snakes, ladders |
| `Snake` | Snake entity (head → tail) |
| `Ladder` | Ladder entity (start → end) |
| `Player` | Player state |
| `Dice` | Random dice roll |
| `DifficultyLevel` | EASY / HARD enum |
