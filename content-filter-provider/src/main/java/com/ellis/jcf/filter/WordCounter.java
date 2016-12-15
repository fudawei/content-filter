package com.ellis.jcf.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ellis.jcf.filter.model.Resoult;
import com.ellis.jcf.utils.CloneHashMap;

public class WordCounter implements ResoultProcesser{
  
  private Integer sensitiverWordNumber=0;
  private CloneHashMap<String ,Integer> wordCount = new CloneHashMap<String, Integer>();
  private Map<String, List<Integer>> wordPosition = new HashMap<String, List<Integer>>();
  
  
  public void  addSensitiveWord(String sensitiveWord) {
    sensitiverWordNumber++;
    if(wordCount.containsKey(sensitiveWord)){
      int count = wordCount.get(sensitiveWord);
      wordCount.put(sensitiveWord, count + 1);
    } else {
      wordCount.put(sensitiveWord, 1);
    }
  }

  public void  addSensitiveWord(String sensitiveWord, int index) {
    addSensitiveWord(sensitiveWord);

    List<Integer> position = null;
    if(wordPosition.containsKey(sensitiveWord)) {
      position = wordPosition.get(sensitiveWord);
      if (position != null) {
        position.add(index);
      }
    } else {
      position = new ArrayList<Integer>();
      position.add(index);
    }
    wordPosition.put(sensitiveWord, position);
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
