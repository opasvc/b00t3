package com.dztzb003.j2t.common.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * list工具类
 */
public class ListUtils {

    //随机数
    public static Random random = new Random();

    //冒泡排序
    public static List bubbleSort(List<Integer> list) {
        if (list.size() < 1) return new ArrayList<>();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            //记录是否进行交换
            boolean is = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    is = true;
                }
            }
            if (!is) {
                break;
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integers.add(random.nextInt(200) + 1);
        }
        List list = printRunTime(integers);
        printList(list);
    }

    public static List printRunTime(List list) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("开始时间: " + start);
        List list1 = bubbleSort(list);
        LocalDateTime end = LocalDateTime.now();
        System.out.println("结束时间: " + end);
        return list1;
    }

    //打印list所有元素
    public static void printList(List list) {
        list.forEach(e -> System.out.print(e + ","));
        System.out.println();
    }
}

