package ru.geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class XOgame {
    static final int SIZE = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static char[][] map;
    static Scanner sc;
    static Random random;

    public XOgame() {
    }

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin('X')) {
                System.out.println("Вы победили! Поздравляем!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            lastAiTurn();
//            aiTurn();
            printMap();
            if (checkWin('O')) {
                System.out.println("Компьютер победил.");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }

    public static void initMap() {
        map = new char[3][3];

        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                map[i][j] = '.';
            }
        }

    }

    public static void printMap() {
        System.out.print("  ");

        int i;
        for (i = 1; i <= map.length; ++i) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (i = 0; i < map.length; ++i) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < map[i].length; ++j) {
                System.out.printf("%c ", map[i][j]);
            }

            System.out.println();
        }

    }

    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("input X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = 'X';
    }

    public static boolean isCellValid(int y, int x) {
        if (y >= 0 && x >= 0 && y < 3 && x < 3) {
            return map[y][x] == '.';
        } else {
            return false;
        }
    }

//    public static void aiTurn() {
//        int x;
//        int y;
//        do {
//            x = random.nextInt(3);
//            y = random.nextInt(3);
//        } while (!isCellValid(y, x));
//
//        map[y][x] = 'O';
//    }

    public static void lastAiTurn() {
        int x = 0;
        int y = 0;
        int count = 0;

        do {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[1][j] == 'X' || map[0][j] == 'X' || map[2][j] == 'X') {
                        count++;
                        if(count == 2){
                            x = j;
                            y = random.nextInt(3);
                            continue;
                        }

                    }
//                    if (map[i][j] == 'X' && (map[i][1] == 'X' || map[i][2] == 'X')) {
//                        x = j;
//                        if(map[i][j] == '.')
//                            y = i;
//                        continue;
//                    }
//                    if (map[1][1] == 'X' && (map[0][0] == 'X' || map[2][2] == 'X')) {
//                        x = j;
//                        if(map[i][j] == '.')
//                            y = i;
//                        continue;
//                    }
//                    if (map[1][1] == 'X' && (map[0][2] == 'X' || map[2][0] == 'X')) {
//                        x = j;
//                        if(map[i][j] == '.')
//                            y = i;
//                        continue;
//                    }
                    else {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    }


                }
            }
       } while (!isCellValid(y, x));

        map[y][x] = 'O';
    }

    public static boolean isFull() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (map[i][j] == '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkWin(char c) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (map[0][j] == c && map[1][j] == c && map[2][j] == c) {
                    return true;
                }
                if (map[i][0] == c && map[i][1] == c && map[i][2] == c) {
                    return true;
                }
                if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {
                    return true;
                }
                if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
                    return true;
                }
            }
        }
        return false;


    }


    static {
        sc = new Scanner(System.in);
        random = new Random();
    }
}
