package boj.etc;
import java.util.*;

/**
 * 문제
 * 오늘은 많은 학생이 기다리던 브실브실 학교의 축제 행사 날이다.
 *
 * 브실이는 학교 축제 담당자로서 축제의 분위기가 잘 살아날 수 있도록 무대 앞에
 * $N$행
 * $M$열로 의자를 하나씩 배치해 두고 각 의자에 응원단 사람을
 * $1$명씩 뒀다.
 *
 * 브실이는 응원단의 사기를 확인하기 위해 연속된
 * $A$개 열 중 가장 박수 횟수가 많은 곳을 찾고자 한다. 즉,
 * $1$열부터
 * $A$열,
 * $2$열부터
 * $A+1$열,
 * $3$열부터
 * $A+2$열,
 * $\cdots$,
 * $M-A+1$열부터
 * $M$열에 대해 각 구간에 속하는 응원단 사람들에게 박수를 치게 해서 그중 가장 많이 박수를 친 구간의 박수 횟수를 찾고자 한다.
 *
 * 하지만 브실이는 지금 학교 축제 담당자로서 할 일이 많아 이 일은 우리에게 맡기고 다른 일을 하러 갔다.
 *
 * 각 응원단 사람이 박수를 치는 횟수가 주어졌을 때 가장 많이 박수를 친 구간의 박수 횟수가 몇 회인지 알아보자.
 *
 * 입력
 * 첫 번째 줄에 정수
 * $N$과
 * $M$이 공백으로 구분되어 주어진다.
 * $(1 \le N,M \le 2\,000)$ 
 *
 * 두 번째 줄부터
 * $N$개의 줄에 걸쳐 박수 횟수에 대한 정보가 주어진다.
 *
 *
 * $i+1$번째 줄에는
 * $i$행
 * $1$열부터
 * $i$행
 * $M$열까지 그 자리에 있는 응원단 사람의 박수 횟수를 나타내는 정수
 * $Q_{i,1}, Q_{i,2}, \cdots, Q_{i,M}$이 공백으로 구분되어 주어진다.
 * $(1 \le Q_{i,j} \le 500)$ 
 *
 *
 * $N+2$번째 줄에는 브실이가 정한 열의 개수
 * $A$가 주어진다.
 * $(1 \le A \le M)$ 
 *
 * 출력
 * 가장 많이 박수를 친 구간의 박수 횟수를 출력한다.
 *
 * 예제 입력 1
 * 3 4
 * 1 5 2 6
 * 2 3 1 5
 * 1 2 1 1
 * 2
 * 예제 출력 1
 * 16
 * 예제 입력 2
 * 1 5
 * 1 3 2 5 4
 * 3
 * 예제 출력 2
 * 11
 * 예제 입력 3
 * 5 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 1
 * 예제 출력 3
 * 20
 */
public class boj_29718 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열

        int[][] applause = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                applause[i][j] = sc.nextInt();
            }
        }

        int A = sc.nextInt(); // 연속된 열 개수

        // 열 별로 전체 박수 합을 미리 계산해서 1차원 배열로 만듦
        int[] colSum = new int[M];
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                colSum[j] += applause[i][j];
            }
        }

        // 슬라이딩 윈도우로 A개 열의 최대 합 찾기
        int maxSum = 0;
        int windowSum = 0;

        // 초기 구간 합
        for (int i = 0; i < A; i++) {
            windowSum += colSum[i];
        }
        maxSum = windowSum;

        // 나머지 구간 슬라이딩
        for (int i = A; i < M; i++) {
            windowSum = windowSum - colSum[i - A] + colSum[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        System.out.println(maxSum);
    }
}
