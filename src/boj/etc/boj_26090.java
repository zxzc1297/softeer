package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 다음 두 조건을 모두 만족하는 수열을 완전한 수열이라고 한다.
 *
 * 수열의 길이는 소수이다.
 * 수열의 합은 소수이다.
 * 어떤 수열의 연속 부분 수열 중, 완전한 수열의 개수를 수열의 안정도라고 한다.
 *
 * 정수로 이루어진 수열
 * $a_1,a_2,\cdots,a_N$이 주어진다.
 *
 * 이 수열의 안정도를 구해보자.
 *
 * 입력
 * 첫째 줄에 정수
 * $N$이 주어진다.
 * $(4\leq N \leq 500)$ 
 *
 * 둘째 줄에 정수로 이루어진 수열
 * $a_1,a_2,\cdots,a_N$이 공백으로 구분되어 주어진다.
 * $(0\leq a_i \leq 2\,000)$ 
 *
 * 출력
 * 첫째 줄에 주어진 수열의 안정도를 출력한다.
 *
 * 예제 입력 1
 * 4
 * 2 7 4 1
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 4
 * 0 0 1 0
 * 예제 출력 2
 * 0
 */
public class boj_26090 {
    static final int MAX = 1_000_000;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 소수 판별 배열 초기화
        sieve(MAX);

        // 2. 가능한 연속 부분 수열 탐색
        int count = 0;

        for (int i = 0; i < N; i++) {
            int sum = 0;

            for (int j = i; j < N; j++) {
                sum += a[j];
                int length = j - i + 1;

                if (isPrime[length] && isPrime[sum]) {
                    count++;
                }
            }
        }

        // 출력
        System.out.println(count);
    }

    // 에라토스테네스의 체
    static void sieve(int n) {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) continue;

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
