package com.example.snakeladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Board {

    private final int size;
    private final int totalCells;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final Map<Integer, Integer> snakeMap;
    private final Map<Integer, Integer> ladderMap;

    public Board(int size, DifficultyLevel difficulty) {
        this.size = size;
        this.totalCells = size * size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        generateSnakesAndLadders(size, difficulty);
    }

    private void generateSnakesAndLadders(int count, DifficultyLevel difficulty) {
        Random random = new Random();
        Set<Integer> occupied = new HashSet<>();
        occupied.add(1);
        occupied.add(totalCells);

        int snakeCount = count;
        int ladderCount = count;

        if (difficulty == DifficultyLevel.HARD) {
            snakeCount = (int) Math.ceil(count * 1.5);
            ladderCount = (int) Math.ceil(count * 0.7);
        }

        for (int i = 0; i < snakeCount; i++) {
            int head, tail;
            do {
                head = random.nextInt(totalCells - 2) + 3;
                tail = random.nextInt(head - 1) + 1;
            } while (occupied.contains(head) || occupied.contains(tail) || head == tail);
            occupied.add(head);
            occupied.add(tail);
            Snake snake = new Snake(head, tail);
            snakes.add(snake);
            snakeMap.put(head, tail);
        }

        for (int i = 0; i < ladderCount; i++) {
            int start, end;
            do {
                start = random.nextInt(totalCells - 2) + 2;
                end = random.nextInt(totalCells - start) + start + 1;
                if (end > totalCells) end = totalCells;
            } while (occupied.contains(start) || occupied.contains(end) || start == end);
            occupied.add(start);
            occupied.add(end);
            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
            ladderMap.put(start, end);
        }
    }

    public int getTotalCells() {
        return totalCells;
    }

    public int getNewPosition(int position) {
        if (snakeMap.containsKey(position)) {
            int tail = snakeMap.get(position);
            System.out.println("  Bitten by snake at " + position + "! Goes down to " + tail);
            return tail;
        }
        if (ladderMap.containsKey(position)) {
            int end = ladderMap.get(position);
            System.out.println("  Climbed ladder at " + position + "! Goes up to " + end);
            return end;
        }
        return position;
    }

    public List<Snake> getSnakes() {
        return Collections.unmodifiableList(snakes);
    }

    public List<Ladder> getLadders() {
        return Collections.unmodifiableList(ladders);
    }

    public void printBoard() {
        System.out.println("Board Size: " + size + "x" + size + " (" + totalCells + " cells)");
        System.out.println("Snakes:");
        for (Snake s : snakes) {
            System.out.println("  " + s);
        }
        System.out.println("Ladders:");
        for (Ladder l : ladders) {
            System.out.println("  " + l);
        }
    }
}
