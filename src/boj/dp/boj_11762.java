package boj.dp;
import java.util.*;

/**
 * 문제
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
 *
 *
 *
 * 입력
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 9
 * 예제 출력 2
 * 55
 */
public class boj_11762 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];

        //dp 초기값 설정
        dp[1] = 1;
        if(n>1) dp[2] = 2;

        if(n > 2){
            // 점화식 반복하여 미리 세팅
            for(int i=3; i<=n; i++){
                //미리 나머지를 계속 더하기
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }
        }

        System.out.print(dp[n]);
        sc.close();
    }
}
