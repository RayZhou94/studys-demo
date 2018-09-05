import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shallowdream on 2018/9/5.
 * 数组任务分组
 */
public class SplitArray {

    public static void main(String[] args) {
        List<String> array = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n");
        split(array);
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

    public synchronized static void print(List array){
        array.forEach(System.out::print);
        System.out.println("xxx");
    }

    public static void split2(List<String> array){
        //java8  任务切割
        Stream.iterate(0,n->n+1).limit(array.size()).parallel().forEach(a->{
            List<String> sendList = new ArrayList<>(array).stream().skip(a*2).limit(2).parallel().collect(Collectors.toList());
            print(sendList);
        });
    }
}
