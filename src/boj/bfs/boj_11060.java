package boj.bfs;
import java.util.*;

/**
 * 문제
 * 재환이가 1×N 크기의 미로에 갇혀있다. 미로는 1×1 크기의 칸으로 이루어져 있고, 각 칸에는 정수가 하나 쓰여 있다. i번째 칸에 쓰여 있는 수를 Ai라고 했을 때, 재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프할 수 있다. 예를 들어, 3번째 칸에 쓰여 있는 수가 3이면, 재환이는 4, 5, 6번 칸 중 하나로 점프할 수 있다.
 *
 * 재환이는 지금 미로의 가장 왼쪽 끝에 있고, 가장 오른쪽 끝으로 가려고 한다. 이때, 최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성하시오. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 Ai (0 ≤ Ai ≤ 100)가 주어진다.
 *
 * 출력
 * 재환이가 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력한다. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 2 0 1 3 2 1 5 4 2
 * 예제 출력 1
 * 5
 */
public class boj_11060 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 미로 크기
        int[] A = new int[N]; // 각 칸의 점프 가능 거리
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(minJumps(N, A));
    }

    public static int minJumps(int N, int[] A) {
        boolean[] visited = new boolean[N]; // 방문 여부 체크
        Queue<int[]> queue = new LinkedList<>(); // BFS 탐색 (위치, 점프 횟수)

        queue.add(new int[]{0, 0}); // 시작 위치 (0번 인덱스, 점프 횟수 0)
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int index = current[0];
            int jumps = current[1];

            // 도착했으면 반환
            if (index == N - 1) {
                return jumps;
            }

            // 현재 위치에서 점프할 수 있는 범위 탐색
            for (int i = 1; i <= A[index]; i++) {
                int next = index + i;
                if (next < N && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, jumps + 1});
                }
            }
        }

        // 도달할 수 없는 경우
        return -1;
    }
}
