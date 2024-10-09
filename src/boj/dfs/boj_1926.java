package boj.dfs;
import java.util.*;
/*8
문제
어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 그림의 넓이란 그림에 포함된 1의 개수이다.

입력
첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)

출력
첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.

예제 입력 1
6 5
1 1 0 1 1
0 1 1 0 0
0 0 0 0 0
1 0 1 1 1
0 0 1 1 1
0 0 1 1 1
예제 출력 1
4
9
 */
public class boj_1926 {
    static int[][] painting, visited;
    static int cnt, answer, n, m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        painting = new int[n][m];
        visited = new int[n][m];

        //그림 배열 받기
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                painting[i][j] = sc.nextInt();
            }
        }

        //정답 초기값 설정
        cnt = 0;
        answer = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //칠해진 부분 찾기
                if(painting[i][j] == 1 && visited[i][j] == 0){
                    //dfs로 전체 갯수와 최대 넓이 구하기
                    visited[i][j] = 1;
                    cnt++;
                    int size = dfs(i,j);
                    answer = Math.max(answer,size);
                }
            }
        }



        //답 출력
        System.out.println(cnt);
        System.out.println(answer);
    }

    //dfs 구현
    static int dfs(int x, int y){
        int size = 1;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<n && ny>=0 && ny<m && visited[nx][ny] == 0 && painting[nx][ny] == 1){
                visited[nx][ny] = 1;
                size += dfs(nx, ny);
            }
        }

        return size;
    }
}
