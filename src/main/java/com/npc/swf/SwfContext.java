package com.npc.swf;

import java.util.HashMap;
import java.util.Map;

import com.npc.swf.filter.SensitiveWordsNode;
import com.npc.swf.utils.Config;

public class SwfContext {

  /**
   * 敏感词字典
   */
  private Map<Character, SensitiveWordsNode>  swfDics = new HashMap<Character, SensitiveWordsNode>();
  
  private Config config;  
  
  
  public SwfContext(Map<Character, SensitiveWordsNode>  swfDics) {
    super();
    this.swfDics = swfDics;
  }

  public static SwfContext create(Map<Character, SensitiveWordsNode>  swf){
    return new SwfContext(swf);
  }

  public Map<Character, SensitiveWordsNode> getSwfDics() {
    return swfDics;
  }

  public void setSwfDics(Map<Character, SensitiveWordsNode> swfDics) {
    this.swfDics = swfDics;
  }
  
}
