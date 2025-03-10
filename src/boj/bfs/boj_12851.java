package boj.bfs;
import java.util.*;

/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 4
 * 2
 */
public class boj_12851 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 수빈이의 위치 N, 동생의 위치 K 입력
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        // BFS를 위한 큐와 방문 배열
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001]; // 방문 시간 기록 (초 단위)
        int[] ways = new int[100001];   // 해당 위치에 도달하는 방법 수 기록

        // 초기 위치 설정
        queue.add(N);
        visited[N] = 1; // 시작 지점 방문 처리
        ways[N] = 1;    // 시작 지점은 1가지 방법

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 위치에서 이동 가능한 세 가지 경우
            for (int next : new int[] {current - 1, current + 1, current * 2}) {
                if (next >= 0 && next <= 100000) { // 유효한 범위 체크
                    // 처음 방문하는 경우
                    if (visited[next] == 0) {
                        visited[next] = visited[current] + 1;
                        ways[next] = ways[current];
                        queue.add(next);
                    }
                    // 이미 방문한 경우, 같은 시간에 도달할 때만 방법 수 추가
                    else if (visited[next] == visited[current] + 1) {
                        ways[next] += ways[current];
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(visited[K] - 1); // 초 단위는 1부터 시작하므로 1을 빼줌
        System.out.println(ways[K]);
    }
}
