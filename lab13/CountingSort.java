/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        // 解决负数存在情况下的排序

        // 原本：每个数字作为索引对应其频率，相应的也完成了对数字的排序
        // 负数无法索引 方法1：使用两个数组来进行计数 小于0的值取绝对值 java中无法进行负数索引
        // find min
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = min < i ? min : i;
        }

        if (min >= 0) return naiveCountingSort(arr);

        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // 根据正负分别放入两个数组,统计频率
        int[] counts = new int[max + 1];
        int[] counts_neg = new int[Math.abs(min) + 1];
        for (int i : arr) {
            if (i >= 0) {
                counts[i]++;
            }
            else {
                counts_neg[Math.abs(i)]++;
            }
        }

        // 计算起始位置
        int[] starts_neg = new int[Math.abs(min) + 1];
        int pos_neg = 0;
        for (int i = 0; i < starts_neg.length; i += 1) {
            starts_neg[i] = pos_neg;
            pos_neg += counts_neg[i];
        }

        int[] starts = new int[max + 1];
        int pos = starts_neg[Math.abs(min)] + counts_neg[Math.abs(min)];

        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            if (item < 0) {
                item = item * (-1) ;
                int place = starts[item];
                sorted[place] = item;
                starts[item] += 1;
            }
            else {
                int place = starts[item];
                sorted[place] = item;
                starts[item] += 1;
            }
        }

        return sorted;
    }
}
