package aop.aspect;

import aop.advice.Advisor;

import java.util.List;

/**
 * @author zhiqiu
 */
public class MethodInvocation {

    private List<Advisor> advisors;

    private int count = 0;

    public MethodInvocation(){

    }

    public MethodInvocation(List<Advisor> advisors){
        this.advisors = advisors;
    }
    public void proceed(){
        AspectJAdvice aspectJAdvice = null;
        if (count < advisors.size()){
            Advisor advisor = advisors.get(count++);
            aspectJAdvice = newAspectJAdvice(advisor);
            aspectJAdvice.invoke(this);
        }
    }

    public AspectJAdvice newAspectJAdvice(Advisor advisor){
        if (advisor.isBefore()){
            return new AspectJBeforeAdvice(advisor.getMethod(), advisor.getAspect());
        }else if (advisor.isAfter()){
            return new AspectJAfterAdvice(advisor.getMethod(), advisor.getAspect());
        }else {
            return new AspectJTargetAdvice(advisor.getTargetMethod(), advisor.getTarget());
        }
    }
}
