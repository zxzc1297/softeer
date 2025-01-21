package boj.greedy;
import java.util.*;

/**
 * 문제
 * 0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)
 *
 * 입력
 * 첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.
 *
 * 출력
 * 첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
 *
 * 예제 입력 1
 * 3 4
 * 0000
 * 0010
 * 0000
 * 1001
 * 1011
 * 1001
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 3 3
 * 111
 * 111
 * 111
 * 000
 * 000
 * 000
 * 예제 출력 2
 * 1
 * 예제 입력 3
 * 1 1
 * 1
 * 0
 * 예제 출력 3
 * -1
 */
public class boj_1080 {
    static int[][] A, B;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        int n = sc.nextInt(); // 행렬의 행 개수
        int m = sc.nextInt(); // 행렬의 열 개수
        A = new int[n][m];
        B = new int[n][m];

        // 행렬 A 입력
        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < m; j++) {
                A[i][j] = row.charAt(j) - '0';
            }
        }

        // 행렬 B 입력
        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < m; j++) {
                B[i][j] = row.charAt(j) - '0';
            }
        }

        // 연산 횟수 계산
        int result = getMinOperations(n, m);
        System.out.println(result);
    }

    // 최소 연산 횟수 계산 함수
    private static int getMinOperations(int n, int m) {
        int count = 0;

        // 3x3 변환을 적용할 수 있는 범위만 탐색
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                if (A[i][j] != B[i][j]) {
                    flip(i, j); // 3x3 뒤집기 연산 수행
                    count++;
                }
            }
        }

        // 변환이 완료되었는지 확인
        if (!isSame(n, m)) {
            return -1; // 변환 불가능
        }

        return count;
    }

    // 3x3 영역 뒤집기 함수
    private static void flip(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                A[i][j] = 1 - A[i][j]; // 0 -> 1, 1 -> 0
            }
        }
    }

    // A와 B가 같은지 확인하는 함수
    private static boolean isSame(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
