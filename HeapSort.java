import java.util.Arrays;

public class HeapSort {

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 将array变成大根堆排列
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
		// 这个size为heapSize
		int size = arr.length;
		// 给heapsize减一，同时将第一个与最后一个交换
		// 因为第一个是最大的，所以在最后
		swap(arr, 0, --size);
		while (size > 0) {
			// 用heapify调出一个大根堆
			// 再交换
			heapify(arr, 0, size);
			swap(arr, 0, --size);
		}
	}

	// 完全二叉树
	public static void heapInsert(int[] arr, int index) {
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	// 调整heap
	public static void heapify(int[] arr, int index, int size) {
		int left = index * 2 + 1;
		// 判断左孩子是否越界
		while (left < size) {
			// 判断右孩子是否越界，并且右孩子是否大于左孩子，是的话返回右孩子index，不是的话就是左孩子
			// 这个变量为2个孩子中最大孩子的index
			int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
			// 将对比出两个孩子较大的index和当前index对比，返回较大index
			largest = arr[largest] > arr[index] ? largest : index;
			// 如果相等，那就是在这个位置，不需要下沉
			if (largest == index) {
				break;
			}
			swap(arr, largest, index);
			// 再次初始化index和left
			index = largest;
			left = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	