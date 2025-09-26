package boj.implementation;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.
 *
 * 먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.
 *
 * 알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
 * 문자열의 시작과 끝은 공백이 아니다.
 * '<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
 * 태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다. 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다. 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.
 *
 * 입력
 * 첫째 줄에 문자열 S가 주어진다. S의 길이는 100,000 이하이다.
 *
 * 출력
 * 첫째 줄에 문자열 S의 단어를 뒤집어서 출력한다.
 *
 * 예제 입력 1
 * baekjoon online judge
 * 예제 출력 1
 * noojkeab enilno egduj
 * 예제 입력 2
 * <open>tag<close>
 * 예제 출력 2
 * <open>gat<close>
 * 예제 입력 3
 * <ab cd>ef gh<ij kl>
 * 예제 출력 3
 * <ab cd>fe hg<ij kl>
 */
public class boj_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean inTag = false; // 태그 안인지 여부

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == '<') {
                // 단어가 끝났으므로 스택에 남은 문자 모두 출력
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                inTag = true;
                sb.append(c);
            } else if (c == '>') {
                inTag = false;
                sb.append(c);
            } else if (inTag) {
                // 태그 내부는 그대로 출력
                sb.append(c);
            } else {
                // 태그 밖
                if (c == ' ') {
                    // 단어가 끝났으므로 스택 비우고 공백 출력
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                } else {
                    // 단어의 일부이므로 스택에 저장
                    stack.push(c);
                }
            }
        }

        // 마지막 단어 처리
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}
