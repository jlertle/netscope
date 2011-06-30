package extend.scalefree;

import java.util.*;

public class JxScaleFreeTopology {
	Object m_owner;
	JxScaleFreeNodeCollection m_nodeset;
	JxScaleFreeEdgeCollection m_edgeset; 
	
	public static Random random = new Random();
	
	JxScaleFreeTopology( Object owner )
	{
		m_nodeset = new JxScaleFreeNodeCollection();
		m_edgeset = new JxScaleFreeEdgeCollection();
		this.m_owner = owner;
	}
	
	JxScaleFreeNodeCollection nodeset(){
		return this.m_nodeset;
	}
	
	JxScaleFreeEdgeCollection edgeset(){
		return this.m_edgeset;
	}
	
	/**
	 * Generate scalefree topology
	 * @param nodecount
	 */
	void generate_random_graph( int nodecount, int edgecount )
	{
		int i, x, y;
		JxScaleFreeEdge edge;  
		JxScaleFreeNode node;  
		int nodefrom, nodeto;

		// generate nodes and place them into the node set
	    for (i=0; i< nodecount; i++){
			x = random.nextInt(100);
			y = random.nextInt(100);
			m_nodeset.add( new JxScaleFreeNode( x, y, 100 ));			
		}
	    
	    // generate edges and place them into the nodeset
	    for (i=0; i< edgecount; i++){
			nodefrom = random.nextInt(nodecount);
			nodeto = random.nextInt(nodecount);
			m_edgeset.add( new JxScaleFreeEdge( m_edgeset, nodefrom, nodeto, 100 ));			
		}
	}
	
	/**
	 * Generate scalefree topology
	 * @param nodecount
	 */
	void generate( int nodecount )
	{
		int i, x, y;
		JxScaleFreeEdge edge;  
		JxScaleFreeNode node;  

		// generate nodes and place them into the node set
	    for (i=0; i< nodecount; i++){
			x = random.nextInt(100);
			y = random.nextInt(100);
			m_nodeset.add( new JxScaleFreeNode( x, y, 100 ));			
		}
	    
	    // generate edges and place them into the nodeset
	
	    initnode = random.nextInt( nodecount );
	    ArrayList<int> addedset = new ArrayList<int>();
	    ArrayList<int> leftset = new ArrayList<int>();
	    
	    
	    
           // �����߲����߼��뵽�߼���
		
		    node = m_nodes.get(0);   //��һ����(��Ϊ��ʼ��)                
	       
		    JoinInNetNode.add(node); //���뵽������
	        
		    node.set_degree(1);      
		
	    for (i = 1; i<m_nodes.count(); i++) //(���ڵ��Ϊ1-9999�Ľڵ����μ�������)
		{   
			
	    	JxScaleFreeNode cur_node; 
	    	
			JxScaleFreeNode select_node;
		  
		    cur_node = m_nodes.get(i);       //��ǰ�ڵ�
		  
			select_node =selectnodeto(i-1);  //ѡ����֮�����Ľڵ�
            
			JoinInNetNode.add(cur_node);     //��ǰ���������
			
			cur_node.set_degree(cur_node.degree()+1);   //��ǰ��Ķȼ�1
			
			JoinInNetNode.add(select_node);            //ѡ�е��������
			
			select_node.set_degree(select_node.degree()+1);   //ѡ�е�Ķȼ�1
			
			edge = new JxScaleFreeEdge( i,cur_node.id(),select_node.id(), 10, 0 ); //�±�(�ߺţ���㣬�յ㣬����Ȩֵ)
			
			m_edges.add(edge);
		}
	}	
	void load()
	{
		
	}
	void save()
	{
		
	}

}
