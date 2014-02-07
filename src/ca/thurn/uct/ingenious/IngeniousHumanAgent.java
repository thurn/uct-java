package ca.thurn.uct.ingenious;

import java.util.Scanner;

import ca.thurn.uct.core.ActionScore;
import ca.thurn.uct.core.Agent;
import ca.thurn.uct.core.Player;
import ca.thurn.uct.core.State;

/**
 * Human agent for Ingenious.
 */
public class IngeniousHumanAgent implements Agent<IngeniousAction> {

  private Scanner in = new Scanner(System.in);  
  
  private final State<IngeniousAction> stateRepresentation;
  
  /**
   * @param stateRepresentation A state representation which will be totally
   *     ignored.
   */
  public IngeniousHumanAgent(State<IngeniousAction> stateRepresentation) {
    this.stateRepresentation = stateRepresentation;
  }
  
  public ActionScore<IngeniousAction> pickAction(Player player, State<IngeniousAction> rootNode) {
    IngeniousState state = (IngeniousState)rootNode;
    IngeniousPiece piece = null;
    int index, x1, y1, x2, y2;
    while (true) {
      System.out.println("Select a piece [0,5]");
      index = in.nextInt();
      piece = state.getPiece(player, index);
      System.out.println("Enter x1");
      x1 = in.nextInt();
      System.out.println("Enter y1");
      y1 = in.nextInt();
      System.out.println("Enter x2");
      x2 = in.nextInt();
      System.out.println("Enter y2");
      y2 = in.nextInt();
      if (state.isOpen(x1, y1) && state.isOpen(x2, y2)) {
        try {
          state.hexDirection(x1, y1, x2, y2);
          break;
        } catch (RuntimeException rte) {
          // Retry
        }
      }
      System.out.println("Invalid action selection!");      
    }
    return new ActionScore<IngeniousAction>(new IngeniousAction(piece, x1, y1, x2, y2));
  }

  @Override
  public State<IngeniousAction> getStateRepresentation() {
    return stateRepresentation;
  }


}