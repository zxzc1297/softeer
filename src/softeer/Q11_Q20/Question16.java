package softeer.Q11_Q20;
import java.util.*;
/**
 * 우선 [그림 1]과 같이 정사각형 모양의 지도가 있다. 1은 장애물이 있는 곳을, 0은 도로가 있는 곳을 나타낸다.
 * 당신은 이 지도를 가지고 연결된 장애물들의 모임인 블록을 정의하고, 불록에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 장애물이 좌우, 혹은 아래위로 붙어 있는 경우를 말한다. 대각선 상에 장애물이 있는 경우는 연결된 것이 아니다.
 *
 * [그림 2] 블록 별 번호 부여
 *
 *
 *
 * [그림 2]는 [그림 1]을 블록 별로 번호를 붙인 것이다.
 *
 *
 *
 * 지도를 입력하여 장애물 블록수를 출력하고, 각 블록에 속하는 장애물의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 *
 * 제약조건
 * N은 정사각형임으로 가로와 세로의 크기는 같으며 5 ≤ N ≤ 25
 *
 * 입력형식
 * 입력 값의 첫 번째 줄에는 지도의 크기 N이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 *
 * 출력형식
 * 첫 번째 줄에는 총 블록 수를 출력 하시오.
 *
 * 그리고 각 블록 내 장애물의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 *
 * 입력예제1
 * 7
 * 1110111
 * 0110101
 * 0110101
 * 0000100
 * 0110000
 * 0111110
 * 0110000
 * 출력예제1
 * 3
 * 7
 * 8
 * 9
 */
public class Question16 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static int answer = 0; //장애물 발견수
    static int sum = 0; //장애물수
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int[][] board = new int[n + 1][n + 1];
        sc.nextLine();
        for (int i = 1; i < n+1; i++) {
            String data = sc.nextLine();
            char[] tmp = data.toCharArray();
            for (int j =0 ; j< data.length(); j++) {
                board[i][j+1] = Integer.parseInt(String.valueOf(tmp[j]));
            }

        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (board[i][j] == 1) { //장애물을 발견
                    answer++; // 장애물을 발견해서 장애물 발견 건수에 +1 을 해준다.
                    board[i][j] = 0; //장애물을 0으로 변경하여 장애물로 인식하지 못하도록 한다.
                    sum =1; //장애물을 발견했으니 장애물 수를 1로 초기화
                    dfs(i, j, board);
                    list.add(sum); //탐색이 끝나면 list에 장애물수를 기록한다 -> sort를 위해
                }
            }
        }
        System.out.println(answer); //장애물건수
        list.sort(Comparator.comparingInt(o -> o)); //list에 담긴 장애물 수들을 오름차순으로 정렬

        list.stream().forEach(a-> System.out.println(a));
    }

    public static void dfs(int x, int y, int[][] board) {

        for (int i = 0; i < 4; i++) { //상하좌우로 움직일 수 있다.
            int nx = x + dx[i]; //방문할 다음 좌표를 지정
            int ny = y + dy[i]; //방문할 다음 좌표를 지정

            if (nx >= 1 && nx < n+1
                    && ny >= 1 && ny < n +1
                    && board[nx][ny] == 1) {
                board[nx][ny] = 0; //다음엔 장애물로 인식하지 않도록 0으로 변경해준다.
                sum++; //장애물수를 더해준다.
                dfs(nx, ny, board); //탐색
            }
        }
    }
}
