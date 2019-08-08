package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

public class DbUtil {
	
	private static String CLASS_NAME;
	private static String URL;
	private static String USER;
	private static String PWD;
	private static Vector<Connection> connectionPool = new  Vector<Connection>();//建立一个连接池存储connection
	private static Integer maxSzie = 10;
	private static Integer currentSzie = 0;
	
	static{
		FileInputStream fis = null;
		try{
			String path = DbUtil.class.getResource("/jdbc.properties").getPath();
			
			fis = new FileInputStream(path);
			Properties pps = new Properties();
			pps.load(fis);//让properties对象加载我们写的properties文件
			
			CLASS_NAME = pps.getProperty("jdbc.classname");
			URL = pps.getProperty("jdbc.url");
			USER = pps.getProperty("jdbc.user");
			PWD = pps.getProperty("jdbc.password");
		
			Class.forName(CLASS_NAME);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized static Connection getConnection(){
		try{
			System.out.println(currentSzie + "    "+ maxSzie);//在控制台打印当前的连接池信息
			if(connectionPool.size() > 0){
				Connection con = connectionPool.get(0);
				try{
					con.createStatement().execute("SELECT 1");
				}catch(Exception e){
					connectionPool.remove(0);
					return DriverManager.getConnection(URL, USER, PWD);
				}
				connectionPool.remove(0);
				return con;
			}
			if(currentSzie < maxSzie){
				Connection con = DriverManager.getConnection(URL, USER, PWD);
				currentSzie++;
				return con;
			}
			
			return null;
		}catch(SQLException e){
			return null;
		}
	}
	
	public static void close(Connection con , Statement st , ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			
			if(st != null){
				st.close();
			}
			
			if(con != null){
				connectionPool.add(con);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
