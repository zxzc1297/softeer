package boj.etc;
import java.util.*;

/**
 * 문제
 * 지민이는 세계에서 가장 유명한 사람이 누구인지 궁금해졌다. 가장 유명한 사람을 구하는 방법은 각 사람의 2-친구를 구하면 된다. 어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해선, 두 사람이 친구이거나, A와 친구이고, B와 친구인 C가 존재해야 된다. 여기서 가장 유명한 사람은 2-친구의 수가 가장 많은 사람이다. 가장 유명한 사람의 2-친구의 수를 출력하는 프로그램을 작성하시오.
 *
 * A와 B가 친구면, B와 A도 친구이고, A와 A는 친구가 아니다.
 *
 * 입력
 * 첫째 줄에 사람의 수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 각 사람이 친구이면 Y, 아니면 N이 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 유명한 사람의 2-친구의 수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * NYY
 * YNY
 * YYN
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 3
 * NNN
 * NNN
 * NNN
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 5
 * NYNNN
 * YNYNN
 * NYNYN
 * NNYNY
 * NNNYN
 * 예제 출력 3
 * 4
 * 예제 입력 4
 * 10
 * NNNNYNNNNN
 * NNNNYNYYNN
 * NNNYYYNNNN
 * NNYNNNNNNN
 * YYYNNNNNNY
 * NNYNNNNNYN
 * NYNNNNNYNN
 * NYNNNNYNNN
 * NNNNNYNNNN
 * NNNNYNNNNN
 * 예제 출력 4
 * 8
 */
public class boj_1058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 사람 수
        sc.nextLine(); // 개행 처리

        char[][] friendMap = new char[n][n];
        for (int i = 0; i < n; i++) {
            friendMap[i] = sc.nextLine().toCharArray();
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            boolean[] isFriend = new boolean[n];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // 1단계 친구
                if (friendMap[i][j] == 'Y') {
                    isFriend[j] = true;
                } else {
                    // 2단계 친구: i - k - j
                    for (int k = 0; k < n; k++) {
                        if (friendMap[i][k] == 'Y' && friendMap[k][j] == 'Y') {
                            isFriend[j] = true;
                            break;
                        }
                    }
                }
            }

            int count = 0;
            for (boolean b : isFriend) {
                if (b) count++;
            }
            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}
