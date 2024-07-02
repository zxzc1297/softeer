package softeer.Q21_Q30;

import java.util.*;
import java.io.*;

public class Question22 {
    static ArrayList<Integer> sums;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,-1,1};
    static int[][] maps, friends;
    static boolean[][] visited;
    static int n,m,answer;
    public static void main(String[] args) throws IOException {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        n = Integer.parseInt(first[0]);
        m = Integer.parseInt(first[1]);
        maps = new int[n+2][n+2];
        visited = new boolean[n+2][n+2];
        //1.배열 생성
        for(int i=1; i<=n; i++){
            String[] arr = br.readLine().split(" ");
            for(int j=1; j<=n; j++){
                maps[i][j] = Integer.parseInt(arr[j-1]);
            }
        }

        //2.시작 위치 설정
        friends = new int[m][2];
        for(int k=1; k<=m; k++){
            String[] arr = br.readLine().split(" ");
            int x = Integer.parseInt(arr[0]);
            int y = Integer.parseInt(arr[1]);
            friends [k-1] = new int[]{x,y};
            visited[x][y] = true;
            answer += maps[x][y];
        }
        //3.시작 위치도 수확 이후 dfs 진행
        dfs(friends[0][0], friends[0][1], 0, 0, answer);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int fCnt, int time, int sum){
        answer = Math.max(answer, sum);

        if(time == 3){
            if(fCnt+1<m){
                dfs(friends[fCnt+1][0], friends[fCnt+1][1], fCnt+1, 0, sum);
            }
        } else {
            for(int i=0; i<4; i++){
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if(nx>0 && nx <=n && ny>0 && ny<=n && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    dfs(nx, ny, fCnt, time+1, sum + maps[nx][ny]);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
