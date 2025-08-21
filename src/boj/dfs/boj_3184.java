package boj.dfs;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 미키의 뒷마당에는 특정 수의 양이 있다. 그가 푹 잠든 사이에 배고픈 늑대는 마당에 들어와 양을 공격했다.
 *
 * 마당은 행과 열로 이루어진 직사각형 모양이다. 글자 '.' (점)은 빈 필드를 의미하며, 글자 '#'는 울타리를, 'o'는 양, 'v'는 늑대를 의미한다.
 *
 * 한 칸에서 수평, 수직만으로 이동하며 울타리를 지나지 않고 다른 칸으로 이동할 수 있다면, 두 칸은 같은 영역 안에 속해 있다고 한다. 마당에서 "탈출"할 수 있는 칸은 어떤 영역에도 속하지 않는다고 간주한다.
 *
 * 다행히 우리의 양은 늑대에게 싸움을 걸 수 있고 영역 안의 양의 수가 늑대의 수보다 많다면 이기고, 늑대를 우리에서 쫓아낸다. 그렇지 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
 *
 * 맨 처음 모든 양과 늑대는 마당 안 영역에 존재한다.
 *
 * 아침이 도달했을 때 살아남은 양과 늑대의 수를 출력하는 프로그램을 작성하라.
 *
 * 입력
 * 첫 줄에는 두 정수 R과 C가 주어지며(3 ≤ R, C ≤ 250), 각 수는 마당의 행과 열의 수를 의미한다.
 *
 * 다음 R개의 줄은 C개의 글자를 가진다. 이들은 마당의 구조(울타리, 양, 늑대의 위치)를 의미한다.
 *
 * 출력
 * 하나의 줄에 아침까지 살아있는 양과 늑대의 수를 의미하는 두 정수를 출력한다.
 *
 * 예제 입력 1
 * 6 6
 * ...#..
 * .##v#.
 * #v.#.#
 * #.o#.#
 * .###.#
 * ...###
 * 예제 출력 1
 * 0 2
 * 예제 입력 2
 * 8 8
 * .######.
 * #..o...#
 * #.####.#
 * #.#v.#.#
 * #.#.o#o#
 * #o.##..#
 * #.v..v.#
 * .######.
 * 예제 출력 2
 * 3 1
 * 예제 입력 3
 * 9 12
 * .###.#####..
 * #.oo#...#v#.
 * #..o#.#.#.#.
 * #..##o#...#.
 * #.#v#o###.#.
 * #..#v#....#.
 * #...v#v####.
 * .####.#vv.o#
 * .......####.
 * 예제 출력 3
 * 3 5
 */
public class boj_3184 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    // 이동 방향 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int sheep = 0; // 최종 양의 수
    static int wolf = 0;  // 최종 늑대의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 모든 좌표 탐색
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    int[] result = dfs(i, j); // 영역 탐색
                    int sheepCnt = result[0];
                    int wolfCnt = result[1];

                    if (sheepCnt > wolfCnt) {
                        sheep += sheepCnt; // 양이 늑대 이김
                    } else {
                        wolf += wolfCnt;   // 늑대가 양 잡아먹음
                    }
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    // DFS로 영역 탐색
    static int[] dfs(int x, int y) {
        visited[x][y] = true;

        int sheepCnt = 0;
        int wolfCnt = 0;

        if (map[x][y] == 'o') sheepCnt++;
        if (map[x][y] == 'v') wolfCnt++;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                if (!visited[nx][ny] && map[nx][ny] != '#') {
                    int[] next = dfs(nx, ny);
                    sheepCnt += next[0];
                    wolfCnt += next[1];
                }
            }
        }

        return new int[]{sheepCnt, wolfCnt};
    }
}
