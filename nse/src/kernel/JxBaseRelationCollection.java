package kernel;

import java.util.ArrayList;
import java.util.Random;

// import extend.scalefree.JxScaleFreeEdge;
// import extend.scalefree.JxScaleFreeNode;

/**
 * This class maintains a list of JiBaseRelation object. You can add/remove a relation
 * object into/from this collection.
 *
 * @author Allen
 *
 */
public class JxBaseRelationCollection extends ArrayList<JiBaseRelation>{
	
	private static final long serialVersionUID = 1L;

    static  JxBaseNodeCollection nodeSet=new JxBaseNodeCollection();
	static  JxBaseRelationCollection relationSet=new JxBaseRelationCollection();
	ArrayList<Integer> addedSet=new ArrayList<Integer>(); 
	Random random=new Random(); 
	
    public int count() 
	{		
	  return super.size();		
	}
	  
	public boolean add(JiBaseRelation relation)
	{
	  return super.add(relation);
	}
	

	/** get JxScaleFreeEdge object at specified position with index */
	public JiBaseRelation get(int index)
	{ 
	  return super.get(index); 
	}
	
	/** 
	 * Generate relation objects into the relation collection. These objects 
	 * may describe the network topology as a graph, but it's not mandatory to 
	 * do so.
	 */
	public void generate()
	{
		JxBaseEngine engine = (JxBaseEngine)m_owner;
		Random random = engine.random();
		JxBaseTrace = engine.getTrace();
		
		// todo		
	}
	
      public void generateGraph( int nodecount){
		
		/**�����ڵ㣬��������뵽������
		 * ��α�֤�����Ľڵ����겻�ظ����ظ��ĸ���Ϊ1/10000��
        */
	    for (int id=0; id< nodecount; id++) {
	    	int loc_x = random.nextInt(100);
	    	int loc_y = random.nextInt(100);
	    	
	    	/**��ӽڵ�-��ʼ������50������100 */
	    	JxBaseNode node=new JxBaseNode(id,loc_x,loc_y,50,100);
	        nodeSet.add(node);
	    }
	    
	    int[] randomNodeSerial =new int[nodecount]; //�ӵ㼯�����ѡ��ڵ�
	    randomNodeSerial =randomSerial(nodecount); 
	    
	    int currentNodeId=0;
    	int selectNodeId=0;
    	
	    for (int i = 0; i<nodecount; i++){ //(���ڵ�����μ������� 	
	    
	    	/** ����õ���һ���߲�����addedSet��*/   //ƫ�������㷨���������⣡����                                             
	    	if(i==0)
	    	{
	    	  currentNodeId = randomNodeSerial[0];
	    	  addedSet.add(currentNodeId); 
	        }
	    	/**ǰ���������һ����,���������߼��� */
	    	else if(i==1)
	    	{ 
	    	 currentNodeId = randomNodeSerial[1];
	    	 selectNodeId = randomNodeSerial[0];
	     	 addedSet.add(currentNodeId);  
	
	    	 JxBaseRelation relation=new JxBaseRelation(0, currentNodeId,selectNodeId,20);
	    	 relationSet.add(relation);
	    	 
	    	 /**�õ���ǰ�ĵ㼰ѡ�е�*/
	    	 JiNode currentNode=nodeSet.get(currentNodeId);
			 JiNode selectNode=nodeSet.get(selectNodeId);
				
			 
			/** ����ǰ����ѡ�е�Ķȷֱ��1*/
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
				
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);    	 
	    }	
	   else{
	         currentNodeId=randomNodeSerial[i]; 
	         
	        /**ѡ����֮�����Ľڵ�ID*/
			 selectNodeId =selectnodeto();
			/**�������ı߼���߼��� */
			JxBaseRelation relation=new JxBaseRelation(i-1,selectNodeId, currentNodeId,20);
         	relationSet.add(relation); 
			
			/**����ǰ��ı�ż�ѡ�е�ı�ż��뵽addedSet��  */
         	addedSet.add(currentNodeId);
         	addedSet.add(selectNodeId);         //���һ�λ���һ��

			JiNode currentNode=nodeSet.get(currentNodeId);
			JiNode selectNode=nodeSet.get(selectNodeId);
			
			/** ����ǰ����ѡ�е�Ķȷֱ��1*/
			int currentNodeDegree =currentNode.getDegree();
			currentNode.setDegree(currentNodeDegree+1);
			
			int selectNodeDegree=selectNode.getDegree();
			selectNode.setDegree(selectNodeDegree+1);
	      }     	
		}
   }     
	
    protected int selectnodeto() {  //2n-2	
		int p = random.nextInt(addedSet.size()); //������0�����б���֮�������ֵ
		return addedSet.get(p);  //����ѡ��
    }

    /**
     * Make the relation order in the list randomized. 
     */
    public void randomize()
    {
    	// todo
    }
    
    public static int[] randomSerial(int count){
		   
		  int result[]=new int [count];	
	      for(int i=0;i<count;i++){
	    	  result[i]=i;
	      }	      
	      Random random=new Random();
	      for(int i=count-1; i>0; i--){
	    	  int r= random.nextInt(i);
	    	  int temp =result[i];
	      	  result[i]=result[r];
	    	  result[r]=temp;
	      }
	     return result;   	
	} 
	
	public JiBaseRelation search(int id){
		boolean found = false;
		JiBaseRelation  relation = new JxBaseRelation();
		for (int i=0; i<super.size(); i++){
			relation = this.get(i);
			if (relation.getId()== id){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}
	
	
	public JiBaseRelation search(int nodefrom, int nodeto){
		
		boolean found = false;
		
		JiBaseRelation relation = new JxBaseRelation();
		
		for (int i=0; i<super.size(); i++){
			
			relation = this.get(i);
			
			 if ((relation.getNodeFrom() == nodefrom) && (relation.getNodeTo() == nodeto)){
				found = true;
				break;
			}
		}
		return (found ? relation: null);
	}	
	
	
	public ArrayList<JiBaseRelation> in_edges_of( int nodefrom ){
		
		ArrayList<JiBaseRelation> in_edge_list =new ArrayList<JiBaseRelation>();
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  in_edge_list.add(relation);
		   }   	 
		}
		return in_edge_list;
	}
	
	
	public ArrayList<JiBaseRelation> out_edges_of( int nodefrom ){
		
		ArrayList<JiBaseRelation> out_edge_list =new ArrayList<JiBaseRelation>();
		
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom){
			  out_edge_list.add(relation);
		   }   	 
		}
		return out_edge_list;
	}
	
	
	public ArrayList<JiBaseRelation> edges_of( int nodefrom,int nodeto ){
   
		ArrayList<JiBaseRelation> edge_list =new ArrayList<JiBaseRelation>();
		JiBaseRelation relation=new JxBaseRelation();
		
		for(int i=0;i<super.size();i++){
		   relation=this.get(i);
		   if(relation.getNodeFrom()==nodefrom||relation.getNodeTo()==nodeto){
			  edge_list.add(relation);
		   }   	 
		}
		return edge_list;
	}
	
}
