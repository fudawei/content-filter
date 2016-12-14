package com.ellis.jcf.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CloneHashMap<Ｋ,V> extends HashMap<Ｋ,V>{
  
  public CloneHashMap() {
    super();
  }

  public CloneHashMap(int initialCapacity) {
    super(initialCapacity);
  }

  @SuppressWarnings("rawtypes")
  public Object clone() {
    Map target = new HashMap();
    for (Iterator keyIt = this.keySet().iterator(); keyIt.hasNext();) {
      Object key = keyIt.next();
      target.put(key, this.get(key));
    }
    return target;
  }
}
