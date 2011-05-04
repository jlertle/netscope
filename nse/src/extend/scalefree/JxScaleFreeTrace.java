package extend.scalefree;

import java.sql.Connection;
import java.util.Date; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {
    
	Connection con=null;
	
	Statement node_sta=null;
	
	Statement edge_sta=null;
    
	//�����ݿ�
	public Connection Open_Database(String database)
	{
		try{
			 Class.forName("org.hsqldb.jdbc.JDBCDriver");  //������� 

			 con= DriverManager.getConnection("jdbc:hsqldb:mem:score", "sa", "");
	       } 
		     catch (SQLException e){
              
		       e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		    	 e.printStackTrace();
           }     
		return con;     
	}

    	
     //�����ڵ������ڵ�ṹ
    
      public void Save_NodeTopo(Connection con,JxScaleFreeNodeCollection m_nodes)
      {   
    	   try{
    			 node_sta=con.createStatement();
    	    		
	                     // assert ((con!= null) && !(con.isClosed())); //�ǿ���δ�ر�
    	     	         // assert (node_sta != null); //�ǿ�
    			
    			long cur_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(cur_time);  
     			
     			String node_tablename = "nodetable" + str_time;  
    			
    			String create_node = "create table" + node_tablename + "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";
    			
    			node_sta.executeUpdate(create_node);       //�����ڵ�ṹ��         
    			
    			   int i;
    			
    			   JxScaleFreeNode node;       //δʵ������������
    			
    			   for(i=0;i<m_nodes.count();i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.get_nodeid()); //ת��Ϊ�ַ���
    			
    			        String loc_x= Integer.toString(node.get_x());    
    			  
    			        String loc_y= Integer.toString(node.get_y());     
    			
    		          //������ڵ�ṹ��
    			       
    			        String insert_nodetable = "Insert into"+ node_tablename +"(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        node_sta.executeUpdate(insert_nodetable);	        
    		    }
    			 
    			   node_sta.close();
			        
			       con.close();
    		} 
    		catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    	
    	}
    
     //�����߱�����
    	
    	public void Save_EdgeTopo(Connection con,JxScaleFreeEdgeCollection m_edges) 
    	{  
    	  try {
    			edge_sta=con.createStatement();
    	     /**		
    			assert ((con != null) && !(con.isClosed())); // �ǿ���δ�ر�
    			assert (sta != null); // �ǿ�
    	     */

    			long cur_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(cur_time);  
     			
     			String edge_tablename = "edgetable" + str_time;  
    			
    			String create_edge = "create table" + edge_tablename + "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";
    			
    			node_sta.executeUpdate(create_edge);       //�����߽ṹ��      
    			
    			int i;
    			
    			JxScaleFreeEdge edge= new JxScaleFreeEdge();
    			
    			for(i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.get_edgeid());  //ת��Ϊ�ַ���
    			
    			     String node_from=Integer.toString(edge.get_nodefrom());    
    			
    			     String node_to=Integer.toString(edge.get_nodeto()); 
    			
    			     String insert_edgetable = "Insert into"+ edge_tablename +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				
    			     edge_sta.executeUpdate(insert_edgetable);	
    			}
    			 
    			edge_sta.close();
    		
    			con.close();
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    		}
    	}	
	
	public void load_nodetopo()  //���ؽڵ�ṹ
	{
		
	}
	public void load_edgetopo()  //���ر߽ṹ
	{
	  	
	}
	
}
