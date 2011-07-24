package kernel;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The most fundamental implementation of Trace object.
 */
public class JxBaseTrace implements JiBaseTrace {
    
	
	private Connection con = null;
	private Statement sta = null;
	
   
	/** Owner of this object. It's usually the simulation engine object */
	private Object m_owner = null;
	
	/** Where the trace data files are placed */
	private String m_datadir = null;
	
	/** Current database name */ 
	private String m_curdbname = null;
	
	JxBaseTrace(Object owner) {
		m_owner = owner;
		m_datadir = "c:/temp/";
		m_curdbname = "";
	}
    
	JxBaseTrace(Object owner, String datadir) {
		m_owner = owner;
		m_datadir = datadir;
		m_curdbname = "";
	}
	

	public Object getOwner()
	{
		
	};
	
	public Object setOwner()
	{
		
	};

	public void openDatabase()
	{
		
	};
	
	public void closeDatabase()
	{
		
	};	
	
	void open(String datadir)

	public void open(String datadir )

	{
		/*
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			String databasename = getNextDatabaseDir();
			con = DriverManager.getConnection("jdbc:hsqldb:file:"
					+ databasename + ";shutdown=true", "sa", "");
			sta = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void open()
	{
		if (m_datadir == null)
			m_datadir = getNextDatabaseDir();
		open(m_datadir);
	}

	/** Free resources allocated to this object. */
	public void close()
	{
/*		
		//if (database is still open)
		{
			try{
				sta.close();
				con.close();
			} catch (SQLException e) {
				con = null;
			}
		}
*/		
	}
	
	/*
	 * finalize() will be called by JVM automatically. But JVM cannot guarantee
	 * when to call it. So We call system.runFinalization() in the engine to force
	 * the JVM to call finalize() of each revoked objects.
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize()
	{
		close();
	}
	
	/**
	 * Save a sinle node into the database.
	 * 
	 * @param node
	 */
	public void save( JiBaseNode node )
	{
		
	}


	/** save nodes */
	public void save( JxBaseNodeCollection nodes ) 
	{

	/**
	 * Save the collection of all nodes into database.
	 * @param nodes The collection of all nodes.
	 */
	public void save( JiBaseNodeCollection nodes ) 
	{
/*		
		
>>>>>>> 842e6dbd5390d2169129d0c47e51a3e149f1779d
		try {
			String str_time = getNextDatabaseName();
			String node_tablename = "nodetable" + str_time;
			String create_node = "create table " + node_tablename
					+ "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
			sta.executeUpdate(create_node); // �����ڵ�ṹ��

			for (int i = 0; i < nodeSet.count(); i++) {
				node = nodeSet.get(i);
				String node_id = Integer.toString(node.getId()); // ת��Ϊ�ַ���
				String loc_x = Integer.toString(node.getLocx());
				String loc_y = Integer.toString(node.getLocy());

				String insert_nodetable = "Insert into " + node_tablename
						+ "(NODEID,LOC_X,LOC_Y) VALUES (" + node_id + ","
						+ loc_x + "," + loc_y + ")";
				sta.executeUpdate(insert_nodetable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
	}

	/** save relation */
	public void save( JiBaseRelation relation ){
		
	}

	/** save relations */
	public void save( JxBaseRelationCollection relations ){

	
	/**
	 * Save a single relation object into the database.
	 * 
	 * @param relation
	 */
	public void save( JiBaseRelation relation )
	{
		
	}

	/**
	 * Save the relation collection object into the database.
	 * 
	 * @param relations
	 */
	public void save( JiBaseRelationCollection relations )
	{
		/*
>>>>>>> 842e6dbd5390d2169129d0c47e51a3e149f1779d
		try {
			String str_time = getNextDatabaseName();

			String edge_tablename = "edgetable" + str_time;
			String create_edge = "create table " + edge_tablename
					+ "(EDGEID integer,NODE_FROM integer,NODE_TO INTEGER)";

			sta.executeUpdate(create_edge); // �����߽ṹ
			for (int i = 0; i < relationSet.count(); i++) {

				JiBaseRelation relation = relationSet.get(i);

				String relation_id = Integer.toString(relation.getId()); // ת��Ϊ�ַ���
				String node_from = Integer.toString(relation.getNodeFrom());
				String node_to = Integer.toString(relation.getNodeTo());

				String insert_edgetable = "Insert into " + edge_tablename
						+ "(EDGEID,NODE_FROM,NODE_TO) VALUES (" + relation_id
						+ "," + node_from + "," + node_to + ")";

				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		*/
	}
	

	
	

	/** 
	 * Load meta nodes data from database into an JiBaseNodeCollection object  
	 * 
	 * @param nodes An JiBaseNodeCollection object containing the nodes loaded.
	 */
	public void load( JxBaseNodeCollection nodes )




	{
		// todo
	}


	public void load( JxBaseRelationCollection relations )


	public void load( JxBaseRelationCollection relations )

	{
		// todo
		
	}

	public void trace( JiBaseNode node )
	{
		
	}
		
	public void trace( JiBaseRelation relation )
	{
		
	}
	
	/**
	 * Take a snapshot at the whole network. All the current status of the network 
	 * such as node queue length and relation transmission traffic will be saved
	 * into trace data files.
	 * 
	 * This function is usually called when the simulation start. 
	 *  
	 * @param nodes
	 * @param relations
	 */
	public void snapshot( JiBaseNodeCollection nodes, JiBaseRelationCollection relations )
	{
		
	}
	
	@Override
    public void restore( String datadir, JiBaseNodeCollection nodes, JiBaseRelationCollection relations ) 
	{		
		this.open( datadir );
		this.load( nodes );
		this.load( relations );
	}
	
/*	
	// all the following should be eliminated
	
    
	JxBaseNodeCollection nodeSet = JxBaseRelationCollection.nodeSet;
	JxBaseRelationCollection relationSet = JxBaseRelationCollection.relationSet;

	JiBaseNode node = new JxBaseNode();

	ResultSet res = null;

	boolean evertra_node = false;
	boolean evertra_edge = false;

	String tracenode_tablename = null;
	String traceedge_tablename = null;


	Random random = new Random();
	ArrayList<Integer> m_array = new ArrayList<Integer>();

<<<<<<< HEAD

	public void loadNode(String tablename) { // ���ؽڵ�ṹ
	try {
=======
*/
/*
	public void loadNode(String tablename) { // ���ؽڵ��
		try {
>>>>>>> 842e6dbd5390d2169129d0c47e51a3e149f1779d
			String select_node = "select * from " + tablename;
			ResultSet r = sta.executeQuery(select_node);

			while (r.next()) {

				int i = 0;
				JiBaseNode node = nodeSet.get(i++);

				int nodeId = Integer.parseInt(r.getString(1));
				int nodeLocx = Integer.parseInt(r.getString(2));
				int nodeLocy = Integer.parseInt(r.getString(3));

				node.setId(nodeId);
				node.setLocx(nodeLocx);
				node.setLocx(nodeLocy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadEdge(String tablename) {// ���ر߽ṹ
		try {
			String select_edge = "select * from " + tablename;
			ResultSet r = sta.executeQuery(select_edge);

			while (r.next()) {

				int i = 0;
				JiBaseRelation relation = relationSet.get(i);

				int relationId = Integer.parseInt(r.getString(1));
				int nodeFrom = Integer.parseInt(r.getString(2));
				int nodeTo = Integer.parseInt(r.getString(3));

				relation.setId(relationId);
				relation.setNodeFrom(nodeFrom);
				relation.setNodeTo(nodeTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void traceNode(int experienttime) {// ���������
		
		try {
			if (evertra_node == false) { // ��ֻ֤����һ�νڵ����ݱ�

				String str_time = getNextDatabaseName();
				tracenode_tablename = "tracenode" + str_time; // �ڵ����ݱ���
				String create_node = "create table " + tracenode_tablename
						+ "(CUR_TIME integer, NODEID integer,LENGTH integer)";
				sta.executeUpdate(create_node); // �����ڵ�ṹ��
				evertra_node = true;
			}

			for (int i = 0; i < nodeSet.count(); i++) {
				node = nodeSet.get(i);
				String node_id = Integer.toString(node.getId()); // ת��Ϊ�ַ���(�ڵ��)
				String length = Integer.toString(node.getValue()); // (���ĳ���)

				String cur_time = Integer.toString(experienttime);

				String trace_node = "Insert into " + tracenode_tablename
						+ "(CUR_TIME,NODEID,LENGTH) VALUES (" + cur_time + ","
						+ node_id + "," + length + ")";

				sta.executeUpdate(trace_node);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
*/
	public void traceEdge(int experienttime) { // ���������
/*		
		try {

			if (evertra_edge == false) { // ��ֻ֤����һ�νڵ����ݱ�

				String str_time = getNextDatabaseName();
				traceedge_tablename = "traceedge" + str_time; // �����ݱ���
				String trace_edge = "create table "
						+ traceedge_tablename
						+ "(CUR_TIME integer, EDGEID integer,PACKETSUM integer)";

				sta.executeUpdate(trace_edge); // �����ڵ�ṹ��
				evertra_edge = true;
			}

			for (int i = 0; i < relationSet.count(); i++) {

				JiBaseRelation relation = relationSet.get(i);

				String cur_time = String.valueOf(experienttime);
				String edge_id = Integer.toString(relation.getId()); // ת��Ϊ�ַ���
				String packet_sum = Integer.toString(relation.getPacketSum());

				String insert_edgetable = "Insert into " + traceedge_tablename
						+ "(CUR_TIME,EDGEID,PACKETSUM) VALUES (" + cur_time
						+ "," + edge_id + "," + packet_sum + ")";
				sta.executeUpdate(insert_edgetable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
	}
	
	public String getNextDatabaseDir() 
	{
		// todo
		Date date = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm_ss");// �������ڸ�ʽ(���ݱ���ʽ��Ҫ��)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_hhmmss");// �������ڸ�ʽ(���ݱ���ʽ��Ҫ��)
		String cur_time = sdf.format(date);
		return cur_time;
	}

	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(JiBaseNodeCollection nodes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(JiBaseRelationCollection relations) {
		// TODO Auto-generated method stub
		
	}

}