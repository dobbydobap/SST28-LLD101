package com.example.snakeladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size n (board will be n x n): ");
        int n = scanner.nextInt();

        System.out.print("Enter number of players: ");
        int x = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter difficulty level (easy/hard): ");
        String diffInput = scanner.nextLine().trim().toUpperCase();
        DifficultyLevel difficulty;
        if (diffInput.equals("HARD")) {
            difficulty = DifficultyLevel.HARD;
        } else {
            difficulty = DifficultyLevel.EASY;
        }

        List<Player> playerList = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            playerList.add(new Player("Player" + i));
        }

        Board board = new Board(n, difficulty);
        Dice dice = new Dice(6);
        Game game = new Game(board, dice, playerList);
        game.play();

        scanner.close();
    }
}
