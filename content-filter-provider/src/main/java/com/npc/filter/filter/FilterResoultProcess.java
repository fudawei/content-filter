package com.npc.filter.filter;

import java.util.Map;

import com.npc.filter.utils.CloneHashMap;

public class FilterResoultProcess {
  
  private Integer sensitiverWordNumber=0;
  private CloneHashMap<String ,Integer> wordCount = new CloneHashMap<String, Integer>();
  
  
  public void  addsensitiveWord(String sensitiveWord) {
    sensitiverWordNumber++;
    if(wordCount.containsKey(sensitiveWord)){
      int count = wordCount.get(sensitiveWord);
      wordCount.put(sensitiveWord, count + 1);
    } else {
      wordCount.put(sensitiveWord, 1);
    }
  }
  
  public Resoult filterResoult(){
    Resoult rs = new Resoult(sensitiverWordNumber.intValue(), (Map)wordCount.clone());
    clearCache();
    return rs;
  }
  
  private void clearCache(){
    wordCount.clear();
    sensitiverWordNumber = 0;
  }
  
}
