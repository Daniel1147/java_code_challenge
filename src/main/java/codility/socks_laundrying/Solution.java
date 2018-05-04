package codility.socks_laundrying;

import java.util.*;

class Solution {
  public int solution(int K, int[] C, int[] D) {
    Map<Integer, Integer> cleanSock, dirtySock;
    Integer color, num, pairNum;
    int count, pairCount;

    // collect clean socks
    cleanSock = new HashMap<Integer, Integer>();
    for (int i = 0; i < C.length; i++) {
      color = Integer.valueOf(C[i]);
      if (cleanSock.get(color) == null)
        cleanSock.put(color, 1);
      else
        cleanSock.put(color, cleanSock.get(color) + 1);
    }

    // printMap(cleanSock);

    // collect dirty socks
    dirtySock = new HashMap<Integer, Integer>();
    for (int i = 0; i < D.length; i++) {
      color = Integer.valueOf(D[i]);
      if (dirtySock.get(color) == null)
        dirtySock.put(color, 1);
      else
        dirtySock.put(color, dirtySock.get(color) + 1);
    }

    // printMap(dirtySock);

    count = 0;
    for (Map.Entry<Integer, Integer> e : cleanSock.entrySet()) {
      num = e.getValue();
      color = e.getKey();

      pairNum = num / 2;
      count += pairNum;
      cleanSock.put(color, num - pairNum * 2);
    }

    // match clean dirty sock
    Iterator<Map.Entry<Integer, Integer>> iter;
    Map.Entry<Integer, Integer> entry;

    iter = cleanSock.entrySet().iterator();
    while (K > 0 && iter.hasNext()) {
      entry = iter.next();
      color = entry.getKey();
      if (dirtySock.get(color) != null && dirtySock.get(color) > 0) {
        K--;
        dirtySock.put(color, dirtySock.get(color) - 1);
      }
    }

    return count;
  }

  private void printMap(Map<Integer, Integer> m) {
    for (Map.Entry<Integer, Integer> e : m.entrySet())
      System.out.printf("key => %d, value => %d\n", e.getKey(), e.getValue());
  }
}
