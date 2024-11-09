package boj.tree;
import java.util.*;

/**
 * 문제
 * 영선회사에는 매우 좋은 문화가 있는데, 바로 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리 칭찬이 있다. 즉, 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬을 받는다.
 *
 * 모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
 *
 * 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력하시오,
 *
 * 입력
 * 첫째 줄에는 회사의 직원 수 n명, 최초의 칭찬의 횟수 m이 주어진다. 직원은 1번부터 n번까지 번호가 매겨져 있다. (2 ≤ n, m ≤ 100,000)
 *
 * 둘째 줄에는 직원 n명의 직속 상사의 번호가 주어진다. 직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장이다. 1번의 경우, 상사가 없으므로 -1이 입력된다.
 *
 * 다음 m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w가 주어진다. (2 ≤ i ≤ n, 1 ≤ w ≤ 1,000)
 *
 * 사장은 상사가 없으므로 칭찬을 받지 않는다.
 *
 * 출력
 * 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력하시오.
 *
 * 예제 입력 1
 * 5 3
 * -1 1 2 3 4
 * 2 2
 * 3 4
 * 5 6
 * 예제 출력 1
 * 0 2 6 6 12
 */
public class boj_14267 {
    static List<Integer>[] subordinates;
    static int[] praise;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int n = sc.nextInt(); // 직원 수
        int m = sc.nextInt(); // 최초의 칭찬 횟수

        // 직속 상사 정보 입력
        subordinates = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            subordinates[i] = new ArrayList<>();
        }

        // 각 직원의 직속 상사를 읽어와서 인접 리스트 구성
        for (int i = 1; i <= n; i++) {
            int manager = sc.nextInt();
            if (manager != -1) { // -1은 상사가 없는 경우 (사장)
                subordinates[manager].add(i);
            }
        }

        // 칭찬 정보 입력
        praise = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int employee = sc.nextInt();
            int amount = sc.nextInt();
            praise[employee] += amount;
        }

        // DFS를 통해 칭찬 전파
        propagatePraise(1);

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            System.out.print(praise[i] + " ");
        }
    }

    // 칭찬을 전파하는 DFS 메서드
    static void propagatePraise(int current) {
        for (int subordinate : subordinates[current]) {
            praise[subordinate] += praise[current];
            propagatePraise(subordinate);
        }
    }
}
