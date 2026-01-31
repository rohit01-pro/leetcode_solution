import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        String[] components = path.split("/");
        for (String directory : components) {
            if (directory.equals("") || directory.equals(".")) {
                continue;
            }
            if (directory.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(directory);
            }
        }
        StringBuilder result = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }
        return result.toString();
    }
}