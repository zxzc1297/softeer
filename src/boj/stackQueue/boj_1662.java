package boj.stackQueue;
import java.util.*;
import java.io.*;

/**
 * 1662 압축
 * 문제
 * 압축되지 않은 문자열 S가 주어졌을 때, 이 문자열중 어떤 부분 문자열은 K(Q)와 같이 압축 할 수 있다. K는 한자리 정수이고, Q는 0자리 이상의 문자열이다. 이 Q라는 문자열이 K번 반복된다는 뜻이다. 압축된 문자열이 주어졌을 때, 이 문자열을 다시 압축을 푸는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 압축된 문자열 S가 들어온다. S의 길이는 최대 50이다. 문자열은 (, ), 0-9사이의 숫자로만 들어온다.
 *
 * 출력
 * 첫째 줄에 압축되지 않은 문자열의 길이를 출력한다. 이 값은 2,147,473,647 보다 작거나 같다.
 *
 * 예제 입력 1
 * 33(562(71(9)))
 * 예제 출력 1
 * 19
 * 예제 입력 2
 * 123
 * 예제 출력 2
 * 3
 * 예제 입력 3
 * 10342(76)
 * 예제 출력 3
 * 8
 * 예제 입력 4
 * 0(0)
 * 예제 출력 4
 * 0
 * 예제 입력 5
 * 1(1(1(1(1(1(1(0(1234567890))))))))
 * 예제 출력 5
 * 0
 * 예제 입력 6
 * 1()66(5)
 * 예제 출력 6
 * 7
 */
public class boj_1662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("");

        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < arr.length; i++){

            if(!arr[i].equals(")")) {stack.push(arr[i]);}
            else {
                int cnt = 0;
                while (!stack.peek().equals("(")) {
                    String topStr = stack.pop();
                    if (topStr.equals("+")) {
                        int len = Integer.parseInt(stack.pop());
                        cnt += len;
                    } else cnt++;
                }
                stack.pop(); // ( 제거
                int k = Integer.parseInt(stack.pop());
                cnt = cnt * k;
                stack.push(String.valueOf(cnt));
                stack.push("+");
            }
        }
        int answer = 0;
        while(!stack.empty()){
            if(stack.peek().equals("+")){
                stack.pop();
                answer += Integer.parseInt(stack.pop());
            }
            else{
                stack.pop();
                answer++;
            }
        }
        System.out.println(answer);
    }


    /**  틀린 풀이
     * public static void main(String[] args){
     *         Scanner sc = new Scanner(System.in);
     *         String s = sc.next();
     *
     *         Stack<Character> stack = new Stack<>();
     *         int answer = 0;
     *         for(int i=0; i<s.length(); i++){
     *             if(s.charAt(i) == ')'){
     *                 int tmp = answer;
     *
     *                 while(!stack.isEmpty()){
     *                     System.out.println("stack.peek() = " + stack.peek());
     *                     if(stack.peek() == '('){
     *                         stack.pop(); // ( 꺼내기
     *                         int cnt = stack.pop() - '0'; // 반복횟수 꺼내기
     *                         answer = cnt * tmp;
     *                         break;
     *                     } else {
     *                         stack.pop();
     *                         tmp++;
     *                     }
     *                 }
     *             } else {
     *                 System.out.println("here2");
     *                 stack.push(s.charAt(i));
     *             }
     *         }
     *         System.out.println(answer + stack.size());
     *         sc.close();
     *     }
     */

}
