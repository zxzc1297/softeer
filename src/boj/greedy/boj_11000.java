package boj.greedy;
import java.util.*;

/**
 *문제
 * 수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.
 *
 * 김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
 *
 * 참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
 *
 * 수강신청 대충한 게 찔리면, 선생님을 도와드리자!
 *
 * 입력
 * 첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)
 *
 * 이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)
 *
 * 출력
 * 강의실의 개수를 출력하라.
 *
 * 예제 입력 1
 * 3
 * 1 3
 * 2 4
 * 3 5
 * 예제 출력 1
 * 2
 */
public class boj_11000 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 강의 목록을 저장할 리스트
        int[][] classes = new int[n][2];

        // 강의 목록 받기
        for (int i = 0; i < n; i++) {
            classes[i][0] = sc.nextInt(); // 시작 시간
            classes[i][1] = sc.nextInt(); // 종료 시간
        }

        // 시작 시간을 기준으로 정렬 (같은 시작 시간일 경우 종료 시간 기준으로 정렬)
        Arrays.sort(classes, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        // 강의실의 종료 시간을 관리할 우선순위 큐
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        // 각 강의를 우선순위 큐에 추가
        for (int i = 0; i < n; i++) {
            int start = classes[i][0];
            int end = classes[i][1];

            // 현재 수업의 시작 시간보다 이전에 끝나는 강의실이 있다면 재사용
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
                roomEndTimes.poll(); // 종료된 강의실 제거
            }

            // 현재 수업을 위한 강의실 추가
            roomEndTimes.offer(end);
        }

        // 사용된 강의실의 수는 우선순위 큐의 크기
        System.out.println(roomEndTimes.size());
        sc.close();
    }
}
