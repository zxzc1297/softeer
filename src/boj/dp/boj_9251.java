package boj.dp;
import java.util.*;

/**
 * 문제
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 *
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.
 *
 * 예제 입력 1
 * ACAYKP
 * CAPCAK
 * 예제 출력 1
 * 4
 */
public class boj_9251 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        // 두 문자열의 길이
        int len1 = str1.length();
        int len2 = str2.length();

        // DP 테이블 초기화 (len1+1 x len2+1 크기의 배열)
        int[][] dp = new int[len1 + 1][len2 + 1];

        // DP 테이블 채우기
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 두 문자가 같으면 이전 대각선에서 +1
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 다르면, 이전 행 또는 열 중 더 큰 값 가져오기
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // LCS 길이 출력
        System.out.println(dp[len1][len2]);

        sc.close();
    }
}
