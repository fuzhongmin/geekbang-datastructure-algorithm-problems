class Solution {
    public int[] plusOne(int[] digits) {
        int val = digits[digits.length - 1];
        if (val < 9) {
            digits[digits.length - 1] = val + 1;
            return digits;
        }
        for (int i = digits.length - 1; i>=0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (digits[0] != 0) {
            return digits;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}