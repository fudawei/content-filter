package com.ellis.jcf.filter;

import com.ellis.jcf.SwfContext;

public abstract class SensitiveWordsFilter {
  
  
  
  public SensitiveWordsFilter(SwfContext sc) {
    super();
    this.sc = sc;
  }

  SwfContext sc;
    
  public abstract Resoult filter(String article);
  
  
}
