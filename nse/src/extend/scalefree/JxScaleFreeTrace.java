package extend.scalefree;

import java.sql.Connection;
import java.util.Date; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {

    //�����ݿ�
	public void opendatabase(){
		
		Connection con = null;
		Statement sta = null;
		//System.out.println("dbconnect.createnodetable() is ok");
		try {
			if (con != null)
				con.close();
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); // ������� 

			con = DriverManager.getConnection(url, "sa", "");
			sta = con.createStatement();
		} catch (SQLException e) {
			con = null;
			sta = null;
		} catch (ClassNotFoundException e) {
			con = null;
			sta = null;
		}
	}
	
	//�ر����ݿ�
    public void CloseDatabase() 
    	{
    	    Connection con = null;
    		Statement sta = null;
    		try {
    			if (sta != null) {
    				sta.executeUpdate("SHUTDOWN"); // SHUTDOWN�÷� 
    				sta.close();
    			}
    			if (con != null) {
    				con.close();
    			}
    		} catch (SQLException e) {
    			sta = null;
    			con = null;
    		}
    	}
    	
    
    //�������ݿ�
    	public void CreateDataBase() 
    	{  
    		Connection con=null;
            Statement sta=null;
    		try {
    			assert ((con != null) && !(con.isClosed())); // �ǿ���δ�ر�
    			assert (sta != null); // �ǿ�
    			Date   date=new   Date(); 
    			String   newdatabase=date.toString(); 
    			String sql1 = "create   database"+newdatabase;
    			sta.executeUpdate(sql1);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
 
     //�����ڵ��
    	public void CreateNodeTable()
    	{   
    		Connection con=null;
            Statement sta=null;
    		try{
    			assert ((con!= null) && !(con.isClosed())); //�ǿ���δ�ر�
    			assert (sta != null); //�ǿ�
    			String sql2 = "create table Node_topology((int node_id,int loc_x,int loc_y,int tx.power)";// �����ڵ��
    			sta.executeUpdate(sql2);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    
     // �����߱�
    	public void CreateEdgeTable() 
    	{  
    		Connection con=null;
            Statement sta=null;
    		try {
    			assert ((con != null) && !(con.isClosed())); // �ǿ���δ�ر�
    			assert (sta != null); // �ǿ�
    			String sql3 = "create table Edge_topology((int edge_id,int node_from,int node_to)";// �����߱�
    			sta.executeUpdate(sql3);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
	
	
	public void save_nodetopo()  //�����ṹ
	{
	   	
	}
	public void save_edgetopo()  //����߽ṹ
	{
	 	
	}
	public void trace_node()     //��¼�������(���ݷ���)
	{
		 
	}
	public void trace_edge()     //��¼�߷��Ͱ�������(���ݷ���)
	{
		 
	}
	
	public void load_nodetopo()  //���ؽڵ�ṹ
	{
		
	}
	public void load_edgetopo()  //���ر߽ṹ
	{
	  	
	}
	
}
