package jdbc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBUtils {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSession sqlSession;

    static {
        init();
    }
    public static void init(){
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSession(){
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    public void close(){
        sqlSession.close();
    }
}
