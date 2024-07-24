package boj.bfs;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 *
 *
 * 입력
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *
 * 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 *
 * 출력
 * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 *
 * 예제 입력 1
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 * 예제 출력 1
 * 5
 * 28
 * 0
 */
public class boj_7562 {
    static int[] dirX = {-2,-1,1,2,-2,-1,1,2};
    static int[] dirY = {1,2,2,1,-1,-2,-2,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());
        for(int i=0; i<total; i++){
            //1.체스판 생성
            int length = Integer.parseInt(br.readLine());
            //2.출발 좌표, 도착 좌표 생성, 방문 배열 생성
            int[][] maps = new int[length][length];
            boolean[][] visited = new boolean[length][length];
            String[] firstarr = br.readLine().split(" ");
            String[] secarr = br.readLine().split(" ");
            int[] first = new int[]{Integer.parseInt(firstarr[0]), Integer.parseInt(firstarr[1]), 0};
            int[] end = new int[]{Integer.parseInt(secarr[0]), Integer.parseInt(secarr[1])};

            // 출발지와 도착지가 같은 경우
            if (first[0] == end[0] && first[1] == end[1]) {
                System.out.println(0);
                continue;
            }
            //3.생성 후 나이트가 갈 수 있는 8개 방향 생성 이때 체스판 범위 안에 있는지 판별
            Queue<int[]> q = new LinkedList<>();
            q.offer(first);
            visited[first[0]][first[1]] = true;
            while(!q.isEmpty()){
                int[] temp = q.poll();
                int x = temp[0];
                int y = temp[1];
                int time = temp[2];
                boolean found = false;
                for(int j=0; j<8; j++){
                    int newX = x + dirX[j];
                    int newY = y + dirY[j];

                    if(newX == end[0] && newY == end[1]){
                        System.out.println(time+1);
                        found = true;
                        break;
                    }

                    if(newX>=0 && newX<length && newY>=0 && newY<length && !visited[newX][newY]){
                        visited[newX][newY] = true;
                        q.offer(new int[]{newX, newY, time+1});
                    }
                }
                if(found)
                    break;
            }
        }
    }
}
