import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shallowdream on 2018/9/5.
 * 数组任务分组
 */
public class SplitArray {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        List<String> array = new ArrayList<>(100000000);
        for (int i = 0; i < 1000000; i++){
            array.add("array" + i);
        }
        split(array);
        long end = System.currentTimeMillis();

        System.out.println(end - begin);
    }

    public static void split(List<String> array){
        List<List<String>> arrayList = new ArrayList<>();
        int index = 2;
        for (int i = 0; i < array.size(); i += index){
            List<String> newList;
            if (i+index > array.size()){
                newList = array.subList(i, array.size());
            }else {
                newList = array.subList(i, i+index);
            }
            arrayList.add(newList);
        }
        arrayList.parallelStream().forEach(var->print(var));
    }

    public static void print(List array){
        array.forEach(System.out::print);
        System.out.println("    " + Thread.currentThread().getName());
    }

    public static void split2(List<String> array){
        //java8  任务切割
        Stream.iterate(0,n->n+1).limit(array.size()).parallel().forEach(a->{
            List<String> sendList = new ArrayList<>(array).stream().skip(a*2).limit(2).parallel().collect(Collectors.toList());
            print(sendList);
        });
    }
}
