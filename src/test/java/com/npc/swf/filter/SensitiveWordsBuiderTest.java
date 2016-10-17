package com.npc.swf.filter;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.npc.swf.filter.SensitiveWordsBuider;
import com.npc.swf.filter.SensitiveWordsNode;

public class SensitiveWordsBuiderTest {

  @Test
  public void testBuild(){
    String[] strArr = {"中国人","中国地图","中国地图版本","中国政府","政府官员","文化大革命","三体文明","黑暗森林","大跃进","三体世界"};
    Map<Character, SensitiveWordsNode>  swnMap = SensitiveWordsBuider.build(strArr);
    p(swnMap, 0);
  }
  
  private void p(Map<Character, SensitiveWordsNode>  swnMap,int level){
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
