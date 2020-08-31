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

        while(true) {
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

            aiTurn();
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

        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map[i].length; ++j) {
                map[i][j] = '.';
            }
        }

    }

    public static void printMap() {
        System.out.print("  ");

        int i;
        for(i = 1; i <= map.length; ++i) {
            System.out.print(i + " ");
        }

        System.out.println();

        for(i = 0; i < map.length; ++i) {
            System.out.print(i + 1 + " ");

            for(int j = 0; j < map[i].length; ++j) {
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
        } while(!isCellValid(y, x));

        map[y][x] = 'X';
    }

    public static boolean isCellValid(int y, int x) {
        if (y >= 0 && x >= 0 && y < 3 && x < 3) {
            return map[y][x] == '.';
        } else {
            return false;
        }
    }

    public static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while(!isCellValid(y, x));

        map[y][x] = 'O';
    }

    public static boolean isFull() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (map[i][j] == '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkWin(char c) {
        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {
            return true;
        } else if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {
            return true;
        } else if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {
            return true;
        } else if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {
            return true;
        } else if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {
            return true;
        } else if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {
            return true;
        } else if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
            return true;
        } else {
            return map[0][2] == c && map[1][1] == c && map[2][0] == c;
        }
    }

    static {
        sc = new Scanner(System.in);
        random = new Random();
    }
}
