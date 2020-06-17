import java.util.Arrays;

//每次都取最小值，从小到大swap进array中
//总共swap n-1次，每次需要用当前的和min swap
//每次求min需要对比没有排序的所有元素，需要n-1次

public class SelectionSort{
	

	public void static selectionSort(int[] arr) {
		if(arr == null || arr < 2) {
			return;
		}
		for(int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, minIndex, i)
		}
	}

	public void static swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
