class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (String item : tokens) {
            if (isNumberic(item)) {
                stack.push(Integer.parseInt(item));
                continue;
            }
            if (stack.size() > 1) {
                Integer a = stack.pop();
                Integer b = stack.pop();
                int result = operate(b, a, item);
                stack.push(result);
            }
        }
        return stack.peek();
    }

    public boolean isNumberic(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars.length > 1 && (chars[i] == '-' || (chars[i] == '+'))) {
                continue;
            }
            if (!(chars[i] - '0' >= 0 && chars[i] - '0' <= 9)) {
                return false;
            }
        }
        return true;
    }

    public int operate(int a, int b, String operator) {
        if ("+".equals(operator)) {
            return a + b;
        }
        if ("-".equals(operator)) {
            return a - b;
        }
        if ("*".equals(operator)) {
            return a * b;
        }
        if ("/".equals(operator)) {
            return a / b;
        }
        return -1;
    }
}