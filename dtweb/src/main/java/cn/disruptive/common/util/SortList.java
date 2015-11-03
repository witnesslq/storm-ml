package cn.disruptive.common.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
/**
* 通用排序
*/
public class SortList{
	public static<E> void Sort(List<E> list, final String method, final String sort){
        Collections.sort(list, new Comparator<E>() {            
            public int compare(E a, E b) {
                int ret = 0;
                try{
                    Method m1 = a.getClass().getMethod(method);
                    Method m2 = b.getClass().getMethod(method);
                    if(sort != null && "desc".equals(sort))//倒序
                        ret = m2.invoke(b).toString().compareTo(m1.invoke(a).toString());    
                    else//正序
                        ret = m1.invoke(a).toString().compareTo(m2.invoke(b).toString());
                }catch(NoSuchMethodException ne){
                    ne.printStackTrace();
                }catch(IllegalAccessException ie){
                    ie.printStackTrace();
                }catch(InvocationTargetException it){
                    it.printStackTrace();
                }
                return ret;
            }
         });
    }
}
