package extend.dla;
import java.util.*;
import extend.cellular.Node;
public class scalefree {
	int i=0,j=0;
	int id=0;
    SFNode sfnode[]=new SFNode[10000];  
    Edge edge[]=new Edge[10000];
    Random random=new Random(); 
	public void gen_scalefree_topo() //�����ṹ
	{ 
	  for(i=0;i<100;i++)
	  {
		for(j=0;j<100;j++)    //����10000����
	    {               
		  sfnode[id++].loc_x= random.nextInt(100); 
		  sfnode[id++].loc_y = random.nextInt(100);
	    }
		
      }
 }
	public void save_node_topo()  //�����ṹ
	{
	   	
	}
	public void save_edge_topo()  //����߽ṹ
	{
	 	
	}
	public void save_node_packetlength() // �����-������
	{
		 
	}
	public void load_node_topo() //���ؽڵ�ṹ
	{
		
	}
	public void load_edge_topo() //���ر߽ṹ
	{
	  	
	}
	public void load_node_packetlength()//���ؽڵ������
	{
		
	}
	public void  run()
	{
		
	}
  }

