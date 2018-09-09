package leetcode;

import java.util.*;

public class One {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, List<Integer>> valueIndexMap = generateValueIndexMap(nums);

        for (Map.Entry<Integer, List<Integer>> entry : valueIndexMap.entrySet()) {
            Integer valueA = entry.getKey();
            Integer valueB = target - entry.getKey();

            List<Integer> indexList = valueIndexMap.get(valueB);
            if (null != indexList) {
                if (!Objects.equals(valueA, valueB)) {
                    result[1] = indexList.get(0);
                    result[0] = entry.getValue().get(0);
                } else {
                    result[0] = indexList.get(0);
                    result[1] = indexList.get(1);
                }
                break;
            }
        }

        return result;
    }

    /**
     * 假设数组中元素的值没有重复
     * @param nums 包含所有整数的数组，数组中的元素可能重复
     * @return 数组中数值和下标的Map key:数值 value:对应数组中的下标的集合
     */
    private Map<Integer, List<Integer>> generateValueIndexMap(int[] nums) {
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueIndexMap.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }

        return valueIndexMap;
    }

    public int[] twoSumBetter(int[] nums, int target) {
        int[] result = new int[2];
        // 值和下标的Map
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valueIndexMap.containsKey(target - nums[i])) {
                result[0] = valueIndexMap.get(target - nums[i]);
                result[1] = i;
                break;
            }
            valueIndexMap.put(nums[i], i);
        }

        return result;
    }
}
