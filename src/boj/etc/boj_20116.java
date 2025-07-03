package boj.etc;
import java.util.*;

/**
 * 문제
 * 진수에게는 총 n개의 상자가 있다. 모든 상자는 2L × 2L 크기의 정사각형 모양이고, 상자의 밀도는 균일하다.
 *
 * 진수는 이 상자들을 바닥에서부터 차곡차곡 쌓아올린다. 바닥은 y=0 이다.
 *
 * 이 상자들을 바닥에 가까이 있는 있는 상자부터 각각 1번, 2번, ..., n번 상자라고 보았을 때 i번 상자의 중심은 (xi, 2L×i - L) 이 되고, 이는 i번 상자 1개의 무게 중심과 같다.
 *
 * 위에서 상자의 밀도는 균일하다고 가정하였으므로, 상자 여러 개의 무게 중심을 구하면 각각의 상자들의 무게 중심을 평균 낸 값이 된다.
 *
 * 진수는 원하는 중심 좌표에 상자들을 쌓아올릴 때 무너지지 않고 균형을 이루는 지를 알고 싶다.
 *
 * 모든 1 ≤ i < n 에 대하여 i+1, i+2, ..., n 번 상자들의 무게 중심의 x좌표가 i번 상자의 구간 안에 포함되면 박스 전체가 균형을 이루게 된다. i번 상자의 구간는 xi-L과 xi+L 사이로 정의하며, xi-L, xi+L은 포함하지 않는다. 따라서 상자 모서리에 걸쳐 있는 경우는 균형을 이루지 않는 것으로 한다.
 *
 * n개의 상자들의 중심 좌표가 주어지면, 해당 상자들이 균형을 이루는지 알아내보자.
 *
 * 입력
 * 첫 번째 줄에는 상자의 개수 n (1 ≤ n ≤ 200,000) 과 상자의 사이즈 L (1 ≤ L ≤ 109) 이 주어진다.
 *
 * 두 번째 줄에는 진수가 원하는 각각의 상자들의 무게 중심 x좌표 x1, x2, ..., xn (-109 ≤ xi ≤ 109) 이 주어진다.
 *
 * 출력
 * 첫 번째 줄에 해당 상자들이 균형을 이룬다면 "stable", 그렇지 않다면 "unstable" 을 출력한다.
 */
public class boj_20116 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();      // 상자의 개수
        long L = sc.nextLong();    // 상자의 한 변 크기 / 2

        long[] x = new long[n + 1]; // 1-based indexing
        for (int i = 1; i <= n; i++) {
            x[i] = sc.nextLong();
        }

        // 누적합 계산 (double로 처리해야 나중에 평균 계산 시 정밀도 유지)
        double[] prefixSum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + x[i];
        }

        // i번 상자의 위에 쌓인 상자들의 무게 중심 평균이 범위 안에 있는지 확인
        for (int i = 1; i < n; i++) {
            int count = n - i;
            double sum = prefixSum[n] - prefixSum[i];
            double avg = sum / count;

            double left = x[i] - L;
            double right = x[i] + L;

            // 경계 포함하지 않음
            if (!(avg > left && avg < right)) {
                System.out.println("unstable");
                return;
            }
        }

        System.out.println("stable");
    }
}
