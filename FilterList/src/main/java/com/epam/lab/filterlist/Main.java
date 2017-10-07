package com.epam.lab.filterlist;

/**
 * Created by Kate on 02.10.2017.
 */
public class Main {

    public static void main(String[] args) {
        FilterList<Integer> list = new FilterList<>(new Integer[]{1, 2, 5, 7, 9}, new Integer[]{5, 9, 4});
        for (int element : list) {
            System.out.print(element + " ");
        }
    }
}
