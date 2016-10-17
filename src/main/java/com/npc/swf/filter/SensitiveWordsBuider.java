package com.npc.swf.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <summary>
 * 敏感词树
 * </summary
 * @author fudw
 */
public class SensitiveWordsBuider {
  
  public static List<SensitiveWordsNode> build(List<String> words){
    
    return null;
  }
  
  public static Map<Character, SensitiveWordsNode> build(String[] sensitiveWords){
    if(sensitiveWords == null && sensitiveWords.length == 0) {
      return new HashMap<Character, SensitiveWordsNode>();
    }
    Map<Character, SensitiveWordsNode>  swnMap = new HashMap<Character, SensitiveWordsNode>();
    for (int i = 0; i < sensitiveWords.length; i++) {
      String word = sensitiveWords[i];
      
      if("".equals(word)) {
        break;
      }
      char[] charArray = word.toCharArray();
      char first = charArray[0];
      SensitiveWordsNode node = null;
      if(swnMap.containsKey(first)) {
        node = swnMap.get(first);
      } else {
        node = new SensitiveWordsNode();
        node.setValue(first);
        node.setHead(true);
        Map<Character, SensitiveWordsNode> nodes1 = new HashMap<Character, SensitiveWordsNode>();
        node.setNodes(nodes1);
        swnMap.put(first, node);
      }
      
      int index = 1;
      Map<Character, SensitiveWordsNode> tmpNodes = node.getNodes();
      
      while(index < charArray.length) {
        if(tmpNodes != null) {
          if(tmpNodes.containsKey(charArray[index])) {
            tmpNodes = tmpNodes.get(charArray[index]).getNodes();
            index ++; 
            continue;
          } else {
            SensitiveWordsNode current = new SensitiveWordsNode();
            Map<Character, SensitiveWordsNode> nodes = new HashMap<Character, SensitiveWordsNode>();
            current.setNodes(nodes);
            current.setValue(charArray[index]);
            if(index == charArray.length - 1) {
              current.setEnd(true);
            }
            tmpNodes.put(charArray[index], current);
            tmpNodes = current.getNodes();
          }
          
        }
        index ++;     
      } 
    }
    return swnMap;
  }
  
}
