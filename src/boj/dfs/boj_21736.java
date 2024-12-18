package boj.dfs;
import java.util.*;

/**
 * 문제
 * 2020년에 입학한 헌내기 도연이가 있다. 도연이는 비대면 수업 때문에 학교에 가지 못해 학교에 아는 친구가 없었다. 드디어 대면 수업을 하게 된 도연이는 어서 캠퍼스 내의 사람들과 친해지고 싶다.
 *
 * 도연이가 다니는 대학의 캠퍼스는
 * $N \times M$ 크기이며 캠퍼스에서 이동하는 방법은 벽이 아닌 상하좌우로 이동하는 것이다. 예를 들어, 도연이가 (
 * $x$,
 * $y$)에 있다면 이동할 수 있는 곳은 (
 * $x+1$,
 * $y$), (
 * $x$,
 * $y+1$), (
 * $x-1$,
 * $y$), (
 * $x$,
 * $y-1$)이다. 단, 캠퍼스의 밖으로 이동할 수는 없다.
 *
 * 불쌍한 도연이를 위하여 캠퍼스에서 도연이가 만날 수 있는 사람의 수를 출력하는 프로그램을 작성해보자.
 *
 * 입력
 * 첫째 줄에는 캠퍼스의 크기를 나타내는 두 정수
 * $N$ (
 * $ 1 \leq N \leq 600$),
 * $M$ (
 * $ 1 \leq M \leq 600$)이 주어진다.
 *
 * 둘째 줄부터
 * $N$개의 줄에는 캠퍼스의 정보들이 주어진다. O는 빈 공간, X는 벽, I는 도연이, P는 사람이다. I가 한 번만 주어짐이 보장된다.
 *
 * 출력
 * 첫째 줄에 도연이가 만날 수 있는 사람의 수를 출력한다. 단, 아무도 만나지 못한 경우 TT를 출력한다.
 *
 * 예제 입력 1
 * 3 5
 * OOOPO
 * OIOOX
 * OOOXP
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 3 3
 * IOX
 * OXP
 * XPP
 * 예제 출력 2
 * TT
 */
public class boj_21736 {
    static int N, M; // 캠퍼스 크기
    static char[][] campus; // 캠퍼스 맵
    static boolean[][] visited; // 방문 체크 배열
    static int peopleCount = 0; // 도연이가 만난 사람의 수

    // 상하좌우 이동을 위한 방향 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        campus = new char[N][M];
        visited = new boolean[N][M];

        int startX = 0, startY = 0;

        // 캠퍼스 정보 입력 및 도연이 위치 확인
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < M; j++) {
                campus[i][j] = line.charAt(j);
                if (campus[i][j] == 'I') { // 도연이의 위치
                    startX = i;
                    startY = j;
                }
            }
        }

        // DFS 시작
        dfs(startX, startY);

        // 결과 출력
        if (peopleCount == 0) {
            System.out.println("TT");
        } else {
            System.out.println(peopleCount);
        }
    }

    // DFS 탐색
    public static void dfs(int x, int y) {
        visited[x][y] = true;

        // 만약 사람이 있는 곳(P)이라면 카운트 증가
        if (campus[x][y] == 'P') {
            peopleCount++;
        }

        // 상하좌우로 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 이동할 좌표가 유효하고, 방문하지 않았으며, 벽이 아닌 경우에만 이동
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && campus[nx][ny] != 'X') {
                dfs(nx, ny);
            }
        }
    }
}
