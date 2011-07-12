package kernel;

// public class JxSimuApplication

public class JxSimulator {
    
	/**��*/
    JiNode node= new JxStdNode(); 
    
    /**��*/
	JiRelation relation=new JxStdRelation();
	
	/**�໥����*/
	JiInteraction interact=new JxStdInteraction();
	
	/**���*/
	JiTrace trace=new JxStdTrace();
	int nodecount=10;
	
    /**�㼯*/
	JxNodeCollection nodeset = new JxNodeCollection();
	
	/**�߼�*/
	JxRelationCollection relationset = new JxRelationCollection();
	
	/**���캯��*/
	JxSimulator(JxNodeCollection NodeSet,JxRelationCollection relationset,JxStdInteraction Interaction){	
		this.nodeset=NodeSet;
		this.relationset=relationset;
		this.interact=Interaction;	
	}

	
	void run(){

		/**�������� */
		
		nodes.generate( 10000 );
		relations.generate( 20000 );
		

		stdRelation.generateGraph(nodecount);
	
		
		/** ����ߺͽڵ�ṹ */
		stdTrace.saveNode(sta);
		stdTrace.saveEdge(sta);
		
		int experienttime=1;
		
		for(int i=0;i<experienttime;i++){
			stdInteraction.interact();
			stdTrace.traceNode(sta,i);
			stdTrace.traceEdge(sta,i);
		}
		stdTrace.CloseDatabase();
		System.out.println("everything is ok");
	}
	public static void main(String []args){
		
		JxNodeCollection nodeset = new JxNodeCollection();
		JxRelationCollection relationset = new JxRelationCollection();
		JxStdInteraction interaction = new JxStdInteraction() ;
		
		relationset.generate();
		
		JxSimulator simulator=new JxSimulator(nodeset, relationset, interaction);
		
		for (int i=0; i<500; i++)	
		simulator.run();
	}		
}
