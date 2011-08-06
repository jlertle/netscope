/**
 * 
 */
package extend.scalefree;
import java.util.*;

import kernel.JxBaseEngine;
import kernel.JxBaseInteraction;
import kernel.JxBaseNodeCollection;
import kernel.JxBaseRelationCollection;
import kernel.JxBaseRelation;
import kernel.JiBaseNode;
import kernel.JxBaseTrace;

  public class JxScaleFreeSimuApplication 
  {  
	    JxBaseEngine engine = new JxBaseEngine();
	    JxBaseRelation relation=new JxBaseRelation();
	    
		JxBaseRelationCollection  relations = new JxBaseRelationCollection();
		JxBaseNodeCollection  leftnodes = new JxBaseNodeCollection();
		JxBaseNodeCollection addednodes=new JxBaseNodeCollection();
	
		JiBaseNode nodeFrom=null;
		JiBaseNode nodeTo=null;
		
		
	    protected JiBaseNode selectnodeto() 
	    {    	
	    	Random random= engine.getRandom();
			int p= random.nextInt(addednodes.count()); 
			return addednodes.get(p);  
	    }
	    
	    
	    public void generateGraph()
	    { 
	    	for(int i=0;i<relations.count();i++)
			{ 
	    	   engine.setNodes(new JxBaseNodeCollection(engine, 10)); 
			   engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
			
		       relations=(JxBaseRelationCollection)engine.getRelations();
		       leftnodes=(JxBaseNodeCollection)engine.getNodes();
	           engine.setInteraction(new JxBaseInteraction(engine));
		       engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
		
		       engine.execute(10000);	    
		    
		        if(i==0)
		        {
		    	    relation=(JxBaseRelation)relations.get(i);	
		    	  
		    	    nodeFrom = leftnodes.get(0);
		    	    nodeTo=leftnodes.get(1);
		    	  
		    	    relation.setNodeFrom(nodeFrom);
		    	    relation.setNodeTo(nodeTo);
		    	  
		    	    leftnodes.remove( nodeFrom );
		    	    leftnodes.remove( nodeTo );
		    	  
		    	    addednodes.add(nodeFrom); 
		    	    addednodes.add(nodeTo);
	            }
	    	
	    	   else 
	    	   { 
		    	  nodeFrom  = leftnodes.get(0);
		    	  nodeTo = [0];
		     	    addedSet.add(currentNodeId);  
		
			    	 JxBaseRelation relation=new JxBaseRelation(0, currentNodeId,selectNodeId,20);
			    	 relationSet.add(relation);
			    	 
			    	 // �õ���ǰ�ĵ㼰ѡ�е�
			    	 JiNode currentNode=nodeSet.get(currentNodeId);
					 JiNode selectNode=nodeSet.get(selectNodeId);
						
					 
					// ����ǰ����ѡ�е�Ķȷֱ��1
					int currentNodeDegree =currentNode.getDegree();
					currentNode.setDegree(currentNodeDegree+1);
						
					int selectNodeDegree=selectNode.getDegree();
					selectNode.setDegree(selectNodeDegree+1);    	 
			  	
			 
			         currentNodeId=randomNodeSerial[i]; 
			         
			        // ѡ����֮�����Ľڵ�ID
					 selectNodeId =selectnodeto();
					// �������ı߼���߼��� 
					JxBaseRelation relation=new JxBaseRelation(i-1,selectNodeId, currentNodeId,20);
		         	relationSet.add(relation); 
					
					// ����ǰ��ı�ż�ѡ�е�ı�ż��뵽addedSet��  
		         	addedSet.add(currentNodeId);
		         	addedSet.add(selectNodeId);         //���һ�λ���һ��

					JiNode currentNode=nodeSet.get(currentNodeId);
					JiNode selectNode=nodeSet.get(selectNodeId);
					
					// ����ǰ����ѡ�е�Ķȷֱ��1
					int currentNodeDegree =currentNode.getDegree();
					currentNode.setDegree(currentNodeDegree+1);
					
					int selectNodeDegree=selectNode.getDegree();
					selectNode.setDegree(selectNodeDegree+1);
		     }
		}
	 }
			
       public static void main(String []args)
       {	
			
	   }     	   		
 }
	

