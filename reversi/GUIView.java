package reversi;
import javax.swing.*;
import java.awt.*;

public class GUIView implements IView {

    private JFrame frameBlack;
    private JLabel feedbackLabelBlack;
    private JButton greedyAIButtonBlack;
    private JButton restartButtonBlack;
    private JPanel boardPanelBlack;
    private JFrame frameWhite;
    private JLabel feedbackLabelWhite;
    private JButton greedyAIButtonWhite;
    private JButton restartButtonWhite;
    private JPanel boardPanelWhite;
    private IModel model;
    private IController controller;

    @Override
    public void initialise(IModel model, IController controller) {
        // Initialize GUI components
        // this is for black frame
        this.model = model;
        this.controller = controller;
        frameBlack = new JFrame("Reversi - Player Black");
        feedbackLabelBlack = new JLabel();
        greedyAIButtonBlack = new JButton("Greedy AI (play black)");
        restartButtonBlack = new JButton("Restart");
        boardPanelBlack = new JPanel(new GridLayout(model.getBoardWidth(), model.getBoardHeight()));
        // Add action listeners to buttons
        greedyAIButtonBlack.addActionListener(e -> this.controller.doAutomatedMove(2));
        
        restartButtonBlack.addActionListener(e -> {
            controller.startup();
            refreshView();
        });

        // Add components to frames
        frameBlack.getContentPane().add(BorderLayout.NORTH, feedbackLabelBlack);
        frameBlack.getContentPane().add(BorderLayout.CENTER, boardPanelBlack);
        JPanel buttonPanelBlack = new JPanel(new FlowLayout());
        buttonPanelBlack.add(greedyAIButtonBlack);
        buttonPanelBlack.add(restartButtonBlack);
        frameBlack.getContentPane().add(BorderLayout.SOUTH, buttonPanelBlack);

        // Set frame properties
        frameBlack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameBlack.setSize(400, 400);
        frameBlack.setVisible(true);
        // this is for white frame
         frameWhite = new JFrame("Reversi - Player White");
        feedbackLabelWhite = new JLabel();
        greedyAIButtonWhite = new JButton("Greedy AI (play white)");
        restartButtonWhite = new JButton("Restart");
        boardPanelWhite = new JPanel(new GridLayout(model.getBoardWidth(), model.getBoardHeight()));
        // Add action listeners to buttons
        greedyAIButtonWhite.addActionListener(e -> this.controller.doAutomatedMove(1));
        restartButtonWhite.addActionListener(e -> {
            controller.startup();
            refreshView();
        });
        // Add components to frames
        frameWhite.getContentPane().add(BorderLayout.NORTH, feedbackLabelWhite);
        frameWhite.getContentPane().add(BorderLayout.CENTER, boardPanelWhite);
        JPanel buttonPanelWhite = new JPanel(new FlowLayout());
        buttonPanelWhite.add(greedyAIButtonWhite);
        buttonPanelWhite.add(restartButtonWhite);
        frameWhite.getContentPane().add(BorderLayout.SOUTH, buttonPanelWhite);
        // Set frame properties
        frameWhite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWhite.setSize(400, 400);
        frameWhite.setVisible(true);
        
        
    }

    @Override
    public void refreshView() {
        refreshViewForBlackFrame();
        refreshViewForWhiteFrame();
    }
    public void refreshViewForBlackFrame()
    {
        boardPanelBlack.removeAll();

        initializeViewForBlack();

        frameBlack.revalidate();
        frameBlack.repaint();
    }
    public void refreshViewForWhiteFrame()
    {
        boardPanelWhite.removeAll();
        initializeViewForWhite();
        frameWhite.revalidate();
        frameWhite.repaint();
    }
    public void initializeViewForWhite(){
        // Initialize board squares
        for ( int y = model.getBoardHeight()-1 ; y >=0  ; y-- )
        {
            for ( int x = model.getBoardWidth()-1 ; x >=0 ; x-- )
            {
                switch( model.getBoardContents(x, y) )
                {
                    case 1:
                        CustomButton whiteButton = new CustomButton(Color.BLACK, Color.WHITE);
                        boardPanelWhite.add(whiteButton);
                        break;
                    case 2:
                        CustomButton blackButton = new CustomButton(Color.WHITE, Color.BLACK);
                        boardPanelWhite.add(blackButton);
                        break;
                    default:
                        JButton emptyButton = new JButton();
                        emptyButton.setBackground(Color.GREEN);
                        boardPanelWhite.add(emptyButton);
                        break;
                }
            }
        }
    }
    public void initializeViewForBlack()
    {
        // Initialize view for black frame
        for ( int y = 0 ; y < model.getBoardHeight() ; y++ )
		{ 
			for ( int x = 0 ; x < model.getBoardWidth() ; x++ )
			{
				switch( model.getBoardContents(x, y) )
				{
				case 1:		
                    CustomButton whiteButton = new CustomButton(Color.BLACK, Color.WHITE);
                    boardPanelBlack.add(whiteButton);
                 break;
				case 2:		
                    CustomButton blackButton = new CustomButton(Color.WHITE, Color.BLACK);
                    boardPanelBlack.add(blackButton);
                 break;
				default:
                    JButton emptyButton = new JButton();
                    emptyButton.setBackground(Color.green);
                    boardPanelBlack.add(emptyButton);
                 break;
				}
			}
			
		}
    }
    

    @Override
    public void feedbackToUser(int player, String message) {
        // Update feedback label for the respective player
        if (player == 2) {
            feedbackLabelBlack.setText(message);
        } else if (player == 1) {
            feedbackLabelWhite.setText(message);
        }
    }
}
