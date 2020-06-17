import java.util.Arrays;

//选arr中最大的作为最右边，下一次忽略最后一个，对arr-1进行排序
//如果当前元素大于下一个元素，交换顺序，这样下一个元素会比前一个元素大，最右边的元素会变成整个数组最大的
public class BubbleSort() {

	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int end = arr.length - 1; end > 0; end--) {
			for (int i = 0; i < end; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i+1);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}