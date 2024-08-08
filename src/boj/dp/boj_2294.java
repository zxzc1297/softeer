package boj.dp;
import java.util.*;

/**
 * 문제
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 입력
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.
 *
 * 출력
 * 첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 3 15
 * 1
 * 5
 * 12
 * 예제 출력 1
 * 3
 */
public class boj_2294 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[k+1];
        Arrays.fill(dp, k + 1); // 큰 값으로 초기화
        dp[0] = 0;

        for(int coin : arr){
            for(int j=coin; j<=k; j++){
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        System.out.println(dp[k] > k ? -1 : dp[k]);
        sc.close();
    }
}
