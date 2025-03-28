package boj.backTracking;
import java.util.*;

/**
 * 문제
 * 서준이는 아빠로부터 N개의 회의와 하나의 회의실을 선물로 받았다. 각 회의는 시작 시간, 끝나는 시간, 회의 인원이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다. 단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작 시간은 끝나는 시간보다 항상 작다. N개의 회의를 회의실에 효율적으로 배정할 경우 회의를 진행할 수 있는 최대 인원을 구하자.
 *
 * 입력
 * 첫째 줄에 회의의 수 N이 주어진다. 둘째 줄부터 N + 1 줄까지 공백을 사이에 두고 회의의 시작시간, 끝나는 시간, 회의 인원이 주어진다.
 *
 * 출력
 * 첫째 줄에 회의실에서 회의를 진행할 수 있는 최대 인원을 출력한다.
 *
 * 제한
 * 1 ≤ N ≤ 25
 * 임의의 회의 K(1≤ K ≤ N)는 회의 K − 1과 회의 K + 1과는 회의 시간이 겹치고 다른 회의들과는 회의 시간이 겹치지 않는다.
 * 모든 회의의 시작 시간과 끝나는 시간은 231 − 1보다 작거나 같은 자연수 또는 0이다.
 * 모든 회의의 시작 시간과 끝나는 시간은 서로 다르다.
 * 회의 인원은 1,000보다 작거나 같은 자연수 이다.
 * 예제 입력 1
 * 6
 * 10 40 80
 * 30 60 60
 * 50 80 70
 * 70 100 100
 * 90 120 40
 * 110 140 50
 * 예제 출력 1
 * 230
 * 1번, 4번, 6번 회의를 진행하는 게 최적이다.
 */
public class boj_19621 {
    static int N;
    static int[][] meetings;
    static int maxPeople = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        meetings = new int[N][3]; // [시작 시간, 끝나는 시간, 인원]

        for (int i = 0; i < N; i++) {
            meetings[i][0] = sc.nextInt(); // 시작 시간
            meetings[i][1] = sc.nextInt(); // 종료 시간
            meetings[i][2] = sc.nextInt(); // 인원 수
        }

        // 종료 시간을 기준으로 정렬 (종료 시간이 같다면 시작 시간이 작은 순서로)
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        // 백트래킹 시작
        backtrack(0, 0, 0);

        System.out.println(maxPeople);
        sc.close();
    }

    // 백트래킹 함수 (현재 회의 index, 현재까지의 최대 인원, 마지막으로 선택한 회의의 종료 시간)
    static void backtrack(int index, int people, int lastEndTime) {
        if (index == N) {
            maxPeople = Math.max(maxPeople, people);
            return;
        }

        // 현재 회의를 선택하는 경우
        if (meetings[index][0] >= lastEndTime) {
            backtrack(index + 1, people + meetings[index][2], meetings[index][1]);
        }

        // 현재 회의를 선택하지 않는 경우
        backtrack(index + 1, people, lastEndTime);
    }
}
