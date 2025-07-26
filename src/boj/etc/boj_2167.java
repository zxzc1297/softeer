package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 2차원 배열이 주어졌을 때 (i, j) 위치부터 (x, y) 위치까지에 저장되어 있는 수들의 합을 구하는 프로그램을 작성하시오. 배열의 (i, j) 위치는 i행 j열을 나타낸다.
 *
 * 입력
 * 첫째 줄에 배열의 크기 N, M(1 ≤ N, M ≤ 300)이 주어진다. 다음 N개의 줄에는 M개의 정수로 배열이 주어진다. 배열에 포함되어 있는 수는 절댓값이 10,000보다 작거나 같은 정수이다. 그 다음 줄에는 합을 구할 부분의 개수 K(1 ≤ K ≤ 10,000)가 주어진다. 다음 K개의 줄에는 네 개의 정수로 i, j, x, y가 주어진다(1 ≤ i ≤ x ≤ N, 1 ≤ j ≤ y ≤ M).
 *
 * 출력
 * K개의 줄에 순서대로 배열의 합을 출력한다. 배열의 합은 231-1보다 작거나 같다.
 *
 * 예제 입력 1
 * 2 3
 * 1 2 4
 * 8 16 32
 * 3
 * 1 1 2 3
 * 1 2 1 2
 * 1 3 2 3
 * 예제 출력 1
 * 63
 * 2
 * 36
 */
public class boj_2167 {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 입력: 배열 크기
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 행
        int M = Integer.parseInt(st.nextToken());  // 열

        // 2. 원본 배열 및 누적 합 배열 선언
        int[][] arr = new int[N + 1][M + 1]; // 1-indexed
        int[][] prefix = new int[N + 1][M + 1];

        // 3. 입력: 배열 데이터
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                // 2차원 누적합 계산
                prefix[i][j] = arr[i][j]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        // 4. 입력: 쿼리 수
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 5. 쿼리 처리
        for (int q = 0; q < K; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int result = prefix[x][y]
                    - prefix[i - 1][y]
                    - prefix[x][j - 1]
                    + prefix[i - 1][j - 1];

            sb.append(result).append('\n');
        }

        // 6. 출력
        System.out.print(sb);
    }
}
