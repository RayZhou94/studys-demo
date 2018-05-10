package jdbc;

import java.util.List;

public interface Mapper<T> {

    List<T> selectAll();

    List<T> selectBy(String var);

    T selectOne(Long id);

    void insert(T t);
}
