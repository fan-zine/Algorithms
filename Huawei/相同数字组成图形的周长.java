package Huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 相同数字组成图形的周长 {
    public static class Graph {
        int row, col;

        public Graph(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<List<Graph>> graphList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer[] s = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            List<Graph> singleGraph = new ArrayList<>();
            for (int j = 1; j < s.length - 1; ) {
                Graph g = new Graph(s[j], s[j + 1]);
                singleGraph.add(g);
                j += 2;
            }
            graphList.add(singleGraph);
        }
        List<Integer> ans = new ArrayList<>();
        for (List<Graph> graphs : graphList) {
            int overlap = 0;
            for (int i = 0; i < graphs.size(); i++) {
                for (int j = i + 1; j < graphs.size(); j++) {
                    boolean neighbor = isNeighbor(graphs.get(i), graphs.get(j));
                    if (neighbor) {
                        overlap++;
                    }
                }
            }
            int perimeter = graphs.size() * 4 - overlap * 2;
            ans.add(perimeter);
        }
        StringBuilder s1 = new StringBuilder();
        for (Integer an : ans) {
            s1.append(an).append(" ");
        }
        String s = s1.substring(0,s1.length() - 1);
        System.out.println(s);
    }

    public static boolean isNeighbor(Graph g1, Graph g2) {
        if (g1.row == g2.row && (g1.col - g2.col == 1 || g1.col - g2.col == -1)) return true;
        if (g1.col == g2.col && (g1.row - g2.row == 1 || g1.row - g2.row == -1)) return true;
        return false;
    }
}
