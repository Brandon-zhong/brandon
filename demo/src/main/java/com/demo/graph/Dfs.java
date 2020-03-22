package com.demo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author brandon
 * Created on 2020-02-25.
 * desc: 图的深度优先搜索
 **/
public class Dfs {

    boolean found = false; // 全局变量或者类成员变量

    public Graph createGraph() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        return graph;
    }

    public void dfs(Graph graph, int f, int t) {
        //用来存储访问过的元素，true-访问过，false-未访问
        boolean[] visited = new boolean[graph.getV()];
        //存储当前位置是从那里来的，下标为当前位置，值为来的位置
        int[] pre = new int[graph.getV()];
        Arrays.fill(pre, -1);

        LinkedList<Integer>[] adj = graph.getAdj();
        recurDfs(adj, f, t, visited, pre);
        printDfs(pre, f, t);
    }

    private void recurDfs(LinkedList<Integer>[] adj, int f, int t, boolean[] visited, int[] pre) {
        //判断是否找到
        if (found) {
            return;
        }
        if (f == t) {
            found = true;
            return;
        }
        //标记当前位置以搜索
        visited[f] = true;
        for (int i = 0; i < adj[f].size(); ++i) {
            Integer w = adj[f].get(i);
            if (visited[w]) {
                continue;
            }
            //如果当前位置没有访问过，则标记当前位置的来时路径以及继续递归
            pre[w] = f;
            recurDfs(adj, w, t, visited, pre);
        }
    }

    private void printDfs(int[] pre, int f, int t) {
        if (pre[t] != -1 && f != t) {
            printDfs(pre, f, pre[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Dfs dfs = new Dfs();
        dfs.dfs(dfs.createGraph(), 0, 6);
    }

}
