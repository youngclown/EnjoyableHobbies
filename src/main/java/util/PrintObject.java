package util;

import java.lang.reflect.Method;

public class PrintObject {

    public static void printVO(Object vo, boolean valueChk) {
        Method[] methods = vo.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name != null && "get".equals(name.substring(0, 3))) {
                name = name.replace("get", "");
                name = name.substring(0, 1).toLowerCase() + name.substring(1);

                if ("class".equals(name))
                    continue;
                if ("serialversionuid".equals(name))
                    continue;

                try {
                    Object obj = method.invoke(vo);
                    String value = obj != null ? "" + method.invoke(vo) : "";

                    if (!valueChk) {
                        System.out.println(name + "\t: " + value);
                    } else if (valueChk) {
                        if (String[].class.equals(method.getReturnType())) {
                            String[] strArr = (String[]) method.invoke(vo, new Object[]{});
                            value = "{";
                            for (int n = 0; n < strArr.length; n++)
                                value = value + (n > 0 ? "," : "") + strArr[n];
                            value = value + "}";

                            System.out.println(name + "\t: " + value);
                        } else {
                            System.out.println(name + "\t: " + value);
                        }
                    }
                } catch (Exception ignore) {

                }
            }
        }
        System.out.println("<< END printVO : " + vo.getClass().getName() + " >>");
    }
}
