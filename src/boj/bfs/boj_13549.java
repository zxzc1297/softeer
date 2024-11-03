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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 수빈이의 위치
        int K = sc.nextInt();  // 동생의 위치

        // 수빈이가 이미 동생의 위치에 있는 경우
        if (N == K) {
            System.out.println(0);
            return;
        }

        // BFS를 위한 큐와 방문 배열
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001];  // 방문 시간 저장 배열

        queue.offer(N);
        visited[N] = 1;  // 시작 위치 방문 처리 (0초가 아닌 1로 시작해 나중에 -1 처리)

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 위치에서 이동 가능한 위치 계산
            for (int next : new int[]{current * 2, current - 1, current + 1}) {
                // next 위치가 범위 내에 있고 아직 방문하지 않은 경우
                if (next >= 0 && next <= 100000 && visited[next] == 0) {
                    // 순간이동인 경우에는 0초 소요
                    if (next == current * 2) {
                        visited[next] = visited[current];
                        queue.add(next);  // 높은 우선순위로 처리하기 위해 먼저 큐에 넣기
                    } else {
                        visited[next] = visited[current] + 1;  // 걷는 경우 1초 추가
                        queue.offer(next);
                    }

                    // 동생의 위치에 도달한 경우 최단 시간 출력
                    if (next == K) {
                        System.out.println(visited[next] - 1);
                        return;
                    }
                }
            }
        }
    }
}
