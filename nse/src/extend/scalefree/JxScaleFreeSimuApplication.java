/**
 * 
 */
package extend.scalefree;
import java.sql.Connection;
import java.util.*;
import kernel.JxBaseEvent;
import kernel.JxBaseEventQueue;
import kernel.JxBaseSimulator;
import java.sql.Statement;
/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode;  //�Ѽ�������Ľڵ㣨��ɵ�����
	
	public static Random random = new Random();

	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //�㼯��
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //�߼���
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //����ṹ
	
	Statement sta=null;
	
	Connection con=null;
		
	//��ʼ��
	
	void init()  
	{

		String database=null;
       
	    generate( 10000 );                     
		
	}
	
	void generate( int nodecount ) //��������
	{		
		  int i, x, y;
		
		  JxScaleFreeEdge edge;   //��
		
		  JxScaleFreeNode node;   //�ӵ�
		
	    for (i=0; i< nodecount; i++){
			
			x = random.nextInt(100);
			
			y = random.nextInt(100);
			
			m_nodes.add( new JxScaleFreeNode( x, y, 100 ));//����½ڵ㣨100����������
			
		}
	
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
	     
	 //ѡ��ߵ�ĩ�ڵ�
	protected JxScaleFreeNode selectnodeto(int b) {  
	
		int p = random.nextInt(JoinInNetNode.size());//������0�����б���֮�������ֵ
	   
		return JoinInNetNode.get(p); //����ѡ�е�
	    
	}
	
	
	//ÿһʱ��ͬһ���ϵ����ڽڵ㽻���������ĸ��������
	void evolve()
	{
		int i;
		
		JxScaleFreeEdge edge;
		
		JxScaleFreeNode node1, node2;
		
		JxScaleFreeNode  sender=new JxScaleFreeNode();  
		
		JxScaleFreeNode  receiver=new JxScaleFreeNode(); 

		for (i=0; i<m_edges.count(); i++)
		{
			edge = m_edges.get_edge(i);
			
			node1=m_nodes.get_node(edge.nodefrom());  //(�õ���Ӧ�ĵ�(���id��))
			
			node2 = m_nodes.get_node(edge.nodeto());  //�õ���Ӧ�ĵ�(�յ�id��)
			
			int r=random.nextInt(2);     //���ѡ���ͷ���
			     
			if(r==0){
			         
			    	  sender=node1;
				      
			    	  receiver=node2;
			      }
		    if(r==1){
			    	  
			    	  sender=node2;
			    	  
			    	  receiver=node1;
			      }
			 
		   int packet_num=Minimum(sender.length(),(receiver.capacity()-receiver.length()),
				   edge.bandwidth());  //�õ����ݵİ�����
		   
		   packet_num+=packet_num;
		   
		   edge.set_packetsum(packet_num); //��¼���ϰ�������
		   
		   sender.set_length(sender.length()-packet_num); 
		   
		   receiver.set_length(receiver.length()+packet_num);
		}
	}
	
	//������ֵ�е���Сֵ
	public int  Minimum(int sender_length,int receiver_capacity,int band_with){ //���Ͱ��ĸ���ҪС��������ֵ
		
		int minimum=sender_length;
		
		if(receiver_capacity<minimum)
		
			minimum=receiver_capacity;
		
		if(band_with<minimum)
		
			minimum=band_with;
		
		minimum=random.nextInt(minimum);  //���������⣨������
		
		return minimum;
	}
	
	void trace()
	{
	//save this step into trace file(�������־�ļ���)
		
	}
	
	void load()  //���ݿ⡪���ڴ�
	{
		// load edges;
		m_trace.Load_Edgetopo();
		// load nodes
		m_trace.Load_Nodetopo();
	}
	
	void save()  //�ڴ桪�����ݿ�
	{
		String database=null;   //��ʼ��
		
		con=m_trace.Open_Database( database );  //�����ݿ�
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //����ڵ�ṹ
			
		m_trace.Save_EdgeTopo(con,m_edges);    //����߽ṹ
	}
 
	void run( int duration )  //����
	{
		
		
		for (int t=0; t<duration; t++)
		{
			evolve();
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

	/**
	 * Runs the simulation for a given amount of time.
	 * 
	 * @param timeInSec the time in seconds until the simulation is to run
	 */
	/**public void run(double timeInSec) {         
		long tmax = lastEventTime + (int)(JxBaseSimulator.ONE_SECOND * timeInSec);                 
		while( lastEventTime < tmax )
	    {
	        JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
	        if( event != null ){
	            lastEventTime = event.time;
	            
	            
	            //event.execute(); 
	        }
	        else
	            break;
	    }
	}
*/
	

	/**
	 * Clears the List of nodes.
	 */
	/*public void clear() {
		m_nodes.clear();
		m_edges.clear();
	}*/

	/**
	 * Get the event queue
	 * @return eventQueue in the simulator.
	 */
	/*public JxBaseEventQueue getEventQueue() { //�õ��¼�����
		return eventQueue;
	}*/	
	
	
}

