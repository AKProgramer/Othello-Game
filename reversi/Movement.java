package reversi;

import java.util.*;

public class Movement {
    private int player;
    private int x;
    private int y;
    IModel model;
    Movement(IModel model){
        this.model = model;
        this.player = 0;
        this.x = 0;
        this.y = 0;
    }
    public void setPlayer(int player) {
        this.player = player;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int opponentCountInLeft(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the left side
        for (int i = x - 1; i >= 0; i--) {
            if (model.getBoardContents(i, y) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, y) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInRight(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the right side
        for (int i = x + 1; i < model.getBoardWidth(); i++) {
            if (model.getBoardContents(i, y) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, y) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInUp(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the up side
        for (int i = y - 1; i >= 0; i--) {
            if (model.getBoardContents(x, i) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(x, i) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInDown(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the down side
        for (int i = y + 1; i < model.getBoardHeight(); i++) {
            if (model.getBoardContents(x, i) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(x, i) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInUpperLeft(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the upper left side
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (model.getBoardContents(i,j) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, j) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInUpperRight(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the upper right side
        for (int i = x + 1, j = y - 1; i < model.getBoardWidth() && j >= 0; i++, j--) {
            if (model.getBoardContents(i,j) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, j) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInLowerLeft(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the lower left side
        for (int i = x - 1, j = y + 1; i >= 0 && j < model.getBoardHeight(); i--, j++) {
            if (model.getBoardContents(i, j) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, j) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int opponentCountInLowerRight(){
        int opponent = (player == 1) ? 2 : 1;
        int count = 0;
        boolean playerFound = false; // Flag to check if player piece found on the lower right side
        for (int i = x + 1, j = y + 1; i < model.getBoardWidth() && j < model.getBoardHeight(); i++, j++) {
            if (model.getBoardContents(i, j) == opponent) {
                count++;
            } else if (count >= 1 && model.getBoardContents(i, j) == player) {
                playerFound=true;
                break; // Exit loop if player piece found
            } 
        }
        if(!playerFound)
        {
            count=0;
        }
        return count;
    }
    public int storeLeftOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store left opponent pieces");
         int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInLeft();
        int store=count;
        System.out.println("Total opponent pieces found on the left side: " + count);
            // Store opponent positions only if player piece is found on the left side
            for (int i = x - 1; i > 0 && store > 0; i--) {
                if (model.getBoardContents(i, y) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + y);
                    opponentPiecesMap.get("left").add(new int[]{i, y});
                    store-=1;
                } 
               
            }
        
    
        return count;
    }
    public int storeRightOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store right opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent + " player: " + player);
        int count = opponentCountInRight();
        int store = count;
        System.out.println("Total opponent pieces found on the right side: " + count);
            // Store opponent positions only if player piece is found on the right side
            for (int i = x + 1; i < model.getBoardWidth() && store > 0; i++) {
                    System.out.println("in am in right "+i + "," + y);
                    System.out.println("store: "+store);
                if (model.getBoardContents(i, y) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + y);
                    opponentPiecesMap.get("right").add(new int[]{i, y});
                    store-=1;
                    }
                } 
        return count;
    }
    public int storeUpOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store up opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInUp();
       // boolean playerFound = false; // Flag to check if player piece found on the up side
        int store= count;
        System.out.println("Total opponent pieces found on the up side: " + count);
            // Store opponent positions only if player piece is found on the up side
            for (int i = y - 1; i > 0 && store > 0; i--) {
                if (model.getBoardContents(x, i) == opponent) {
                   
                    System.out.println("Opponent piece found at: " + x + "," + i);
                    opponentPiecesMap.get("up").add(new int[]{x, i});
                    store-=1;
                } 
               
            }
        return count;
    }
    
    public int storeDownOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store down opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInDown();
        int store=count;
            // Store opponent positions only if player piece is found on the down side
            for (int i = y + 1; i < model.getBoardHeight() && store > 0; i++) {
                if (model.getBoardContents(x, i) == opponent) {
                    System.out.println("Opponent piece found at: " + x + "," + i);
                    opponentPiecesMap.get("down").add(new int[]{x, i});
                    store-=1;
                } 
        }
    
        return count;
    }
    public int storeUpperLeftOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store upper left opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInUpperLeft();
        int store=count;
         System.out.println("Total opponent pieces found on the upper left side: " + count);
            // Store opponent positions only if player piece is found on the upper left side
            for (int i = x - 1, j = y - 1; i > 0 && j > 0 && store > 0; i--, j--) {
                if (model.getBoardContents(i, j) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + j);
                    opponentPiecesMap.get("upperLeft").add(new int[]{i, j});
                    store-=1;
                } 
        }
    
        return count;
    }
    public int storeUpperRightOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store upper right opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInUpperRight();
        int store=count;
         System.out.println("Total opponent pieces found on the upper right side: " + count);
        // store=count;
      
            // Store opponent positions only if player piece is found on the upper right side
            for (int i = x + 1, j = y - 1; i < model.getBoardWidth() && j > 0 && store > 0; i++, j--) {
                if (model.getBoardContents(i, j) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + j);
                    opponentPiecesMap.get("upperRight").add(new int[]{i, j});
                    store-=1;
                } 
        }
    
        return count;
    }
    public int storeLowerLeftOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store lower left opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInLowerLeft();
        int store = count;
         System.out.println("Total opponent pieces found on the lower left side: " + count);
            // Store opponent positions only if player piece is found on the lower left side
            for (int i = x - 1, j = y + 1; i > 0 && j < model.getBoardHeight() && store > 0; i--, j++) {
                if (model.getBoardContents(i, j) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + j);
                    opponentPiecesMap.get("lowerLeft").add(new int[]{i, j});
                    store-=1;
                } 
               
            }
        return count;
    }
    public int storeLowerRightOpponentPieces(Map<String, List<int[]>> opponentPiecesMap) {
        System.out.println("Store lower right opponent pieces");
        int opponent = (player == 1) ? 2 : 1;
        System.out.println("Opponent: " + opponent);
        int count = opponentCountInLowerRight();
        int store=count;
        System.out.println("Total opponent pieces found on the lower right side: " + count);
            // Store opponent positions only if player piece is found on the lower right side
            for (int i = x + 1, j = y + 1; i < model.getBoardWidth() && j < model.getBoardHeight() && store > 0; i++, j++) {
                if (model.getBoardContents(i, j) == opponent) {
                    System.out.println("Opponent piece found at: " + i + "," + j);
                    opponentPiecesMap.get("lowerRight").add(new int[]{i, j});
                    store-=1;
                } 
        }
    
        return count;
    }
}
