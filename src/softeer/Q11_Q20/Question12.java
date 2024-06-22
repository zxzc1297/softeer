package softeer.Q11_Q20;

import java.util.Scanner;

/*
남우는 어버이날을 맞아 부모님의 일을 돕기로 하였습니다. 남우의 부모님께서는 농사를 지으시기에, 남우는 땅을 일구는 일을 도우려고 합니다.
남우에게 할당된 땅은 3 * 3 크기의 격자로 이루어져 있으며, 각 땅의 높이는 1이상 3이하의 정수값으로 이루어져 있습니다. 부모님께서 농사를 지을 땅의 크기는 1 * 3이며, 농사를 짓기 위해서는 해당 영역 내 땅의 높이가 전부 동일해야 합니다. 따라서 남우는 특정 땅의 높이를 낮추거나 높여, 3 * 3 격자 내에 부모님께서 농사를 지을 수 있는 영역이 최소 1곳 이상 생기도록 만들려고 합니다.

남우가 특정 땅의 높이를 1만큼 낮추거나 높이는데 1만큼의 비용이 소요된다고 했을 때, 부모님께서 농사를 지으실 수 있도록 땅을 일구기 위해 남우에게 필요한 최소 비용을 구하는 프로그램을 작성해보세요.

단, 1 * 3 크기의 영역은 가로, 세로로 놓이는 것이 모두 가능하기에, 3 * 3 크기의 격자에서는 땅의 높이만 동일하다면 최대 6개의 영역에 농사를 지을 수 있음에 유의합니다.



본 문제의 저작권은 (주) 브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금합니다.

제약조건
1 ≤ 땅의 높이 ≤ 3
입력형식
세 개의 줄에 걸쳐 각 행에 해당하는 땅의 높이 정보가 공백을 사이에 두고 주어집니다.

출력형식
부모님께서 농사를 짓는 것이 가능해지기 위해 남우에게 필요한 최소 비용을 출력합니다.

입력예제1
1 1 1
2 3 1
3 1 2
출력예제1
0
첫 번째 예제에서는 다음과 같이 영역을 잡으면, 이미 땅의 높이가 전부 동일하므로 추가적인 비용이 들지 않습니다.



입력예제2
1 1 3
1 1 3
3 3 1
출력예제2
2
두 번째 예제에서는 다음과 같이 영역을 잡으면, 높이를 전부 1로 맞추기 위해 비용 2가 소요됩니다. 이보다 더 적은 비용으로 농사를 지을 수 있는 영역을 만들 수는 없습니다.


 */
public class Question12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = 500;

        int[][] lands = new int [3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                lands[i][j] = sc.nextInt();
            }
        }

        for(int k=0; k<3; k++){
            int idx1 = lands[k][0];
            int idx2 = lands[k][1];
            int idx3 = lands[k][2];
            int idx4 = lands[0][k];
            int idx5 = lands[1][k];
            int idx6 = lands[2][k];
            int sum = 0;
            int sum2 = 0;

            if(idx1 == idx2 || idx1 == idx3){
                sum = Math.max(idx2,idx3) - Math.min(idx2,idx3);
            } else if(idx2 == idx3){
                sum = Math.max(idx1,idx3) - Math.min(idx1,idx3);
            } else {
                sum = 2;
            }

            if(idx4 == idx5 || idx4 == idx6){
                sum2 = Math.max(idx5,idx6) - Math.min(idx5,idx6);
            } else if(idx5 == idx6){
                sum2 = Math.max(idx4,idx6) - Math.min(idx4,idx6);
            } else{
                sum2 = 2;
            }


            if(sum < answer){
                answer = sum;
            }

            if(sum2 < answer){
                answer = sum2;
            }
        }

        System.out.print(answer);

        sc.close();

    }
}
