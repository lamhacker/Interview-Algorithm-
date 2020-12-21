public class Solution {
    //暴力求解的时间复杂度o(n2),


    //我们优化到线性的时间复杂度来解决问题，
    //那么就是说只能遍历一个数字，那么另一个数字呢，
    // 我们可以事先将其存储起来，使用一个HashMap，
    // 来建立数字和其坐标位置之间的映射，
    // HashMap是常数级的查找效率，这样，我们在遍历数组的时候，
    // 用target减去遍历到的数字，就是另一个需要的数字了，
    // 直接在HashMap中查找其是否存在即可，注意要判断查找到的数字不是第一个数字，
    //比如target是4，遍历到了一个2，那么另外一个2不能是之前那个2，
    //先判断是否存在,则避免了这个问题.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]) + 1, i + 1};
            }
            // (3,0)
            // 
            map.put(nums[i], i);          
        }
        return null;
    }
    

    //两数之和 - 输入有序数组
    // 二分查找
    // 时间o(nlogn) 空间o(1)
    //在数组中找到两个数，使得它们的和等于目标值，
    //可以首先固定第一个数，然后寻找第二个数，
    //第二个数等于目标值减去第一个数的差。利用数组的有序性质，
    //可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，
    //只在第一个数的右侧寻找
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }


    //两数之和 - 输入有序数组
    //双指针  时间最好o(logn) 一般o(n)，空间o(1)
    //初始时两个指针分别指向第一个元素位置和最后一个元素的位置。
    //每次计算两个指针指向的两个元素之和，并和目标值比较。
    //如果两个元素之和等于目标值，则发现了唯一解。如果两个元素之和小于目标值，
    //则将左侧指针右移一位。如果两个元素之和大于目标值，则将右侧指针左移一位。
    //移动指针之后，重复上述操作，直到找到答案。


    public int[] twoSum(int[] nums, int target) {
         public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}

// 三数之和
// 双指针
// 时间o(n^2) 空间o(logn)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}

