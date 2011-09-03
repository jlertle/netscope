package kernel;

public class JxBaseTestApplication {

	/**
	 * Create a network simulator from scratch.
	 */
	
	public JxBaseTestApplication()
	{
	
		
	}
	
	void test1()
	{
		JxBaseEngine engine = new JxBaseEngine();
		
		engine.setNodes(new JxBaseNodeCollection(engine, 10)); 
		engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
		engine.setInteraction(new JxBaseInteraction(engine));
		engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
		
		engine.execute(10000);
	}

	/**
	 * Reload the initial parameters from saved network trace.
	 */
	void test2() 
	{   
		JxBaseEngine engine = new JxBaseEngine();
        
		engine.setTrace( new JxBaseTrace(engine, "/temp/expr/"));
		
		engine.load( engine.getNodes() );
		engine.load( engine.getRelations() );
		engine.setInteraction( new JxBaseInteraction(engine) );
		engine.execute( 100000 );
       
		/** restore */
		engine.restore( "/temp/expr/20110722-124512-01" );
	}
	
	void test3()
	{
		String traceclass, nodesclass, relationsclass, interactionclass;
		
		traceclass = "nse.kernel.JxBaseTrace";
		nodesclass = "nse.kernel.JxBaseNodesCollection";
		relationsclass = "nse.kernel.JxBaseRelationsCollection";
		interactionclass = "nse.kernel.JiBaseInteraction";
		
		JxBaseEngine engine = new JxBaseEngine();
		engine.open( nodesclass, relationsclass, interactionclass, traceclass );
		engine.execute(10000);
	}
	
	public static void main(String []args)
	{
		JxBaseTestApplication test=new JxBaseTestApplication();
		test.test1();
		test.test2();
		test.test3();
	} 
}