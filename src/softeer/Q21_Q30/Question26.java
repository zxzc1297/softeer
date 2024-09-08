package softeer.Q21_Q30;
import java.util.*;

/**
 * 남우는 우후죽순으로 자라 있는 나무들을 잘 정리하여 정원을 가꾸려고 합니다. 정원은 n × n 격자 모양으로 이루어져 있으며, 각 칸에는 하나의 나무가 심어져 있습니다. 상하좌우로 맞닿아 있는 경우를 인접한 경우라고 했을 때, 남우는 최대 4번 인접해 있는 두 나무를 묶는 것을 진행하려고 합니다. 이때 묶은 나무끼리는 서로 겹쳐서는 안 되며, 두 나무가 묶였을 때 얻을 수 있는 아름다움은 두 나무의 키의 합과 동일하다고 합니다. 이러한 상황에서 묶인 쌍의 아름다움의 합을 최대로 만들어보려고 합니다.
 *
 *
 *
 * 예를 들어 n = 4 일 때 다음과 같이 정원 정보가 주어진 경우를 생각해보겠습니다. 각 격자 칸에 적혀있는 숫자는 각 칸에 있는 나무들의 키를 의미합니다.

 *
 * 이때 다음과 같이 나무들을 묶게 되면 서로 묶은 쌍끼리 겹치지 않되 얻을 수 있는 아름다움의 합이 25(=7+6+7+5)로 최대가 됩니다.

 *
 * 초기 격자의 상태가 주어졌을 때 최대 4개의 쌍을 겹치지 않게 잘 골라 얻을 수 있는 아름다움의 총 합을 최대로 하는 프로그램을 작성해보세요.
 *
 *
 *
 *
 *
 * 본 문제의 저작권은 (주)브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금지합니다.
 *
 * 제약조건
 * 2 ≤ n ≤ 4
 * 1 ≤ 나무의 키 ≤ 10
 * 입력형식
 * 첫 번째 줄에는 격자의 크기를 나타내는 n이 주어집니다.
 * 두 번째 줄부터는 n개의 줄에 걸쳐 각 행에 해당하는 나무의 키 정보 n개가 공백을 사이에 두고 주어집니다.
 *
 * 출력형식
 * 첫 번째 줄에 최대 4개의 쌍을 서로 겹치지 않게 묶어 얻을 수 있는 최대 아름다움의 합을 출력합니다. 꼭 4쌍을 전부 골라도 되지 않음에 유의합니다.
 *
 * 입력예제1
 * 4
 * 2 1 3 3
 * 5 1 2 1
 * 2 1 2 3
 * 5 1 1 1
 *
 * 출력예제1
 * 25
 *
 * 입력예제2
 * 2
 * 1 2
 * 3 4
 *
 * 출력예제2
 * 10
 *
 * 입력예제3
 * 3
 * 7 4 5
 * 7 3 5
 * 4 6 6
 *
 * 출력예제3
 * 44
 */
public class Question26 {
    static class Pair {
        int x1, y1, x2, y2, beauty;

        public Pair(int x1, int y1, int x2, int y2, int beauty) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.beauty = beauty;
        }
    }
    static int[][] grid;
    static boolean[][] used;  // 이미 묶인 나무인지 여부를 저장하는 배열
    static int n;
    static List<Pair> pairs = new ArrayList<>();

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        used = new boolean[n][n];  // 초기값은 false

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // 가능한 모든 쌍을 찾는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < n) {
                    pairs.add(new Pair(i, j, i + 1, j, grid[i][j] + grid[i + 1][j]));
                }
                if (j + 1 < n) {
                    pairs.add(new Pair(i, j, i, j + 1, grid[i][j] + grid[i][j + 1]));
                }
            }
        }

        // 최대 4개의 쌍을 선택하여 아름다움을 계산
        System.out.println(maxBeauty(0, 0));
    }
    public static int maxBeauty(int index, int count) {
        // 이미 4개의 쌍을 선택했거나, 모든 쌍을 확인한 경우
        if (count == 4 || index == pairs.size()) {
            return 0;
        }

        Pair currentPair = pairs.get(index);
        int maxBeauty = 0;

        // 현재 쌍을 선택할 수 있는 경우
        if (!used[currentPair.x1][currentPair.y1] && !used[currentPair.x2][currentPair.y2]) {
            // 나무를 사용 표시
            used[currentPair.x1][currentPair.y1] = true;
            used[currentPair.x2][currentPair.y2] = true;

            // 현재 쌍을 선택한 경우의 아름다움 계산
            maxBeauty = currentPair.beauty + maxBeauty(index + 1, count + 1);

            // 사용 표시 해제 (백트래킹)
            used[currentPair.x1][currentPair.y1] = false;
            used[currentPair.x2][currentPair.y2] = false;
        }

        // 현재 쌍을 선택하지 않은 경우도 확인
        maxBeauty = Math.max(maxBeauty, maxBeauty(index + 1, count));

        return maxBeauty;
    }
}
