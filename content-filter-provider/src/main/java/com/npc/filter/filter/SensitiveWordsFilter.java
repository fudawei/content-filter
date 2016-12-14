package com.npc.filter.filter;

import com.npc.filter.SwfContext;

public abstract class SensitiveWordsFilter {
  
  
  
  public SensitiveWordsFilter(SwfContext sc) {
    super();
    this.sc = sc;
  }

  SwfContext sc;
    
  public abstract Resoult filter(String article);
  
  
}
