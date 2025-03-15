package boj.dp;
import java.util.*;

/**
 * 문제
 * 규환이는 리그 오브 레전설이라는 게임을 좋아한다. 이 게임에서는 N초의 시간 동안 싸움을 하는데, 규환이가 플레이하는 캐릭터는 A, B 두 가지 스킬을 사용할 수 있다.  A 스킬의 시전 시간은 1초고, B 스킬의 시전 시간은 M초이다. 규환이는 다양한 스킬 조합을 원하기 때문에 가능한 모든 스킬 조합을 알아보고 싶어 한다. 단, 시전 시간 동안은 다른 스킬을 사용할 수 없으며, 스킬을 안 쓰고 있는 시간은 없어야 한다.
 *
 * 예를 들어, N이 4초이고, M이 2초이면 가능한 스킬 조합은 AAAA, AAB, ABA, BAA, BB로 5가지가 가능하다.
 *
 * 입력
 * 첫 번째 줄에 싸움 시간 N과 B 스킬의 시전 시간 M이 주어진다. (N은 10,000 이하의 자연수, M은 2 이상 100 이하의 자연수)
 *
 * 출력
 * 가능한 조합의 수를 1,000,000,007로 나눈 나머지 값을 출력한다.
 *
 * 예제 입력 1
 * 4 2
 * 예제 출력 1
 * 5
 * 예제 입력 2
 * 3 2
 * 예제 출력 2
 * 3
 */
public class boj_17271 {
    static final int MOD = 1_000_000_007; // 모듈러 값
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 총 싸움 시간
        int M = sc.nextInt(); // B 스킬의 시전 시간
        sc.close();

        // DP 테이블 초기화
        int[] dp = new int[N + 1];
        dp[0] = 1; // 0초는 아무것도 사용하지 않은 경우 1가지

        // DP 채우기
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1]; // A 스킬 사용
            if (i >= M) {
                dp[i] = (dp[i] + dp[i - M]) % MOD; // B 스킬 사용
            }
        }

        // 결과 출력
        System.out.println(dp[N]);
    }
}
