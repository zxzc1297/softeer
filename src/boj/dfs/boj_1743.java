package boj.dfs;
import java.util.*;

/**
 * 문제
 * 코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.
 *
 * 이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다. 참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다.
 *
 * 통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
 *
 * 선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
 *
 * 입력
 * 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.  그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
 *
 * 좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
 *
 * 출력
 * 첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.
 *
 * 예제 입력 1
 * 3 4 5
 * 3 2
 * 2 2
 * 3 1
 * 2 3
 * 1 1
 * 예제 출력 1
 * 4
 */
public class boj_1743 {
    static int N,M,K;
    static int answer = 0;
    static int[][] foods;
    static int[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        foods = new int[N][M];
        visited = new int[N][M];

        //음식 위치 기입
        for(int i=0; i<K; i++){
            int tx = sc.nextInt();
            int ty = sc.nextInt();
            //음식이 있는 곳 1로 변경
            foods[tx-1][ty-1] = 1;
        }

        //순회하면서 음식의 크기 산정
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){
                if(foods[j][k] == 1 && visited[j][k] == 0){
                    //음식이 있고 방문하지 않았을때 dfs로 크기 탐색
                    int tmpSize = dfs(j,k);
                    answer = Math.max(answer, tmpSize);
                }
            }
        }

        System.out.println(answer);
        sc.close();
    }

    public static int dfs(int x, int y){
        int size = 1;
        visited[x][y] = 1;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<N && ny>=0 && ny<M && foods[nx][ny]==1 && visited[nx][ny]==0){
                size += dfs(nx,ny);
            }
        }

        return size;
    }
}
