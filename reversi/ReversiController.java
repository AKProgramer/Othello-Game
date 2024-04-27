package reversi;

import java.util.*;
public class ReversiController implements IController{
    IModel model;
    IView view;
    Movement movement;
    Map<String, List<int[]>> opponentPiecesMap;
    private int consecutiveNoMovesCount;
    int blackCount;
    int whiteCount;
    @Override
    public void initialise(IModel model, IView view) {
       this.model=model;
       this.view=view;
       movement=new Movement(model);
        opponentPiecesMap = new HashMap<>();
        String[] directions = {"left", "right", "up", "down", "upperLeft", "upperRight", "lowerLeft", "lowerRight"};
        for (String direction : directions) {
            opponentPiecesMap.put(direction, new ArrayList<>());
        }
        consecutiveNoMovesCount = 0;
    }

    @Override
    public void startup() {
       int playerTurn = model.getPlayer() == 1 ? 2 : 1;
        model.setPlayer(playerTurn);
        model.setFinished(false);
        model.clear(0);
        model.setBoardContents(3, 3, 1);
        model.setBoardContents(4, 4, 1);
        model.setBoardContents(3, 4, 2);
        model.setBoardContents(4, 3, 2);
        if(playerTurn == 1) {
            view.feedbackToUser(1, "White player - choose where to put your piece");
            view.feedbackToUser(2, "Black player - not your turn");
        } else {
            view.feedbackToUser(2, "Black player - choose where to put your piece");
            view.feedbackToUser(1, "White player - not your turn");
        }
       
       // 1 (white) or 2 (black)
       
        view.refreshView();
    }

    @Override
    public void update() {
        if(movement.getPlayer()==0)
        movement.setPlayer(model.getPlayer());
        int count1= movement.storeLeftOpponentPieces(opponentPiecesMap);
        if(count1 > 0)
        {
         for(int[] position : opponentPiecesMap.get("left"))
         {
           ///  System.out.println("i am left opponent side");
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count2 = movement.storeRightOpponentPieces(opponentPiecesMap);
     if(count2 > 0)
     {
        // System.out.println("i am right opponent side");
         for(int[] position : opponentPiecesMap.get("right"))
         {
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count3 = movement.storeUpOpponentPieces(opponentPiecesMap);
     if(count3 > 0)
     {
         for(int[] position : opponentPiecesMap.get("up"))
         {
            // System.out.println("i am up opponent side");
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count4 = movement.storeDownOpponentPieces(opponentPiecesMap);
     if(count4 > 0)
     {
         for(int[] position : opponentPiecesMap.get("down"))
         {
             //System.out.println("i am down opponent side");
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count5 = movement.storeUpperLeftOpponentPieces(opponentPiecesMap);
     if(count5 > 0)
     {
         for(int[] position : opponentPiecesMap.get("upperLeft"))
         {
            // System.out.println("i am upperLeft opponent side");
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count6 = movement.storeUpperRightOpponentPieces(opponentPiecesMap);
     if(count6 > 0)
     {
         for(int[] position : opponentPiecesMap.get("upperRight"))
         {
          //   System.out.println("i am upperRight opponent side");
             model.setBoardContents(position[0], position[1], movement.getPlayer());
             
         }
     }
     int count7 = movement.storeLowerLeftOpponentPieces(opponentPiecesMap);
     if(count7 > 0)
     {
         for(int[] position : opponentPiecesMap.get("lowerLeft"))
         {
 
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
     int count8 = movement.storeLowerRightOpponentPieces(opponentPiecesMap);
     if(count8 > 0)
     {
         for(int[] position : opponentPiecesMap.get("lowerRight"))
         {
             model.setBoardContents(position[0], position[1], movement.getPlayer());
         }
     }
        //This code block is responsible for refreshing opponent pieces map after each turn
        String[] directions = {"left", "right", "up", "down", "upperLeft", "upperRight", "lowerLeft", "lowerRight"};
        for (String direction : directions) {
            opponentPiecesMap.put(direction, new ArrayList<>());
        }
        // This code block is responsible for switching player after each turn
       int nextPlayer= (model.getPlayer() == 1) ? 2 : 1;
        model.setPlayer(nextPlayer);
      //  System.out.println("Player " + model.getPlayer() + " starts");
        if(nextPlayer == 1) {
            view.feedbackToUser(1, "White player - choose where to put your piece");
            view.feedbackToUser(2, "Black player - not your turn");
        } else {
            view.feedbackToUser(2, "Black player - choose where to put your piece");
            view.feedbackToUser(1, "White player - not your turn");
        }
        view.refreshView();
        
    }

    @Override
    public void squareSelected(int player, int x, int y) {
        if(!isValidMove(player, x, y))
        {
            return;
        }
        if(model.getBoardContents(x, y) != 0) {
            return;
        }
        model.setBoardContents(x, y, player);
        movement.setX(x);
        movement.setY(y);
        movement.setPlayer(player);
        update();
       }

    public boolean isValidMove(int player, int x, int y) {

        if(model.hasFinished()) {
            
            return false;
        }
       if(x!=-1 && y!=-1)
       {
        if(model.getBoardContents(x, y) != 0) {
            // view.feedbackToUser(player, "Invalid move! Square already occupied.");
             return false;
         }
         if(x < 0 || x > model.getBoardWidth() || y < 0 || y > model.getBoardHeight()) {
            // view.feedbackToUser(player, "Invalid move! Out of bounds.");
             return false;
         }
       }

       List<int[]> moveList= checkForPass();
       boolean flag = false;
       for(int[] piece : moveList)
       {
        if(piece[0]!=0)
        {
            flag=true;
            break;
        }
       }
         if(!flag){
            consecutiveNoMovesCount+=1;
            if(consecutiveNoMovesCount >= 2)
            {
                endGame();
                return false;
            }
            update();

            return false;
         }
         else
         {
             consecutiveNoMovesCount = 0;
         }
        return true;
    }
    void endGame()
    {
        blackCount=0;
        whiteCount=0;
        // this nested loop count number of white and black pieces on board after each turn
        for(int i = 0; i < model.getBoardWidth(); i++)
        {
            for(int j = 0; j < model.getBoardHeight(); j++)
            {
                if(model.getBoardContents(i, j) == 1)
                {
                    whiteCount+=1;
                }
                else if(model.getBoardContents(i, j) == 2)
                {
                    blackCount+=1;
                }
            }
        }
        model.setFinished(true);
        if(whiteCount > blackCount)
        {
            view.feedbackToUser(1,"White won. "+ "White " + whiteCount + " to Black " + blackCount + ". Reset game to replay.");
            view.feedbackToUser(2,"White won. "+  "White " + whiteCount + " to Black " + blackCount + ". Reset game to replay.");
        }
        else if(blackCount > whiteCount)
        {
            view.feedbackToUser(1,"Black won. "+  "Black " + blackCount + " to White " + whiteCount + ". Reset game to replay.");
            view.feedbackToUser(2,"Black won. "+  "Black " + blackCount + " to White " + whiteCount + ". Reset game to replay.");
        }
        else
        {
            view.feedbackToUser(1, "Draw. Both players ended with 32 pieces. Reset game to replay.");
            view.feedbackToUser(2, "Draw. Both players ended with 32 pieces. Reset game to replay.");
        }
        for(int i = 0; i < model.getBoardWidth(); i++)
        {
            for(int j = 0; j < model.getBoardHeight(); j++)
            {
                if(model.getBoardContents(i, j)==0 && whiteCount > 0){
                    model.setBoardContents(i, j, 1);
                    whiteCount-=1;
                }
                else if(model.getBoardContents(i, j)==0){
                    model.setBoardContents(i, j, 2);
                    blackCount-=1;
                }
               
            }
        }
        view.refreshView();
    }
    @Override
    public void doAutomatedMove(int player) {
        if(model.getPlayer() != player) {
            view.feedbackToUser(player, "It is not your turn!");
            return;
        }
        if(!isValidMove(player, -1, -1))
        {
            return;
        }
        List<int[]> opponentPieces = checkForPass();
        int max=0;
        for(int[] piece : opponentPieces)
        {
            if(piece[0] > max)
            {
                max = piece[0];
            }
        }
       // System.out.println("Max: " + max);
        for(int[] piece : opponentPieces)
        {
            if(piece[0] == max)
            {
            //    System.out.println("Automated move: x: " + piece[1] + " y: " + piece[2]);
                model.setBoardContents(piece[1], piece[2], player);
                movement.setX(piece[1]);
                movement.setY(piece[2]);
                movement.setPlayer(player);
                update();
                break;
            }
        }
    }
    private List<int[]> checkForPass()
    {
        int player =model.getPlayer();
        List<int[]> opponentPieces = new ArrayList<>();
        for(int i = 0; i < model.getBoardWidth(); i++)
        {
            for(int j = 0; j < model.getBoardHeight(); j++)
            {
                if(model.getBoardContents(i, j) == 0)
                {
                    movement.setX(i);
                    movement.setY(j);
                    movement.setPlayer(player);
                    int leftCount= movement.opponentCountInLeft();
                    int rightCount= movement.opponentCountInRight();
                    int upCount= movement.opponentCountInUp();
                    int downCount= movement.opponentCountInDown();
                    int upperLeftCount= movement.opponentCountInUpperLeft();
                    int upperRightCount= movement.opponentCountInUpperRight();
                    int lowerLeftCount= movement.opponentCountInLowerLeft();
                    int lowerRightCount= movement.opponentCountInLowerRight();
                    int totalCount= leftCount + rightCount + upCount + downCount + upperLeftCount + upperRightCount + lowerLeftCount + lowerRightCount;
                    opponentPieces.add(new int[]{totalCount, i,j});
                }
            }
        }
        return opponentPieces;
    }
    
}
