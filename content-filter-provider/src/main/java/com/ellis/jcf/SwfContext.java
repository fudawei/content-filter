package com.ellis.jcf;

import java.util.HashMap;
import java.util.Map;

import com.ellis.jcf.filter.model.CharNode;
import com.ellis.jcf.utils.Config;

public class SwfContext {

  /**
   * 敏感词字典
   */
  private Map<Character, CharNode>  swfDics = new HashMap<Character, CharNode>();
  
  private Config config;  
  
  
  public SwfContext(Map<Character, CharNode>  swfDics) {
    super();
    this.swfDics = swfDics;
  }

  public static SwfContext create(Map<Character, CharNode>  swf){
    return new SwfContext(swf);
  }

  public Map<Character, CharNode> getSwfDics() {
    return swfDics;
  }

  public void setSwfDics(Map<Character, CharNode> swfDics) {
    this.swfDics = swfDics;
  }
  
}
