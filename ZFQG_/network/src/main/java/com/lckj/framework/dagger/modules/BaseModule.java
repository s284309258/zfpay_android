package com.lckj.framework.dagger.modules;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BaseModule {

    private final static Map<String,WeakReference<Object>> sInstanceSet = new HashMap<>();

    //对实例进行弱引用,在不需要时会进行自动销毁
    protected static void weakReferenceInstance(String key,Object object){
        sInstanceSet.put(key,new WeakReference<Object>(object));
    }

    protected static <T> void weakReferenceInstance(T object,Class<T> target){
        sInstanceSet.put(target.getName(),new WeakReference<Object>(object));
    }

    /**获得弱引用对象
     * */
    protected static <T> T getReferenceInstance(Class<T> target){
        WeakReference<Object> instance = sInstanceSet.get(target.getName());
        if(instance == null || instance.get() == null){
            return null;
        }else{
            return (T)instance.get();
        }
    }

    /**获得弱引用对象
     * */
    protected static <T> T getReferenceInstance(String key,Class<T> target){
        WeakReference<Object> instance = sInstanceSet.get(key);
        if(instance == null || instance.get() == null){
            return null;
        }else{
            return (T)instance.get();
        }
    }
}
