package com.ellis.jcf.filter;

import com.ellis.jcf.SwfContext;
import com.ellis.jcf.filter.model.Resoult;

public abstract class WordsFilter {
  
  
  
  public WordsFilter(SwfContext sc) {
    super();
    this.sc = sc;
  }

  SwfContext sc;
    
  public abstract Resoult filter(String article);
}
