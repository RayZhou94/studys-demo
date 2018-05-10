package aop.advice;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhiqiu
 */
public class AdviceUtil {

    public static List<Advisor> getAdvisor(Method method, Advised advised, Object proxy){
        List<Advisor> list = new LinkedList<>();

        Advisor advisorThis = new Advisor();
        advisorThis.setTargetMethod(method);
        advisorThis.setTarget(advised.getTarget());
        list.add(advisorThis);

        List<Advisor> advisors = advised.getAdvisors();
        for (Advisor advisor : advisors){
            if (advisor.getTargetMethod().getName().equalsIgnoreCase(method.getName())){
                list.add(advisor);
            }
        }

        return list;
    }
}
