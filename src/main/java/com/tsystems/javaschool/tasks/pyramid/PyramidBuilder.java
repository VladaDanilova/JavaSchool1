package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PyramidBuilder {
    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     * @author vladadanilova
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        //проверим, что не null
        if(inputNumbers.contains(null))
            throw new CannotBuildPyramidException(); //используем наше исключение CannotBuildPyramidException
        //чекним, как много строк будет в нашей пирамиде с помощью метода checkRow
        int rows = checkRow(inputNumbers);
        //если выкинет -1, то мы не можем построить пирамиду
        if(rows == -1)
            throw new CannotBuildPyramidException();
        //можно опытным путем заметить, что столбцов в пирамиде
        // будет 2*rows - 1
        int cols = 2*rows-1;
        Collections.sort(inputNumbers);
        Queue<Integer> queue = new LinkedList<>(inputNumbers); //для удобства используем очередь, отсортировав перед этим inputNumbers
        int[][] pyramid = new int[rows][cols];
        /*
        первое число пирамиды располагается по центру
        далее влево и вправо отходит на 1 и ставятся следующие числа
         */
        int start = (pyramid[0].length)/2;
        for (int i = 0; i < pyramid.length; i++)
        {
            int start2 = start;
            for (int j = 0; j <= i; j++)
            {
                pyramid[i][start2] = queue.remove();
                start2 = start2 + 2;
            }
            start--;
        }

        return pyramid;
    }

    public static int checkRow(List<Integer> input)
    {
        int size = input.size();
        double answer = (Math.sqrt(size*8+1)-1)/2;

        if (answer == Math.ceil(answer)) //ceil - округление в большую сторону
            return (int) answer;

            return -1;
    }


}
