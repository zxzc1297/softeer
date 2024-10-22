package boj.implementation;
import java.util.*;

/**
 * 문제
 * IQ Test의 문제 중에는 공통된 패턴을 찾는 문제가 있다. 수열이 주어졌을 때, 다음 수를 찾는 문제이다.
 *
 * 예를 들어, 1, 2, 3, 4, 5가 주어졌다. 다음 수는 무엇인가? 당연히 답은 6이다. 약간 더 어려운 문제를 보면, 3, 6, 12, 24, 48이 주어졌을 때, 다음 수는 무엇인가? 역시 답은 96이다.
 *
 * 이제 제일 어려운 문제를 보자.
 *
 * 1, 4, 13, 40이 주어졌을 때, 다음 수는 무엇일까? 답은 121이다. 그 이유는 항상 다음 수는 앞 수*3+1이기 때문이다.
 *
 * 은진이는 위의 3문제를 모두 풀지 못했으므로, 자동으로 풀어주는 프로그램을 작성하기로 했다. 항상 모든 답은 구하는 규칙은 앞 수*a + b이다. 그리고, a와 b는 정수이다.
 *
 * 수 N개가 주어졌을 때, 규칙에 맞는 다음 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 N개의 수가 주어진다. 이 수는 모두 절댓값이 100보다 작거나 같은 정수이다.
 *
 * 출력
 * 다음 수를 출력한다. 만약 다음 수가 여러 개일 경우에는 A를 출력하고, 다음 수를 구할 수 없는 경우에는 B를 출력한다.
 *
 * 예제 입력 1
 * 4
 * 1 4 13 40
 * 예제 출력 1
 * 121
 * 예제 입력 2
 * 5
 * 1 2 3 4 5
 * 예제 출력 2
 * 6
 * 예제 입력 3
 * 5
 * 3 6 12 24 48
 * 예제 출력 3
 * 96
 * 예제 입력 4
 * 1
 * 0
 * 예제 출력 4
 * A
 */
public class boj_1111 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (N == 1) {
            System.out.println("A");
            return;
        }

        if (N == 2) {
            if (arr[0] == arr[1]) {
                System.out.println(arr[0]);
            } else {
                System.out.println("A");
            }
            return;
        }

        // N >= 3일 때
        boolean isConsistent = true;
        Integer a = null, b = null;

        // 첫 두 수로 a와 b를 계산
        if (arr[1] - arr[0] != 0) {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]); // a 계산
        } else {
            a = 1;
        }
        b = arr[1] - a * arr[0]; // b 계산

        // 다른 모든 항이 이 a, b 규칙을 따르는지 확인
        for (int i = 1; i < N - 1; i++) {
            if (arr[i + 1] != arr[i] * a + b) {
                isConsistent = false;
                break;
            }
        }

        if (isConsistent) {
            // 일관된 규칙이 있으면 다음 수 출력
            System.out.println(arr[N - 1] * a + b);
        } else {
            System.out.println("B");
        }
    }
}
