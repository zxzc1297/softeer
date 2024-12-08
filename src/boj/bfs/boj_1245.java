package boj.bfs;
import java.util.*;

/**
 * 문제
 * 농부 민식이가 관리하는 농장은 N×M 격자로 이루어져 있다. 민식이는 농장을 관리하기 위해 산봉우리마다 경비원를 배치하려 한다. 이를 위해 농장에 산봉우리가 총 몇 개 있는지를 세는 것이 문제다.
 *
 * 산봉우리의 정의는 다음과 같다. 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다. 여기서 "인접하다"의 정의는 X좌표 차이와 Y좌표 차이가 모두 1 이하인 경우이다. 또한 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다.
 *
 * 문제는 격자 내에 산봉우리의 개수가 총 몇 개인지 구하는 것이다.
 *
 * 입력
 * 첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)이 주어진다. 둘째 줄부터 N+1번째 줄까지 각 줄마다 격자의 높이를 의미하는 M개의 정수가 입력된다. 격자의 높이는 500보다 작거나 같은 음이 아닌 정수이다.
 *
 * 출력
 * 첫째 줄에 산봉우리의 개수를 출력한다.
 *
 * 예제 입력 1
 * 8 7
 * 4 3 2 2 1 0 1
 * 3 3 3 2 1 0 1
 * 2 2 2 2 1 0 0
 * 2 1 1 1 1 0 0
 * 1 1 0 0 0 1 0
 * 0 0 0 1 1 1 0
 * 0 1 2 2 1 1 0
 * 0 1 1 1 2 1 0
 * 예제 출력 1
 * 3
 */
public class boj_1245 {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        N = scanner.nextInt();
        M = scanner.nextInt();
        grid = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 산봉우리 개수 카운트
        int peakCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (isPeak(i, j)) {
                        peakCount++;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(peakCount);
    }

    // 산봉우리가 맞는지 확인하는 함수
    private static boolean isPeak(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> group = new ArrayList<>();
        boolean isPeak = true;

        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        group.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (grid[nx][ny] > grid[x][y]) {
                        // 더 높은 곳이 있으면 산봉우리가 아님
                        isPeak = false;
                    }
                    if (!visited[nx][ny] && grid[nx][ny] == grid[x][y]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        group.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return isPeak;
    }
}
