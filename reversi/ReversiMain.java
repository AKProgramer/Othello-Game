package reversi;





public class ReversiMain
{
	IModel model;
	IView view;
	IController controller;

	ReversiMain()
	{
		// Choose ONE of the models
		model = new SimpleModel();
		//model = new SimpleTestModel();
		
		// Choose ONE of the views
		view = new GUIView();
		//view = new FakeTextView();
		//view = new GUIView(); // You need to implement this one yourself!
		
		// Choose one controller
		controller = new ReversiController();
		//controller = new ReversiController(); // You need to implement this one yourself!
		
		// Don't change the lines below here, which connect things together
		
		// Initialise everything...
		model.initialise(8, 8, view, controller);
		controller.initialise(model, view);
		view.initialise(model, controller);
		
		// Now start the game - set up the board
		controller.startup();
	}
	
	public static void main(String[] args)
	{
		new ReversiMain();
	}
}
