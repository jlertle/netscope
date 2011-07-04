package extend.scalefree;

import java.util.ArrayList;

public class JxScaleFreeEdgeCollection extends ArrayList<JxScaleFreeEdge>{

	
	public int count() {		
		return super.size();		
	}
	
	
   	public boolean  add( int nodefrom, int nodeto ){	
		JxScaleFreeEdge edge = new JxScaleFreeEdge(nodefrom, nodeto);
		return super.add( edge );
	}
    
   	
	public JxScaleFreeEdge get_edge(int id){ //得到相应的边
		return super.get(id); 
	}
	
	
	/** get JxScaleFreeEdge object at specified position with index */
	public JxScaleFreeEdge get(int index){ 
		return super.get(index); 
	}
	
	
	public JxScaleFreeEdge search(int id){
		boolean found = false;
		JxScaleFreeEdge edge = null;
		for (int i=0; i<super.size(); i++){
			edge = this.get(i);
			if (edge.id() == id){
				found = true;
				break;
			}
		}
		return (found ? edge: null);
	}
	
	
	public JxScaleFreeEdge search(int nodefrom, int nodeto){
		boolean found = false;
		JxScaleFreeEdge edge = null;
		for (int i=0; i<super.size(); i++){
			edge = this.get(i);
			if ((edge.nodefrom() == nodefrom) && (edge.nodeto() == nodeto)){
				found = true;
				break;
			}
		}
		return (found ? edge: null);
	}	
	
	
	public ArrayList<JxScaleFreeEdge> out_edges_of( int nodefrom ){		 		
		ArrayList<JxScaleFreeEdge> out_edge_list =new ArrayList<JxScaleFreeEdge>();
		JxScaleFreeEdge edge=null;
		for(int i=0;i<super.size();i++){
		   edge=this.get(i);
		   if(edge.m_nodefrom==nodefrom){
			   
		   }
		}
		return null;
	}
	
	
	public ArrayList<JxScaleFreeEdge> in_edges_of( int nodeto ){
		return null;
	}
	
	
	public ArrayList<JxScaleFreeEdge> edges_of( int nodeid){
		return null;
	}
	
	
	public ArrayList<JxScaleFreeNode> neighbors_of( int nodeid ){
		return null;
	}
	
	
}
