package boj.etc;
import java.util.*;

/**
 * 문제
 * N개의 수 A1, A2, ..., AN이 입력으로 주어진다. 총 M개의 구간 i, j가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 둘째 줄에는 A1, A2, ..., AN이 주어진다. (-1,000 ≤ Ai ≤ 1,000) 셋째 줄에는 구간의 개수 M이 주어진다. (1 ≤ M ≤ 100,000) 넷째 줄부터 M개의 줄에는 각 구간을 나타내는 i와 j가 주어진다. (1 ≤ i ≤ j ≤ N)
 *
 * 출력
 * 총 M개의 줄에 걸쳐 입력으로 주어진 구간의 합을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 10 20 30 40 50
 * 5
 * 1 3
 * 2 4
 * 3 5
 * 1 5
 * 4 4
 * 예제 출력 1
 * 60
 * 90
 * 120
 * 150
 * 40
 * 예제 입력 2
 * 3
 * 1 0 -1
 * 6
 * 1 1
 * 2 2
 * 3 3
 * 1 2
 * 2 3
 * 1 3
 * 예제 출력 2
 * 1
 * 0
 * -1
 * 1
 * -1
 * 0
 */
public class boj_11441 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 수의 개수
        int[] A = new int[N + 1]; // 1-based index
        int[] prefix = new int[N + 1]; // 누적합 배열

        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
            prefix[i] = prefix[i - 1] + A[i];
        }

        int M = sc.nextInt(); // 구간 수

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < M; q++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            sb.append(prefix[j] - prefix[i - 1]).append("\n");
        }

        System.out.print(sb); // 출력 최적화
    }
}
