package boj.twoPointer;
import java.util.*;
public class boj_2003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;
        int start = 0, end = 0;
        long sum = 0;  // 합이 M보다 커질 수 있으므로 long

        while (true) {
            if (sum >= M) {
                if (sum == M) count++;
                sum -= arr[start++];
            } else {
                if (end == N) break;
                sum += arr[end++];
            }
        }

        System.out.println(count);
    }
}
