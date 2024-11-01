package softeer.Q21_Q30;
import java.util.*;

/**
 * 산타는 썰매를 끌어줄 루돌프를 선택하려고 합니다. 루돌프 후보는 넷으로, 축구 월드컵 리그 경기를 통해 상위 2마리를 선발한다고 합니다. 산타는 이미 1번 루돌프를 마음에 들어하고 있기 때문에, 1번 루돌프가 선택될 확률을 구하고 싶습니다.
 *
 *
 *
 *
 *
 *
 *
 * 각 루돌프는 힘 Fi​ 정보가 주어졌을 때, i번 루돌프와 j번 루돌프의 축구 경기 결과는 다음과 같이 예측해볼 수 있습니다.
 *
 * - i번 루돌프가 이길 확률은 5Fi​+5Fj​4Fi​​ 입니다.
 *
 * - j번 루돌프가 이길 확률은 5Fi​+5Fj​4Fj​​ 입니다.
 *
 * - i번 루돌프와 j번 루돌프 경기가 비길 확률은 5Fi​+5Fj​Fi​+Fj​​ 입니다.
 *
 *
 *
 * 리그 경기에서는 모든 루돌프 끼리 정확히 1번씩 경기를 진행한 뒤 승점을 합산하여 등수를 매깁니다. 각 경기마다 승리시 3점, 비겼을 시 1점, 졌을 시 0점을 얻게 됩니다. 최종 점수가 동일한 경우에는 번호가 작은 루돌프가 더 높은 순위를 얻게 된다 했을 때, 1번 루돌프가 선택될 확률을 구하는 프로그램을 작성해보세요.
 *
 *
 *
 *
 *
 * 본 문제의 저작권은 (주)브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금지합니다.
 *
 * 제약조건
 * 1 ≤ Fi​ ≤ 100
 * 입력형식
 * 첫 번째 줄에는 각각 1번, 2번, 3번, 4번 루돌프의 힘을 나타내는 F1​, F2​, F3​, F4​ 값이 공백을 사이에 두고 주어집니다.
 *
 * 출력형식
 * 첫 번째 줄에 1번 루돌프가 상위 2등 안에 들어 선택될 확률(%)을 소수점 셋째자리까지 반올림하여 출력합니다.
 *
 * 입력예제1
 *
 * 72 20 20 20
 *
 * 출력예제1
 *
 * 87.328
 *
 * 첫 번째 예제에서 경기 결과가 다음과 같이 나온 경우를 생각해봅시다.
 *
 *
 *
 *
 *
 *
 *
 * 각 루돌프의 승점은 순서대로 6, 4, 4, 3이기에 1번 루돌프가 1등을 하게 됩니다. 이렇게 경기가 진행될 확률은
 *
 * (1번 루돌프가 2번 루돌프를 이길 확률) * (1번 루돌프가 3번 루돌프에게 질 확률) * (1번 루돌프가 4번 루돌프를 이길 확률) * (2번 루돌프가 3번 루돌프와 비길 확률) * (2번 루돌프가 4번 루돌프를 이길 확률) * (3번 루돌프가 4번 루돌프에게 질 확률) = (4*72)/(5*72+5*20) * (4*20)/(5*72+5*20) * (4*72)/(5*72+5*20) * (20+20)/(5*20+5*20) * (4*20)/(5*20+5*20) * (4*20)/(5*20+5*20) = 0.218%
 *
 * 가 됩니다.
 *
 * 이러한 상황들을 전부 구해주면 1번 루돌프가 상위 2등 안에 들 확률은 87.328%가 나오게 됩니다.
 *
 * 입력예제2
 *
 * 25 60 40 30
 *
 * 출력예제2
 *
 * 43.011
 */
public class Question28 {
    static double[][] winProb, drawProb;
    static int[] points = {3, 1, 0};
    static double totalProbability = 0;
    static double favorableProbability = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] F = new int[4];
        for (int i = 0; i < 4; i++) {
            F[i] = sc.nextInt();
        }
        sc.close();

        // 승리와 무승부 확률 계산
        winProb = new double[4][4];
        drawProb = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                double total = 5.0 * F[i] + 5.0 * F[j];
                winProb[i][j] = 4.0 * F[i] / total;
                winProb[j][i] = 4.0 * F[j] / total;
                drawProb[i][j] = drawProb[j][i] = (F[i] + F[j]) / total;
            }
        }

        // 모든 경기 결과 시뮬레이션
        simulateMatches(0, new int[4], 1.0);

        // 확률 계산 및 출력
        double result = (favorableProbability / totalProbability) * 100;
        System.out.printf("%.3f\n", result);
    }

    // 각 경기마다 결과를 시뮬레이션하는 재귀 함수
    static void simulateMatches(int matchIdx, int[] scores, double prob) {
        if (matchIdx == 6) {  // 6경기가 모두 완료된 경우
            calculateProbability(scores, prob);
            return;
        }

        // i, j 설정
        int[][] matches = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}};
        int i = matches[matchIdx][0];
        int j = matches[matchIdx][1];

        // i번 루돌프 승리
        scores[i] += 3;
        simulateMatches(matchIdx + 1, scores, prob * winProb[i][j]);
        scores[i] -= 3;

        // j번 루돌프 승리
        scores[j] += 3;
        simulateMatches(matchIdx + 1, scores, prob * winProb[j][i]);
        scores[j] -= 3;

        // 무승부
        scores[i] += 1;
        scores[j] += 1;
        simulateMatches(matchIdx + 1, scores, prob * drawProb[i][j]);
        scores[i] -= 1;
        scores[j] -= 1;
    }

    // 상위 2등 안에 1번 루돌프가 있는지 체크
    static void calculateProbability(int[] scores, double prob) {
        totalProbability += prob;

        // 점수로 정렬하여 순위 확인
        int rank = 1;
        for (int i = 1; i < 4; i++) {
            if (scores[i] > scores[0]) rank++;
        }

        if (rank <= 2) {
            favorableProbability += prob;
        }
    }
}
