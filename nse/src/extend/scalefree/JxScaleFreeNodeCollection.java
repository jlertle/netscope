package extend.scalefree;
import java.util.ArrayList;
public class JxScaleFreeNodeCollection extends ArrayList<JxScaleFreeNode>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//private static final long serialVersionUID = 6512692821729075017L; 
    //ArrayList<JxScaleFreeNode> nodelist; 
	int count() {  //���ؽڵ���
		
		return super.size();
	}
	
	public JxScaleFreeNode get_node(int index) //�õ�������Ϊindex�Ľڵ�
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node )//��ӽڵ�
	{
		super.add(index,node); 
	}
	
	public int indexOf(JxScaleFreeNode node)//ָ���ַ���һ�γ���ʱ������
	{
		return super.indexOf(node);
	}
	
	public JxScaleFreeNode search( int id )
	{
	  
		return null;
	}
	
	public void clear()  //����ڵ�
	{
		super.clear();
	}
}
