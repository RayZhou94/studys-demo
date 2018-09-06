package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by shallowdream on 2018/9/5.
 */
public abstract class AbstractForkJoinTask<T> extends RecursiveTask<T> {

    protected abstract T compute();
}
