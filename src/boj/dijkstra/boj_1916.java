package boj.dijkstra;
import java.util.*;

/**
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 *
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 8
 * 1 2 2
 * 1 3 3
 * 1 4 1
 * 1 5 10
 * 2 4 2
 * 3 4 1
 * 3 5 1
 * 4 5 3
 * 1 5
 * 예제 출력 1
 * 4
 */
public class boj_1916 {
    public static class Node implements Comparable<Node> {
        int city, cost; // 도시 번호와 비용을 나타내는 클래스

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost); // 비용을 기준으로 우선순위 정렬
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 도시의 개수
        int M = scanner.nextInt(); // 버스의 개수

        // 그래프 초기화 (인접 리스트 형태로 저장)
        List<Node>[] graph = new ArrayList[N + 1]; // 1번부터 N번까지 도시를 저장하기 위해 크기를 N+1로 설정
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt(); // 출발 도시
            int to = scanner.nextInt(); // 도착 도시
            int cost = scanner.nextInt(); // 버스 비용
            graph[from].add(new Node(to, cost)); // 출발 도시에서 도착 도시로 가는 간선과 비용 추가
        }

        int start = scanner.nextInt(); // 출발점 (최소 비용 경로의 시작 도시)
        int end = scanner.nextInt(); // 도착점 (최소 비용 경로의 목적 도시)

        // 다익스트라 알고리즘 실행 및 결과 출력
        System.out.println(dijkstra(graph, N, start, end));
    }

    public static int dijkstra(List<Node>[] graph, int N, int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐를 사용하여 최소 비용 정점 탐색
        int[] dist = new int[N + 1]; // 각 도시까지의 최소 비용 저장 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // 초기값을 무한대로 설정

        // 시작점 초기화
        dist[start] = 0; // 시작점까지의 비용은 0
        pq.offer(new Node(start, 0)); // 시작점을 큐에 추가

        while (!pq.isEmpty()) {
            Node current = pq.poll(); // 큐에서 비용이 가장 작은 노드를 꺼냄

            // 이미 처리된 노드라면 무시
            if (current.cost > dist[current.city]) {
                continue;
            }

            // 현재 도시와 연결된 모든 인접 도시를 확인
            for (Node neighbor : graph[current.city]) {
                int newCost = dist[current.city] + neighbor.cost; // 현재 도시를 경유한 비용 계산
                if (newCost < dist[neighbor.city]) { // 기존 비용보다 작다면 갱신
                    dist[neighbor.city] = newCost; // 최소 비용 갱신
                    pq.offer(new Node(neighbor.city, newCost)); // 갱신된 도시를 큐에 추가
                }
            }
        }

        return dist[end]; // 도착점까지의 최소 비용 반환
    }
}
