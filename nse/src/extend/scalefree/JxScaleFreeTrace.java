package extend.scalefree;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

import java.sql.ResultSet;

import java.util.*;

import java.text.*;

public class JxScaleFreeTrace {
	
    JxScaleFreeNodeCollection m_nodes=new JxScaleFreeNodeCollection();  //(���ڱ����Ľ��) 
    
    ArrayList<JxScaleFreeNode> JoinInNetNode=new ArrayList<JxScaleFreeNode>(); // �Ѽ�������Ľڵ㣨��ɵ�����
	
    JxScaleFreeEdgeCollection m_edges=new JxScaleFreeEdgeCollection();  //(���ڱ���ߵĽ��)
    
	JxScaleFreeNodeCollection m_nodesload=new JxScaleFreeNodeCollection(); 
	
	JxScaleFreeEdgeCollection m_edgesload=new JxScaleFreeEdgeCollection();
	
	ArrayList<Integer> m_array = new ArrayList<Integer>();
 	
	JxScaleFreeNode node= null; 
	
	JxScaleFreeEdge edge= null;
	
	Connection con=null;
	
	Statement sta=null;
	
	ResultSet res =null;
	
    Boolean evertra_node =false;  
	
	Boolean evertra_edge =false;
	
	Random random= new Random();
	
	public void generate( int nodecount ){ //��������
		
	    int i,x,y;   
		
        JxScaleFreeEdge edge=new JxScaleFreeEdge(); //��   
		
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
			
			ArrayList<Integer> node_array =Randomrank(m_nodes.count());
		 
		    if(i==0){  //��һ��ֱ�ӽ���һ���ڵ��������
		       
		    	cur_node=m_nodes.get(node_array.get(i));
		    	
		    	JoinInNetNode.add(cur_node); 
		    	 
		    	cur_node.set_degree(cur_node.degree()+1); 
		    }
		    else {
		    	
			cur_node = m_nodes.get(node_array.get(i));
		    
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
  
	//ÿһʱ��ͬһ���ϵ����ڽڵ㽻���������ĸ��������   
	public void evolve(){
	try{
			JxScaleFreeEdge edge;
			
			JxScaleFreeNode sender, receiver;
			
			int packet_num=0;  //��¼���ϵİ�����
			
			ArrayList<Integer> edge_array=Randomrank(m_edges.count());

			for (int i=0; i<m_edges.count(); i++){
				
				edge = m_edges.get_edge(edge_array.get(i));  //����õ�һ����
				
				sender = m_nodes.get_node(edge.nodefrom());  //(�õ���Ӧ�ĵ�(���id��)
				
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
	//����1-n��������в��ظ�������
    public ArrayList<Integer> Randomrank(int nodecount){
   
	 boolean[] bool =new boolean [nodecount];
      
	 int i;
	 
	 int num=0;
	 
	 if(m_array.size()>0) { //ÿ����������ʱ��Ҫ��֤m_array���ǿյġ�
		 
		 m_array.clear();
	 }
	 
	 for(i=0;i<nodecount;i++){ 
		 
     do{
		 
	    num = random.nextInt(nodecount); 
	  	
	 }while(bool[num]);
	 
	    m_array.add(num);
	  
	    bool[num]=true;
    } 
	 return m_array;
  }
	
	//�����ݿ�
	public Statement Open_Database()
	{
		try{
			 Class.forName("org.hsqldb.jdbcDriver"); //������� 

			 con= DriverManager.getConnection("jdbc:hsqldb:file:netscope2;shutdown=true", "sa", ""); //������Ϊnetscope�����ݿ�
			 
			 sta=con.createStatement();  //��������
			 
		     System.out.println("con is ok");
	       } 
		     catch (SQLException e){
              
		      e.printStackTrace();
	       }
		     catch (ClassNotFoundException e){
              
		      e.printStackTrace();
           }     
		return sta;    //�������� 
	}    	
	//�����ڵ������ڵ�ṹ  
    public void Save_NodeTopo(int m_nodeslength)
    {   
    	try{	        
     			   String node_topo = "node_topo" +currenttime();  //(���ݱ����в����пո�)
    			
    			   String str1 ="create table " + node_topo + "(NODEID INTEGER,LOC_X INTEGER,LOC_Y INTEGER)";
    		
    		       sta.executeUpdate(str1);         //�����ڵ�ṹ��         
    			
    			   for(int i=0;i<m_nodeslength;i++){
    				
    				    node= m_nodes.get(i);
    				
    			        String node_id=Integer.toString(node.id()); //ת��Ϊ�ַ���
    			
    			        String loc_x= Integer.toString(node.x());    
    			  
    			        String loc_y= Integer.toString(node.y());     
    			   			       
    			        String insert_nodetable = "Insert into  people(NODEID,LOC_X,LOC_Y) VALUES ("+node_id+","+loc_x+","+loc_y+")";  
    		
    			        sta.executeUpdate(insert_nodetable);	
    			        
    			        sta.close();
    			        
    			        System.out.println("savenode success!");
    			   }
		   } 
    	   catch (SQLException e){
               
           	e.printStackTrace();
           	
           }
      }
    //�����߱�����
    public void Save_EdgeTopo()
    {  
          try { 
     			String edge_topo = "edge_topo" + currenttime();  
    			
    			String str2 = "create table "+ edge_topo + "(EDGEID INTEGER,NODE_FROM INTEGER,NODE_TO INTEGER)";
    			
    			sta.executeUpdate(str2);       //�����߽ṹ��      
    				
    			for(int i=0;i<m_edges.count();i++){	
    				
    			     edge=m_edges.get(i);	
    				
    			     String edge_id=Integer.toString(edge.id());  //ת��Ϊ�ַ���
    			
    			     String node_from=Integer.toString(edge.nodefrom());    
    			
    			     String node_to=Integer.toString(edge.nodeto()); 
    			
    			     String insert_edgetable = "Insert into " +edge_topo +"(EDGEID,NODE_FROM,NODE_TO) VALUES ("+ edge_id +"," + node_from + "," + node_to+ ")";  
    				     
    			     sta.executeUpdate(insert_edgetable); 
    			     
    			}
    			   System.out.println("saveedge success!"); 
    			
    			   sta.close();
    		}  catch (SQLException e){
                
            	e.printStackTrace();    
            }    
    	}	
	
    public void Load_Nodetopo(String node_tablename) {  //���ؽڵ�ṹ 
    try{
    	
	   String select_nodetopo="select * from "+ node_tablename ; 
    		
       res =sta.executeQuery(select_nodetopo);
       
       int i=0;
    
       while(res.next()){
     
    	   m_nodesload.get(i++).set_id(Integer.parseInt(res.getString(1)));  //�õ��ڵ� ���
  
    	   m_nodesload.get(i++).set_x((Integer.parseInt(res.getString(2))));     //�õ��ڵ�X����
    	   
    	   m_nodesload.get(i++).set_y((Integer.parseInt(res.getString(2))));     //�õ��ڵ�y����
    	
        }
       res.close();
       
       sta.close();
       
      }catch(Exception e){
    	 
    	  e.printStackTrace();
      }	
	}
	
	public void Load_Edgetopo(String edge_tablename)  {//���ر߽ṹ
	try{
	    	
			   String select_edgetopo="select * from "+edge_tablename; 
		    		
		       res =sta.executeQuery(select_edgetopo);
		       
		       int i=0;
		  
		       while(res.next()){

		    	   m_edgesload.get(i++).set_id((Integer.parseInt(res.getString(1)))); //�õ��ڵ� ���

		    	   m_edgesload.get(i++).set_nodefrom((Integer.parseInt(res.getString(2))));    //�õ��ڵ�X����
		    	   
		    	   m_edgesload.get(i++).set_nodeto((Integer.parseInt(res.getString(3))));     //�õ��ڵ�y����
		    	
		    	   res.close();
		    	   
		    	   sta.close();
		      }
		      }catch(Exception e){
		    	 
		    	  e.printStackTrace();
		    	  
		      }   
	}
	
	public void Trace_Node(int time){ //���������( time: �� �� �� �� )	   	
	try{
		         String tracenode_table ="tra_node"+currenttime(); //�ڵ����ݱ���	      
		
		           if( evertra_node==false){  //��֤ÿ�νڵ����һ�� 
	 			
				        String str3 = "create table " + tracenode_table + "(EXP_TIME INTEGER,NODEID INTEGER,LENGTH INTEGER)";
				
				        sta.executeUpdate(str3);  //�����ڵ�ṹ�� //
				        
			            evertra_node = true;
			      }     
			   
			   String exp_time= String.valueOf(time); 
			
			   for(int i=0;i<m_nodes.count();i++){				
				
				    node= m_nodes.get(i);
				
			        String node_id=Integer.toString(node.id()); //ת��Ϊ�ַ���(�ڵ��)
			
			        String length= Integer.toString(node.get_length()); //(���ĳ���)
			     
		          //������ڵ����ݣ�
			       
			        String trace_node = "Insert into " + tracenode_table  + "(EXP_TIME,NODEID,LENGTH) VALUES ("+exp_time+","+node_id+","+length+")";  
		
			        sta.executeUpdate(trace_node);	  
			        
			        sta.close();
			        
			        System.out.println("tracenode success!");
			   }
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	} 
	
	public void Trace_Edge(int time){ //�������	
	try {
		
			if(evertra_node==false) { 
			
	       //��ֻ֤����һ�νڵ����ݱ�
				       
	 			       String traceedge_table = "tra_edge"+ currenttime(); //�����ݱ���
				
				        String str4= "create table " + traceedge_table+"(EXP_TIME INTEGER, EDGEID INTEGER,PACKETSUM INTEGER)";
				
				        sta.executeUpdate(str4);//�����ڵ�ṹ��   //
				        
				        evertra_node=true;
			}
 			  
 			String exp_time= String.valueOf(time); //�������
 			
 			for(int i=0;i<m_edges.count();i++){	
 				
 			     edge=m_edges.get(i);	
 				
 			     String edge_id=Integer.toString(edge.id());  //ת��Ϊ�ַ���
 			
 			     String packet_sum=Integer.toString(edge.get_packetsum());    
 			
 			     String insert_edgetable = "Insert into trace_edge(EXP_TIME,EDGEID,PACKETSUM) VALUES ("+exp_time+"," +edge_id+ "," + packet_sum+ ")";  
 				
 			     sta.executeUpdate(insert_edgetable);	
 			     
 			     sta.close();
 			     
 			    System.out.println("trace edge success!");
 			}
 		} catch (Exception e) {
 			
 			e.printStackTrace();
 		}		
	}
	
    public void CloseDatabase() //�ر����ݿ�
	{   
	   try {
		       sta.close();
		       
		       con.close();  
				
			} catch (SQLException e) {
						
			   con = null;
			}
		}
  
    public String currenttime() {
    	
    	 Date date = new Date();
    	
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm");//�������ڸ�ʽ(���ݱ��ʽ��Ҫ��)
    	 
    	 String cur_time =sdf.format(date);
    	 
    	 return cur_time;
    	 
    }
		
}
