package com.ellis.jcf.filter;

import com.ellis.jcf.filter.model.CharNode;

import java.util.Map;
import java.util.Set;

public class PrintUtil {
  
  public static void p(Map<Character, CharNode>  swnMap, int level){
    Set<Map.Entry<Character,CharNode>>  set =  swnMap.entrySet();
    for (Map.Entry<Character,CharNode> entry : set) {
      for (int i = 0; i < level; i++) {
        System.out.print("   ");
      }
      System.out.println( entry.getKey());
      p(entry.getValue().getNodes(),level +1);
    }
  }
  
}
