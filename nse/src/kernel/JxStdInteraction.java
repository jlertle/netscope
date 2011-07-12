package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
    
	
	/**����relation���󣬲�ת��ΪJxStdRelation */
	JiRelation relation=new JxStdRelation();
    JxStdRelation stdrelation=(JxStdRelation)relation;
    
    
    /** �õ� �Ѵ洢�ı߼��Ϻ͵㼯��*/
<<<<<<< HEAD
    JxEdgeCollection edgeCollection=JxStdRelation.edgeCollection;
	JxNodeCollection nodeCollection=JxStdRelation.nodeCollection;
=======
    JxRelationCollection edgeCollection=stdrelation.edgeCollection;
	JxNodeCollection nodeCollection=stdrelation.nodeCollection;
>>>>>>> f7dd2920cbc8f6be9964d882446f0dca27b6d6f5
	
	int packetsum=0;
	Random random=new Random();

	
	/**�����еı���һ�ΰ�����*/
	public void interact(){
		
		int edgeCount=edgeCollection.count();
		
		for(int i=0;i<edgeCount;i++){
			
			int []randomSerial=new int[edgeCount];
			
			/** �õ�������еı߼� */
			randomSerial=JxStdRelation.randomSerial(edgeCount);
			
			/** ����õ�һ���� */
			int relationId=randomSerial[i];
			relation = edgeCollection.get(relationId);
			
		    int nodefrom=relation.getNodeFrom();
		    int nodeto=relation.getNodeTo();
		  
			/**���ͽڵ�-���սڵ�*/
			JiNode sender =new JxStdNode();
		    JiNode receiver=new JxStdNode();
		    
		    int temp=random.nextInt(2);
		    
		    if(temp==0){
		       sender=nodeCollection.get(nodefrom);	
		       receiver=nodeCollection.get(nodeto);	
		    }
		    else{
		       sender=nodeCollection.get(nodeto);	
			   receiver=nodeCollection.get(nodefrom);	
		    }
			
		    /**���͵�İ��ĸ���*/
		    int senderValue=sender.getValue();
		    
		    /**���յ��ʣ��ռ�*/
		    int receiverVolume=receiver.getCapacity()-receiver.getValue();
	
		   /**�ߵĴ���*/
		    int bandwidth=relation.getBandWidth();
		    
		    
			int packet=Minimum(senderValue,receiverVolume,bandwidth);	    	  
	        sender.setValue(senderValue-packet);     
	        receiver.setValue(receiverVolume+packet);
	     
	        packetsum +=packet;
	        }
			relation.setPacketSum(packetsum); //��¼���ϰ�������    
        }
			   
		public int  Minimum(int a,int b,int c) { //���Ͱ��ĸ���ҪС��������ֵ				   
			   
			   int minimum=0;
			   int mini=a;
			   if(b<mini)
			    mini=b;   
			   if(c<mini)
			    mini=c;
	          
			  minimum=random.nextInt(mini);  
	          return minimum;	 
		}	
}
			

	
