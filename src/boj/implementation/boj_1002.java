package boj.implementation;
import java.util.*;

/**
 * 문제
 * 조규현과 백승환은 터렛에 근무하는 직원이다. 하지만 워낙 존재감이 없어서 인구수는 차지하지 않는다. 다음은 조규현과 백승환의 사진이다.
 *
 *
 *
 * 이석원은 조규현과 백승환에게 상대편 마린(류재명)의 위치를 계산하라는 명령을 내렸다. 조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.
 *
 * 조규현의 좌표
 * $(x_1, y_1)$와 백승환의 좌표
 * $(x_2, y_2)$가 주어지고, 조규현이 계산한 류재명과의 거리
 * $r_1$과 백승환이 계산한 류재명과의 거리
 * $r_2$가 주어졌을 때, 류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수
 * $T$가 주어진다. 각 테스트 케이스는 다음과 같이 이루어져 있다.
 *
 * 한 줄에 공백으로 구분 된 여섯 정수
 * $x_1$,
 * $y_1$,
 * $r_1$,
 * $x_2$,
 * $y_2$,
 * $r_2$가 주어진다.
 *
 * 출력
 * 각 테스트 케이스마다 류재명이 있을 수 있는 위치의 수를 출력한다. 만약 류재명이 있을 수 있는 위치의 개수가 무한대일 경우에는
 * $-1$ 출력한다.
 *
 * 제한
 *
 * $-10\,000 ≤ x_1, y_1, x_2, y_2 ≤ 10\,000$ 
 *
 * $1 ≤ r_1, r_2 ≤ 10\,000$ 
 * 예제 입력 1
 * 3
 * 0 0 13 40 0 37
 * 0 0 3 0 7 4
 * 1 1 1 1 1 5
 * 예제 출력 1
 * 2
 * 1
 * 0
 */
public class boj_1002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int r1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int r2 = sc.nextInt();

            // 두 점 사이 거리의 제곱
            int dx = x2 - x1;
            int dy = y2 - y1;
            int distSq = dx * dx + dy * dy;

            int sumR = r1 + r2;
            int diffR = Math.abs(r1 - r2);

            int sumRSq = sumR * sumR;
            int diffRSq = diffR * diffR;

            if (distSq == 0 && r1 == r2) {
                // 두 원이 완전히 겹침
                System.out.println(-1);
            } else if (distSq > sumRSq) {
                // 두 원이 떨어져 있음
                System.out.println(0);
            } else if (distSq < diffRSq) {
                // 한 원이 다른 원 안에 있음
                System.out.println(0);
            } else if (distSq == sumRSq || distSq == diffRSq) {
                // 외접 또는 내접
                System.out.println(1);
            } else {
                // 두 점에서 만남
                System.out.println(2);
            }
        }

        sc.close();
    }
}
