package extend.fractal;
/*�˲��֣���1000*1000�������ϰ�DLA��ʽ����10����ڵ㡣
  **/
import java.util.*;
import java.math.*;
public class Fractal {
int i=0,j=0,t=0,n=0;  
int loc_x,loc_y;  //��������
int upordown;       
int radius;   //       
int distance;
int forward;
int l3,driver,graphmode;
long l1,l2;        //l1:�������
int grids[][]=new int[1000][1000]; //����1000000�������
Random random=new Random();
JiNetworkTopology genMeshTopology(int Radius) 
{
  radius=Radius;
  for(i=0;i<1000;i++)
  {
    for(j=0;j<1000;j++)
    grids[i][j]=0;  	   
  }   
   grids[500][500]=1;   //���ĵ���1
  
  while(t<100000)
 {//�ͷ�һ������
	loc_x=random.nextInt(1000);   //�ͷŵ�ĺ�����                
	loc_y=(int)Math.sqrt(radius*radius-(loc_x-500)*(loc_y-500)); //���ͷŵ��������
	upordown=random.nextInt(2);   //���������ĵ���ϲ���²�
		if(upordown==0)
		{
			loc_y=500+loc_y;        
		}
		else
		{
			loc_y=500-loc_y; 
	   }
	 if(grids[loc_x][loc_y+1]==1|grids[loc_x][loc_y-1]==1|grids[loc_x+1][loc_y]==1|grids[loc_x-1][loc_y]==1)
		continue;               //�뾶Բ�ϵĵ㲻�ܱ���1
	 while(true)
	{
	  forward=random.nextInt(4); //��ǰ�˶�һ��
	  if(forward==0)
		 loc_x=loc_x+1;           
	  if(forward==1)
		  loc_x=loc_x-1; 
	  if(forward==2)
		  loc_y=loc_y+1;
	  if(forward==3)
		  loc_y=loc_y-1;
	 distance=(int)Math.sqrt((i-500)*(i-500)+(j-500)*(j-500)); 
	 if(distance>radius)
		continue;
	 if(!(grids[loc_x][loc_y+1]==1|grids[loc_x][loc_y-1]==1|grids[loc_x+1][loc_y]==1|grids[loc_x-1][loc_y]==1))
		continue; 
	 else{
		  grids[loc_x][loc_y]=1;
		  node[n++].loc_x=loc_x;
		  node[n++].loc_y=loc_y;
		  break;
	 }
	 
  }  	
			
		}
	}
	
		public int Distacnce()       //�����ĵ�ľ���
		{
		
		      return distance;
		}
		boolean aggregation=false;
		public boolean aggregation()   //����
		{ 
		  if((grids[i+1][j]|grids[i-1][j]|grids[i][j-1]|grids[i][j+1])==1)
			    aggregation=true;
			  return aggregation;
		}
}



