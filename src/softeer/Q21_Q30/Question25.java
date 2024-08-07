package softeer.Q21_Q30;
import java.util.*;
import java.io.*;

/**
 * 어버이날을 맞이하여 민상이는 아버지와 함께 여행을 가려고 합니다. 민상이는 여행을 위해 인기 있는 장소와 이동 경로를 조사했습니다. 우연하게도 이를 지도로 표시해보니 트리 모양이었습니다! 트리 모양이라 함은, 모든 인기 있는 장소끼리 전부 연결되어 있고 사이클이 존재하지 않는다는 뜻입니다. 장소를 정점으로, 연결 관계를 간선으로 표시하여 트리를 그려볼 수 있습니다.
 *
 *
 *
 *
 *
 *
 *
 * 이번 여행에서의 시작점은 1번 정점입니다. 또, 끝점은 1번 정점을 트리의 루트로 봤을 때의 리프 노드들 중 하나로 결정하려고 합니다. 이렇게 시작점과 끝점이 정해지게 되면, 시작점에서 출발하여 기존에 왔던 곳으로 되돌아 가지 않고 도착지를 향해서만 이동하게 됩니다. 예를 들어, 1→2→5→8 순으로는 여행이 가능하지만 1→2→1, 2→5→8 순으로는 여행을 할 수 없습니다.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 또, 각 간선에는 길의 종류를 뜻하는 대문자 알파벳이 하나씩 적혀있습니다.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 이번 여행은 아버지께서 최대 행복 지수를 얻으실 수 있게끔 계획하려고 합니다. 아버지께서는 평소 산책을 좋아하시기에 이미 좋아하는 길의 순서를 나타내는 문자열을 S를 가지고 계십니다. 여행을 통해 얻게 되는 행복 지수는 시작점에서 끝점까지 이동하는 길에 적혀있는 알파벳을 순서대로 나열했을 때 얻을 수 있는 문자열 T와 문자열 S와의 가장 긴 공통 부분 수열(Longest Common Subsequence, 이하 LCS)의 길이로 결정됩니다. 가장 긴 공통 부분 수열은 두 수열에 공통으로 나타나는 부분수열로, 문자열 ACAD와 문자열 ACDD를 생각해보면 두 문자열의 가장 긴 공통 부분 수열은 ACD가 됩니다. 부분 수열이기에, 연속해서 문자가 나올 필요는 없지만 순서대로 나타나야만 함에 유의합니다.
 *
 *
 *
 * 예를 들어, 아버지께서 좋아하시는 산책길 종류 순서에 해당하는 문자열 S가 ACQFZFIE라고 가정해봅시다. 만약 이번 여행에서 1→2→5→8 순서로 여행한다면 문자열 T는 AZQ가 됩니다. 따라서 이 경로를 통해 얻게되는 LCS는 AQ 또는 AZ가 되므로 아버지께서 얻게 되는 행복 지수는 2가 됩니다.
 *
 *
 *
 *
 *
 *
 *
 * 하지만 1→2→4→7 순서로 여행하게 된다면 문자열 T는 ACF로 LCS 역시 ACF가 되므로 아버지께서는 행복 지수를 3 만큼 얻을 수 있게 됩니다.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 여행을 통해 아버지께서 얻을 수 있는 최대 행복 지수를 구하는 프로그램을 작성해보세요.
 *
 *
 *
 *
 *
 * 본 문제의 저작권은 (주)브랜치앤바운드에 있으며, 저작자의 동의 없이 무단 전재/복제/배포를 금지합니다.
 *
 * 제약조건
 * 3 ≤ N, M ≤ 5,000, N, M은 정수
 * 1 ≤ u, v ≤ N, u≠v
 * c, S는 영문 대문자('A' ~ 'Z')로만 구성되어 있습니다.
 * 입력형식
 * 첫 번째 줄에는 정점의 개수 N과 주어지는 문자열의 길이 M이 공백으로 구분되어 주어집니다.
 * 두 번째 줄에는 길이가 M인 문자열 S이 주어집니다. S는 아버지가 평소에 좋아하시는 산책길 종류 순서를 의미합니다.
 * 세 번째 줄부터 N−1줄에 걸쳐 정점 u, v, 영문 대문자 c가 공백을 사이에 두고 주어집니다. 트리에서 정점 u와 정점 v가 연결되어 있고, 해당 길의 종류가 c임을 뜻합니다.
 *
 * 출력형식
 * 아버지께서 얻을 수 있는 최대 행복 지수를 출력합니다.
 *
 * 입력예제1
 * 4 3
 * ABC
 * 1 4 A
 * 4 2 C
 * 3 1 B
 *
 * 출력예제1
 * 2
 *
 * 입력예제2
 * 9 8
 * ACQFZFIE
 * 1 2 A
 * 4 2 C
 * 5 2 Z
 * 4 7 F
 * 8 5 Q
 * 1 3 H
 * 3 6 D
 * 9 6 V
 *
 * 출력예제2
 * 3
 */
public class Question25 {

    static class Edge {
        int to;
        char c;
        Edge(int to, char c) {
            this.to = to;
            this.c = c;
        }
    }

    static List<List<Edge>> graph;
    static String S;
    static int maxHappiness = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        S = br.readLine();

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            char c = temp[2].charAt(0);
            graph.get(x).add(new Edge(y, c));
            graph.get(y).add(new Edge(x, c));
        }

        dp = new int[S.length() + 1];
        dfs(1, 0, new boolean[n + 1], new int[S.length() + 1]);

        System.out.println(maxHappiness);
    }

    public static void dfs(int node, int parent, boolean[] visited, int[] currentDp) {
        visited[node] = true;
        boolean isLeaf = true;

        for (Edge edge : graph.get(node)) {
            if (edge.to != parent) {
                isLeaf = false;
                if (!visited[edge.to]) {
                    int[] newDp = updateLCS(currentDp, edge.c);
                    dfs(edge.to, node, visited, newDp);
                }
            }
        }

        if (isLeaf) {
            maxHappiness = Math.max(maxHappiness, currentDp[S.length()]);
        }

        visited[node] = false;
    }

    public static int[] updateLCS(int[] prevDp, char newChar) {
        int[] newDp = new int[S.length() + 1];
        for (int j = 1; j <= S.length(); j++) {
            if (newChar == S.charAt(j - 1)) {
                newDp[j] = prevDp[j - 1] + 1;
            } else {
                newDp[j] = Math.max(newDp[j - 1], prevDp[j]);
            }
        }
        return newDp;
    }

    /** ================= 틀린 풀이 ================= **/

    /** // 간선 정보를 담기 위한 Edge 클래스 정의
    static class Edge {
        int to; // 연결된 정점 번호
        char c; // 간선에 적힌 문자

        Edge(int to, char c) {
            this.to = to;
            this.c = c;
        }
    }

    static List<List<Edge>> graph; // 트리를 표현하는 인접 리스트
    static String S; // 아버지가 좋아하는 산책길 종류 순서
    static int maxHappiness = 0; // 최대 행복 지수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");

        int n = Integer.parseInt(first[0]); // 정점의 개수
        int m = Integer.parseInt(first[1]); // 문자열 S의 길이

        S = br.readLine(); // 아버지가 좋아하는 산책길 종류 순서

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < n - 1; i++) {
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            char c = temp[2].charAt(0);
            graph.get(x).add(new Edge(y, c));
            graph.get(y).add(new Edge(x, c)); // 양방향 간선으로 추가
        }

        // 방문 여부를 기록하는 배열
        boolean[] visited = new boolean[n + 1];
        visited[1] = true; // 시작점인 1번 정점을 방문 표시
        dfs(1, "", visited); // DFS 시작

        // 최대 행복 지수 출력
        System.out.println(maxHappiness);
    }

    // DFS 함수 정의
    public static void dfs(int node, String path, boolean[] visited) {
        boolean isLeaf = true; // 리프 노드 여부 체크
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.to]) {
                isLeaf = false; // 자식 노드가 있으면 리프 노드가 아님
                visited[edge.to] = true; // 방문 표시
                dfs(edge.to, path + edge.c, visited); // 다음 노드로 DFS
                visited[edge.to] = false; // 방문 표시 해제 (백트래킹)
            }
        }

        if (isLeaf) {
            // 리프 노드에 도달했을 경우, 현재 경로와 S의 LCS 계산
            maxHappiness = Math.max(maxHappiness, lcs(path, S));
        }
    }

    // 두 문자열의 LCS(Longest Common Subsequence) 길이를 계산하는 함수
    public static int lcs(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        // 동적 계획법(DP)을 이용한 LCS 계산
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[a.length()][b.length()];
    } **/

}
