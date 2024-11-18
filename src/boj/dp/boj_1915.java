package boj.dp;
import java.util.*;

/**
 * 문제
 * n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.
 *
 * 0	1	0	0
 * 0	1	1	1
 * 1	1	1	0
 * 0	0	1	0
 * 위와 같은 예제에서는 가운데의 2×2 배열이 가장 큰 정사각형이다.
 *
 * 입력
 * 첫째 줄에 n, m(1 ≤ n, m ≤ 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * 0100
 * 0111
 * 1110
 * 0010
 * 예제 출력 1
 * 4
 */
public class boj_1915 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];

        // 입력 행렬 읽기
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        // 각 셀에서 끝나는 가장 큰 정사각형의 크기를 저장하는 DP 테이블
        int[][] dp = new int[n][m];
        int maxSize = 0;

        // DP 테이블 채우기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1; // 가장자리는 1일 경우 1로 설정
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }

        // 가장 큰 정사각형의 넓이 출력
        System.out.println(maxSize * maxSize);

        sc.close();
    }
}
