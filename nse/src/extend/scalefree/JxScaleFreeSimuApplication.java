/**
 * 
 */
package extend.scalefree;
import java.sql.Connection;
import java.util.*;
import java.sql.Statement;
/**
 * @author Allen
 * 
 */
public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode;
	 
	JxScaleFreeNodeCollection m_nodes;   
	
	JxScaleFreeEdgeCollection m_edges; 
	
	JxScaleFreeTrace m_trace; 
	
	 Random random ;
	   
     Statement sta ;
		
	 Connection con ;
		
	 String str;
	 
	int packet_num ;
	//��ʼ��
	public JxScaleFreeSimuApplication(){
		  
   	     JoinInNetNode = new ArrayList<JxScaleFreeNode>();  //�Ѽ�������Ľڵ㣨��ɵ�����
		
		 m_nodes = new JxScaleFreeNodeCollection(); //�㼯��(����������)
	  
		 m_edges = new JxScaleFreeEdgeCollection(); //�߼���(����������)
		 
		 m_trace = new JxScaleFreeTrace(); //����ṹ
		 
		 random = new Random();
		   
	     sta = null;
		
	     con = null;
			
		 str = null;
		 
	}
	
	public void generate( int nodecount ){ //��������
			
		  int i,x,y;   
		
		  JxScaleFreeEdge edge=new JxScaleFreeEdge();   //��   
		
	    for (i=0; i< nodecount; i++){  //����ָ�������ĵ�
			
			x=random.nextInt(100);
			
		    y=random.nextInt(100);
			
			m_nodes.add(new JxScaleFreeNode(i,x,y,30,100));//����½ڵ㣨30-��ʼ���أ�100����������   
		}  
	    
	 // System.out.println(" m_nodesize is "+m_nodes.size());		
           // �����߲����߼��뵽�߼���       
		
	    for (i = 0; i< nodecount; i++){ //(���ڵ��Ϊ1-9999�Ľڵ����μ�������)
		   
	    	JxScaleFreeNode cur_node = new JxScaleFreeNode(); 
	    	
			JxScaleFreeNode select_node = new JxScaleFreeNode();
		 
		    if(i==0){  //��һ��ֱ�ӽ���һ���ڵ��������
		       
		    	cur_node=m_nodes.get(randomrank(nodecount).get(0));
		    	
		    	JoinInNetNode.add(cur_node); 
		    	 
		    	cur_node.set_degree(cur_node.degree()+1); 
		    }
		    else {
		    	
			cur_node = m_nodes.get(randomrank(nodecount).get(i));//((���нڵ������������һ��)�������еĵ�i��ֵ
		    
		    JoinInNetNode.add(cur_node);    //��ǰ���������
		    
			cur_node.set_degree(cur_node.degree()+1);  //��ǰ��Ķȼ�1
		  
			select_node = selectnodeto();    //ѡ����Ѽ�������ĵ���ѡ����֮�����Ľڵ�
			
			JoinInNetNode.add(select_node);  //ѡ�е��������
		
			select_node.set_degree(select_node.degree()+1);  //ѡ�е�Ķȼ�1
			
			edge = new JxScaleFreeEdge(i-1,cur_node.id(),select_node.id(), 10, 0); //�±�(�ߺţ���㣬�յ㣬����Ȩֵ)
            
            m_edges.add(edge);//(�ߺŴ�0��ʼ)
		   }    	  
		}
	}	     
	 //ѡ��ߵ�ĩ�ڵ�
	protected JxScaleFreeNode selectnodeto() {  
	
		int p = random.nextInt(JoinInNetNode.size()); //������0�����б���֮�������ֵ
		
		return JoinInNetNode.get(p);  //����ѡ�нڵ�
    }
	
	//���ɺ���0-n���������ظ���������	
   public  ArrayList<Integer>  randomrank(int n){  
		
	   ArrayList<Integer>  RandomList = new ArrayList<Integer>(); //����1-n��������е���
	   
	   boolean [] exist=new boolean[n];
	  
	   int number;
	   
	   for(int i=0;i<n;i++){	   
		
	  do{
			
	      number =random.nextInt(n);
		
		} while(exist[number]); 
		   
	      exist[number]=true;
	   
		  RandomList.add(number);
	   }
	   
	   return  RandomList;
	}
   
	//ÿһʱ��ͬһ���ϵ����ڽڵ㽻���������ĸ��������   
	public void evolve(){
    try{
		JxScaleFreeEdge edge;
		
		JxScaleFreeNode sender, receiver;
		
		int packet_num=0;  //��¼���ϵİ�����

		for (int i=0; i<m_edges.count(); i++){
			
			edge = m_edges.get_edge(randomrank(m_edges.count()).get(i)); //����õ�һ����
			
			sender = m_nodes.get_node(edge.nodefrom());  //(�õ���Ӧ�ĵ�(���id��))
			
			receiver = m_nodes.get_node(edge.nodeto());  //�õ���Ӧ�ĵ�(�յ�id��)
			
			int r = random.nextInt(2); //���ѡ���ͷ���
     		     
			      if(r==0){  //�ӽڵ�1-�ڵ�2
			    	  
			    	packet_num=Minimum(sender.get_length(),(receiver.get_capacity()-receiver.get_length()),edge.get_bandwidth());
			    	  
				    sender.set_length(sender.get_length()-packet_num); 
				    
				    receiver.set_length(receiver.get_length()+packet_num);
			      }
		         if(r==1){  //�ӽڵ�2-�ڵ�1
		        	 
		    	     packet_num=Minimum(receiver.get_length(),(sender.get_capacity()-sender.get_length()),edge.get_bandwidth());
				    
				     sender.set_length(sender.get_length()+packet_num);
		    	     
		    	     receiver.set_length(receiver.get_length()-packet_num); 
			      }
		         
		         packet_num+=packet_num;
				   
				 edge.set_packetsum(packet_num); //��¼���ϰ�������    
        }
    } catch(Exception e){
		    	
		e.printStackTrace();
		
	  }	   
 }		
	//������ֵ�е���Сֵ
	public int  Minimum(int sender_length,int receiver_capacity,int band_width) { //���Ͱ��ĸ���ҪС��������ֵ		
		
		   int mini=0;
		
		   int minimum=sender_length;
		
		   if(receiver_capacity<minimum)
		
		    minimum=receiver_capacity;
		
		    if(band_width<minimum)
		
		    minimum=band_width;
	 
		    mini=random.nextInt(minimum);
	 
	  return  mini;  
 }
    public	void trace(int time) {
	
	    m_trace.Trace_Node( time ); //timeΪ�������
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){ //���ݿ⡪���ڴ�

		// load edges;
		
		m_trace.Load_Edgetopo(str);
		
		// load nodes
		
		m_trace.Load_Nodetopo(str);
	}
	 
	public void save(){//�ڴ桪�����ݿ�
	  
		m_trace.Save_NodeTopo();    //����ڵ�ṹ
		
		m_trace.Save_EdgeTopo();    //����߽ṹ
	}
	
	public void init()  //��ʼ�����������˽ṹ��
	{
	      
	  generate( 10 );     
		    
	  save(); 
			
	}

	public void run(int duration){  //����
		
		for (int time=0; time<duration; time++){ 
		
			evolve();
			
			trace(time);  //����ʵ����		
		}	
	}
  
	public static void main(String[] args) {
		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);

	}
}

