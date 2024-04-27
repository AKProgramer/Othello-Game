# Reversi Game - Prerequisites for Running in VS Code

To run the Reversi game in VS Code, you'll need to ensure that you have the following prerequisites installed and configured:

## Prerequisites

1. **Java Development Kit (JDK)**:
   - Install the latest version of JDK compatible with your operating system.
   - Set up the `JAVA_HOME` environment variable pointing to the JDK installation directory.

2. **Visual Studio Code**:
   - Install Visual Studio Code, a lightweight and powerful source code editor.
   - Install the Java Extension Pack for VS Code, which includes essential tools for Java development.

3. **Project Structure**:
   - Create a project folder for the Reversi game.
   - Place the Java files (`ReversiController.java`, `GUIView.java`, etc.) in the appropriate package structure within the project folder.

4. **Interface Definitions**:
   - Ensure the existence of interface definitions (`IModel.java`, `IView.java`, `IController.java`) used by the Reversi classes.
   - Implement these interfaces according to your requirements.

## Running the Game

1. **Compile Java Files**:
   - Open Visual Studio Code and navigate to the project folder.
   - Compile the Java files using the command: `javac package_name/*.java`.

2. **Run the Game**:
   - Run the main class (e.g., a class with a `main` method) that initializes the game (e.g., `ReversiController`).

3. **Interact with the GUI**:
   - Once the game is running, interact with the GUI to play Reversi.
   - Use the buttons and interface elements provided to make moves and navigate through the game.

4. **Debugging**:
   - Utilize the debugging features of Visual Studio Code to troubleshoot any issues during development.

# Reversi Game Package

This package contains the implementation of the Reversi game, including the game logic, controller, and graphical user interface (GUI) view.

## Table of Contents
- [ReversiController.java](#reversicontrollerjava)
- [GUIView.java](#guiviewjava)

## ReversiController.java

This class implements the controller for the Reversi game. It manages the game logic, player turns, and updates the model accordingly.

### Methods

- `initialise(IModel model, IView view)`: Initializes the controller with the model and view.
- `startup()`: Initializes the game and starts it.
- `update()`: Updates the game state after each move.
- `squareSelected(int player, int x, int y)`: Handles the selection of a square by a player.
- `isValidMove(int player, int x, int y)`: Checks if a move is valid.
- `endGame()`: Ends the game and determines the winner.
- `doAutomatedMove(int player)`: Performs an automated move for the specified player.
- `checkForPass()`: Checks for possible moves for the current player.

## GUIView.java

This class implements the graphical user interface (GUI) for the Reversi game. It provides a visual representation of the game board and allows players to interact with it.

### Methods

- `initialise(IModel model, IController controller)`: Initializes the GUI with the model and controller.
- `refreshView()`: Refreshes the GUI view.
- `refreshViewForBlackFrame()`: Refreshes the view for the black player's frame.
- `refreshViewForWhiteFrame()`: Refreshes the view for the white player's frame.
- `initializeViewForWhite()`: Initializes the view for the white player's frame.
- `initializeViewForBlack()`: Initializes the view for the black player's frame.
- `feedbackToUser(int player, String message)`: Provides feedback to the specified player.
