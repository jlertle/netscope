package extend.scalefree;

import java.util.*;

public class JxScaleFreeTopology {
	
	Object m_owner;
	
	JxScaleFreeNodeCollection m_nodeset;
	
	JxScaleFreeEdgeCollection m_edgeset; 
	
	ArrayList<JxScaleFreeNode>  JoinInNetNode;
	
	public static Random random = new Random();
	
	JxScaleFreeTopology( Object owner ){
		
		m_nodeset = new JxScaleFreeNodeCollection();
		
		m_edgeset = new JxScaleFreeEdgeCollection();
		
		this.m_owner = owner;
	}
	
	JxScaleFreeTopology(){
		
		m_nodeset = new JxScaleFreeNodeCollection();
		
		m_edgeset = new JxScaleFreeEdgeCollection();
		
	}
	
	JxScaleFreeNodeCollection nodeset(){
		
		return this.m_nodeset;
	}
	
	JxScaleFreeEdgeCollection edgeset(){
		
		return this.m_edgeset;
	}
	
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
			
			m_nodeset.add( new JxScaleFreeNode(x, y, 100));			
		}
	    
	    // generate edges and place them into the nodeset
	    for (i=0; i< edgecount; i++){
			
	    	nodefrom = random.nextInt(nodecount);
			
	    	nodeto = random.nextInt(nodecount);
	    	
	    	m_edgeset.add(new JxScaleFreeEdge(nodefrom, nodeto, 100));
	    	
	    	//m_edgeset.add( new JxScaleFreeEdge( m_edgeset, nodefrom, nodeto, 100 ));			
		}
	}
	
	void generate( int nodecount ){
		
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
	
	    int initnode = random.nextInt( nodecount );
	    
	    ArrayList<Integer> addedset = new ArrayList<Integer>();
	    
	    ArrayList<Integer> leftset = new ArrayList<Integer>();
	  
           // �����߲����߼��뵽�߼���
		
		    node = m_nodeset.get(0); //��һ����(��Ϊ��ʼ��)                
	       
		    JoinInNetNode.add(node); //���뵽������
	        
		    node.set_degree(1);      
		
	    for (i = 1; i<m_nodeset.count(); i++){ //(���ڵ��Ϊ1-9999�Ľڵ����μ������� 
			
	    	JxScaleFreeNode cur_node; 
	    	
			JxScaleFreeNode select_node;
		  
		    cur_node = m_nodeset.get(i);     //��ǰ�ڵ�
		  
			select_node =selectnodeto();  //ѡ����֮�����Ľڵ�
            
			JoinInNetNode.add(cur_node);     //��ǰ���������
			
			cur_node.set_degree(cur_node.degree()+1);  //��ǰ��Ķȼ�1
			
			JoinInNetNode.add(select_node);            //ѡ�е��������
			
			select_node.set_degree(select_node.degree()+1);   //ѡ�е�Ķȼ�1
			
			edge = new JxScaleFreeEdge( i,cur_node.id(),select_node.id(), 10, 0 ); //�±�(�ߺţ���㣬�յ㣬����Ȩֵ)
			
			m_edgeset.add(edge);
		}    
	}	
	protected JxScaleFreeNode selectnodeto() {  
		
		int p = random.nextInt(JoinInNetNode.size()); //������0�����б���֮�������ֵ
		
		return JoinInNetNode.get(p);  //����ѡ�нڵ�
    }
	void load(){
		
	}
	void save(){
		
	}
}
