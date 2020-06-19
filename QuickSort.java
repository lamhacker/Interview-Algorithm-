import java.util.Arrays;

// 每次取数组最后一个数放入中间位置，中间位置的两边做递归
public class QuickSort {

	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int l, int r) {
		if (l < r) {
			// 随机快排，一般快排可以删去这行
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
			// 将最后一个数放入位置
			int[] p = partition(arr, l, r);
			//左边递归
			quickSort(arr, l, p[0] - 1);
			//右边递归
			quickSort(arr, p[1] + 1, r);
		}
	}

	// 也可以用荷兰国旗
	public static int[] partition(int[] arr, int l, int r) {
		// 比arr最后一个数小的区域的指针，一开始为0，所以为l-1
		int less = l - 1;
		// 表示大于最后一个数的指针
		// 因为需要让最后一个数不参与递归，虽然more里有了最后一个，但其实不在
		// 后面的会将最后一个与more最边缘的位置进行替换
		int more = r;
		// 两个指针不重合
		// l表示指向当前指针
		while (l < more) {
			// 如果当前位置的值小于arr最后一个数
			// 调换当前位置的值放到less区域中
			// 同时增加less区域位置，当前指针往后移一位
			if (arr[l] < arr[r]) {
				swap(arr, ++less, l++);
			// 如果当前位置的值大于arr最后一个数
			// 调换当前位置的值放到more区域中最外面的一位
			// more区域往左增大一位， 
			// 因为不知道调换过来more区域最外面一位的值是大于还是小于，所以当前指针不变
			} else if (arr[l] > arr[r]) {
				swap(arr, --more, l);
			// 如果当前位置等于arr的中间一个数
			// 移动当前指针到下一个数
			// more和less区域都不增加
			} else {
				l++;
			}
		}
		// 把最后面的放中间
		// 因为more最外围的数也是大于最后面的数，而最后面的应该放入中间，所以替换
		swap(arr, more, r);
		// 返回数组，第一个为等于区域左边界， 第二个为等于区域右边界
		return new int[] { less + 1, more };
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	