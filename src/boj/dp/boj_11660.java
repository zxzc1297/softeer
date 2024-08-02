package boj.dp;
import java.util.*;
import java.io.*;

/**
 * 문제
 * N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다.
 *
 * 예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.
 *
 * 1	2	3	4
 * 2	3	4	5
 * 3	4	5	6
 * 4	5	6	7
 * 여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.
 *
 * 표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)
 *
 * 출력
 * 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
 *
 * 예제 입력 1
 * 4 3
 * 1 2 3 4
 * 2 3 4 5
 * 3 4 5 6
 * 4 5 6 7
 * 2 2 3 4
 * 3 4 3 4
 * 1 1 4 4
 * 예제 출력 1
 * 27
 * 6
 * 64
 * 예제 입력 2
 * 2 4
 * 1 2
 * 3 4
 * 1 1 1 1
 * 1 2 1 2
 * 2 1 2 1
 * 2 2 2 2
 * 예제 출력 2
 * 1
 * 2
 * 3
 * 4
 */
public class boj_11660 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        int[][] nums = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            String[] temp = br.readLine().split(" ");
            for(int j=1; j<=n; j++){
                nums[i][j] = nums[i-1][j] + nums[i][j-1] - nums[i-1][j-1] + Integer.parseInt(temp[j-1]);
            }
        }

        for(int i=0; i<m; i++){
            String[] temp = br.readLine().split(" ");
            int x1 = Integer.parseInt(temp[0]);
            int y1 = Integer.parseInt(temp[1]);
            int x2 = Integer.parseInt(temp[2]);
            int y2 = Integer.parseInt(temp[3]);
            int sum = nums[x2][y2] - nums[x2][y1-1] - nums[x1-1][y2] + nums[x1-1][y1-1];
            System.out.println(sum);
        }
    }
}
