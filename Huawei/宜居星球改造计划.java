package Huawei;

import java.util.*;

public class 宜居星球改造计划 {
    public static class Cor {
        int row;
        int col;

        public Cor(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> area = new ArrayList<>();
        Queue<Cor> queue = new ArrayDeque<>();
        int row = 0, col = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (Objects.equals(s, "ss")) break;
            String[] s1 = s.split(" ");
            List<Integer> a = new ArrayList<>();
            col = 0;
            for (String s2 : s1) {
                if ("YES".equals(s2)) {
                    a.add(1);
                    queue.add(new Cor(row, col));
                } else if ("NO".equals(s2)) {
                    a.add(0);
                } else if ("NA".equals(s2)) {
                    a.add(-1);
                }
                col++;
            }
            area.add(a);
            row++;
        }
        int days = bfs(area, queue);
        for (int i = 0; i < area.size(); i++) {
            for (int j = 0; j < area.get(0).size(); j++) {
                if (area.get(i).get(j) == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(days);
    }

    public static int bfs(List<List<Integer>> area, Queue<Cor> queue) {
        int day = -1;
        while (!queue.isEmpty()) {
            day++;
            int explore = queue.size();
            for (int i = 0; i < explore; i++) {
                Cor poll = queue.poll();
                explore(area, poll, queue);
            }
        }
        return day;
    }

    public static void explore(List<List<Integer>> area, Cor cor, Queue<Cor> queue) {
        int row = cor.row, col = cor.col;
        exploreSpecifiedArea(area, queue, row - 1, col);
        exploreSpecifiedArea(area, queue, row + 1, col);
        exploreSpecifiedArea(area, queue, row, col - 1);
        exploreSpecifiedArea(area, queue, row, col + 1);
    }

    private static void exploreSpecifiedArea(List<List<Integer>> area, Queue<Cor> queue, int row, int col) {
        if (row >= 0 && row < area.size() && col >= 0 && col < area.get(0).size() && area.get(row).get(col) == 0) {
            // 不确定这个地方能不能修改得了
            area.get(row).set(col, 1);
            queue.add(new Cor(row, col));
        }
    }
}



