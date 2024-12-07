package boj.bfs;
import java.util.*;

/**
 * 문제
 * N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다. 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다. 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.
 *
 *
 *
 * <그림 1>
 *
 * <그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다. 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.
 *
 *
 *
 * <그림 2>
 *
 *
 *
 * <그림 3>
 *
 * 모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다. 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.
 *
 * 출력
 * 출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.
 *
 * 예제 입력 1
 * 8 9
 * 0 0 0 0 0 0 0 0 0
 * 0 0 0 1 1 0 0 0 0
 * 0 0 0 1 1 0 1 1 0
 * 0 0 1 1 1 1 1 1 0
 * 0 0 1 1 1 1 1 0 0
 * 0 0 1 1 0 1 1 0 0
 * 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0
 * 예제 출력 1
 * 4
 */
public class boj_2638 {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        N = scanner.nextInt();
        M = scanner.nextInt();
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 치즈가 녹는 데 걸리는 시간
        int time = 0;
        while (true) {
            // 외부 공기 탐색
            markExternalAir();

            // 현재 치즈 상태에서 녹을 치즈 찾기
            List<int[]> meltList = findMeltCheese();

            if (meltList.isEmpty()) {
                // 녹을 치즈가 없으면 종료
                break;
            }

            // 치즈 녹이기
            for (int[] cheese : meltList) {
                grid[cheese[0]][cheese[1]] = 0;
            }

            time++;
        }

        // 결과 출력
        System.out.println(time);
    }

    // 외부 공기를 표시하는 함수
    private static void markExternalAir() {
        visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // 녹을 치즈를 찾는 함수
    private static List<int[]> findMeltCheese() {
        List<int[]> meltList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) { // 치즈인 경우
                    int airContact = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && ny >= 0 && nx < N && ny < M && grid[nx][ny] == 0 && visited[nx][ny]) {
                            airContact++;
                        }
                    }

                    // 외부 공기와 접촉한 변이 2개 이상이면 녹음
                    if (airContact >= 2) {
                        meltList.add(new int[]{i, j});
                    }
                }
            }
        }

        return meltList;
    }
}
