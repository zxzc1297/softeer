package boj.dp;
import java.util.*;

/**
 * 문제
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다. 또, 수열에서 수를 하나 제거할 수 있다. (제거하지 않아도 된다)
 *
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 수를 제거하지 않았을 때의 정답은 12+21인 33이 정답이 된다.
 *
 * 만약, -35를 제거한다면, 수열은 10, -4, 3, 1, 5, 6, 12, 21, -1이 되고, 여기서 정답은 10-4+3+1+5+6+12+21인 54가 된다.
 *
 * 입력
 * 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄에 답을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 10 -4 3 1 5 6 -35 12 21 -1
 * 예제 출력 1
 * 54
 */
public class boj_13398 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] dp = new int[n];    // 수 제거 X
        int[] dp2 = new int[n];   // 수 1개 제거 O

        dp[0] = arr[0];
        dp2[0] = arr[0]; // 첫 번째도 제거하지 않은 것처럼 시작

        int result = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            dp2[i] = Math.max(dp2[i - 1] + arr[i], dp[i - 1]); // 현재 i번째 수를 제거한다고 가정

            result = Math.max(result, Math.max(dp[i], dp2[i]));
        }

        System.out.println(result);
    }
}
