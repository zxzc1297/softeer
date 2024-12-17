package boj.dijkstra;
import java.util.*;

/**
 * 문제
 * 매일 아침, 세준이는 학교에 가기 위해서 차를 타고 D킬로미터 길이의 고속도로를 지난다. 이 고속도로는 심각하게 커브가 많아서 정말 운전하기도 힘들다. 어느 날, 세준이는 이 고속도로에 지름길이 존재한다는 것을 알게 되었다. 모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다.
 *
 * 세준이가 운전해야 하는 거리의 최솟값을 출력하시오.
 *
 * 입력
 * 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다. 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다. 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.
 *
 * 출력
 * 세준이가 운전해야하는 거리의 최솟값을 출력하시오.
 *
 * 예제 입력 1
 * 5 150
 * 0 50 10
 * 0 50 20
 * 50 100 10
 * 100 151 10
 * 110 140 90
 * 예제 출력 1
 * 70
 * 예제 입력 2
 * 2 100
 * 10 60 40
 * 50 90 20
 * 예제 출력 2
 * 80
 * 예제 입력 3
 * 8 900
 * 0 10 9
 * 20 60 45
 * 80 190 100
 * 50 70 15
 * 160 180 14
 * 140 160 14
 * 420 901 5
 * 450 900 0
 * 예제 출력 3
 * 432
 */
public class boj_1446 {
    // 지름길 정보를 저장하기 위한 클래스
    public static class Shortcut {
        int start, end, length;

        public Shortcut(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 지름길의 개수
        int D = scanner.nextInt(); // 고속도로의 길이

        // 지름길 정보를 저장할 리스트
        List<Shortcut> shortcuts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int length = scanner.nextInt();
            if (end <= D && end > start) { // 유효한 지름길만 추가
                shortcuts.add(new Shortcut(start, end, length));
            }
        }

        // 다익스트라 알고리즘 실행 및 결과 출력
        System.out.println(dijkstra(D, shortcuts));
    }

    public static int dijkstra(int D, List<Shortcut> shortcuts) {
        // 최단 거리를 저장하는 배열
        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 초기값 무한대로 설정

        dist[0] = 0; // 시작점의 거리는 0

        for (int i = 0; i <= D; i++) {
            // 직선 거리를 업데이트
            if (i > 0) {
                dist[i] = Math.min(dist[i], dist[i - 1] + 1);
            }

            // 현재 위치에서 출발하는 지름길 처리
            for (Shortcut shortcut : shortcuts) {
                if (shortcut.start == i) {
                    dist[shortcut.end] = Math.min(dist[shortcut.end], dist[i] + shortcut.length);
                }
            }
        }

        // 목적지 D까지의 최단 거리 반환
        return dist[D];
    }
}
