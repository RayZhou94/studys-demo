package jdbc;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BaseDao<T> implements Mapper<T> {

    private SqlSession sqlSession;


    @Override
    public List<T> selectAll() {
        List<T> list = null;
        try {
            sqlSession = DBUtils.getSession();
            Mapper<T> mapper = sqlSession.getMapper(Mapper.class);
            list = mapper.selectAll();
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return list;
    }

    @Override
    public List<T> selectBy(String var) {
        List<T> list = null;
        try {
            sqlSession = DBUtils.getSession();
            Mapper<T> mapper = sqlSession.getMapper(Mapper.class);
            list = mapper.selectBy(var);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return list;
    }

    @Override
    public T selectOne(Long id) {
        T t = null;
        try {
            sqlSession = DBUtils.getSession();
            Mapper<T> mapper = sqlSession.getMapper(Mapper.class);
            t = mapper.selectOne(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return t;
    }

    @Override
    public void insert(T t) {
        try {
            sqlSession = DBUtils.getSession();
            Mapper<T> mapper = sqlSession.getMapper(Mapper.class);
            mapper.insert(t);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
