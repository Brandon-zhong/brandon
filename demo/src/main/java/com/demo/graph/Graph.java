package com.demo.graph;

import java.util.LinkedList;

/**
 * @author brandon
 * Created on 2020-02-24.
 * desc:
 **/

public class Graph { // 无向图
    private int v; // 顶点的个数
    private LinkedList<Integer>[] adj; // 邻接表

    public int getV() {
        return v;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }
}