package boj.dfs;
import java.util.*;

/**
 * 문제
 * 눈송이들이 많은 동네인 숙명여대 앞마당에서 눈사람 만들기 대회를 연다. 앞마당의 길이는
 * $N$이고 위치
 * $1$부터 위치
 * $N$ 까지만 눈이 쌓여있다. 위치
 * $i$에 눈이
 * $a_i$만큼 쌓여있다. 대회 규칙은 해당 앞마당에서
 * $M$초 동안 눈덩이를 굴려 눈사람을 만드는 것이다. 눈덩이의 시작 크기는
 * $1$이다. 눈덩이의 시작 위치는
 * $0$이다.
 *
 * 가장 큰 눈사람을 만들고 싶던 수수는 눈덩이를 굴리는 법을 연구했다. 눈덩이를 굴리는 방법에는 두 가지가 있다. 눈덩이를 굴리거나 던질 때 1초가 소모된다.
 *
 * 눈덩이를 현재 위치 +1칸으로 굴린다. 현재 칸의 위치를
 * $i$라고 하면 눈덩이의 크기는
 * $a_{i+1}$ 만큼 늘어난다.
 * 눈덩이를 현재 위치 +2칸으로 던진다. 눈덩이가 착지하며 충격을 받아 눈덩이의 크기는 원래의 크기의 반으로 줄어들고  현재 칸의 위치를
 * $i$라고 하면 눈덩이의 크기는
 * $a_{i+2}$ 만큼 늘어난다. 이 때 소수점은 절사한다. 눈덩이를 던져 크기가
 * $0$이 되어도 눈덩이는 사라지지 않는다.
 * 눈덩이가 앞마당의 끝에 도달한 경우 남은 시간과 관계없이 눈덩이 굴리기는 끝이 난다. 대회 시간 내에 가장 크게 만들 수 있는 눈덩이의 크기를 구하는 프로그램을 작성해보자.
 *
 * 입력
 * 첫째 줄에 공백을 기준으로 앞마당의 길이
 * $N$ (
 * $1 \leq N \leq 100$), 대회의 시간
 * $M$ (
 * $1 \leq M \leq 10$)이 주어진다.
 *
 * 둘째 줄에 길이가
 * $N$인 수열
 * $a$가 주어진다. (
 * $1 \leq a_i \leq 1\,000\,000$)
 *
 * 출력
 * 첫째 줄에 대회 시간 내에 가장 크게 만들 수 있는 눈덩이의 크기를 출력한다.
 *
 * 예제 입력 1
 * 10 5
 * 1 3 4 5 6 7 8 10 12 14
 * 예제 출력 1
 * 28
 */
public class boj_21735 {
    static int N, M;
    static int[] snow;
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 앞마당 길이
        M = sc.nextInt(); // 제한 시간
        snow = new int[N + 1]; // 위치 1부터 시작

        for (int i = 1; i <= N; i++) {
            snow[i] = sc.nextInt();
        }

        // dp[time][position][size] 저장 (크게 만들 수 있는 눈덩이 크기)
        // 시간: 0~M, 위치: 0~N, 크기: 제한 없음 (여기선 메모이제이션 없이 DFS만 사용)
        System.out.println(dfs(0, 0, 1)); // 시간 0, 위치 0, 크기 1 시작
    }

    static int dfs(int time, int pos, int size) {
        if (pos >= N || time == M) {
            return size;
        }

        int max = 0;

        // 1. 굴리기 (1초 소비, 위치 +1)
        if (pos + 1 <= N) {
            int newSize = size + snow[pos + 1];
            max = Math.max(max, dfs(time + 1, pos + 1, newSize));
        }

        // 2. 던지기 (1초 소비, 위치 +2, 사이즈 절반)
        if (pos + 2 <= N) {
            int newSize = (size / 2) + snow[pos + 2];
            max = Math.max(max, dfs(time + 1, pos + 2, newSize));
        }

        return max;
    }
}
