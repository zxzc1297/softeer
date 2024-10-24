package boj.dp;
import java.util.*;

/**
 * 문제
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 *
 * 입력
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 경우의 수를 출력한다. 경우의 수는 231보다 작다.
 *
 * 예제 입력 1
 * 3 10
 * 1
 * 2
 * 5
 * 예제 출력 1
 * 10
 */
public class boj_2293 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n];
        long[] dp = new long[k+1];

        //코인 종류 할당
        for(int i=0; i<n; i++){
            coins[i] = sc.nextInt();
        }

        // DP 테이블 초기화: 0원을 만드는 방법은 1가지 (동전 사용 안함)
        dp[0] = 1;

        // DP 테이블 업데이트
        for (int coin : coins) {
            for (int j = coin; j <= k; j++) {
                dp[j] += dp[j - coin]; // coin을 사용하여 j원을 만들 수 있는 경우의 수 더하기
            }
        }

        //결과 출력
        System.out.print(dp[k]);
        sc.close();
    }
}
