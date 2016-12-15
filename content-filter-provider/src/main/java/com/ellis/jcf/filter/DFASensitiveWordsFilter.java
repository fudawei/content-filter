package com.ellis.jcf.filter;

import java.util.HashMap;
import java.util.Map;

import com.ellis.jcf.SwfContext;
import com.ellis.jcf.filter.model.CharNode;
import com.ellis.jcf.filter.model.Resoult;

public class DFASensitiveWordsFilter extends WordsFilter {
  
  private WordCounter resoultFilter;
  public DFASensitiveWordsFilter(SwfContext sc) {
    super(sc);
    resoultFilter = new WordCounter();
    
  }

  @Override
  public Resoult filter(String article) {

    boolean current = false;
    if(article == null || "".equals(article)) {
      return new Resoult(0, new HashMap<String,Integer>());
    }

    Map<Character, CharNode> dfaDic = super.sc.getSwfDics();
    char[] array = article.toCharArray();

    int index = 0;
    while(index < array.length) {

      char word = array[index];
      if(!dfaDic.containsKey(word)) {
        index++;
        continue;
      } else {
        CharNode node = dfaDic.get(word);
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
            resoultFilter.addSensitiveWord(sw.toString());
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
