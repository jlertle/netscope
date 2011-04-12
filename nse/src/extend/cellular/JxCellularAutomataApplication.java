package extend.cellular;                        

import trace.JxDbConnection;

public class JxCellularAutomataApplication 
{
    int vel_in=5;           //�����ٶȹ̶�Ϊ5
    int vel_inc=2;          //�ٶ�����Ϊ2
    int time=0;             //ʱ��
    int id=0;               //�ڵ��   
    
    Node node[]=new Node[3];              //�ڵ�����
    String link []=new String[3];         //�����飬ʹ������ǰһ��Ҫ������
    JxDbConnection dbconnect=new JxDbConnection();  //JDBC�࣬ʹ���µ���֮ǰһ��Ҫ����new����
    String str1;
    String str2;
    
   
    public void init()                  
    {
    	;
	}
   public void step()                        //ÿһ�ζ����нڵ�ǰ��һ��
	{  
      try
      {
    	for(id=0;id<3;id++)
	   {  //  node[id]=new Node();           //ֻ���������޷�ʹ�ã����������ÿ��Ԫ�ظ���ֵ
    		if(id==0)                        //Դ�ڵ�
    	   {  
       		    if(vel_in>(node[id].que_capacity-node[id].que_length))   // �ⲿ�����Ƿ����
		      {
       		     node[id].que_length=node[id].que_capacity;               //�����󣬶��б���
       		     
		      }  else 
	            node[id].que_length+=vel_in;                             
       		   
               if(node[id].lastfailed==true)             //�ж���һ�η����Ƿ�ʧ��
	         { 
	           node[id].sendcount=node[id].sendcount/2;
	           node[id].que_length-=node[id].sendcount;   //����������һ����Լ���
	         } else
	         { 
	           node[id].sendcount+=vel_inc;
	           node[id].que_length-=node[id].sendcount;
	         }
    	   } else if(id==3)                               //�޽ڵ�   
    	     {  if(node[id-1].sendcount>(node[id].que_capacity-node[id].que_length)) //�޽ڵ���ܵ������Ƿ����
    	       {
 	   		     node[id-1].lastfailed=true;
 	   		     node[id].que_length=node[id].que_capacity;
 	           } else
 	           {
 	        	  node[id-1].lastfailed=false; 
 	        	  node[id].que_length+=node[id-1].sendcount;
 	    	      node[id].que_length-=node[id].sendcount;              //�޽ڵ�����ٶȹ̶�����
 	           }
 	         } 
    	     else                                                       //�м�ڵ�
    	   {  if(node[id-1].sendcount>(node[id].que_capacity-node[id].que_length)) //�ж���ǰһ�ڵ㷢���Ƿ�ʧ��
    	     {
	   		  node[id-1].lastfailed=true;
	   		  node[id].que_length=node[id].que_capacity;
	         }else    
	         { 
	           node[id-1].lastfailed=false;                         //ǰһ�ڵ㷢�ͳɹ�
	           node[id].que_length+=node[id-1].sendcount;
	         }
	         if(node[id].lastfailed==true)                         //�жϱ��ڵ��ϴη����Ƿ�ʧ��
	         {
	           node[id].sendcount=node[id].sendcount/2; 	           //��ʧ�����ٶȼ���
	           node[id].que_length-=node[id].sendcount;
	         }else                                               
	         {
	           node[id].sendcount+=vel_inc;                           //���ɹ��ٶ�����
	           node[id].que_length-=node[id].sendcount;
	         }
    	   }
   	          if(id<3)                                              // �ߵ������Ƚڵ�����1
	          {    
	        	  node[id].packet_sum+=node[id].sendcount;          //ÿ����������ܰ���
		          str1= Integer.toString(id);  
		          str2= Integer.toString(id+1); 
		          link[id]=str1+" "+str2;                           //ÿ���ߵı��    
		       }	           
	    }
      }catch( NullPointerException e)
      {
    	  
    	  ;
      }
   }  
	
	
    public void run()                                     //����10��
   { /*
    try
	  { dbconnect.opendatabase("testdbx.script");
	    dbconnect.createnodetable();                     // �����ڵ��   
	    dbconnect.createlinktable();                     // �����߱�
        for(time=0;time<10;time++);
		{   
			step();
			System.out.println("I Love You");            //print���������,println������С�
		  //System.out.println(node[id].que_length);
			for(id=0;id<3;id++)
			{
				System.out.println(node[id].que_length);
				System.out.println(node[id].packet_sum);
				dbconnect.insertnodetable(time, id, node[id].que_length);
				dbconnect.insertlinktable(time, link[id], node[id].packet_sum);
			}
		 }
	   }catch(NullPointerException e)
	    {
		    ;
	    }
	    */
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       JxCellularAutomataApplication linesimu = new JxCellularAutomataApplication();
       linesimu.run();
   
       }

}
