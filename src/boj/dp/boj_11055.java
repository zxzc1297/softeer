package boj.dp;
import java.util.*;

/**
 * 문제
 * 수열 A가 주어졌을 때, 그 수열의 증가하는 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가하는 부분 수열은 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 이고, 합은 113이다.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 합이 가장 큰 증가하는 부분 수열의 합을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 100 2 50 60 3 5 6 7 8
 * 예제 출력 1
 * 113
 */
public class boj_11055 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] dp = new int[N];

        // 수열 입력 받기
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            dp[i] = A[i]; // 초기값 설정
        }

        // DP 배열 채우기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) { // 증가하는 부분 수열
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
        }

        // 최대 합 찾기
        int maxSum = 0;
        for (int sum : dp) {
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
        sc.close();
    }
}
