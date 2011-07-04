/**
 * 
 */
package extend.scalefree;

import java.util.*;
import java.sql.Statement;
import java.sql.Connection;

public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode;  //�Ѽ�������Ľڵ㣨��ɵ�����
	
	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //�㼯��
	
	JxScaleFreeTopology  topo =new JxScaleFreeTopology(); 
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //�߼���	
	
	JxScaleFreeTopology m_topo= new JxScaleFreeTopology();
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //����ṹ	

	Statement sta=null;	
	Connection con=null;

	 String str;

	
	public static Random random = new Random();
	
	Statement sta=null;	
	
	Connection con=null;
		
	String str;

	 
	int packet_num;
	//��ʼ��
	public JxScaleFreeSimuApplication(){
		
		 m_trace = new JxScaleFreeTrace(); //����ṹ
		    
   	     m_edges = new JxScaleFreeEdgeCollection(); //�߼���(����������)
   	     
   	     m_nodes = new JxScaleFreeNodeCollection(); //�㼯��(����������)	
   	     
   		 random = new Random();
			

			edge = m_edges.get_edge(randomrank(m_edges.count()).get(i)); //����õ�һ����
			
			sender = m_nodes.search(edge.nodefrom()); 
			
			receiver = m_nodes.search(edge.nodeto()); 
			
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

		 str = null;
>>>>>>> 5132d5ba41ba3e385b1f5813952847a36767b779
	 
	}	
	
    public	void trace(int time) {
	
	    m_trace.Trace_Node( time );   //timeΪ�������
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){   //���ݿ⡪���ڴ�
		
		m_trace.Load_Edgetopo(str);
		
		m_trace.Load_Nodetopo(str);
	}
	
	public void exit(){
		
		m_trace.CloseDatabase();
		
	}
	public void init() {  //��ʼ�����������˽ṹ��
	

	public void init()  //��ʼ�����������˽ṹ��
	{
		String database=null;   //��ʼ��
		/* todo
		con=m_trace.openDatabase( database );  //�����ݿ�
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //����ڵ�ṹ
		*/
	      
	  generate( 10 );     	    
	  save(); 

		String database=null;   //��ʼ��
		
		sta=m_trace.openDatabase( database );  //�����ݿ�
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //����ڵ�ṹ
		
	    topo.generate( 10 );     
		    
	    topo.save(); 	

	}

	public void run(int duration)
	{  //����
		
		for (int time=0; time<duration; time++){ 

			evolve();
			trace(time);  //����ʵ����		
		}	
	}


	/**
	 * This defines the time resolution. Every time and time interval
	 * in the simulator is represented in this resolution. This rate
	 * corresponds to the 38.4 kbps speed, but maybe a little friendlier.
	 */
	//public static final int ONE_SECOND = 40000;
	
	/** Holds the events */
    //private JxBaseEventQueue eventQueue = new JxBaseEventQueue();
	
	/** The time of the last event using the given resolution */
	//long lastEventTime = 0; 
	/**
	 * @return Returns the time of the last event in 1/ONE_SECOND
	 */
	/**public long getSimulationTime() { 
		return lastEventTime;
	}*/

	/**
	 * @return Returns the time of the last event in milliseconds.
	 */
	/*public long getSimulationTimeInMillisec() {     
		return (long)(1000 * lastEventTime / (double)JxBaseSimulator.ONE_SECOND);
	}*/

	/**
	 * Adds an event to the event queue.
	 *  
	 * @param e the event to be added to the queue
	 */
	/**public void addEvent(JxBaseEvent e) {
		getEventQueue().add( e );        
	}*/

	/**
	 * Processes and executes the next event. 
	 */
	/**public void step() {
		JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
		if( event != null ){
			lastEventTime = event.time;
			//event.execute();
			trace();
		}
	}*/

	/**
	 * Processes and executes the next "n" event.
	 * 
	 * @param n the number of events to be processed
	 */
	/**public void step(int n) {
		for( int i=0; i<n; ++i )
			step();        
	}*/
  
	public static void main2(String[] args) {


		    m_trace.evolve();

			trace(time);  //����ʵ����		
		}	
	}

	public static void main(String[] args) {

		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);


		
		System.out.println("success !");

	}
}

