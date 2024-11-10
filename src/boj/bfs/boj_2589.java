package boj.bfs;
import java.util.*;

/**
 * 문제
 * 보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.
 *
 *
 *
 * 예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.
 *
 *
 *
 * 보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.
 *
 * 출력
 * 첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 7
 * WLLWWWL
 * LLLWLLL
 * LWLWLWW
 * LWLWLLL
 * WLLWLWW
 * 예제 출력 1
 * 8
 */
public class boj_2589 {
    static int rows, cols;
    static char[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 지도 크기 입력
        rows = sc.nextInt();
        cols = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기

        // 지도 입력
        map = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 각 육지에서 BFS를 수행하여 최장 거리 구하기
        int maxDistance = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 'L') {
                    maxDistance = Math.max(maxDistance, bfs(i, j));
                }
            }
        }

        // 결과 출력
        System.out.println(maxDistance);
    }

    // BFS 메서드: 시작 지점 (x, y)에서의 최장 거리 계산
    private static int bfs(int x, int y) {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0}); // {x, y, 거리}
        visited[x][y] = true;

        int maxDist = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int curDist = current[2];

            maxDist = Math.max(maxDist, curDist);

            // 네 방향 탐색
            for (int[] dir : directions) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                        && !visited[newX][newY] && map[newX][newY] == 'L') {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, curDist + 1});
                }
            }
        }

        return maxDist;
    }
}
