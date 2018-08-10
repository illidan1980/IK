package IK.LinkedListsQueuesStacks;

import java.util.Stack;

/**
 * Created  on 5/19/2016.
 */
public class ValidMatchingParan {
    static boolean hasMatchingParantheses(String strExpression) {
        Stack<Character> stack = new Stack<>();
        for(Object c: strExpression.toCharArray()){
            switch ((char)c){
                case '(':
                    stack.push('(');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
            }
        }

        if(!stack.isEmpty())
            return false;
        return true;
    }
    static int longestMatchingParantheses(String strExpression) {
        Stack<Character> stack = new Stack<>();
        int maxLength = 0; // no valid parenthesis
        int currentLength = 0;
        for(Object c: strExpression.toCharArray()){
            switch ((char)c){
                case '(':
                    stack.push('(');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(') {
                        stack.empty(); currentLength = 0;
                    } else {
                        currentLength += 2;
                        if(currentLength > maxLength)
                            maxLength = currentLength;
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{') {
                        stack.empty(); currentLength = 0;
                    } else {
                        currentLength += 2;
                        if(currentLength > maxLength)
                            maxLength = currentLength;
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '[') {
                        stack.empty(); currentLength = 0;
                    } else {
                        currentLength += 2;
                        if(currentLength > maxLength)
                            maxLength = currentLength;
                    }
                    break;
            }
        }

        return maxLength;
    }
    public static void main(String[] args){
        String str = "((((())(((()";
        System.out.println(hasMatchingParantheses(str));
        System.out.println(longestMatchingParantheses(str));
    }
}
