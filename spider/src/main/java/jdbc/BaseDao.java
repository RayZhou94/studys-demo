package jdbc;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BaseDao<T> {

    private static Mapper mapper;

    private static SqlSession sqlSession;

    static {
        try {
            sqlSession = DBUtils.getSession();
            mapper = sqlSession.getMapper(Mapper.class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<T> selectAll() {
        List<T> list = null;
        list = mapper.selectAll();
        sqlSession.commit();
        return list;
    }

    public List<T> selectBy(String var) {
        List<T> list = null;
        list = mapper.selectBy(var);
        sqlSession.commit();
        return list;
    }

    public T selectOne(Long id) {
        T t = null;
        t = (T) mapper.selectOne(id);
        sqlSession.commit();
        return t;
    }

    public void insert(T t) {
        try {
            sqlSession = DBUtils.getSession();
            mapper = sqlSession.getMapper(Mapper.class);
            mapper.insert(t);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
