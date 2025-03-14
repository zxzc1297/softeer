package boj.bruteForce;
import java.util.*;

/**
 * 문제
 * 형택이는 건축가이다. 지금 막 형택이는 형택이의 남자 친구 기훈이의 집을 막 완성시켰다. 형택이는 기훈이 방의 바닥 장식을 디자인했고, 이제 몇 개의 나무 판자가 필요한지 궁금해졌다. 나무 판자는 크기 1의 너비를 가졌고, 양수의 길이를 가지고 있다. 기훈이 방은 직사각형 모양이고, 방 안에는 벽과 평행한 모양의 정사각형으로 나누어져 있다.
 *
 * 이제 ‘-’와 ‘|’로 이루어진 바닥 장식 모양이 주어진다. 만약 두 개의 ‘-’가 인접해 있고, 같은 행에 있다면, 두 개는 같은 나무 판자이고, 두 개의 ‘|’가 인접해 있고, 같은 열에 있다면, 두 개는 같은 나무 판자이다.
 *
 * 기훈이의 방 바닥을 장식하는데 필요한 나무 판자의 개수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 방 바닥의 세로 크기N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 M개의 문자가 주어진다. 이것은 바닥 장식 모양이고, '-‘와 ’|‘로만 이루어져 있다. N과 M은 50 이하인 자연수이다.
 *
 * 출력
 * 첫째 줄에 문제의 정답을 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * ----
 * ----
 * ----
 * ----
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * 6 9
 * -||--||--
 * --||--||-
 * |--||--||
 * ||--||--|
 * -||--||--
 * --||--||-
 * 예제 출력 2
 * 31
 * 예제 입력 3
 * 7 8
 * --------
 * |------|
 * ||----||
 * |||--|||
 * ||----||
 * |------|
 * --------
 * 예제 출력 3
 * 13
 */
public class boj_1388 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 방의 세로 크기
        int m = sc.nextInt(); // 방의 가로 크기
        sc.nextLine(); // 버퍼 비우기

        char[][] floor = new char[n][m];

        // 바닥 장식 입력
        for (int i = 0; i < n; i++) {
            floor[i] = sc.nextLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][m];
        int count = 0;

        // 필요한 나무 판자의 개수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    count++;
                    if (floor[i][j] == '-') {
                        // 가로로 연결된 '-' 탐색
                        for (int k = j; k < m && floor[i][k] == '-'; k++) {
                            visited[i][k] = true;
                        }
                    } else if (floor[i][j] == '|') {
                        // 세로로 연결된 '|' 탐색
                        for (int k = i; k < n && floor[k][j] == '|'; k++) {
                            visited[k][j] = true;
                        }
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(count);
    }
}
