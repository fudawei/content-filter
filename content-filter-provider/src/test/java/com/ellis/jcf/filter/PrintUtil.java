package com.ellis.jcf.filter;

import java.util.Map;
import java.util.Set;

public class PrintUtil {
  
  public static void p(Map<Character, SensitiveWordsNode>  swnMap,int level){
    Set<Map.Entry<Character,SensitiveWordsNode>>  set =  swnMap.entrySet();
    for (Map.Entry<Character,SensitiveWordsNode> entry : set) {
      for (int i = 0; i < level; i++) {
        System.out.print("   ");
      }
      System.out.println( entry.getKey());
      p(entry.getValue().getNodes(),level +1);
    }
  }
  
}
