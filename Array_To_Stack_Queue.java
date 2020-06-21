public class Array_To_Stack_Queue {

	//数组实现栈
	public static class ArrayStack {
		private Integer[] arr;
		private Integer size;

		public ArrayStack(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
		}
		//返回栈顶，但是不去掉
		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[size - 1];
		}

		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[size++] = obj;
		}

		public Integer pop() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--size];
		}
	}

	// 数组实现一个队列  维持first,last指针
	// 先进先出，可循环
	public static class ArrayQueue {
		private Integer[] arr;
		private Integer size;
		private Integer first;
		private Integer last;

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			first = 0;
			last = 0;
		}

		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[first];
		}


		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			size++;
			arr[last] = obj;
			// 如果已经到了最后位置了 跳回到0，没有到的话就往下走一个位置
			last = last == arr.length - 1 ? 0 : last + 1;
		}

		public Integer poll() {
			// 没有东西
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;
			int tmp = first;
			// 如果到达最后，那就返回0，否则往下走一个
			first = first == arr.length - 1 ? 0 : first + 1;
			return arr[tmp];
		}
	}

	public static void main(String[] args) {

	}

}
