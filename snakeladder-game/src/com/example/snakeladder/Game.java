package com.example.snakeladder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {

    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private final List<Player> winners;

    public Game(Board board, Dice dice, List<Player> playerList) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.winners = new ArrayList<>();
    }

    public void play() {
        System.out.println("\n--- Game Start ---\n");
        board.printBoard();
        System.out.println();

        int totalPlayers = players.size();

        while (players.size() > 1) {
            Player current = players.poll();

            if (current.hasWon()) {
                continue;
            }

            int roll = dice.roll();
            int oldPos = current.getPosition();
            int newPos = oldPos + roll;

            System.out.println(current.getName() + " rolled " + roll + " | " + oldPos + " -> " + newPos);

            if (newPos > board.getTotalCells()) {
                System.out.println("  Cannot move beyond " + board.getTotalCells() + ". Stays at " + oldPos);
                players.add(current);
                continue;
            }

            newPos = board.getNewPosition(newPos);
            current.setPosition(newPos);

            if (newPos == board.getTotalCells()) {
                current.setWon(true);
                winners.add(current);
                System.out.println("  " + current.getName() + " wins! (Rank #" + winners.size() + ")");

                if (players.size() == 1) {
                    Player last = players.poll();
                    winners.add(last);
                }
                continue;
            }

            System.out.println("  " + current.getName() + " is now at " + newPos);
            players.add(current);
        }

        System.out.println("\n--- Game Over ---\n");
        System.out.println("Final Rankings:");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println("  #" + (i + 1) + " " + winners.get(i).getName());
        }
    }
}
