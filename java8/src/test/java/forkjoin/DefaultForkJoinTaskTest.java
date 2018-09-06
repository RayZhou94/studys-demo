package forkjoin;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;


/**
 * Created by shallowdream on 2018/9/6.
 */
public class DefaultForkJoinTaskTest {

    private List<Integer> taskList;

    private int threshold = 100;

    private Random random = new Random();

    @Before
    public void init(){
        taskList = new ArrayList<>(16000);
        for (int i = 0; i < 16000; i++){
            taskList.add(random.nextInt(1000000));
        }
    }

    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
//        System.out.println("begin.....");
//        for (int i = 0; i < taskList.size(); i++){
//            if (i % 10 == 0){
//                System.out.println();
//            }else {
//                System.out.print(taskList.get(i) + "  ");
//            }
//        }
//
//        System.out.println("taskList end...");


        long forjoinBegin = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AbstractForkJoinTask<List<Integer>> forkJoinTask = new DefaultForkJoinTask(taskList, threshold);
        ForkJoinTask<List<Integer>> future = forkJoinPool.submit(forkJoinTask);
        List<Integer> result = future.get();
        System.out.println(result.size());
        result.forEach(var-> System.out.print(var + "  "));
//        for (int i = 0; i < result.size(); i++){
//            if (i % 10 == 0){
//                System.out.println();
//            }else {
//                System.out.print(result.get(i) + "  ");
//            }
//        }
        forkJoinPool.shutdown();

        long forJoinEnd = System.currentTimeMillis();

        System.out.println("forkjoin consume " + (forJoinEnd -forjoinBegin) + "s");


        taskList.stream().sorted((a,b)->b.compareTo(a)).limit(10).forEach(var-> System.out.print(var + "  "));

        long end = System.currentTimeMillis();
        System.out.println("java8 consume " + (end -forJoinEnd) + "s");

    }

}