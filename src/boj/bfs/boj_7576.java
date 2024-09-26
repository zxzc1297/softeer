package boj.bfs;
import java.util.*;

/**
 * 문제
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
 *
 *
 *
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
 *
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
 *
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 *
 * 예제 입력 1
 * 6 4
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 * 예제 출력 1
 * 8
 * 예제 입력 2
 * 6 4
 * 0 -1 0 0 0 0
 * -1 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * 6 4
 * 1 -1 0 0 0 0
 * 0 -1 0 0 0 0
 * 0 0 0 0 -1 0
 * 0 0 0 0 -1 1
 * 예제 출력 3
 * 6
 * 예제 입력 4
 * 5 5
 * -1 1 0 0 0
 * 0 -1 -1 -1 0
 * 0 -1 -1 -1 0
 * 0 -1 -1 -1 0
 * 0 0 0 0 0
 * 예제 출력 4
 * 14
 * 예제 입력 5
 * 2 2
 * 1 -1
 * -1 1
 * 예제 출력 5
 * 0
 */
public class boj_7576 {
    static int M, N;
    static int[][] box;
    static int[][] days; // 토마토가 익은 날짜를 저장하는 배열
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};  // 상하좌우 이동을 위한 배열
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();  // 가로
        N = sc.nextInt();  // 세로
        box = new int[N][M];
        days = new int[N][M];
        visited = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();

        // 입력 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                box[i][j] = sc.nextInt();
                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j});  // 익은 토마토가 있는 좌표를 큐에 삽입
                    visited[i][j] = true;
                }
                if (box[i][j] == -1) {
                    days[i][j] = -1;  // 토마토가 없는 곳은 -1로 설정
                }
            }
        }

        // BFS 탐색 시작
        bfs(queue);

        // 결과 계산
        int maxDays = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0 && !visited[i][j]) {
                    // 익지 않은 토마토가 남아 있으면 -1 출력
                    System.out.println(-1);
                    return;
                }
                maxDays = Math.max(maxDays, days[i][j]);
            }
        }

        // 저장될 때부터 모든 토마토가 익어있는 경우 0을 출력
        System.out.println(maxDays);
    }

    // BFS 함수
    static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위를 벗어나지 않고, 아직 방문하지 않은 익지 않은 토마토
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && box[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    days[nx][ny] = days[x][y] + 1;  // 하루가 지났으므로 +1
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
