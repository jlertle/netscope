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
			
		 str = null;
	 
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
	
		String database=null;   //��ʼ��
		
		sta=m_trace.openDatabase( database );  //�����ݿ�
		   
		m_trace.Save_NodeTopo(con,m_nodes);    //����ڵ�ṹ
		
	    topo.generate( 10 );     
		    
	    topo.save(); 	
	}

	public void run(int duration){  //����
		
		for (int time=0; time<duration; time++){ 

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

