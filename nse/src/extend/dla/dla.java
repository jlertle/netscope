package extend.dla;
/*�˲��֣���1000*1000�������ϰ�DLA��ʽ����10����ڵ㡣
  **/
import java.util.*;
import java.math.*;
public class dla {
int i,j;               //��������
int highorlow;       
int radius;          
int distance;
int next;
int l3,driver,graphmode;
long l1,l2;                         //l1:�������
int grids[][]=new int[1000][1000];
Random random=new Random();
public void dla()
{
   for(i=0;i<1000;i++)
	{
	   for(j=0;j<1000;j++)
	     grids[i][j]=0;  	
	}
	grids[500][500]=1;	        //���ĵ���1
	
	for(l1=0;l1<100000;l1++)    //�ͷ�10�������
	{
		i=random.nextInt(1000);
		j=(int)Math.sqrt(radius*radius-(i-500)*(i-500));
		highorlow=random.nextInt(2);
		if(highorlow==0)
		{
			j=500+j;
		}
		else
		{
		   j=500-j;
	    }
	}
}
	public void NextStep()       //��ǰ�˶�һ��
   { 
	next=random.nextInt(4);
	  if(next==0)
		  i=i+1;
	  if(next==1)
		  i=i-1;  
	  if(next==2)
		  j=j+1;
	  if(next==3)
		  j=j-1;
	}
	public int Distacnce()       //�����ĵ�ľ���
	{
	  distance=(int)Math.sqrt((i-500)*(i-500)+(j-500)*(j-500)); 
	      return distance;
	}
	boolean aggregation=false;
	public boolean aggregation()   //����
	{ 
	  if((grids[i+1][j]|grids[i-1][j]|grids[i][j-1]|grids[i][j+1])==1)
		    aggregation=true;
		  return aggregation;
	}
	 public void main()
	 {dla();
	 aggregation();
	 }
}

