package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
	
	/**����relation���󣬲�ת��ΪJxStdRelation */
	JiRelation relation=new JxStdRelation();   
	
   /** �õ� �Ѵ洢�ı߼��Ϻ͵㼯��*/
   JxRelationCollection relationSet=JxRelationCollection.relationSet;
   JxNodeCollection nodeSet=JxRelationCollection.nodeSet;
     
   Random random=new Random();
     
	/**�����еı���һ�ΰ�����*/
	public void interact()
	{ 	
	   /**�����������*/	
       int edgeCount=relationSet.count();
	   int []randomSerial=new int[edgeCount];
	   randomSerial=JxRelationCollection.randomSerial(edgeCount);
	  
	    for(int i=0;i<edgeCount;i++){			

          /** ����õ�һ���� */ 
		  int relationId=randomSerial[i];
		  relation = relationSet.get(relationId);
		  
		    int nodefrom=relation.getNodeFrom();
		    int nodeto=relation.getNodeTo();
		  
			/**���ͽڵ�-���սڵ�*/
			JiNode sender =new JxStdNode();
		    JiNode receiver=new JxStdNode();
		    
		    /**���ȷ�����ͷ���*/
		    int temp=random.nextInt(2);
		    if(temp==0){
		       sender=nodeSet.get(nodefrom);	
		       receiver=nodeSet.get(nodeto);	
		    }
		    else{
		       sender=nodeSet.get(nodeto);	
			   receiver=nodeSet.get(nodefrom);	
		    }			
		     /**���͵�İ�����*/
		     int senderValue=sender.getValue();  
		 		
		    
		 	 /**���յ��ʣ��ռ�*/
		     int receiverVolume=receiver.getCapacity()-receiver.getValue();
		 
		     /**�ߵĴ���*/
		    int bandwidth=relation.getBandWidth();
		       
		   	int packet=Minimum(senderValue,receiverVolume,bandwidth);
		 	
			sender.setValue(senderValue-packet); 
			
			int receiverValue =receiver.getValue();
	        receiver.setValue(receiverValue+packet);
	        
	        int packetsum =relation.getPacketSum()+packet;
	        relation.setPacketSum(packetsum); //��¼���ϰ�������  	        
	        }	
	       for(int i=0;i<nodeSet.count();i++)
		   {
			  System.out.println("nodeId��" +nodeSet.get(i).getId()+" loc_x "+nodeSet.get(i).getLocx()+" loc_y "+ nodeSet.get(i).getLocy()+" nodelength "+nodeSet.get(i).getValue());
		   }
	}
			   
		public int  Minimum(int a,int b,int c) { //���Ͱ��ĸ���ҪС��������ֵ				   
			   
			   int minimum=0;
			   int mini=a;
			   if(b<mini)
			    mini=b;   
			   if(c<mini)
			    mini=c;
	          if(mini==0)
	          {
	        	minimum=0;
	          }
	          else
	          {
	        	minimum=random.nextInt(mini);
	          }  
	          return minimum;	 
		}	


		
}
			

	
