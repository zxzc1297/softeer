package boj.bfs;
import java.util.*;

/**
 * 문제
 * 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.
 *
 * 문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.
 *
 * 입력
 * 지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)
 *
 * 다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다. 입력에서 2는 단 한개이다.
 *
 * 출력
 * 각 지점에서 목표지점까지의 거리를 출력한다. 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
 *
 * 예제 입력 1
 * 15 15
 * 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 0 0 0 1
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 1 0 0 0
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 * 예제 출력 1
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
 * 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
 * 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
 * 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 * 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
 * 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
 * 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
 * 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
 * 11 12 13 14 15 16 17 18 19 20 0 0 0 0 25
 * 12 13 14 15 16 17 18 19 20 21 0 29 28 27 26
 * 13 14 15 16 17 18 19 20 21 22 0 30 0 0 0
 * 14 15 16 17 18 19 20 21 22 23 0 31 32 33 34
 */
public class boj_14940 {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0}; // 하, 상, 우, 좌
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로

        map = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];

        int startX = 0, startY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    System.out.print(0 + " ");
                } else if (!visited[i][j]) {
                    System.out.print(-1 + " ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        dist[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[cx][cy] + 1;
                queue.add(new int[]{nx, ny});
            }
        }
    }
}
