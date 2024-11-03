package boj.bfs;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 뱀과 사다리 게임을 즐겨 하는 큐브러버는 어느 날 궁금한 점이 생겼다.
 *
 * 주사위를 조작해 내가 원하는 수가 나오게 만들 수 있다면, 최소 몇 번만에 도착점에 도착할 수 있을까?
 *
 * 게임은 정육면체 주사위를 사용하며, 주사위의 각 면에는 1부터 6까지 수가 하나씩 적혀있다. 게임은 크기가 10×10이고, 총 100개의 칸으로 나누어져 있는 보드판에서 진행된다. 보드판에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.
 *
 * 플레이어는 주사위를 굴려 나온 수만큼 이동해야 한다. 예를 들어, 플레이어가 i번 칸에 있고, 주사위를 굴려 나온 수가 4라면, i+4번 칸으로 이동해야 한다. 만약 주사위를 굴린 결과가 100번 칸을 넘어간다면 이동할 수 없다. 도착한 칸이 사다리면, 사다리를 타고 위로 올라간다. 뱀이 있는 칸에 도착하면, 뱀을 따라서 내려가게 된다. 즉, 사다리를 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 크고, 뱀을 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 작아진다.
 *
 * 게임의 목표는 1번 칸에서 시작해서 100번 칸에 도착하는 것이다.
 *
 * 게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값을 구해보자.
 *
 * 입력
 * 첫째 줄에 게임판에 있는 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.
 *
 * 둘째 줄부터 N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동한다는 의미이다.
 *
 * 다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동한다는 의미이다.
 *
 * 1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다. 모든 칸은 최대 하나의 사다리 또는 뱀을 가지고 있으며, 동시에 두 가지를 모두 가지고 있는 경우는 없다. 항상 100번 칸에 도착할 수 있는 입력만 주어진다.
 *
 * 출력
 * 100번 칸에 도착하기 위해 주사위를 최소 몇 번 굴려야 하는지 출력한다.
 *
 * 예제 입력 1
 * 3 7
 * 32 62
 * 42 68
 * 12 98
 * 95 13
 * 97 25
 * 93 37
 * 79 27
 * 75 19
 * 49 47
 * 67 17
 * 예제 출력 1
 * 3
 */
public class boj_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀 수

        int[] board = new int[101]; // 1번 칸부터 100번 칸까지 사용
        Arrays.fill(board, -1); // 기본적으로 -1로 채움 (사다리나 뱀이 없는 칸)

        // 사다리 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        // 뱀 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        System.out.println(bfs(board));
    }

    public static int bfs(int[] board) {
        Queue<int[]> queue = new LinkedList<>(); // 큐에는 현재 위치와 주사위를 굴린 횟수를 저장
        boolean[] visited = new boolean[101]; // 방문 체크 배열
        queue.offer(new int[]{1, 0}); // 시작점 (1번 칸)과 주사위 굴린 횟수 0으로 시작
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int rolls = current[1];

            // 100번 칸에 도달한 경우
            if (position == 100) return rolls;

            // 주사위를 굴려 이동
            for (int dice = 1; dice <= 6; dice++) {
                int nextPosition = position + dice;

                // 보드 범위를 벗어나지 않는 경우에만 이동
                if (nextPosition <= 100 && !visited[nextPosition]) {
                    // 사다리나 뱀이 있는 경우 해당 위치로 이동
                    if (board[nextPosition] != -1) {
                        nextPosition = board[nextPosition];
                    }

                    // 이동한 위치를 방문 처리하고 큐에 추가
                    if (!visited[nextPosition]) {
                        visited[nextPosition] = true;
                        queue.offer(new int[]{nextPosition, rolls + 1});
                    }
                }
            }
        }

        return -1; // 이 부분은 문제 조건상 실행되지 않음 (항상 100번 칸에 도달할 수 있음)
    }
}
