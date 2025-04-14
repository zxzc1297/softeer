package boj.bfs;
import java.util.*;

/**
 * 문제
 * N×M 크기의 공간에 아기 상어 여러 마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 아기 상어가 최대 1마리 존재한다.
 *
 * 어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와의 거리이다. 두 칸의 거리는 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수이고, 이동은 인접한 8방향(대각선 포함)이 가능하다.
 *
 * 안전 거리가 가장 큰 칸을 구해보자.
 *
 * 입력
 * 첫째 줄에 공간의 크기 N과 M(2 ≤ N, M ≤ 50)이 주어진다. 둘째 줄부터 N개의 줄에 공간의 상태가 주어지며, 0은 빈 칸, 1은 아기 상어가 있는 칸이다. 빈 칸과 상어의 수가 각각 한 개 이상인 입력만 주어진다.
 *
 * 출력
 * 첫째 줄에 안전 거리의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 5 4
 * 0 0 1 0
 * 0 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 0 0 0 1
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 7 4
 * 0 0 0 1
 * 0 1 0 0
 * 0 0 0 0
 * 0 0 0 1
 * 0 0 0 0
 * 0 1 0 0
 * 0 0 0 1
 * 예제 출력 2
 * 2
 */
public class boj_17086 {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;

    // 8방향 이동 (상하좌우 + 대각선)
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();

        // 입력 + 상어 위치를 모두 큐에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // BFS 시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }

        // 가장 큰 거리 찾기
        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxDist = Math.max(maxDist, dist[i][j]);
            }
        }

        System.out.println(maxDist);
    }
}
