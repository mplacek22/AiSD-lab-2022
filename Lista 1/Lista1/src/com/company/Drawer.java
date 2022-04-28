package com.company;

public class Drawer {
    public static void drawTriangle(int size) {
        if (size<=0){
            System.out.print("fail\n");
        }
        else {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <=i ; j++) {
                    System.out.print("#");
                }
                System.out.print("\n");
            }
        }
    }

    public static void drawSquare(int size) {
        if (size<=0){
            System.out.print("fail\n");
        }
        else if (size==1){
            System.out.print("#\n");
        }
        else {
            for (int i = 0; i < size; i++) {
                System.out.print("#");
            }
            System.out.print("\n");
            for (int j = 0; j < size-2; j++) {
                System.out.print("#");
                for (int k = 0; k < size-2; k++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                System.out.print("\n");
            }
            for (int i = 0; i < size; i++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }

    public static void drawPyramid(int size) {
        if (size<=0){
            System.out.print("fail\n");
        }
        else {
            for (int i = 1; i <= size ; i++) {
                for (int j = 0; j <size-i ; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 2*i-1; j++) {
                    System.out.print("#");
                }
                System.out.print("\n");
            }
        }
    }

    public static void drawPyramid(int size, int offset) {
        if (size<=0 || offset<0){
            System.out.print("fail\n");
        }
        else {
            for (int i = 1; i <= size ; i++) {
                for (int j = 0; j <size-i+offset ; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 2*i-1; j++) {
                    System.out.print("#");
                }
                System.out.print("\n");
            }
        }
    }

    public static void drawChristmasTree(int size) {
        if (size<=0){
            System.out.print("fail\n");
        }
        else {
            for (int i = 1; i <=size ; i++) {
                drawPyramid(i,size-i);
            }
        }
    }
}
