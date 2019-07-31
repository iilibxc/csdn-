package cn.easyBuy.utils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

public class DBUtil {
	
private static DataSource dataSource;	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;	
	static {
		init();
	}
	
	public static void init(){
		Properties params=new Properties();
		String configFile = "database.properties";
		InputStream is=DBUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver=params.getProperty("driver");
		url=params.getProperty("url");
		user=params.getProperty("username");
		password=params.getProperty("password");
	}   
	
	//生成connection
	public static Connection getConnection() {
    	 Connection connection=null;
    	 try {
			Class.forName(driver);
			 connection= DriverManager.getConnection(url,user ,password);    	 			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	 return connection;
     }

 



 private static void close(Connection conn)  {
       if(conn!=null) {
	  try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }	 
 }
 
 
 private static void close(Statement statement) {
	 if(statement!=null) {
	 try {
		statement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 }
 }
 
 private static void close(ResultSet rs) {
	if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 
private  static void close(Connection connection ,Statement statement) {
	close(connection);
	close(statement);
}


private static void close(Connection connection,Statement statement,ResultSet resultSet) {
	close(connection);
	close(statement);
	close(resultSet);
}


/**
 * 更新操作
 * @param sql 执行的SQL语句
 * @param param 对应的参数列表
 * @return true 更新成功， false 更新失败
 */
public static boolean update(String sql, Object[] param){

    PreparedStatement preparedStatement = null;
    Connection connection = getConnection();
    try {
        preparedStatement = connection.prepareStatement(sql);

        if (settingParams(preparedStatement, param) == false){
            return false;
        }

        int result = preparedStatement.executeUpdate();
        if (result > 0){
            return true;
        }
        return false;

    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        close(connection, preparedStatement);
    }
    return false;
}


/**
 * 设置参数
 * @param preparedStatement Statement对象
 * @param param 参数列表
 * @return
 * @throws SQLException
 */
private static boolean settingParams(PreparedStatement preparedStatement, Object[] param) throws SQLException {

    if (param != null && param.length > 0){
        // 获取ParameterMetaData
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        // 获得SQL中占位符个数
        int paramCount = parameterMetaData.getParameterCount();

        // 占位符个数与参数个数不一致，返回false表示出错
        if (paramCount != param.length){
            return false;
        }
        // 设置对应的参数信息
        for (int i = 0; i < paramCount; i++){
            preparedStatement.setObject(i+1, param[i]);
        }
    }
    return true;
}


/**
 * 获取单个Bean
 * @param sql 执行SQL语句
 * @param param 对应的参数列表
 * @param clazz 所要获取的对象的类型
 * @param <T>  对象的类型
 * @return
 */
public static <T> T queryForBean(String sql, Object[] param, Class<T> clazz){

    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {

        preparedStatement = connection.prepareStatement(sql);

        if (settingParams(preparedStatement, param) == false){
            return null;
        }

        resultSet = preparedStatement.executeQuery();
        if (resultSet == null){
            return null;
        }

        if (resultSet.next()){
            // 利用反射机制创建对象
            T data = clazz.newInstance(); 
            // 获得ResultSetMetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 获得列的数量
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 0; i < columnCount; i++){
                // 获得对应的列的名称
                String name = resultSetMetaData.getColumnName(i + 1);
                // 获得对应的列的值
                Object rData = resultSet.getObject(name);
                // 使用BeanUtils工具对属性进行注入
                BeanUtils.copyProperty(data, name, rData);
            }
         return data;

        }else {
            return null;
        }

    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    } finally {
        close(connection, preparedStatement, resultSet);
    }
    return null;
}



/**
 * 获取Bean并且封装成List
 * @param sql 执行SQL语句
 * @param param 对应的参数列表
 * @param clazz 所要获取的对象的类型
 * @param <T>  对象的类型
 * @return list
 */
public static <T> List<T> queryForList(String sql, Object[] param, Class<T> clazz){

    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {

        preparedStatement = connection.prepareStatement(sql);

        if (settingParams(preparedStatement, param) == false){
            return null;
        }

        resultSet = preparedStatement.executeQuery();
        if (resultSet == null){
            return null;
        }
        List<T> results = new ArrayList<>();

        while (resultSet.next()){
            // 创建对象
            T data = clazz.newInstance();
            // 获得ResultSetMetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 获得列的数量
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 0; i < columnCount; i++){
                // 获得对应的列的名称
                String name = resultSetMetaData.getColumnName(i + 1);
                // 获得对应的列的值
                Object rData = resultSet.getObject(name);
                // 使用BeanUtils工具对属性进行注入
                BeanUtils.copyProperty(data, name, rData);
               
            }
            results.add(data);

        }
        return results;

    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    } finally {
        close(connection, preparedStatement, resultSet);
    }
    return null;
}
}
 