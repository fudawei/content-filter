package com.ellis.jcf.filter;

import com.ellis.jcf.filter.model.CharNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <summary>
 * 敏感词树
 * </summary
 * @author fudw
 */
public class SensitiveWordsFactory {
  
  public static Map<Character, CharNode> build(List<String> words){
    String[] wordsArray = words.toArray(new String[words.size()]);
    return build(wordsArray);
  }


  public static Map<Character, CharNode> build(String[] sensitiveWords){
    if(sensitiveWords == null && sensitiveWords.length == 0) {
      return new HashMap<Character, CharNode>();
    }
    Map<Character, CharNode>  swnMap = new HashMap<Character, CharNode>();
    for (int i = 0; i < sensitiveWords.length; i++) {
      String word = sensitiveWords[i];
      
      if("".equals(word)) {
        break;
      }
      char[] charArray = word.toCharArray();
      char first = charArray[0];
      CharNode node = null;
      if(swnMap.containsKey(first)) {
        node = swnMap.get(first);
      } else {
        node = new CharNode();
        node.setValue(first);
        node.setHead(true);
        Map<Character, CharNode> nodes1 = new HashMap<Character, CharNode>();
        node.setNodes(nodes1);
        swnMap.put(first, node);
      }
      
      int index = 1;
      Map<Character, CharNode> tmpNodes = node.getNodes();
      
      while(index < charArray.length) {
        if(tmpNodes != null) {
          if(tmpNodes.containsKey(charArray[index])) {
            tmpNodes = tmpNodes.get(charArray[index]).getNodes();
            index ++; 
            continue;
          } else {
            CharNode current = new CharNode();
            Map<Character, CharNode> nodes = new HashMap<Character, CharNode>();
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
