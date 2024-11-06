package boj.tree;
import java.util.*;

/**
 * 문제
 * 트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
 *
 * 트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
 *
 * 예를 들어, 다음과 같은 트리가 있다고 하자.
 *
 *
 *
 * 현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.
 *
 *
 *
 * 이제 리프 노드의 개수는 1개이다.
 *
 * 입력
 * 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
 *
 * 예제 입력 1
 * 5
 * -1 0 0 1 1
 * 2
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 5
 * -1 0 0 1 1
 * 1
 * 예제 출력 2
 * 1
 */
public class boj_1068 {
    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] deleted;
    static int root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        deleted = new boolean[n];
        root = -1;

        // 트리 구성
        for (int i = 0; i < n; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                root = i; // 루트 노드 설정
            } else {
                tree.get(parent).add(i); // 자식 노드 추가
            }
        }

        int deleteNode = sc.nextInt();
        deleteNodeAndDescendants(deleteNode);

        // 루트가 삭제되었다면, 리프 노드의 개수는 0
        if (deleted[root]) {
            System.out.println(0);
        } else {
            System.out.println(countLeafNodes(root));
        }

        sc.close();
    }

    // 특정 노드와 그 자손 노드들을 삭제
    static void deleteNodeAndDescendants(int node) {
        deleted[node] = true;
        for (int child : tree.get(node)) {
            deleteNodeAndDescendants(child);
        }
    }

    // 리프 노드의 개수 카운트
    static int countLeafNodes(int node) {
        if (deleted[node]) return 0; // 삭제된 노드는 무시
        List<Integer> children = tree.get(node);

        // 자식이 없거나, 모든 자식이 삭제되었을 경우 리프 노드
        boolean isLeaf = true;
        int leafCount = 0;
        for (int child : children) {
            if (!deleted[child]) {
                isLeaf = false;
                leafCount += countLeafNodes(child);
            }
        }

        return isLeaf ? 1 : leafCount;
    }
}
