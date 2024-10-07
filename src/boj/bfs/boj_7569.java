package boj.bfs;
import java.util.*;

/**
 * 문제
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려서 창고에 보관한다.
 *
 *
 *
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어 한다.
 *
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100 이다. 둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다. 각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다. 이러한 N개의 줄이 H번 반복하여 주어진다.
 *
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 여러분은 토마토가 모두 익을 때까지 최소 며칠이 걸리는지를 계산해서 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 *
 * 예제 입력 1
 * 5 3 1
 * 0 -1 0 0 0
 * -1 -1 0 1 1
 * 0 0 0 1 1
 * 예제 출력 1
 * -1
 */
public class boj_7569 {
    static int M, N, H; // M: 가로, N: 세로, H: 높이
    static int[][][] box; // 3차원 상자
    static int[][][] days; // 익은 날짜를 기록
    static Queue<int[]> queue = new LinkedList<>();

    // 6방향 (위, 아래, 왼쪽, 오른쪽, 앞, 뒤)
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt(); // 가로
        N = sc.nextInt(); // 세로
        H = sc.nextInt(); // 높이

        box = new int[H][N][M]; // 상자 3차원 배열
        days = new int[H][N][M]; // 날짜를 기록하는 배열

        // 입력 받기
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = sc.nextInt();
                    if (box[h][n][m] == 1) {
                        // 익은 토마토의 위치를 큐에 추가
                        queue.add(new int[]{h, n, m});
                    }
                }
            }
        }

        // BFS 실행
        bfs();

        // 결과 계산
        int result = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    // 익지 않은 토마토가 남아 있으면 -1 출력
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    // 익는데 걸린 최대 일수 계산
                    result = Math.max(result, days[h][n][m]);
                }
            }
        }

        // 저장될 때부터 모두 익어있는 상태이면 0을 출력
        System.out.println(result);
    }

    // BFS
    static void bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int z = current[0];
            int y = current[1];
            int x = current[2];

            // 6방향 탐색
            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 범위 체크 및 익지 않은 토마토가 있는지 확인
                if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (box[nz][ny][nx] == 0) {
                        box[nz][ny][nx] = 1; // 익음
                        days[nz][ny][nx] = days[z][y][x] + 1; // 날짜 추가
                        queue.add(new int[]{nz, ny, nx}); // 큐에 추가
                    }
                }
            }
        }
    }
}
