/**
 * 
 */
package extend.scalefree;
import java.util.*;
/**
 * @author Allen
 * 
 */
public class JxScaleFreeSimuApplication {
<<<<<<< HEAD
=======

<<<<<<< HEAD
	ArrayList<JxScaleFreeNode> JoinInNetNode;  //�Ѽ�������Ľڵ㣨��ɵ�����
	
	public static Random random = new Random();

	JxScaleFreeNodeCollection m_nodes = new JxScaleFreeNodeCollection();  //�㼯��
	JxScaleFreeEdgeCollection m_edges = new JxScaleFreeEdgeCollection();  //�߼���	
	JxScaleFreeTrace m_trace = new JxScaleFreeTrace();   //����ṹ	
	Statement sta=null;	
	Connection con=null;
=======
	ArrayList<JxScaleFreeNode> JoinInNetNode;
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
	 
	JxScaleFreeNodeCollection m_nodes;   
	
	JxScaleFreeEdgeCollection m_edges; 
	
	JxScaleFreeTrace m_trace; 
	
<<<<<<< HEAD
	Random random;
=======
	 Random random ;
	   
     Statement sta ;
		
	 Connection con ;
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
		
	String str;
	 
	int packet_num;
	//��ʼ��
	public JxScaleFreeSimuApplication(){
		
		 m_trace = new JxScaleFreeTrace(); //����ṹ
		    
   	     m_edges = new JxScaleFreeEdgeCollection(); //�߼���(����������)
   	     
   	     m_nodes = new JxScaleFreeNodeCollection(); //�㼯��(����������)	
   	     
   		 random = new Random();
			
		 str = null;
	 
	}	
	
    public	void trace(int time) {
	
	    m_trace.Trace_Node( time ); //timeΪ�������
	    
	    m_trace.Trace_Edge( time ); 
		
	}
    
    public void load(){   //���ݿ⡪���ڴ�
		
		m_trace.Load_Edgetopo(str);
		
		m_trace.Load_Nodetopo(str);
	}

	public void init(){  //��ʼ�����������˽ṹ��
	      
      m_trace.generate( 10 );   
	   
	  m_trace.Open_Database();    //�����ݿ�
		
	  m_trace.Save_NodeTopo(m_nodes.count());    //����ڵ�ṹ
		
	  m_trace.Save_EdgeTopo();    //����߽ṹ
	  		
	}
	
<<<<<<< HEAD
	public void exit(){
		
		m_trace.CloseDatabase();
		
=======
	public void init()  //��ʼ�����������˽ṹ��
	{
<<<<<<< HEAD
		String database=null;   //��ʼ��
		
		con=m_trace.openDatabase( database );  //�����ݿ�
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //����ڵ�ṹ
=======
	      
	  generate( 10 );     
		    
	  save(); 
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
			
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
	}
<<<<<<< HEAD
 
	void run( int duration )  //����
	{
		for (int t=0; t<duration; t++)
		{
=======

	public void run(int duration){  //����
		
		for (int time=0; time<duration; time++){ 
		
<<<<<<< HEAD
		    m_trace.evolve();
=======
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
			evolve();
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42
			
			trace(time);  //����ʵ����		
		}	
	}
<<<<<<< HEAD


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
=======
  
	public static void main(String[] args) {
		
		JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication();
		 
		app.init();
		
		app.run(10);
<<<<<<< HEAD
		
		System.out.println("success !");
=======
>>>>>>> d8348f5646ad0060ddd73b7a91004eb6ffbecfb1
>>>>>>> 1fc120008ba004ccddef982d9fde31e252cd5b42

	}
}

