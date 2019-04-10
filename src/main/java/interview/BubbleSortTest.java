package interview;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author 李重辰
 * @date 2019/3/1 13:33
 */
public class BubbleSortTest {

  /**
   * 第二次优化 如果已经有序不进行交换
   * 假定数组是有序的，进行任意两个相邻元素比对，如果发生了交换，且将数组状态设为无序。
   * 如果在一次循环中没有任何两个元素发生了交换，则数组已经有序，跳出循环。
   */
  @Test
  public void sortFinal() {
    int[] arr = {1, 2, 6, 4, 9, 7, 8};
    for (int j = 0; j < arr.length; j++) {
      boolean sorted = true;
      for (int i = 0; i < arr.length - 1 - j; i++) {
        if (arr[i] > arr[i + 1]) {
          int temp = arr[i + 1];
          arr[i + 1] = arr[i];
          arr[i] = temp;
          sorted = false;
        }
      }
      if (sorted) {
        break;
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  private static void swapTwo(int[] arr, int i) {
    if (arr[i] > arr[i + 1]) {
      int temp = arr[i + 1];
      arr[i + 1] = arr[i];
      arr[i] = temp;
    }
  }

  @Test
  public void sort1() {
    int[] arr = new int[10000];
    for (int i = 1; i < arr.length - 1; i++) {
      arr[i] = i;
    }
    for (int ignored : arr) {
      for (int i = 0; i < arr.length - 1; i++) {
        swapTwo(arr, i);
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  /**
   * 第一次优化 减少循环次数
   */
  @Test
  public void sort2() {
    int[] arr = new int[10000];
    for (int i = 1; i < arr.length - 1; i++) {
      arr[i] = i;
    }
    for (int j = 0; j < arr.length; j++) {
      for (int i = 0; i < arr.length - 1 - j; i++) {
        swapTwo(arr, i);
      }
    }
    System.out.println(Arrays.toString(arr));
  }
}
