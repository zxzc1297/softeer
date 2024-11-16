package boj.dp;
import java.util.*;

/**
 * 문제
 * N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.
 *
 * 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 이 제약 조건을 그림으로 나타내어 보면 다음과 같다.
 *
 *
 *
 * 별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
 *
 * 출력
 * 첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
 *
 * 예제 입력 1
 * 3
 * 1 2 3
 * 4 5 6
 * 4 9 0
 * 예제 출력 1
 * 18 6
 * 예제 입력 2
 * 3
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 예제 출력 2
 * 0 0
 */
public class boj_2096 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 줄의 개수

        // 현재 줄의 최대, 최소 값 저장
        int[] maxPrev = new int[3];
        int[] minPrev = new int[3];

        // 첫 번째 줄 입력 처리
        for (int i = 0; i < 3; i++) {
            maxPrev[i] = sc.nextInt();
            minPrev[i] = maxPrev[i];
        }

        for (int i = 1; i < N; i++) {
            int[] current = new int[3];
            for (int j = 0; j < 3; j++) {
                current[j] = sc.nextInt();
            }

            int[] maxCurrent = new int[3];
            int[] minCurrent = new int[3];

            // 최대 점수 계산
            maxCurrent[0] = current[0] + Math.max(maxPrev[0], maxPrev[1]);
            maxCurrent[1] = current[1] + Math.max(maxPrev[0], Math.max(maxPrev[1], maxPrev[2]));
            maxCurrent[2] = current[2] + Math.max(maxPrev[1], maxPrev[2]);

            // 최소 점수 계산
            minCurrent[0] = current[0] + Math.min(minPrev[0], minPrev[1]);
            minCurrent[1] = current[1] + Math.min(minPrev[0], Math.min(minPrev[1], minPrev[2]));
            minCurrent[2] = current[2] + Math.min(minPrev[1], minPrev[2]);

            // 갱신
            maxPrev = maxCurrent;
            minPrev = minCurrent;
        }

        // 최대, 최소 값 출력
        int maxResult = Math.max(maxPrev[0], Math.max(maxPrev[1], maxPrev[2]));
        int minResult = Math.min(minPrev[0], Math.min(minPrev[1], minPrev[2]));

        System.out.println(maxResult + " " + minResult);
    }
}
