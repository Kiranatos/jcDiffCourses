package net.kiranatos.javarush.q2сore.lvl7;

/* Java Core,  7 уровень, 10 лекция
 Посчитаем
1. Сделай так, чтобы результат успел посчитаться для всех элементов массива values НЕ используя Thread.sleep
2. Исправь synchronized блок так, чтобы программа не вывела результат на экран

Old variant: package com.javarush.test.level17.lesson10.home10;
 */

public class Core7Lvl17Lesson10Task10 {
    public static Integer count = 0;
    public static int[] values = new int[105];
    static {
        for (int i = 0; i < 105; i++) { values[i] = 0; }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Counter counter3 = new Counter();
        Counter counter4 = new Counter();

        counter1.start();
        counter1.join();
        counter2.start();
        counter2.join();
        counter3.start();
        counter3.join();
        counter4.start();
        counter4.join();

        for (int i = 1; i <= 100; i++) {
            //System.out.println(i + "   " + values[i]);
            if (values[i] > 1) {
                System.out.println(String.format("%d повторилось %d раз", i, values[i]));
            } else if (values[i] == 0) {
                System.out.println(String.format("%d ни разу не встретилось", i));
            }
        }
    } // end of main method

    public static void incrementCount() { count++; }
    public static int getCount() { return count; }

    public static class Counter extends Thread {
        @Override
        public void run() {
            do {
                synchronized (this) {
                    incrementCount();
                    values[getCount()]++;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {  }
            } while (getCount() < 100);
        }
    }
} // end of class Core7Lvl17Lesson10Task10

