package boj.tree;
import java.util.*;

/**
 * 문제
 *
 * $N$개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
 *
 * 입력
 * 첫째 줄에 노드의 개수
 * $N$과 거리를 알고 싶은 노드 쌍의 개수
 * $M$이 입력되고 다음
 * $N-1$개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에는 거리를 알고 싶은
 * $M$개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.
 *
 * 출력
 *
 * $M$개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.
 *
 * 제한
 *
 * $2≤N≤1\,000$ 
 *
 * $1≤M≤1\,000$ 
 * 트리 상에 연결된 두 점과 거리는
 * $10\,000$ 이하인 자연수이다.
 * 트리 노드의 번호는
 * $1$부터
 * $N$까지 자연수이며, 두 노드가 같은 번호를 갖는 경우는 없다.
 * 예제 입력 1
 * 4 2
 * 2 1 2
 * 4 3 2
 * 1 4 3
 * 1 2
 * 3 2
 * 예제 출력 1
 * 2
 * 7
 */
public class boj_1240 {
    static List<int[]>[] adjList;
    static boolean[] visited;
    static int[] distances;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int N = sc.nextInt(); // 노드의 개수
        int M = sc.nextInt(); // 쿼리 수

        // 인접 리스트 초기화
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 트리의 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int dist = sc.nextInt();
            adjList[u].add(new int[]{v, dist});
            adjList[v].add(new int[]{u, dist});
        }

        // 두 노드 간의 거리를 알고 싶은 쿼리 입력 및 처리
        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            // 거리 계산을 위해 BFS 또는 DFS 사용
            int distance = findDistance(start, end, N);
            System.out.println(distance);
        }
    }

    // 두 노드 간의 거리를 구하는 BFS 메서드
    private static int findDistance(int start, int end, int N) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N + 1];
        distances = new int[N + 1];

        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int dist = current[1];

            if (node == end) {
                return dist;
            }

            for (int[] neighbor : adjList[node]) {
                int nextNode = neighbor[0];
                int nextDist = neighbor[1];

                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    distances[nextNode] = dist + nextDist;
                    queue.offer(new int[]{nextNode, distances[nextNode]});
                }
            }
        }
        return -1; // 예외 처리 (이 경우는 트리에서는 발생하지 않음)
    }
}
