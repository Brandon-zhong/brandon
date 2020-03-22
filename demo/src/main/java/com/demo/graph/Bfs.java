package com.demo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author brandon
 * Created on 2020-02-24.
 * desc: 图的广度优先搜索
 **/
public class Bfs {

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

    public void bfs(Graph graph, int f, int t) {
        //用来存储访问过的元素，true-访问过，false-未访问
        boolean[] visited = new boolean[graph.getV()];
        //用户存储访问过但是外层未访问的元素
        Queue<Integer> queue = new LinkedList<>();
        //存储当前位置是从那里来的，下标为当前位置，值为来的位置
        int[] pre = new int[graph.getV()];
        Arrays.fill(pre, -1);

        //从f点开始搜索
        queue.add(f);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            LinkedList<Integer>[] adj = graph.getAdj();
            for (int i = 0; i < adj[poll].size(); ++i) {
                Integer w = adj[poll].get(i);
                //如果当前位置已经被访问，跳过
                if (visited[w]) {
                    continue;
                }
                //将当前位置放入待搜索列表，并记录来时路径
                //如果当前位置要搜索的位置
                pre[w] = poll;
                if (w == t) {
                    printBfs(pre, f, t);
                    return;
                }
                visited[w] = true;
                queue.add(w);
            }
        }


    }

    private void printBfs(int[] pre, int f, int t) {
        if (pre[t] != -1 && f != t) {
            printBfs(pre, f, pre[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Bfs bfs = new Bfs();
        bfs.bfs(bfs.createGraph(), 0, 6);
    }


}
