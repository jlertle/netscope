/**
 * 
 */
package extend.scalefree;
import java.util.*;
import java.util.Random;
import kernel.JxBaseEvent;
import kernel.JxBaseEventQueue;
import kernel.JxBaseSimulator;
/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {

	ArrayList<JxScaleFreeNode> JoinInNetNode ;  //�Ѽ�������Ľڵ㣨��ɵ�����
	/** 
	 * This is a static reference to a Random instance.
	 * This makes experiments repeatable, all you have to do is to set
	 * the seed of this Random class. 
	 */
	public static Random random = new Random();
	
	/**
	 * This defines the time resolution. Every time and time interval
	 * in the simulator is represented in this resolution. This rate
	 * corresponds to the 38.4 kbps speed, but maybe a little friendlier.
	 */
	public static final int ONE_SECOND = 40000;
	
	/** Holds the events */
	private JxBaseEventQueue eventQueue = new JxBaseEventQueue();
	
	/** The time of the last event using the given resolution */
	long lastEventTime = 0; 
	
	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //�㼯��
	
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //�߼���
	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();
	
	void init()  //��ʼ��
	{
     // m_trace.open("database"); //�����ݿ�
		generate( 10000 );
	}
	
	void generate( int nodecount )   //��������
	{		
		int i, x, y;
		JxScaleFreeEdge edge;   //��
		JxScaleFreeNode node;   //�ӵ�
		
		
		for (i=0; i<10000; i++){
			x = random.nextInt(100);
			y = random.nextInt(100);
			m_nodes.add( new JxScaleFreeNode( x, y, 100 ));//����½ڵ㣨100����������			
		}
	
        // Create edge and add them into the edge collection.
		
		    node = m_nodes.get(0);  //��һ����(��Ϊ��ʼ��)                
	        JoinInNetNode.add(node);   
	        node.setDegree(1);      //��ʵʱ���½ӵ�Ķȣ�
		
	    for (i = 1; i<m_nodes.count(); i++)//(���ڵ��Ϊ1-9999�Ľڵ����μ�������)
		{   
			
	    	JxScaleFreeNode cur_node=new JxScaleFreeNode();
			JxScaleFreeNode select_node=new JxScaleFreeNode();
		  
			
		    cur_node = m_nodes.get(i);       //��ǰ�ڵ�
		  
			select_node =selectnodeto(i-1);  //ѡ����һ�ڵ�
            
			JoinInNetNode.add(cur_node);     //��ǰ���������
			cur_node.setDegree(cur_node.degree()+1);//��ǰ��Ķȼ�1
			
			JoinInNetNode.add(select_node); //ѡ�е��������
			select_node.setDegree(select_node.degree()+1);//ѡ�е�Ķȼ�1
			
			edge = new JxScaleFreeEdge( cur_node.get_nodeid(),select_node.get_nodeid(), 10, 0 ); //�±�(��㣬�յ㣬����Ȩֵ)
			m_edges.add(edge);
		}
	}
	
	protected JxScaleFreeNode selectnodeto(int b) { //a=0,b=i-1,p
		
		int i; 
		int p = random.nextInt(JoinInNetNode.size());//������0�����б���֮�������ֵ
	   
		return JoinInNetNode.get(p); //����ѡ�е�
	    
	}
	
	
	
	void evolve()
	{
		int i;
		JxScaleFreeNode node1, node2;
		
		for (i=0; i<m_nodes.count(); i++)
		{
			node1 = m_nodes.get(i);
			node2 = select_neighbor( node1 );
			
			// move some packets from node1 to node2
			
			// trace
		}
	}
	
	
	void load()
	{
		// load edges;
		// load nodes
	}
	
	void save()
	{
		// save edges;
		// save nodes		
	}
 
	void run( int duration )  //����
	{
		for (int t=0; t<duration; t++)
		{
			evolve();
		}
	}

	/**
	 * @return Returns the time of the last event in 1/ONE_SECOND
	 */
	public long getSimulationTime() { 
		return lastEventTime;
	}

	/**
	 * @return Returns the time of the last event in milliseconds.
	 */
	public long getSimulationTimeInMillisec() {     
		return (long)(1000 * lastEventTime / (double)JxBaseSimulator.ONE_SECOND);
	}

	/**
	 * Adds an event to the event queue.
	 *  
	 * @param e the event to be added to the queue
	 */
	public void addEvent(JxBaseEvent e) {
		getEventQueue().add( e );        
	}

	/**
	 * Processes and executes the next event. 
	 */
	public void step() {
		JxBaseEvent event = (JxBaseEvent)getEventQueue().getAndRemoveFirst();
		if( event != null ){
			lastEventTime = event.time;
			//event.execute();
			trace();
		}
	}

	/**
	 * Processes and executes the next "n" event.
	 * 
	 * @param n the number of events to be processed
	 */
	public void step(int n) {
		for( int i=0; i<n; ++i )
			step();        
	}

	/**
	 * Runs the simulation for a given amount of time.
	 * 
	 * @param timeInSec the time in seconds until the simulation is to run
	 */
	public void run(double timeInSec) {         
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

	void trace()
	{
		//save this step into trace file
	}

	/**
	 * Clears the List of nodes.
	 */
	public void clear() {
		m_nodes.clear();
		m_edges.clear();
	}

	/**
	 * Get the event queue
	 * @return eventQueue in the simulator.
	 */
	public JxBaseEventQueue getEventQueue() { //�õ��¼�����
		return eventQueue;
	}	
}

