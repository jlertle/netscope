package extend.scalefree;

public class JxScaleFreeEdge {
	
	private int edgeid;      
	private int nodefrom;   
	private int nodeto;     
	private int bandwith;  
	private int weight;        
	private int distance; 
	private int packetsum;
	
	
	public JxScaleFreeEdge(){//���캯��1 
		
		super();
		this.nodefrom = 0;
		this.nodeto = 0;
		this.bandwith = 0;
		this.weight = 0;
	}

	public JxScaleFreeEdge(int edgeid,int nodefrom, int nodeto, int bandwith, int weight){//���캯��2
		
		super();
		this.edgeid=edgeid;
		this.nodefrom =nodefrom;
		this.nodeto = nodeto;
		this.bandwith = bandwith;
		this.weight = weight;
	}

	@Override //����
	public int hashCode() {   //��ϣ�� ��Ĭ�ϵ��ã�
		final int prime = 31;
		int result = 1;
		result = prime * result +nodefrom;
		result = prime * result + nodeto;
		return result;
	}

	@Override
	public boolean equals(Object obj) { //Ĭ�ϵ���
		if (this == obj) //�Ƿ�ָ��
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JxScaleFreeEdge other = (JxScaleFreeEdge) obj;
		if (nodefrom != other.nodefrom)
			return false;
		if (nodeto != other.nodeto)
			return false;
		return true;
	}

	@Override
	public String toString() { //Ĭ�ϵ���
		return "JxScaleFreeEdge [m_nodefrom=" +nodefrom + ", nodeto=" + nodeto
				+ "]";
	}
/*
	double distance()
	{
		JxScaleFreeNode node1 = JxScaleFreeNodeCollection.search(m_nodefrom);
		JxScaleFreeNode node2 = JxScaleFreeNodeCollection.search(nodeto);
		
		return Math.sqrt(((node1.x() - node2.x()) * (node1.x() - node2.x())
				+ (node1.y() - node2.y()) * (node1.y() - node2.y())));
		
	}
*/	
	public int get_edgeid() {  
		return edgeid;
	}
	public void set_edgeid(int id) {  
		 edgeid=id;
	}
	public int get_nodefrom(){
		return nodefrom;
	}
	public void  set_nodefrom(int node_from){
		 nodefrom=node_from;
	}
	public int get_nodeto(){
		return nodefrom;
	}
	public int set_nodeto(int node_to){
		return nodeto=node_to;
	}
	public int get_bandwidth() {        
		return bandwith;
	}
	
	
	public int distance() {        //
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
    public int get_packetsum(){
		return packetsum;
	}
    public void  set_packetsum(int sum){
    	packetsum=sum;
    }

}
