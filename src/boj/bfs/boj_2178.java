package boj.bfs;
import java.util.*;
public class boj_2178 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[][] maze = new int[n+1][m+1];
        int[][] visited = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            String line = sc.nextLine();
            for (int j=1; j<=m; j++) {
                maze[i][j] = line.charAt(j-1) - '0';
            }
        }

        Queue<int[]> q = new LinkedList<>();
        visited[1][1] = 1;
        q.offer(new int[]{1,1,1});

        while(!q.isEmpty()){
            int[] temp = q.poll();

            if(temp[0] == n && temp[1] == m){
                System.out.print(temp[2]);
                break;
            }

            for(int i=0; i<4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];

                if(nx>0 && ny>0 && nx<=n && ny<=m && visited[nx][ny] != 1 && maze[nx][ny] == 1){
                    visited[nx][ny] = 1;
                    q.offer(new int[]{nx,ny,temp[2]+1});
                }
            }

        }

        sc.close();
    }
}
