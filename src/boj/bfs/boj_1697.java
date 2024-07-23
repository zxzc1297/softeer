package boj.bfs;
import java.util.*;

/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고,
 * 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 4
 */
public class boj_1697 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        boolean[] visited = new boolean[100001];
        if(N == K)
            System.out.println(0);
        else{
            //bfs 구현
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{N, 0});
            visited[N] = true;
            while(!q.isEmpty()){
                int[] temp = q.poll();
                int cnt = temp[1];
                for(int i=0; i<3; i++){
                    int next = temp[0];
                    if(i==0){
                        next += 1;
                    }else if(i==1){
                        next -= 1;
                    }else{
                        next *=2;
                    }

                    if(next == K){
                        System.out.println(cnt+1);
                        return;
                    }

                    if(next>=0 && next<= 100000 && !visited[next]){
                        visited[next] = true;
                        q.offer(new int[]{next, cnt+1});
                    }
                }

            }
        }

        sc.close();
    }
}
