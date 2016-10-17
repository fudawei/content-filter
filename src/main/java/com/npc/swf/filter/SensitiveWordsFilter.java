package com.npc.swf.filter;

import com.npc.swf.SwfContext;

public abstract class SensitiveWordsFilter {
  
  
  
  public SensitiveWordsFilter(SwfContext sc) {
    super();
    this.sc = sc;
  }

  SwfContext sc;
    
  public abstract Resoult filter(String article);
  
  
}
