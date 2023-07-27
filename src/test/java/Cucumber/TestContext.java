package Cucumber;



public class TestContext {
	
	private static TestContext _instance;
	
	private ScenarioContext scenarioContext;
	
	private TestContext()
	{
		scenarioContext = new ScenarioContext();
	}
	

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}


	public static TestContext getInstance() {
		if(_instance == null)
			_instance = new TestContext();
		
		return _instance;
	}


	

}
