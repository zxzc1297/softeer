package boj;
import java.util.*;

/**
 * 문제
 * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 *
 * 출력
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 *
 * 제한
 * 1 ≤ N ≤ 100,000
 * 1 ≤ M ≤ 100,000
 * 1 ≤ i ≤ j ≤ N
 * 예제 입력 1
 * 5 3
 * 5 4 3 2 1
 * 1 3
 * 2 4
 * 5 5
 * 예제 출력 1
 * 12
 * 9
 * 1
 */
public class boj_11659 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int m = sc.nextInt();

        int[] numbers = new int[length];

        for(int j=0; j<length; j++){
            numbers[j] = sc.nextInt();
        }

        int[] sums = new int[length];

        sums[0] = numbers[0];
        for(int i=1; i<length; i++){
            sums[i] = sums[i-1] + numbers[i];
        }

        for(int k=0; k<m; k++){
            int idx1 = sc.nextInt();
            int idx2 = sc.nextInt();
            int answer = idx1 == 1 ? sums[idx2-1] : sums[idx2-1] - sums[idx1-2];
            System.out.println(answer);
        }
        sc.close();
    }
}
