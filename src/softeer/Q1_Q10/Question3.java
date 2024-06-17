package softeer.Q1_Q10;

import java.util.Scanner;

/**
 * Southern Fuegian Railway는 세상에서 가장 남쪽에 있는 철도이다.
 *
 *
 *
 * Southern Fuegian Railway는 x축의 양의 방향을 동쪽으로 하는 2차원 좌표평면으로 나타내어진다.
 *
 *
 *
 * Southern Fuegian Railway는 N개의 역과 역 사이를 잇는 N−1개의 철로로 구성되어 있다. i번째 역은 (xi,yi)에 있으며, j번째 철로는 j번 역과 j+1번 역 사이를 잇는 선분이다. (1 ≤ i ≤ N; 1 ≤ j ≤ N−1) 
 *
 *
 *
 * Southern Fuegian Railway를 보러 간 선아는 세상에서 가장 남쪽에 있는 철도가 지나는 가장 남쪽 점이 어디일지 궁금해졌다.
 *
 * 제약조건
 * 1 ≤ N ≤ 1000
 *
 * |xi|, |yi| ≤ 1000
 *
 * 입력형식
 * 첫 번째 줄에 역의 개수 N이 주어진다.
 *
 * 두 번째 줄부터 N개의 줄에 걸쳐서, 역의 좌표를 의미하는 두 정수 xi,yi가 공백으로 구분되어 주어진다.
 *
 * 두 역이 같은 위치에 있는 경우는 주어지지 않는다.
 *
 * 출력형식
 * 첫 번째 줄에 Southern Fuegian Railway가 지나는 가장 남쪽에 있는 점의 x좌표와 y좌표를 공백으로 구분하여 출력한다. 이 점이 유일한 경우만 입력으로 주어진다.
 *
 * 입력예제1
 * 4
 * 10 5
 * 6 -3
 * 3 2
 * 4 2
 * 출력예제1
 * 6 -3
 * 입력예제2
 * 1
 * 1000 -1000
 * 출력예제2
 * 1000 -1000
 */

public class Question3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int caseAmount = sc.nextInt();

        int x = 1001;
        int y = 1001;

        while(sc.hasNextInt()){
            if(x == 1001 && y == 1001){
                x = sc.nextInt();
                y = sc.nextInt();
            } else {
                int nextX = sc.nextInt();
                int nextY = sc.nextInt();

                if(nextY < y){
                    x = nextX;
                    y = nextY;
                }
            }
        }

        System.out.println(x + " " + y);
        sc.close();
    }
}
