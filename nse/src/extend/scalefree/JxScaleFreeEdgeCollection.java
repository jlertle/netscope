package extend.scalefree;
import java.util.ArrayList;
public class JxScaleFreeEdgeCollection extends ArrayList<JxScaleFreeEdge>{
	/**
	 * 
	 */
	//JxscaleFreeEdge edge=new JxscaleFreeEdge();
	public int count() {
		return super.size();//�������
	}
	public JxScaleFreeEdge get_edge(int edge_id){ //�õ���Ӧ�ı�
		return super.get(edge_id); 
	}
}
