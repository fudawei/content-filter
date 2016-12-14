package com.npc.filter.filter;

import java.util.HashMap;
import java.util.Map;

import com.npc.filter.SwfContext;

public class DFASensitiveWordsFilter extends SensitiveWordsFilter {
  
  private FilterResoultProcess resoultFilter;
  public DFASensitiveWordsFilter(SwfContext sc) {
    super(sc);
    resoultFilter = new FilterResoultProcess();
    
  }

  @Override
  public Resoult filter(String article) {
    boolean current = false;
    if(article == null || "".equals(article)) {
      return new Resoult(0, new HashMap<String,Integer>());
    }
    char[] array = article.toCharArray();
    Map<Character, SensitiveWordsNode> dfaDic = super.sc.getSwfDics();
    int index = 0;
    while(index < array.length) {
      char word = array[index];
      if(!dfaDic.containsKey(word)) {
        index++;
        continue;
      } else {
        SensitiveWordsNode node = dfaDic.get(word);
        StringBuilder sw = new StringBuilder();
        sw.append(word);
        index++;
        while(true) {
          if(index == array.length){
            break;
          }
          word = array[index];
          sw.append(word);
          if(!node.getNodes().containsKey(word)) {
            if(current) {
              index = index - (sw.length()-1);
            }
            break;
          } else {
            node = node.getNodes().get(word);
            index++;
          }
          
          if(node.isEnd()) {
            //System.out.println(sw.toString() + "  1 -" );
            resoultFilter.addsensitiveWord(sw.toString());
            if(current) {
              index = index - (sw.length()-1);
            }
            break;
          }
        }
      }
      
    }
    return resoultFilter.filterResoult();
  }
  
  

 
}
