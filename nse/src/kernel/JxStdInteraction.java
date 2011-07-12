package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
    
	
	/**����relation���󣬲�ת��ΪJxStdRelation */
	JiRelation relation=new JxStdRelation();
    JxStdRelation stdrelation=(JxStdRelation)relation;
    
    
    /** �õ� �Ѵ洢�ı߼��Ϻ͵㼯��*/
    JxRelationCollection edgeCollection=stdrelation.edgeCollection;
	JxNodeCollection nodeCollection=stdrelation.nodeCollection;
	
	
	Random random=new Random();
	int packetsum=0;
	
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
			
		    int senderValue=sender.getValue();
		    int receiverVolume=receiver.getCapacity()-receiver.getValue();
		    int bandwidth=relation.getBandWidth();
		    
			packetsum=Minimum(senderValue,receiverVolume,bandwidth);	    	  
	        sender.setValue(senderValue-packetsum);     
	        receiver.setValue(receiverVolume+packetsum);
	        
	        packetsum+=packetsum;
	        }
			relation.setPacketSum(packetsum); //��¼���ϰ�������    
        }
			   
		public int  Minimum(int a,int b,int c) { //���Ͱ��ĸ���ҪС��������ֵ		
			   
			   int mini;
                
			   int minimum=a;
			   
			   if(b<minimum)
			    minimum=b;
			    
			   if(c<minimum)
			    minimum=c;
		        
			    mini=random.nextInt(minimum);

	            return mini;	 
		}	
}
			

	
