package softeer.Q1_Q10;

import java.util.Scanner;

/*
산타는 연탄 배달을 시작하려고 합니다. 이 도시에는 n개의 마을이 있고, 각 마을은 1차 수직선 상에 위치하고 있습니다.

산타는 이 마을들 중 가장 거리가 가까운 두 마을을 먼저 방문한다고 했을 때, 산타가 처음 방문할 가능성이 있는 서로 다른 두 마을 조합의 수를 구하는 프로그램을 작성해보세요.



본 문제의 저작권은 (주)브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금지합니다.

제약조건
2 ≤ n ≤ 1,000
1 ≤ 마을의 위치 ≤ 1,000,000
입력형식
첫 번째 줄에는 마을의 수를 나타내는 n이 주어집니다.
두 번째 줄에는 n개의 마을의 위치가 공백을 사이에 두고 주어집니다. 마을의 위치는 서로 다르며, 이 위치들은 오름차순으로 주어진다고 가정해도 좋습니다.

출력형식
첫 번째 줄에 산타가 처음 방문할 가능성이 있는 서로 다른 두 마을 조합의 수를 출력합니다.

입력예제1
5
1 3 5 8 10
출력예제1
3
첫 번째 예제에서 가장 가까운 두 마을간의 거리는 2입니다. 이것이 가능한 두 마을 위치의 조합은 {1, 3}, {3, 5}, {8, 10} 이렇게 3개이기에 답은 3이 됩니다.

입력예제2
5
1 3 5 7 8
출력예제2
1
두 번째 예제에서 가장 가까운 두 마을간의 거리는 1입니다. 이것이 가능한 두 마을 위치의 조합은 {7, 8} 밖에 없기에 답은 1이 됩니다.
 */

public class Question4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amt = sc.nextInt();
        int before = 0;
        int cur = 0;
        int beforeGap = 0;
        int cnt = 0;
        int gap = 0;
        while(sc.hasNextInt()){
            cur = sc.nextInt();
            if(before != 0){
                gap = cur - before;
                if(beforeGap == 0){
                    beforeGap = gap;
                    cnt++;
                }
                else{
                    if(gap < beforeGap){
                        cnt = 1;
                        beforeGap = gap;
                    }else if(gap == beforeGap){
                        cnt++;
                    }
                }
            }
            before = cur;

        }

        System.out.println(cnt);

        sc.close();
    }
}
