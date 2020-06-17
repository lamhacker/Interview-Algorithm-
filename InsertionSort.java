import java.util.Arrays;

//插入排序
// 对n-1的数组进行插入操作
// 每次插入都要都要和前面的对比，前面的比后面的大就交换
public class InsertionSort{
		
	public void static insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}


	public void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}