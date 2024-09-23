package boj.bfs;
import java.util.*;

public class boj_2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};  // 상하좌우 이동을 위한 x축 변화
    static int[] dy = {0, 0, -1, 1};  // 상하좌우 이동을 위한 y축 변화

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        int maxHeight = 0;

        // 지역의 높이 정보 입력 및 최대 높이 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeZones = 0;

        // 모든 높이에 대해 물에 잠기지 않는 영역 탐색
        for (int height = 0; height <= maxHeight; height++) {
            visited = new boolean[N][N];
            int safeZones = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 물에 잠기지 않은 새로운 영역 발견 시 BFS 탐색 시작
                    if (map[i][j] > height && !visited[i][j]) {
                        bfs(i, j, height);
                        safeZones++;
                    }
                }
            }
            // 가장 많은 안전 영역의 개수 저장
            maxSafeZones = Math.max(maxSafeZones, safeZones);
        }

        System.out.println(maxSafeZones);
        sc.close();
    }

    // BFS를 이용하여 인접한 물에 잠기지 않은 지역 탐색
    static void bfs(int x, int y, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                // 범위를 벗어나지 않고, 방문하지 않았으며, 물에 잠기지 않은 지역인 경우
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (map[nx][ny] > height && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}
