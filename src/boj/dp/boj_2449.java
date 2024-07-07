package boj.dp;
import java.util.*;

/**
 * 문제
 * 최대 K가지의 서로 다른 색을 표현할 수 있는 전구들이 있다. 이 전구 N개를 다음의 그림과 같이 한 줄로 배치하여 서로 연결한다. (동그라미 안의 숫자는 전구의 색을 의미한다)
 *
 *
 *
 * 각 전구는 스위치가 있어서 전구의 색을 임의의 색으로 바꿀 수 있다. 하나의 전구 색을 바꾸는 경우에는, 색이 바뀌는 전구에 인접한 전구가 같은 색이면, 이 전구의 색도 같이 바뀌게 되며 인접한 전구가 다른 색이 나올 때까지 계속 바뀌게 된다. 예를 들어, 위의 그림에서 4번 전구의 색을 2번 색으로 바꾸면, 5번 전구가 4번 전구와 같은 색이었으므로 2번 색으로 바뀌고, 6번 전구도 5번 전구와 같은 색이었으므로 2번 색으로 바뀌게 된다. 즉, 4번 전구의 색을 2번 색으로 바꾸면, 연결된 같은 색의 모든 전구인 4, 5, 6번의 전구가 2번 색으로 바뀌게 되어 아래의 그림과 같이 된다.
 *
 *
 *
 * 전구의 수 N과 N개의 전등에 대한 초기 색이 주어질 때, 모든 전구의 색이 하나로 같아질 때까지 최소 몇 번 전구의 색을 바꾸어야 하는지를 구하는 프로그램을 작성하시오. 단, 전구의 각 색은 1부터 K까지의 정수로 나타낸다.
 *
 * 입력
 * 입력의 첫 번째 줄에는 전구의 수를 나타내는 양의 정수 N과 전구가 표현할 수 있는 색의 수 K가 주어진다. 단, N은 1이상 200이하의 정수이며, K는 1이상 20이하의 정수이다. 두 번째 줄에는 N개 전구의 색이 전구번호의 순서대로 하나의 정수로 하나의 빈칸을 사이에 두고 주어진다.
 *
 * 출력
 * 첫째 줄에 모든 전구의 색이 하나로 같아질 때까지 전구의 색을 바꾸는 횟수의 최솟값을 하나의 정수로 출력한다.
 *
 * 예제 입력 1
 * 10 3
 * 1 1 2 3 3 3 2 2 1 1
 * 예제 출력 1
 * 2
 */
public class boj_2449 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] a = new int[N+1];
        int[][] dp = new int[N+1][N+1];

        //1. a배열 인접한 수가 같은 수 배제
        for(int i=1; i<=N; i++){
            a[i] = sc.nextInt();
            if(a[i] == a[i-1]){
                N--;
                i--;
            }
        }

        //2. dp 배열 큰 수로 초기화, dp의 i,i 0 으로 초기화 => 한 가지의 색을 통일하는 경우는 0

        for(int j=1; j<=N; j++){
            for(int l=1; l<=N; l++)
                dp[j][l] = 1000;
            dp[j][j] = 0;
        }


        //3. 하나씩 순환하면서 최솟값 산출
        for(int size=2; size<=N; size++){
            for(int start=1; start<=N-size+1; start++){
                int end = start + size - 1;
                for(int mid=start; mid<end; mid++){
                    int newVal = dp[start][mid] + dp[mid+1][end];
                    newVal = a[start] == a[mid+1] ? newVal : newVal + 1;
                    dp[start][end] = Math.min(newVal, dp[start][end]);
                }
            }
        }

        System.out.println(dp[1][N]);
        sc.close();
    }
}
