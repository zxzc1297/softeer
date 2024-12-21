package boj.bfs;
import java.util.*;

/**
 * 문제
 * 전쟁은 어느덧 전면전이 시작되었다. 결국 전투는 난전이 되었고, 우리 병사와 적국 병사가 섞여 싸우게 되었다. 그러나 당신의 병사들은 흰색 옷을 입고, 적국의 병사들은 파란색 옷을 입었기 때문에 서로가 적인지 아군인지는 구분할 수 있다. 문제는 같은 팀의 병사들은 모이면 모일수록 강해진다는 사실이다.
 *
 * N명이 뭉쳐있을 때는 N2의 위력을 낼 수 있다. 과연 지금 난전의 상황에서는 누가 승리할 것인가? 단, 같은 팀의 병사들이 대각선으로만 인접한 경우는 뭉쳐 있다고 보지 않는다.
 *
 * 입력
 * 첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.
 *
 * 출력
 * 첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * WBWWW
 * WWWWW
 * BBBBB
 * BBBWW
 * WWWWW
 * 예제 출력 1
 * 130 65
 */
public class boj_1303 {
    static int N, M; // 전쟁터의 크기
    static char[][] battlefield; // 전쟁터 정보
    static boolean[][] visited; // 방문 여부 체크
    static int[] dx = {1, -1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        N = scanner.nextInt();
        M = scanner.nextInt();
        battlefield = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            battlefield[i] = scanner.next().toCharArray();
        }

        int myPower = 0, enemyPower = 0;

        // 모든 위치를 탐색
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    // 새로운 팀 발견 시 위력 계산
                    int teamSize = bfs(i, j, battlefield[i][j]);
                    int power = teamSize * teamSize;

                    if (battlefield[i][j] == 'W') {
                        myPower += power;
                    } else if (battlefield[i][j] == 'B') {
                        enemyPower += power;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(myPower + " " + enemyPower);
    }

    // BFS를 사용한 팀 크기 계산
    public static int bfs(int x, int y, char team) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[nx][ny] && battlefield[nx][ny] == team) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    size++;
                }
            }
        }

        return size;
    }
}
