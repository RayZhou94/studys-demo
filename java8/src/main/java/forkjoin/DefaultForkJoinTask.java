package forkjoin;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shallowdream on 2018/9/6.
 */
public class DefaultForkJoinTask extends AbstractForkJoinTask<List<Integer>> {

    /**
     * 阈值
     */
    private int threshold;

    /**
     * 待拆分list
     */
    private List<Integer> list;

    protected DefaultForkJoinTask(List<Integer> list, int threshold){
        this.list = list;
        this.threshold =  threshold;
    }

    /**
     * 任务切割，任意切为几份
     * @return
     */
    @Override
    public List<Integer> compute() {
        List<Integer> result;
        if (list.size() < threshold){
            result = process(list);
        }else {
            int middle = list.size() / 3;
            AbstractForkJoinTask<List<Integer>> left = new DefaultForkJoinTask(list.subList(0, middle), threshold);
            AbstractForkJoinTask<List<Integer>> var = new DefaultForkJoinTask(list.subList(middle, 2*middle), threshold);
            AbstractForkJoinTask<List<Integer>> right = new DefaultForkJoinTask(list.subList(2*middle, list.size()), threshold);
            left.fork();
            right.fork();
            var.fork();
            List<Integer> join = left.join();
            join.addAll(right.join());
            join.addAll(var.join());
            result = join;
        }
        //永远只要10个
//        result.stream().sorted((a,b)->b.compareTo(a)).limit(10).collect(Collectors.toList());
        return result.stream().sorted((a,b)->b.compareTo(a)).limit(10).collect(Collectors.toList());
    }

    private List<Integer> process(List<Integer> taskList){
        //获取列表中的前10个最大的数
        return taskList.stream().sorted((a,b)->b.compareTo(a)).limit(10).collect(Collectors.toList());
    }

}
