public class MergeSort {

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	//对左右进行递归
	public static void mergeSort(int[] arr, int l, int r) {
		//只有1个元素
		if (l == r) {
			return;
		}
		// >> 1 相当于处以2
		// (r - l)为数组长度，l为数组起始位置，(r - l)/2为一半的长度
		// 数组起始位置加一半长度为mid
		int mid = l + ((r - l) >> 1);
		mergeSort(arr, l, mid);
		mergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	// 合并arr的左右边已经排好序的继续排序 变成1个arr，然后将目标arr替换
	// @parameter
	// l: left of the array
	// m: middle of the array
	// r: right of the array
	// arr: 原始arr
	public static void merge(int[] arr, int l, int m, int r) {
		//获得需要改的数组的长度
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		//如果左边的arr没有超过middle，并且右边的arr没有超过边界
		//那么如果左边比右边小，将左边当前的放到help数组，否则就是右边，
		//移动help指针，移动放到help数组那个元素对应的arr的指针
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		//左边的arr还有剩下的，那就把剩下的放到help数组中
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		//右边的arr还有剩下的，那就把剩下的放入help数组中
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		//用help的数组替换arr
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

