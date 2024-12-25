package boj.bfs;
import java.util.*;

/**
 * 문제
 * 정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.
 *
 * 2를 곱한다.
 * 1을 수의 가장 오른쪽에 추가한다.
 * A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.
 *
 * 입력
 * 첫째 줄에 A, B (1 ≤ A < B ≤ 109)가 주어진다.
 *
 * 출력
 * A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력한다. 만들 수 없는 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 2 162
 * 예제 출력 1
 * 5
 * 2 → 4 → 8 → 81 → 162
 *
 * 예제 입력 2
 * 4 42
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * 100 40021
 * 예제 출력 3
 * 5
 */
public class boj_16953 {
    static class Node {
        long value;
        int steps;

        public Node(long value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        long B = sc.nextLong();

        System.out.println(bfs(A, B));
    }

    public static int bfs(long A, long B) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(A, 1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == B) {
                return current.steps;
            }

            // 2를 곱하는 경우
            if (current.value * 2 <= B) {
                queue.add(new Node(current.value * 2, current.steps + 1));
            }

            // 1을 추가하는 경우
            if (current.value * 10 + 1 <= B) {
                queue.add(new Node(current.value * 10 + 1, current.steps + 1));
            }
        }

        return -1; // B에 도달할 수 없는 경우
    }
}
