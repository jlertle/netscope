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

	int count() { //���ؽڵ���
		return super.size();
	}
	
	public JxScaleFreeNode get(int index) //�õ�������Ϊindex�Ľڵ�
	{
		return super.get(index);
	}
	
	public void add(int index, JxScaleFreeNode node )//��ӽڵ�
	{
		super.add(node); 
	}
	
	public int indexOf(JxScaleFreeNode node)//ָ���ַ���һ�γ���ʱ������
	{
		return super.indexOf(node);
	}
	
	public JxScaleFreeNode search( int id )//�����ڵ�
	{
		// todo
		return null;
	}
	
	public void clear()//����ڵ�
	{
		super.clear();
	}
}
