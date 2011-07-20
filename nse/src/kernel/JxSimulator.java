package kernel;

public class JxSimulator {
    
	/**��*/
    JiNode node= new JxStdNode(); 
  
    /**��*/
	JiRelation relation=new JxStdRelation();
	
	/**�໥����*/
	JiInteraction interaction=new JxStdInteraction();
	
	/**���*/
	JiTrace trace=new JxStdTrace();
	
	int nodecount=10;
   
	/**�㼯*/
	JxNodeCollection nodeset = new JxNodeCollection();
	
	/**�߼�*/
	JxRelationCollection relationset = new JxRelationCollection();
	
	/**���캯��*/
	public JxSimulator(){
    }
	
	JxSimulator(JxNodeCollection NodeSet,JxRelationCollection relationset,JxStdInteraction Interaction){	
		this.nodeset=NodeSet;
		this.relationset=relationset;
		this.interaction=Interaction;	
	}
	
	void run()
	{
		/**�������� */
		relationset.generateGraph(nodecount);
		
		/**�����ݿ�*/
		trace.openDatabase();
		
		/**����ߺͽڵ�ṹ */
		trace.saveNode();
		trace.saveEdge();
		
		int experienttime=3;
		for(int i=0;i<experienttime;i++){
			
		 	interaction.interact();
		    trace.traceNode(i);
		    trace.traceEdge(i);
		}
		trace.closeDatabase();
		System.out.println("everything is ok");
	}
	
	public static void main(String []args){	
		JxSimulator simulator=new JxSimulator();	
		simulator.run();
		/**
		JxNodeCollection nodeset = new JxNodeCollection();
		JxRelationCollection relationset = new JxRelationCollection();
		JxStdInteraction interaction = new JxStdInteraction();
		
		relationset.generate();
		
		JxSimulator simulator=new JxSimulator(nodeset, relationset, interaction);
	
		for (int i=0; i<500; i++)	
		simulator.run();
	}	*/	
  }
}
