package softeer.Q21_Q30;
import java.util.*;

/**
 * 남우는 미로에 갇혔습니다. 미로는 n × m 크기의 격자로 이루어져 있고, 각 격자 칸은 남우, 출구, 유령, 빈 공간, 벽 중 하나로 이루어져 있습니다. 남우가 출구로 이동하면 미로를 탈출하게 되며, 출구로 이동하기 위해 시작 위치에서 상하 좌우로 인접한 칸 중 격자를 벗어나지 않으면서 벽이 아닌 칸을 골라 이동하는 것을 반복하게 됩니다.
 *
 * 만약 n 이 4이고, m 이 6인 격자가 다음과 같이 주어졌다면, 남우는 다음 경로로 탈출이 가능합니다.
 *
 * 이 미로 안에 유령이 있는 경우도 있습니다. 남우는 유령을 무서워 하기 때문에 유령을 마주치지 않고 탈출하고 싶습니다. 남우는 1초에 한 칸씩 상하 좌우로 인접한 칸 중 격자를 벗어나지 않으며 벽이 아닌 곳으로 이동이 가능하지만, 유령은 1초에 한 칸씩 상하 좌우로 인접한 칸 중 격자를 벗어나지 않는 어떤 칸으로든 이동이 가능합니다. 즉, 유령은 벽도 통과하여 이동하는 것이 가능합니다. 유령은 최선을 다해서 남우를 잡으려 하기에, 남우는 똑똑하게 유령을 피해 출구로 이동해야 합니다. 남우가 이동하는 도중 유령과 마주치게 되거나, 정확히 출구에 도착하는 순간 유령과 마주치게 되더라도 남우는 탈출에 실패하게 됩니다. 단, 유령은 필요에 따라 움직이지 않아도 됨에 유의합니다.
 *
 * 위의 그림에서 유령이 다음과 같이 2마리가 놓여있다면, 남우는 여전히 동일한 경로를 통해 5초 만에 성공적으로 탈출이 가능합니다. 남우가 탈출하는 동안 유령이 절대 남우를 잡을 수 없기 때문에 가능한 경우입니다.
 *
 * 하지만 초기 격자 상태가 다음과 같이 주어진 경우라면, 남우는 절대로 유령을 피해 탈출할 수 없습니다.
 *
 * 그 이유는 다음과 같습니다. 우선 남우는 탈출을 위해 처음에 꼭 아래 방향으로 이동해야 합니다.
 *
 * 남우는 출구로 가기 처음 3초 동안 꼭 다음과 같이 이동해야만 하는데, 이때 두 유령 모두 오른쪽으로만 3칸씩 이동한다고 생각해보겠습니다.
 *
 * 그러면 이제 남우는 두 유령에 둘러싸이게 되어 어떻게 이동해도 탈출할 수 없게 됩니다.
 *
 * 초기 격자 상태가 주어졌을 때, 남우가 유령을 피해 출구까지 이동하여 탈출하는 것이 가능한지 판단하는 프로그램을 작성해보세요.
 *
 *
 *
 * 본 문제의 저작권은 (주)브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금지합니다.
 *
 * [2024-07-24] 테스트케이스가 추가되었습니다.
 * 제약조건
 * 1 ≤ n, m ≤ 1,000
 * 3 ≤ n * m
 * 입력형식
 * 첫 번째 줄에는 격자의 크기를 나타내는 n, m값이 공백을 사이에 두고 주어집니다.
 *
 * 두 번째 줄부터는 n개의 줄에 걸쳐 각 행에 해당하는 격자의 정보가 공백없이 주어집니다. 'N'은 남우가 있는 곳, 'D'는 출구가 있는 곳, 'G'는 유령이 있는 곳이며 '#'은 벽이, '.'은 비어있는 칸임을 뜻합니다. 'N'(남우), 'D'(출구)는 반드시 하나만 주어지며, 'G'(유령)은 주어지지 않을 수도, 하나 이상 주어질 수도 있음에 유의합니다.
 *
 * 출력형식
 * 첫 번째 줄에 답을 출력합니다.
 *
 * 남우가 유령에게 잡히지 않고 출구에 도달하여 탈출할 수 있다면 Yes를, 그렇지 않다면 No를 출력합니다.
 *
 * 남우가 유령을 마주치지 않더라도 출구까지 절대 이동할 수 없는 경우에도 탈출이 불가능한 것임에 유의합니다.
 *
 * 입력예제1
 * 4 6
 * .....D
 * ......
 * .GN#..
 * G.....
 * 출력예제1
 * Yes
 * 입력예제2
 * 4 6
 * ...#.D
 * ...#..
 * .GN#..
 * G.....
 * 출력예제2
 * No
 * 입력예제3
 * 1 5
 * D#.NG
 * 출력예제3
 * No
 * 예제 3에서 남우는 영원히 출구에 도달할 수 없으므로 탈출할 수 없습니다.
 */
public class Question23 {
    static int n, m;
    static char[][] maze;
    static int[][] ghostTime;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.nextLine(); // 줄바꿈 처리

        maze = new char[n][m];
        ghostTime = new int[n][m];
        visited = new boolean[n][m];

        int startX = -1, startY = -1, exitX = -1, exitY = -1;
        List<int[]> ghosts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j);
                ghostTime[i][j] = Integer.MAX_VALUE;

                if (maze[i][j] == 'N') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 'D') {
                    exitX = i;
                    exitY = j;
                } else if (maze[i][j] == 'G') {
                    ghosts.add(new int[]{i, j});
                }
            }
        }

        // 유령의 최단 도달 시간 계산
        calculateGhostTime(ghosts);

        // 남우의 탈출 가능 여부 확인
        if (canEscape(startX, startY, exitX, exitY)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // 유령의 이동 시간 계산 (BFS)
    static void calculateGhostTime(List<int[]> ghosts) {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] ghost : ghosts) {
            queue.offer(new int[]{ghost[0], ghost[1], 0});
            ghostTime[ghost[0]][ghost[1]] = 0;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isInBounds(nx, ny) && ghostTime[nx][ny] > time + 1) {
                    ghostTime[nx][ny] = time + 1;
                    queue.offer(new int[]{nx, ny, time + 1});
                }
            }
        }
    }

    // 남우의 탈출 시도 (BFS)
    static boolean canEscape(int startX, int startY, int exitX, int exitY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            if (x == exitX && y == exitY) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isInBounds(nx, ny) && maze[nx][ny] != '#' && !visited[nx][ny] && ghostTime[nx][ny] > time + 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, time + 1});
                }
            }
        }

        return false;
    }

    // 격자 내부에 있는지 확인
    static boolean isInBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
