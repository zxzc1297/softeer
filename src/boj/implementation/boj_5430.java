package boj.implementation;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 *
 * 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 *
 * 함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
 *
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
 *
 * 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
 *
 * 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
 *
 * 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
 *
 * 전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
 *
 * 출력
 * 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.
 *
 * 예제 입력 1
 * 4
 * RDD
 * 4
 * [1,2,3,4]
 * DD
 * 1
 * [42]
 * RRD
 * 6
 * [1,1,2,3,5,8]
 * D
 * 0
 * []
 * 예제 출력 1
 * [2,1]
 * error
 * [1,2,3,5,8]
 * error
 */
public class boj_5430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder result = new StringBuilder();

        while (T-- > 0) {
            String p = br.readLine(); // 함수
            int n = Integer.parseInt(br.readLine()); // 배열 길이
            String arrStr = br.readLine(); // 배열 문자열

            // 배열의 원소를 파싱하여 Deque에 저장
            Deque<Integer> deque = new ArrayDeque<>();
            if (n > 0) {
                String[] elements = arrStr.substring(1, arrStr.length() - 1).split(",");
                for (String element : elements) {
                    deque.add(Integer.parseInt(element.trim()));
                }
            }

            boolean isReversed = false;
            boolean isError = false;

            for (char cmd : p.toCharArray()) {
                if (cmd == 'R') {
                    isReversed = !isReversed; // 뒤집기 상태 전환
                } else if (cmd == 'D') {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }

                    // isReversed 상태에 따라 앞 또는 뒤에서 제거
                    if (isReversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }

            if (isError) {
                result.append("error\n");
            } else {
                result.append("[");

                // 최종 출력 - isReversed에 따라 다르게 출력
                if (isReversed) {
                    while (!deque.isEmpty()) {
                        result.append(deque.removeLast());
                        if (!deque.isEmpty()) result.append(",");
                    }
                } else {
                    while (!deque.isEmpty()) {
                        result.append(deque.removeFirst());
                        if (!deque.isEmpty()) result.append(",");
                    }
                }

                result.append("]\n");
            }
        }

        System.out.print(result);
    }
}
