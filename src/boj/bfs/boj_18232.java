package boj.bfs;
import java.util.*;

/**
 * 문제
 * 꽉꽉나라에 사는 주예와 방주는 점 S에서 만나 저녁을 먹기로 했다. 주예는 점 S에 도착했지만 길치인 방주가 약속시간이 30분이 지나도 나타나지 않자 방주에게 연락을 하여 방주가 점 E에 있다는 사실을 알아냈다. 주예는 방주에게 그 위치에 가만히 있으라고 했고, 직접 점 E로 가려고 한다.
 *
 * 꽉꽉나라에는 1부터 N까지의 각 점에 하나의 텔레포트 정거장이 위치해 있고 텔레포트를 통하여 연결되어 있는 다른 텔레포트의 정거장으로 이동할 수 있다. 주예는 현재 위치가 점 X라면 X+1이나 X-1로 이동하거나 X에 위치한 텔레포트와 연결된 지점으로 이동할 수 있으며 각 행동에는 1초가 소요된다. 배가 고픈 주예는 최대한 빨리 방주와 만나고 싶어 한다.
 *
 * N과 텔레포트 연결 정보가 주어질 때 점 S에 있는 주예가 점 E까지 가는 최소 시간을 구해보자.
 *
 * 입력
 * 첫 번째 줄에 정수 N, M이 공백으로 구분되어 주어진다. (2 ≤ N ≤ 300,000, 0 ≤ M ≤ min(N×(N-1)/2, 300,000))
 *
 * 두 번째 줄에 정수 S, E가 공백으로 구분되어 주어진다. (1 ≤ S, E ≤ N, S ≠ E)
 *
 * 그 다음 줄부터 M개의 줄에 걸쳐 텔레포트 연결 정보를 의미하는 정수 x, y가 주어진다. (1 ≤ x, y ≤ N, x ≠ y)
 *
 * x y는 점 x의 텔레포트와 점 y의 텔레포트가 연결되어 있다는 뜻이다. M개의 연결정보는 중복되는 x y쌍이 없도록 주어진다.
 *
 * 출력
 * 첫 번째 줄에 주예와 방주가 만날 수 있는 최소 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 1
 * 1 5
 * 1 4
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 10 3
 * 2 5
 * 1 6
 * 1 3
 * 2 8
 * 예제 출력 2
 * 3
 */
public class boj_18232 {
    static List<List<Integer>> teleportMap;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 노드 수
        int M = sc.nextInt(); // 텔레포트 연결 수
        int S = sc.nextInt(); // 시작점
        int E = sc.nextInt(); // 도착점

        teleportMap = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            teleportMap.add(new ArrayList<>());
        }

        // 텔레포트 연결 정보 저장
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            teleportMap.get(x).add(y);
            teleportMap.get(y).add(x);
        }

        visited = new boolean[N + 1];
        System.out.println(bfs(S, E));
    }

    static int bfs(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int time = current[1];

            if (pos == end) return time;

            // X-1 이동
            if (pos - 1 >= 1 && !visited[pos - 1]) {
                visited[pos - 1] = true;
                queue.offer(new int[]{pos - 1, time + 1});
            }

            // X+1 이동
            if (pos + 1 <= N && !visited[pos + 1]) {
                visited[pos + 1] = true;
                queue.offer(new int[]{pos + 1, time + 1});
            }

            // 텔레포트 이동
            for (int next : teleportMap.get(pos)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, time + 1});
                }
            }
        }

        return -1; // 도달 못한 경우 (문제 조건상 없음)
    }
}
