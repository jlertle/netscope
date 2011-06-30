package extend.scalefree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JxScaleFreeTrace {

JxScaleFreeNodeCollection m_nodes=new JxScaleFreeNodeCollection();  //(���ڱ����Ľ��) 
	
	JxScaleFreeEdgeCollection m_edges=new JxScaleFreeEdgeCollection();  //(���ڱ���ߵĽ��)
    
	JxScaleFreeNodeCollection m_nodesload=new JxScaleFreeNodeCollection(); 
	
	JxScaleFreeEdgeCollection m_edgesload=new JxScaleFreeEdgeCollection();
	
	JxScaleFreeNode node= null; 
		
	JxScaleFreeEdge edge= null;
	
	
	ResultSet res =null;
	
	Boolean evertra_node=false;  //һ��Ҫ���ɳ�Ա����
	
	boolean  evertra_edge=false;
	
   String tracenode_tablename=null;
	 
   String traceedge_tablename=null; 
	
   Connection con = null;

	Statement sta = null;

	// �����ݿ�
	public Statement openDatabase(String database) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:mem:score", "sa", "");
			sta = con.createStatement();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sta;
	}

	// �����ڵ������ڵ�ṹ
	public void Save_NodeTopo(Statement sta) {

		JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();
		try {

			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String node_tablename = "nodetable" + str_time;

			String create_node = "create table" + node_tablename
					+ "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";

			sta.executeUpdate(create_node); // �����ڵ�ṹ��

			int i;

			JxScaleFreeNode node; // δʵ������������

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id()); // ת��Ϊ�ַ���

				String loc_x = Integer.toString(node.x());

				String loc_y = Integer.toString(node.y());

				// ������ڵ�ṹ��

				String insert_nodetable = "Insert into" + node_tablename
						+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","
						+ loc_x + "," + loc_y + ")";

				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// �����߱�����
	public void Save_EdgeTopo(Statement sta)

	{
		try {
			long sys_time = System.currentTimeMillis();

			String str_time = String.valueOf(sys_time);

			String edge_tablename = "edge table" + str_time;

			String create_edge = "create table" + edge_tablename
					+ "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";

			sta.executeUpdate(create_edge); // �����߽ṹ��

			int i;

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); // ת��Ϊ�ַ���

				String node_from = Integer.toString(edge.nodefrom());

				String node_to = Integer.toString(edge.nodeto());

				String insert_edgetable = "Insert into" + edge_tablename
						+ "(EDGEID,NODE_FROM,NODE_TO) VALUES (" + edge_id + ","
						+ node_from + "," + node_to + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void Load_Nodetopo(Statement sta, String tablename) { // ���ؽڵ�ṹ

		try {

			String select_nodetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_nodetopo);

			int i = 0;

			JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();

			while (r.next()) {

				m_nodes.get(i).set_id(Integer.parseInt(r.getString(1))); // �õ��ڵ�
																			// ���

				m_nodes.get(i).set_x((Integer.parseInt(r.getString(1)))); // �õ��ڵ�X����

				m_nodes.get(i).set_y((Integer.parseInt(r.getString(1)))); // �õ��ڵ�y����

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void Load_Edgetopo(Statement sta, String tablename) {// ���ر߽ṹ

		try {

			String select_edgetopo = "select * from " + tablename;

			ResultSet r = sta.executeQuery(select_edgetopo);

			int i = 0;

			JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();

			while (r.next()) {

				m_edges.get(i).set_id((Integer.parseInt(r.getString(1)))); // �õ��ڵ�
																			// ���

				m_edges.get(i).set_nodefrom((Integer.parseInt(r.getString(1)))); // �õ��ڵ�X����

				m_edges.get(i).set_nodeto((Integer.parseInt(r.getString(1)))); // �õ��ڵ�y����

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trace_node(Statement sta, JxScaleFreeNodeCollection m_nodes,
			int time) {// ���������

		try {
			// ���Կ��Ƿ���sta��Ϊ����

			boolean tracenode_table = true;

			String tracenode_tablename = null; // һ��Ҫ����ֵ��������

			if (tracenode_table == true) // ��ֻ֤����һ�νڵ����ݱ�
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				tracenode_tablename = "trace node packet " + str_time; // �ڵ����ݱ���

				String create_node = "create table" + tracenode_tablename
						+ "(CUR_TIME INTERGER, NODEID INTERGER,LENGTH INTEGER)";

				sta.executeUpdate(create_node); // �����ڵ�ṹ��

				tracenode_table = false;
			}

			int i;

			JxScaleFreeNode node; // δʵ������������

			String cur_time = String.valueOf(time);

			for (i = 0; i < m_nodes.count(); i++) {

				node = m_nodes.get(i);

				String node_id = Integer.toString(node.id()); // ת��Ϊ�ַ���(�ڵ��)

				String length = Integer.toString(node.length()); // (���ĳ���)

				// ������ڵ����ݣ�

				String trace_node = "Insert into" + tracenode_tablename
						+ "(CUR_TIME,NODEID,LENGTH) VALUES (" + cur_time + ","
						+ node_id + "," + length + ")";

				sta.executeUpdate(trace_node);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void trace_edge(Statement sta, JxScaleFreeEdgeCollection m_edges,
			int time) { // ���������

		try {

			boolean traceedge_table = true;

			String traceedge_tablename = null; // һ��Ҫ����ֵ��������

			if (traceedge_table == true) // ��ֻ֤����һ�νڵ����ݱ�
			{
				long sys_time = System.currentTimeMillis();

				String str_time = String.valueOf(sys_time);

				traceedge_tablename = "trace edge packet" + str_time; // �����ݱ���

				String trace_edge = "create table"
						+ traceedge_tablename
						+ "(CUR_TIME INTERGER, EDGEID INTERGER,PACKETSUM INTEGER)";

				sta.executeUpdate(trace_edge); // �����ڵ�ṹ��

				traceedge_table = false;
			}

			int i;

			String cur_time = String.valueOf(time);

			JxScaleFreeEdge edge = new JxScaleFreeEdge();

			for (i = 0; i < m_edges.count(); i++) {

				edge = m_edges.get(i);

				String edge_id = Integer.toString(edge.id()); // ת��Ϊ�ַ���

				String packet_sum = Integer.toString(edge.get_packetsum());

				String insert_edgetable = "Insert into" + traceedge_tablename
						+ "(CUR_TIME,EDGEID,PACKETSUM) VALUES (" + cur_time
						+ "," + edge_id + "," + packet_sum + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//�����ݿ�
	public Statement Open_Database()
	{
		try{
			 Class.forName("org.hsqldb.jdbcDriver"); //������� 

			 con= DriverManager.getConnection("jdbc:hsqldb:file:netscope;shutdown=true", "sa", ""); //������Ϊnetscope�����ݿ�
			 
			 sta=con.createStatement();  //��������
	       } 
		     catch (SQLException e){
              
		      e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		      e.printStackTrace();
           }     
		return sta;    //�������� 
	}    	
    
	//�����ڵ������ڵ�ṹ  
	
    public void Save_NodeTopo()
    {   
    	try{
    			long sys_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(sys_time);  
     			
     			String node_topo = "nodetopo" + str_time;  
    			
    			String create_node = "create table" + node_topo + "(NODEID INTERGER,LOC_X INTERGER,LOC_Y INTEGER)";
    			
    			sta.executeUpdate(create_node);       //�����ڵ�ṹ��         
    			
    			   for(int i=0;i<m_nodes.count();i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.id()); //ת��Ϊ�ַ���
    			
    			        String loc_x= Integer.toString(node.x());    
    			  
    			        String loc_y= Integer.toString(node.y());     
    			   			       
    			        String insert_nodetable = "Insert into"+ node_topo +"(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        sta.executeUpdate(insert_nodetable);	
    			        
    			        sta.close();
    			   }
		   } 
    	   catch (SQLException e){
               
           	e.printStackTrace();
           
           }
      }
  
     //�����߱�����
    public void Save_EdgeTopo()
    {  
    	  try {
    			long sys_time = System.currentTimeMillis();
     			
     			String str_time=String.valueOf(sys_time);  
     			
     			String edge_topo = "edgetopo" + str_time;  
    			
    			String create_edge = "create table" + edge_topo + "(EDGEID INTERGER,NODE_FROM INTERGER,NODE_TO INTEGER)";
    			
    			sta.executeUpdate(create_edge);       //�����߽ṹ��      
    				
    			for(int i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.id());  //ת��Ϊ�ַ���
    			
    			     String node_from=Integer.toString(edge.nodefrom());    
    			
    			     String node_to=Integer.toString(edge.nodeto()); 
    			
    			     String insert_edgetable = "Insert into"+ edge_topo +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				
    			     sta.executeUpdate(insert_edgetable);	
    			     
    			     sta.close();
    			}
    			
    		}  catch (SQLException e){
                
            	e.printStackTrace();    
            }    
    	}	
	
    public void Load_Nodetopo(String node_tablename) {  //���ؽڵ�ṹ
   
   try{
    	
	   String select_nodetopo="select * from"+ node_tablename ; 
    		
       res =sta.executeQuery(select_nodetopo);
       
       int i=0;
    
       while(res.next()){
     
    	   m_nodesload.get(i++).set_id(Integer.parseInt(res.getString(1)));  //�õ��ڵ� ���
  
    	   m_nodesload.get(i++).set_x((Integer.parseInt(res.getString(2))));     //�õ��ڵ�X����
    	   
    	   m_nodesload.get(i++).set_y((Integer.parseInt(res.getString(2))));     //�õ��ڵ�y����
    	
      }
       res.close();
       
       sta.close();
       
      }catch(Exception e){
    	 
    	  e.printStackTrace();
      }	
	}
	
	public void Load_Edgetopo(String edge_tablename)  {//���ر߽ṹ

		try{
	    	
			   String select_edgetopo="select * from"+edge_tablename ; 
		    		
		       res =sta.executeQuery(select_edgetopo);
		       
		       int i=0;
		  
		       while(res.next()){

		    	   m_edgesload.get(i++).set_id((Integer.parseInt(res.getString(1)))); //�õ��ڵ� ���

		    	   m_edgesload.get(i++).set_nodefrom((Integer.parseInt(res.getString(2))));    //�õ��ڵ�X����
		    	   
		    	   m_edgesload.get(i++).set_nodeto((Integer.parseInt(res.getString(3))));     //�õ��ڵ�y����
		    	
		    	   res.close();
		    	   
		    	   sta.close();
		      }
		      }catch(Exception e){
		    	 
		    	  e.printStackTrace();
		    	  
		      }   
	}
	
	public void Trace_Node(int time){ //���������( time: �� �� �� �� )	   	
	try{
			  //���Կ��Ƿ���sta��Ϊ����
			 
			 // String tracenode_tablename = null;  //һ��Ҫ����ֵ��
			 
			   //��ֻ֤����һ�νڵ����ݱ�
			      if( evertra_node==false){  //��֤ÿ�νڵ����һ��
			    	  
				        long sys_time = System.currentTimeMillis();
		 			
	 			        String str_time=String.valueOf(sys_time);  
	 			
	 			        tracenode_tablename = "trace node packet" + str_time; //�ڵ����ݱ���
				
				        String create_node = "create table" +tracenode_tablename + "(CUR_TIME INTERGER, NODEID INTERGER,LENGTH INTEGER)";
				
				        sta.executeUpdate(create_node);  //�����ڵ�ṹ��       
				        
			            evertra_node = true;
			      }     
			   
			   String cur_time= String.valueOf(time); 
			
			   for(int i=0;i<m_nodes.count();i++){				
				
				    node= m_nodes.get(i);
				
			        String node_id=Integer.toString(node.id()); //ת��Ϊ�ַ���(�ڵ��)
			
			        String length= Integer.toString(node.get_length()); //(���ĳ���)
			     
		          //������ڵ����ݣ�
			       
			        String trace_node = "Insert into"+ tracenode_tablename +"(CUR_TIME,NODEID,LENGTH) VALUES ("+cur_time+","+node_id+","+length+")";  
		
			        sta.executeUpdate(trace_node);	  
			        
			        sta.close();
			   }
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	} 
	
	public void Trace_Edge(int time){ //�������	
	try {
		
			if(evertra_node==false) { 
			
	       //��ֻ֤����һ�νڵ����ݱ�
				        long sys_time = System.currentTimeMillis();
		 			
	 			        String str_time=String.valueOf(sys_time);  
	 			
	 			        traceedge_tablename = "trace edge packet" + str_time;   //�����ݱ���
				
				        String trace_edge = "create table" +traceedge_tablename + "(CUR_TIME INTERGER, EDGEID INTERGER,PACKETSUM INTEGER)";
				
				        sta.executeUpdate(trace_edge);//�����ڵ�ṹ�� 
				        
				        evertra_node=true;
			}
			
 			  
 			String cur_time= String.valueOf(time); 
 			
 			for(int i=0;i<m_edges.count();i++){	
 				
 			     edge=m_edges.get(i);	
 				
 			     String edge_id=Integer.toString(edge.id());  //ת��Ϊ�ַ���
 			
 			     String packet_sum=Integer.toString(edge.get_packetsum());    
 			
 			     String insert_edgetable = "Insert into"+ traceedge_tablename +"(CUR_TIME,EDGEID,PACKETSUM) VALUES ("+cur_time+"," + edge_id+ "," + packet_sum+ ")";  
 				
 			     sta.executeUpdate(insert_edgetable);	
 			     
 			     sta.close();
 			}
 		} catch (Exception e) {
 			
 			e.printStackTrace();
 		}		
	}
	
	
    public void CloseDatabase(Connection c) //�ر����ݿ�
	{   
	   try {
		       sta.close();
		       
		       con.close();  
				
			} catch (SQLException e) {
						
				con = null;
			}
		}
}
