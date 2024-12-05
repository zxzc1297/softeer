package boj.bfs;
import java.util.*;

/**
 * 문제
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
 *
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 *
 * 출력
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 *
 * 예제 입력 1
 * 8 9 10
 * 예제 출력 1
 * 1 2 8 9 10
 */
public class boj_2251 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt(); // 첫 번째 물통의 용량
        int B = scanner.nextInt(); // 두 번째 물통의 용량
        int C = scanner.nextInt(); // 세 번째 물통의 용량

        boolean[][][] visited = new boolean[A + 1][B + 1][C + 1];
        boolean[] possible = new boolean[C + 1];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, C));
        visited[0][0][C] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            int a = current.a;
            int b = current.b;
            int c = current.c;

            // 첫 번째 물통이 비어 있을 때 세 번째 물통의 물의 양을 기록
            if (a == 0) {
                possible[c] = true;
            }

            // 물 옮기기 시뮬레이션
            for (int[] transfer : new int[][] {
                    {a, b, c, 0, 1}, {a, b, c, 0, 2},
                    {a, b, c, 1, 0}, {a, b, c, 1, 2},
                    {a, b, c, 2, 0}, {a, b, c, 2, 1}}) {
                State next = transferWater(transfer, A, B, C);
                if (!visited[next.a][next.b][next.c]) {
                    visited[next.a][next.b][next.c] = true;
                    queue.add(next);
                }
            }
        }

        // 결과 출력
        for (int i = 0; i <= C; i++) {
            if (possible[i]) {
                System.out.print(i + " ");
            }
        }
    }

    // 물 옮기기
    public static State transferWater(int[] transfer, int A, int B, int C) {
        int a = transfer[0];
        int b = transfer[1];
        int c = transfer[2];
        int from = transfer[3];
        int to = transfer[4];

        int[] limits = {A, B, C};
        int[] bottles = {a, b, c};

        // 옮길 물의 양 계산
        int move = Math.min(bottles[from], limits[to] - bottles[to]);

        // 물 옮기기
        bottles[from] -= move;
        bottles[to] += move;

        return new State(bottles[0], bottles[1], bottles[2]);
    }

    // 물통의 상태를 표현하는 클래스
    public static class State {
        int a, b, c;

        public State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
