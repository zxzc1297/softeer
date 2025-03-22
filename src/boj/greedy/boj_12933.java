package boj.greedy;
import java.util.*;

/**
 * 문제
 * 오리의 울음 소리는 "quack"이다. 올바른 오리의 울음 소리는 울음 소리를 한 번 또는 그 이상 연속해서 내는 것이다. 예를 들어, "quack", "quackquackquackquack", "quackquack"는 올바른 오리의 울음 소리이다.
 *
 * 영선이의 방에는 오리가 있는데, 문제를 너무 열심히 풀다가 몇 마리의 오리가 있는지 까먹었다.
 *
 * 갑자기 영선이의 방에 있는 오리가 울기 시작했고, 이 울음소리는 섞이기 시작했다. 영선이는 일단 울음소리를 녹음했고, 나중에 들어보면서 총 몇 마리의 오리가 있는지 구해보려고 한다.
 *
 * 녹음한 소리는 문자열로 나타낼 수 있는데, 한 문자는 한 오리가 낸 소리이다. 오리의 울음 소리는 연속될 필요는 없지만, 순서는 "quack"이어야 한다. "quqacukqauackck"과 같은 경우는 두 오리가 울었다고 볼 수 있다.
 *
 * 영선이가 녹음한 소리가 주어졌을 때, 영선이 방에 있을 수 있는 오리의 최소 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 영선이가 녹음한 소리가 주어진다. 소리의 길이는 5보다 크거나 같고, 2500보다 작거나 같은 자연수이고, 'q','u','a','c','k'로만 이루어져 있다.
 *
 * 출력
 * 영선이 방에 있을 수 있는 오리의 최소 수를 구하는 프로그램을 작성하시오. 녹음한 소리가 올바르지 않은 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * quqacukqauackck
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * kcauq
 * 예제 출력 2
 * -1
 * 예제 입력 3
 * quackquackquackquackquackquackquackquackquackquack
 * 예제 출력 3
 * 1
 */
public class boj_12933 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sound = sc.next();
        sc.close();

        // "quack"의 순서
        char[] quack = {'q', 'u', 'a', 'c', 'k'};
        int[] count = new int[5]; // "quack" 각 문자의 개수 저장

        int ducks = 0; // 동시에 울고 있는 오리의 개수
        int activeDucks = 0; // 현재 진행 중인 오리 수

        for (char c : sound.toCharArray()) {
            int index = "quack".indexOf(c);
            if (index == -1) { // 잘못된 문자 포함 시
                System.out.println(-1);
                return;
            }

            if (index == 0) { // 'q'는 새로운 오리 시작
                count[index]++;
                activeDucks++;
                ducks = Math.max(ducks, activeDucks); // 최대 오리 개수 갱신
            } else {
                if (count[index - 1] == 0) { // 올바른 순서가 아님
                    System.out.println(-1);
                    return;
                }
                count[index - 1]--; // 이전 단계 문자 감소
                count[index]++; // 현재 문자 증가

                if (index == 4) { // 'k'까지 왔으면 오리 한 마리 울음 종료
                    count[index]--;
                    activeDucks--;
                }
            }
        }

        // "quack"이 완전히 짝을 맞추지 못한 경우 예외 처리
        if (activeDucks > 0 || count[0] > 0) {
            System.out.println(-1);
        } else {
            System.out.println(ducks);
        }
    }
}
