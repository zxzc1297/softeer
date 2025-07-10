package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 승형이와 원빈이는 배낭여행을 가기 위해 두 개의 배낭을 준비했다. 각 배낭에는
 * $N$개의 물건이 들어있으며
 *
 * 첫 번째 배낭에는
 * $A_1, A_2, \dots, A_N$의 무게를 가진 물건들이 아래에서 위로 순서대로 쌓여 있다.
 * $A_N$이 맨 위에 있는 물건의 무게이다.
 * 두 번째 배낭에는
 * $B_1, B_2, \dots, B_N$의 무게를 가진 물건들이 아래에서 위로 순서대로 쌓여 있다.
 * $B_N$이 맨 위에 있는 물건의 무게이다.
 * 배낭의 무게는 배낭 안에 남아있는 물건들의 무게의 합으로 정의된다. 원빈이는 최대
 * $K$번 두 배낭 중 하나를 선택하여 맨 위에 있는 물건을 없앨 수 있다. 물건을 없애면 선택한 배낭의 무게는 없앤 물건의 무게만큼 줄어들며 선택한 배낭에 물건이 하나도 없으면 아무런 일도 일어나지 않는다.
 *
 * 원빈이의 행동이 모두 끝난 후, 승형이는 자신이 멜 가방을 결정한다. 승형이는 약삭빠르기 때문에 항상 두 배낭 중 더 가벼운 배낭을 메고 다닌다. 만약 두 배낭의 무게가 같다면 둘 중 아무 배낭이나 메고 다닌다. 원빈이가 들어야 하는 배낭은 승형이가 선택하지 않은 배낭이다.
 *
 * 원빈이가 들어야 하는 배낭의 무게의 최솟값을 구해보자.
 *
 * 입력
 * 첫 번째 줄에 두 정수
 * $N$과
 * $K$가 주어진다.
 * $(1 \leq N \leq 10^5, 0 \leq K \leq 2N)$ 
 *
 * 두 번째 줄에 첫 번째 배낭의 물건들의 무게를 나타내는
 * $N$개의 정수
 * $A_1, A_2, \dots, A_N$이 주어진다.
 * $(1 \leq A_i \leq 10^9)$ 
 *
 * 세 번째 줄에 두 번째 배낭의 물건들의 무게를 나타내는
 * $N$개의 정수
 * $B_1, B_2, \dots, B_N$이 주어진다.
 * $(1 \leq B_i \leq 10^9)$ 
 *
 * 출력
 * 원빈이가 들어야 하는 배낭의 무게의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 3 2
 * 3 1 4
 * 1 5 9
 * 예제 출력 1
 * 6
 * 원빈이는 첫 번째 배낭에서 맨 위의 물건을 없애고, 두 번째 배낭에서 맨 위의 물건을 없앨 수 있다. 최종적으로 남아있는 배낭의 무게는 4와 6이고, 원빈이는 무게 6의 배낭을 메게 된다.
 *
 * 예제 입력 2
 * 3 0
 * 3 1 4
 * 1 5 9
 * 예제 출력 2
 * 15
 */
public class boj_32628 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 빠른 출력을 위한 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        long[] A = new long[N];
        long[] B = new long[N];

        long totalA = 0;
        long totalB = 0;

        String[] aTokens = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(aTokens[i]);
            totalA += A[i];
        }

        String[] bTokens = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            B[i] = Long.parseLong(bTokens[i]);
            totalB += B[i];
        }

        // prefix sums (top부터 제거)
        long[] prefixA = new long[N + 1];
        long[] prefixB = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            prefixA[i] = prefixA[i - 1] + A[N - i];
            prefixB[i] = prefixB[i - 1] + B[N - i];
        }

        long minWeight = Long.MAX_VALUE;

        int maxRemove = Math.min(K, N);
        for (int i = 0; i <= maxRemove; i++) {
            int bRemove = K - i;
            if (bRemove > N) continue;

            long aWeight = totalA - prefixA[i];
            long bWeight = totalB - prefixB[bRemove];

            long winbin = Math.max(aWeight, bWeight);
            minWeight = Math.min(minWeight, winbin);
        }

        bw.write(minWeight + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
