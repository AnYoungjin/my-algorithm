import java.util.*;

class Solution {
    private ArrayList<Integer>[] graph;
    private ArrayList<Node> list = new ArrayList<>();
    private int maxDepth = 0;
    
    class Node {
        int idx;
        int depth;
        
        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
    
    public void bfs(Queue<Node> toVisit, boolean[] visited) {
        // 현재 노드에서 이동할 수 있는 노드가 있다면
        while (!toVisit.isEmpty()) {
            // curNode : 이동한 노드
            Node curNode = toVisit.poll();
            // 1번 노드와 현재 노드 사이의 간선의 개수가 maxDepth보다 크거나 같다면
            if (curNode.depth >= maxDepth) {
                if (curNode.depth > maxDepth) list.clear();
                // 가장 멀리 떨어진 노드 리스트에 추가
                list.add(curNode);
                maxDepth = curNode.depth;
            }
            
            for (Integer nodeIdx : graph[curNode.idx]) {
                // nodeIdx : 현재 노드(curNode)와 연결되어있는 노드의 번호
                if (visited[nodeIdx]) continue;
                // 방문한 적이 없다면 방문할 노드들(큐)에 추가
                toVisit.add(new Node(nodeIdx, curNode.depth + 1));
                // 방문처리
                visited[nodeIdx] = true;
            }
        }
    }
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i < edge.length; i++) {
            // [a, b] => a번 노드와 b번 노드 사이에 간선이 있다.
            int a = edge[i][0];
            int b = edge[i][1];
            if (graph[a] == null) graph[a] = new ArrayList<>();
            if (graph[b] == null) graph[b] = new ArrayList<>();
            // a번 노드와 b번 노드는 서로 연결되어있음
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 현재 1번 노드에서 갈 수 있는 노드들을 큐에 담는다.
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        for (int nodeIdx : graph[1]) {
            // 노드 번호, depth
            q.add(new Node(nodeIdx, 1));
            visited[nodeIdx] = true;
        }
        
        bfs(q, visited);
        
        // 가장 멀리 떨어진 노드들의 개수
        return list.size();
    }
}
