package kernel;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class JxStdTrace implements JiTrace { //(���ڱ����Ľ��) 
	
	JxNodeCollection nodecollection=new JxNodeCollection();
	JxEdgeCollection edgecollection=new JxEdgeCollection();  
    JiNode node=new JxStdNode();
	ResultSet res =null;
	boolean  evertra_edge=false;
 
    Connection con = null;
	Statement sta = null;
	ArrayList<Integer> m_array = new ArrayList<Integer>();
	Random random= new Random();
	
	boolean tracenode_table = false;
	boolean traceedge_table;
	/** �����ݿ�   */
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

	// �����ڵ��������ڵ�ṹ
	public void saveNode(Statement sta) {		
	try {
			long sys_time = System.currentTimeMillis();
			String str_time = String.valueOf(sys_time);
			String node_tablename = "nodetable" + str_time;
			String create_node = "create table " + node_tablename + "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
			sta.executeUpdate(create_node); // �����ڵ�ṹ��

			for (int i = 0; i < nodecollection.count(); i++) {
				node = nodecollection.get(i);
				String node_id = Integer.toString(node.getId()); // ת��Ϊ�ַ���
				String loc_x = Integer.toString(node.getLocx());
				String loc_y = Integer.toString(node.getLocy());

				String insert_nodetable = "Insert into" + node_tablename+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","+ loc_x + "," + loc_y + ")";
				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �����߱�������
	public void saveEdge(Statement sta){
	try {
			long sys_time = System.currentTimeMillis();
			String str_time = String.valueOf(sys_time);
			
			String edge_tablename = "edgetable" + str_time;
            String create_edge = "create table " + edge_tablename+ "(EDGEID integer,NODE_FROM integer,NODE_TO INTEGER)";
            
            sta.executeUpdate(create_edge); // �����߽ṹ��

			for (int i = 0; i < edgecollection.count(); i++) {
				
			    JiRelation relation = edgecollection.get(i);
				
				String relation_id = Integer.toString(relation.getId()); // ת��Ϊ�ַ���
				String node_from = Integer.toString(relation.getNodeFrom());
				String node_to = Integer.toString(relation.getNodeTo());
				
				String insert_edgetable = "Insert into" + edge_tablename
						+ "(EDGEID,NODE_FROM,NODE_TO) VALUES (" + relation_id + ","
						+ node_from + "," + node_to + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void loadNode(Statement sta, String tablename) { // ���ؽڵ��
	try {
			String select_node = "select * from " + tablename;
			ResultSet r = sta.executeQuery(select_node);

			while (r.next()) {
				
				int i = 0;
				JiNode node=nodecollection.get(i++);
				
				int nodeId= Integer.parseInt(r.getString(1));
		        int nodeLocx= Integer.parseInt(r.getString(2));
				int nodeLocy=Integer.parseInt(r.getString(3));
				
				node.setId(nodeId);
				node.setLocx(nodeLocx);
                node.setLocx(nodeLocy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadEdge(Statement sta, String tablename) {// ���ر߽ṹ
    try {
			String select_edge = "select * from " + tablename;
			ResultSet r = sta.executeQuery(select_edge);

			while (r.next()) {
                
				int i = 0;
				JiRelation relation=edgecollection.get(i);
				
				int relationId = Integer.parseInt(r.getString(1));
                int nodeFrom=Integer.parseInt(r.getString(2));
                int nodeTo=Integer.parseInt(r.getString(3));
                
                relation.setId(relationId);
				relation.setNodeFrom(nodeFrom);
                relation.setNodeTo(nodeTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void traceNode(Statement sta,int time) {// ���������
	try {
		    String tracenode_tablename=null;
		    
			if (tracenode_table == false) { //��ֻ֤����һ�νڵ����ݱ�
		
				long sys_time = System.currentTimeMillis();
				String str_time = String.valueOf(sys_time);
				tracenode_tablename = "tracenode" + str_time; // �ڵ����ݱ���
				String create_node = "create table " + tracenode_tablename+ "(CUR_TIME integer, NODEID integer,LENGTH integer)";
				sta.executeUpdate(create_node); //�����ڵ�ṹ��
				tracenode_table = true;
			}

			for (int i = 0; i < nodecollection.count(); i++) {
				node = nodecollection.get(i);
				String node_id = Integer.toString(node.getId()); // ת��Ϊ�ַ���(�ڵ��)
				String length = Integer.toString(node.getValue()); // (���ĳ���)

                String cur_time=Integer.toString(time);
				
				String trace_node = "Insert into" + tracenode_tablename
						+ "(CUR_TIME,NODEID,LENGTH) VALUES (" + cur_time + ","
						+ node_id + "," + length + ")";

				sta.executeUpdate(trace_node);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void traceEdge(Statement sta, int time) { // ���������	  	
    try {
			   String traceedge_tablename=null;
		    
			if (traceedge_table == false){ // ��ֻ֤����һ�νڵ����ݱ�
			
				long sys_time = System.currentTimeMillis();
				String str_time = String.valueOf(sys_time);
				traceedge_tablename = "traceedge" + str_time; // �����ݱ���
				String trace_edge = "create table " + traceedge_tablename
						+ "(CUR_TIME integer, EDGEID integer,PACKETSUM integer)";

				sta.executeUpdate(trace_edge); // �����ڵ�ṹ��
				traceedge_table = true;
			}

			for (int i = 0; i < edgecollection.count(); i++) {

				JiRelation relation =edgecollection.get(i);

				String cur_time = String.valueOf(time);
				String edge_id = Integer.toString(relation.getId()); // ת��Ϊ�ַ���
				String packet_sum = Integer.toString(relation.getPacketSum());

				String insert_edgetable = "Insert into" + traceedge_tablename
						+ "(CUR_TIME,EDGEID,PACKETSUM) VALUES (" + cur_time
						+ "," + edge_id + "," + packet_sum + ")";
				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

    public void CloseDatabase(){   //�ر����ݿ�
	 try {
		       sta.close();
		       con.close();  
		} catch (SQLException e) {
			  con = null;
		  }
   }

    public String currenttime() {	
    	 Date date = new Date();
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm");//�������ڸ�ʽ(���ݱ���ʽ��Ҫ��)
    	 String cur_time =sdf.format(date);
    	 return cur_time;	 
    }
}