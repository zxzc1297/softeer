package boj.dp;
import java.util.*;

/**
 * 문제
 * 민건이네 과일 농장은 N가지 종류의 과일을 재배하는 중이다. 평소 민건이에게 앙심을 품고 있던 지환이는 민건이를 골탕 먹이기 위하여 민건이네 과일 농장에서 과일들을 훔치기로 다짐했다. 지환이는 완벽한 범죄를 위하여 처음 생각한 개수 만큼만 훔치려고 한다. 이때 지환이가 훔칠 수 있는 경우의 수가 몇가지나 될 지 알아보자. 단, 모든 종류의 과일을 적어도 1개는 훔친다.
 *
 * 입력
 * 첫째 줄에 과일의 종류 수 N(1 ≤ N ≤ 10)이 주어진다.
 *
 * 둘째 줄에 훔치려 하는 과일의 개수 M(N ≤ M ≤ 30)이 주어진다.
 *
 * 출력
 * 첫째 줄에 훔칠 수 있는 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 10
 * 예제 출력 1
 * 36
 */
public class boj_17213 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 과일 종류 수
        int M = sc.nextInt(); // 훔칠 과일 개수
        sc.close();

        // 중복 조합 공식: (M-1) C (N-1)
        long answer = combination(M - 1, N - 1);
        System.out.println(answer);
    }

    // 조합 계산 함수
    public static long combination(int n, int r) {
        if (r == 0 || n == r) return 1;
        if (r > n) return 0;

        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }
}
