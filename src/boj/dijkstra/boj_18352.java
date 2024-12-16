package boj.dijkstra;
import java.util.*;

/**
 * 문제
 * 어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재한다. 모든 도로의 거리는 1이다.
 *
 * 이 때 특정한 도시 X로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램을 작성하시오. 또한 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정한다.
 *
 * 예를 들어 N=4, K=2, X=1일 때 다음과 같이 그래프가 구성되어 있다고 가정하자.
 *
 *
 *
 * 이 때 1번 도시에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 2인 도시는 4번 도시 뿐이다.  2번과 3번 도시의 경우, 최단 거리가 1이기 때문에 출력하지 않는다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다. (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N) 둘째 줄부터 M개의 줄에 걸쳐서 두 개의 자연수 A, B가 공백을 기준으로 구분되어 주어진다. 이는 A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 의미다. (1 ≤ A, B ≤ N) 단, A와 B는 서로 다른 자연수이다.
 *
 * 출력
 * X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.
 *
 * 이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력한다.
 *
 * 예제 입력 1
 * 4 4 2 1
 * 1 2
 * 1 3
 * 2 3
 * 2 4
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * 4 3 2 1
 * 1 2
 * 1 3
 * 1 4
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * 4 4 1 1
 * 1 2
 * 1 3
 * 2 3
 * 2 4
 * 예제 출력 3
 * 2
 * 3
 */
public class boj_18352 {
    public static class Node implements Comparable<Node> {
        int city, cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 도시의 개수
        int M = scanner.nextInt(); // 도로의 개수
        int K = scanner.nextInt(); // 목표 거리
        int X = scanner.nextInt(); // 출발 도시 번호

        // 그래프 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            graph.get(from).add(new Node(to, 1)); // 거리 1로 저장
        }

        // 다익스트라 알고리즘 실행
        List<Integer> result = dijkstra(graph, N, X, K);

        // 결과 출력
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int city : result) {
                System.out.println(city);
            }
        }
    }

    public static List<Integer> dijkstra(List<List<Node>> graph, int N, int start, int targetDistance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열을 무한대로 초기화

        // 시작점 초기화
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 현재 노드의 거리가 기록된 거리보다 크면 무시
            if (current.cost > dist[current.city]) {
                continue;
            }

            // 인접 노드 확인
            for (Node neighbor : graph.get(current.city)) {
                int newCost = dist[current.city] + neighbor.cost;

                // 더 짧은 경로를 발견한 경우
                if (newCost < dist[neighbor.city]) {
                    dist[neighbor.city] = newCost;
                    pq.offer(new Node(neighbor.city, newCost));
                }
            }
        }

        // 목표 거리 K인 도시 찾기
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == targetDistance) {
                result.add(i);
            }
        }

        return result;
    }
}
