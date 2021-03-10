package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * @author vladadanilova
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */

    public boolean find(List x, List y) {
        //проверим, не null ли у нас списки
        if (x == null || y == null)
            throw new IllegalArgumentException();
        // если Х у нас пустая последовательность, то при любом Y будет true
        // так как если все вычеркнуть из Y, то тоже получится пустая последовательность
        if(x.isEmpty())
            return true;
        //если Х длиннее, то не подходит
        if (x.size() > y.size())
            return false;

        int lenX = x.size();
        int lenY = y.size();
        int i = 0, j = 0;
        // проверяем, можно ли получить Х из Y
        while (i < lenX && j < lenY)
        {
            while ((j < lenY) && (x.get(i) != y.get(j)))
            {
                j++;
            }
            if (j == lenY)
                break;
            i++;
            j++;
        }
        return i == lenX;
    }
}
