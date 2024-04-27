package reversi;

import java.util.*;
public class ReversiController implements IController{
    IModel model;
    IView view;
    Movement movement;
    Map<String, List<int[]>> opponentPiecesMap;
    boolean draw1;
    boolean draw2;
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
        draw1=false;
        draw2=false;
        blackCount=2;
        whiteCount=2;
        model.setFinished(false);
    }

    @Override
    public void startup() {
        Random random = new Random();
        int playerTurn = random.nextInt(2) + 1;
        System.out.println("Player " + playerTurn + " starts");
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
        String[] directions = {"left", "right", "up", "down", "upperLeft", "upperRight", "lowerLeft", "lowerRight"};
        for (String direction : directions) {
            opponentPiecesMap.put(direction, new ArrayList<>());
        }
        System.out.println("Opponent Pieces:");
        for (Map.Entry<String, List<int[]>> entry : opponentPiecesMap.entrySet()) {
            String direction = entry.getKey();
            List<int[]> pieces = entry.getValue();
            System.out.println("Direction: " + direction);
            System.out.println("Pieces:");
            for (int[] piece : pieces) {
                System.out.println(Arrays.toString(piece));
            }
            System.out.println();
        }
        System.out.println("Hello i am update");
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
        System.out.println("White count: " + whiteCount + " Black count: " + blackCount);
       int nextPlayer= (model.getPlayer() == 1) ? 2 : 1;
        model.setPlayer(nextPlayer);
        System.out.println("Player " + model.getPlayer() + " starts");
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
        if(model.getPlayer() != player) {
            view.feedbackToUser(player, "It's is not your turn!");
            return;
        }
        // if(!isValidMove(player, x, y))
        // {
        //     return;
        // }
        if(model.getBoardContents(x, y) != 0) {
            view.feedbackToUser(player, "Invalid move! Square already occupied.");
            return;
        }
        model.setBoardContents(x, y, player);
        updateTheBoard(player, x, y);
       }

    public boolean isValidMove(int player, int x, int y) {
       
        if(model.getBoardContents(x, y) != 0) {
            view.feedbackToUser(player, "Invalid move! Square already occupied.");
            return false;
        }
        if(x < 0 || x > model.getBoardWidth() || y < 0 || y > model.getBoardHeight()) {
            view.feedbackToUser(player, "Invalid move! Out of bounds.");
            return false;
        }
        movement.setX(x);
        movement.setY(y);
        movement.setPlayer(player);
        if(movement.opponentCountInLeft() == 0 && movement.opponentCountInRight() == 0 && movement.opponentCountInUp() == 0 && movement.opponentCountInDown() == 0 && movement.opponentCountInUpperLeft() == 0 && movement.opponentCountInUpperRight() == 0 && movement.opponentCountInLowerLeft() == 0 && movement.opponentCountInLowerRight() == 0) {
            view.feedbackToUser(player, "Your turn is skipped! No opponent pieces to flip.");
            if(player ==1)
            {
                draw1=true;
            }
            else{
                draw2=true;
            }
            if(draw1 && draw2)
            {
              //  endGame();
            }
            update();
            return false;
        }

        return true;
    }
    void endGame()
    {
        model.setFinished(true);
        if(whiteCount > blackCount)
        {
            view.feedbackToUser(1, "White " + blackCount + " to Black " + whiteCount + ". Reset game to replay.");
            view.feedbackToUser(2, "White " + blackCount + " to Black " + whiteCount + ". Reset game to replay.");
        }
        else if(blackCount > whiteCount)
        {
            view.feedbackToUser(1, "Black " + blackCount + " to White " + whiteCount + ". Reset game to replay.");
            view.feedbackToUser(2, "Black " + blackCount + " to White " + whiteCount + ". Reset game to replay.");
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
    }
// after each turn this function is responsible for fliping opponent peices in model
    private void updateTheBoard(int player, int x, int y) {
        movement.setPlayer(player);
        movement.setX(x);
        movement.setY(y);
       int count1= movement.storeLeftOpponentPieces(opponentPiecesMap);
       if(count1 > 0)
       {
        for(int[] position : opponentPiecesMap.get("left"))
        {
            System.out.println("i am left opponent side");
            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count2 = movement.storeRightOpponentPieces(opponentPiecesMap);
    if(count2 > 0)
    {
        System.out.println("i am right opponent side");
        for(int[] position : opponentPiecesMap.get("right"))
        {
            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count3 = movement.storeUpOpponentPieces(opponentPiecesMap);
    if(count3 > 0)
    {
        for(int[] position : opponentPiecesMap.get("up"))
        {
            System.out.println("i am up opponent side");
            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count4 = movement.storeDownOpponentPieces(opponentPiecesMap);
    if(count4 > 0)
    {
        for(int[] position : opponentPiecesMap.get("down"))
        {
            System.out.println("i am down opponent side");
            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count5 = movement.storeUpperLeftOpponentPieces(opponentPiecesMap);
    if(count5 > 0)
    {
        for(int[] position : opponentPiecesMap.get("upperLeft"))
        {
            System.out.println("i am upperLeft opponent side");
            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count6 = movement.storeUpperRightOpponentPieces(opponentPiecesMap);
    if(count6 > 0)
    {
        for(int[] position : opponentPiecesMap.get("upperRight"))
        {
            System.out.println("i am upperRight opponent side");
            model.setBoardContents(position[0], position[1], player);
            
        }
    }
    int count7 = movement.storeLowerLeftOpponentPieces(opponentPiecesMap);
    if(count7 > 0)
    {
        for(int[] position : opponentPiecesMap.get("lowerLeft"))
        {

            model.setBoardContents(position[0], position[1], player);
        }
    }
    int count8 = movement.storeLowerRightOpponentPieces(opponentPiecesMap);
    if(count8 > 0)
    {
        for(int[] position : opponentPiecesMap.get("lowerRight"))
        {

            model.setBoardContents(position[0], position[1], player);
        }
    }
    update();
}
    @Override
    public void doAutomatedMove(int player) {
        if(model.getPlayer() != player) {
            view.feedbackToUser(player, "It's is not your turn!");
            return;
        }
        List<int[]> opponentPieces = new ArrayList<>();
       for(int i = 0; i < model.getBoardWidth(); i++)
        {
            for(int j = 0; j < model.getBoardHeight(); j++)
            {
                if(model.getBoardContents(i, j) == 0)
                {
                    // if(!isValidMove(player, i, j))
                    //     {
                    //     continue;
                    //     }
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
        int count=0;
        for(int[] piece : opponentPieces)
        {
            count+=1;
            System.out.println(count + " : Total count: " + piece[0] + " x: " + piece[1] + " y: " + piece[2]);
        }
        int max=0;
        for(int[] piece : opponentPieces)
        {
            if(piece[0] > max)
            {
                max = piece[0];
            }
        }
        System.out.println("Max: " + max);
        for(int[] piece : opponentPieces)
        {
            if(piece[0] == max)
            {
                System.out.println("Automated move: x: " + piece[1] + " y: " + piece[2]);
                model.setBoardContents(piece[1], piece[2], player);
                updateTheBoard(player, piece[1], piece[2]);
                break;
            }
        }
    }
    
}
