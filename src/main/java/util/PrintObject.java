package util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintObject {

    public static void printVO(Object vo, boolean valueChk) {
        Method[] methods = vo.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            if (name != null && "get".equals(name.substring(0, 3))) {
                name = name.replace("get", "");
                name = name.substring(0, 1).toLowerCase() + name.substring(1);

                if ("class".equals(name))
                    continue;
                if ("serialversionuid".equals(name))
                    continue;

                try {
                    Object obj = methods[i].invoke(vo, new Object[] {});
                    String value = obj != null ? "" + methods[i].invoke(vo, new Object[] {}) : "";

                    if (!valueChk) {
                        System.out.println(name + "\t: " + value);
                    } else if (valueChk && value != null) {
                        if (String[].class.equals(methods[i].getReturnType())) {
                            String[] strArr = (String[]) methods[i].invoke(vo, new Object[] {});
                            value = "{";
                            for (int n = 0; n < strArr.length; n++)
                                value = value + (n > 0 ? "," : "") + strArr[n];
                            value = value + "}";

                            System.out.println(name + "\t: " + value);
                        } else {
                            System.out.println(name + "\t: " + value);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        System.out.println("<< END printVO : " + vo.getClass().getName() + " >>");
    }
}
