package boj.bfs;
import java.util.*;

/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 2
 */
public class boj_13549 {
    static int[] visited = new int[100001];  // 방문 시간을 기록하는 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        if (N >= K) {
            System.out.println(N - K);  // N이 K보다 크거나 같으면 그냥 걷는 것이 최단 시간
        } else {
            System.out.println(bfs(N, K));
        }
    }

    static int bfs(int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = 1;  // 시작점 방문 처리 (0초를 나타내기 위해 1로 설정)

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 동생의 위치에 도달하면 시간을 반환
            if (current == target) {
                return visited[current] - 1;
            }

            // 걷기: X-1, X+1로 이동
            if (current - 1 >= 0 && visited[current - 1] == 0) {
                visited[current - 1] = visited[current] + 1;
                queue.offer(current - 1);
            }

            if (current + 1 <= MAX && visited[current + 1] == 0) {
                visited[current + 1] = visited[current] + 1;
                queue.offer(current + 1);
            }

            // 순간이동: 2*X로 이동
            if (current * 2 <= MAX && visited[current * 2] == 0) {
                visited[current * 2] = visited[current];
                queue.offer(current * 2);
            }
        }

        return -1;  // 논리상 이 코드는 실행되지 않음
    }
}
