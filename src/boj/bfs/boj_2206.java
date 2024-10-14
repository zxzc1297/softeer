package boj.bfs;
import java.util.*;

/**
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 *
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 *
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * 예제 입력 1
 * 6 4
 * 0100
 * 1110
 * 1000
 * 0000
 * 0111
 * 0000
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 4
 * 0111
 * 1111
 * 1111
 * 1110
 * 예제 출력 2
 * -1
 */
public class boj_2206 {
    static int n, m;
    static int[][] maps;
    static int[][][] visited; // 벽을 부수고 도달했는지 안 부수고 도달했는지 방문 여부
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        maps = new int[n][m];
        visited = new int[n][m][2];

        // 맵을 문자열로 입력받아서 처리
        for (int i = 0; i < n; i++) {
            String line = sc.next(); // 한 줄을 문자열로 입력받음
            for (int j = 0; j < m; j++) {
                maps[i][j] = line.charAt(j) - '0'; // 문자를 숫자로 변환
            }
        }

        // 정답 도출
        System.out.print(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1, 0}); // x좌표, y좌표, 이동거리, 벽 부순여부
        visited[0][0][0] = 1; // 첫 시작 등록

        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            int x = tmp[0];
            int y = tmp[1];
            int dist = tmp[2];
            int wallBroken = tmp[3];

            // 조건 충족 시 거리 리턴
            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            // 4 방향 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 여부
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 벽이 아니고 방문하지 않은 경우
                    if (maps[nx][ny] == 0 && visited[nx][ny][wallBroken] == 0) {
                        visited[nx][ny][wallBroken] = 1;
                        q.offer(new int[]{nx, ny, dist + 1, wallBroken});
                    }
                    // 벽이고 방문하지 않았고 부술 수 있는 경우
                    else if (maps[nx][ny] == 1 && wallBroken == 0 && visited[nx][ny][1] == 0) {
                        visited[nx][ny][1] = 1;
                        q.offer(new int[]{nx, ny, dist + 1, 1});
                    }
                }
            }
        }

        return -1;
    }
}
