package boj.etc;
import java.util.*;

/**
 * 문제
 * 찬솔이는 블로그를 시작한 지 벌써
 * $N$일이 지났다.
 *
 * 요즘 바빠서 관리를 못 했다가 방문 기록을 봤더니 벌써 누적 방문 수가 6만을 넘었다.
 *
 *
 *
 * 찬솔이는
 * $X$일 동안 가장 많이 들어온 방문자 수와 그 기간들을 알고 싶다.
 *
 * 찬솔이를 대신해서
 * $X$일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 구해주자.
 *
 * 입력
 * 첫째 줄에 블로그를 시작하고 지난 일수
 * $N$와
 * $X$가 공백으로 구분되어 주어진다.
 *
 * 둘째 줄에는 블로그 시작
 * $1$일차부터
 * $N$일차까지 하루 방문자 수가 공백으로 구분되어 주어진다.
 *
 * 출력
 * 첫째 줄에
 * $X$일 동안 가장 많이 들어온 방문자 수를 출력한다. 만약 최대 방문자 수가 0명이라면 SAD를 출력한다.
 *
 * 만약 최대 방문자 수가 0명이 아닌 경우 둘째 줄에 기간이 몇 개 있는지 출력한다.
 */
public class boj_21921 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 전체 일수
        int X = sc.nextInt(); // 기간

        int[] visitors = new int[N];
        for (int i = 0; i < N; i++) {
            visitors[i] = sc.nextInt();
        }

        long windowSum = 0;
        for (int i = 0; i < X; i++) {
            windowSum += visitors[i];
        }

        long maxSum = windowSum;
        int count = 1;

        for (int i = X; i < N; i++) {
            windowSum = windowSum - visitors[i - X] + visitors[i];
            if (windowSum > maxSum) {
                maxSum = windowSum;
                count = 1;
            } else if (windowSum == maxSum) {
                count++;
            }
        }

        if (maxSum == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxSum);
            System.out.println(count);
        }
    }
}
