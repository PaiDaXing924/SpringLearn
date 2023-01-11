package com.zhang;

public class test {

    public static void main(String[] args) {
        int interval = 0;
        int a = 2022;
        int b= 2021;
        int maxDate = 0;
        interval = a - b;
        interval *= 365;
        interval += 136 - 136;
        int n = 0;
        --a;
        int i = b % 4;
        b -= i == 0 ? 4 : i;
        maxDate = n + (a - b) / 4;
        interval += maxDate;
        System.out.println(interval);
    }

}
