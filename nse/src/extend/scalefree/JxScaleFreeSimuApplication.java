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
	 
	JxScaleFreeNodeCollection m_nodes;   
	
	JxScaleFreeEdgeCollection m_edges; 
	
	JxScaleFreeTrace m_trace; 
	
	Random random;
		
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
	
	public void exit(){
		
		m_trace.CloseDatabase();
		
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

