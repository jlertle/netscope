package kernel;

import java.util.ArrayList;

public class JxSimulator {

	JiNode node;
	JiRelation rel;
	JiInteraction interact;
	JiTrace trace;
	
	JxNodeCollection node_collection;
	JxEdgeCollection edge_collection;
	
	
	JxSimulator(){	
		for (m_relationset){
			interact( m_relationset.current());
		}	
	}
	
	void step()	{
		
	}
	
	void run(){
		generate();
		interact();
		save_node();
		save_edge();
		trace_node();
		trace_edge();
		load_node();
		load_edge();
	}
}
