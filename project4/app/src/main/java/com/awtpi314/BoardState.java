package com.awtpi314;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardState {
  private VehicleNode board[][] = new VehicleNode[6][6];

  public BoardState(VehicleNode[][] board) {
    for (int i = 0; i < 6; i++) {
      this.board[i] = Arrays.copyOf(board[i], 6);
    }
  }

  public ArrayList<BoardState> getNextStates() {
    ArrayList<BoardState> nextStates = new ArrayList<>();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (board[i][j] != null) {
          if (board[i][j].getIsVertical()) {
            for (int k = i - 1; k >= 0 && board[k][j] == null; k--) {
              VehicleNode[][] newBoard = new VehicleNode[6][6];
              for (int l = 0; l < 6; l++) {
                newBoard[l] = Arrays.copyOf(board[l], 6);
              }

              for (int l = 0; (i + l) < 6 && newBoard[i + l][j] != null
                  && newBoard[i + l][j].getVehicleName() == board[i][j].getVehicleName(); l++) {
                newBoard[k + l][j] = newBoard[i + l][j];
                newBoard[i + l][j] = null;
              }

              nextStates.add(new BoardState(newBoard));
            }

            for (int k = i + 1; k < 6 && board[k][j] == null; k++) {
              VehicleNode[][] newBoard = new VehicleNode[6][6];
              for (int l = 0; l < 6; l++) {
                newBoard[l] = Arrays.copyOf(board[l], 6);
              }

              for (int l = 0; (i - l) >= 0 && newBoard[i - l][j] != null
                  && newBoard[i - l][j].getVehicleName() == board[i][j].getVehicleName(); l++) {
                newBoard[k - l][j] = newBoard[i - l][j];
                newBoard[i - l][j] = null;
              }

              nextStates.add(new BoardState(newBoard));
            }
          } else {
            for (int k = j - 1; k >= 0 && board[i][k] == null; k--) {
              VehicleNode[][] newBoard = new VehicleNode[6][6];
              for (int l = 0; l < 6; l++) {
                newBoard[l] = Arrays.copyOf(board[l], 6);
              }

              for (int l = 0; (j + l) < 6 && newBoard[i][j + l] != null
                  && newBoard[i][j + l].getVehicleName() == board[i][j].getVehicleName(); l++) {
                newBoard[i][k + l] = newBoard[i][j + l];
                newBoard[i][j + l] = null;
              }

              nextStates.add(new BoardState(newBoard));
            }

            for (int k = j + 1; k < 6 && board[i][k] == null; k++) {
              VehicleNode[][] newBoard = new VehicleNode[6][6];
              for (int l = 0; l < 6; l++) {
                newBoard[l] = Arrays.copyOf(board[l], 6);
              }

              for (int l = 0; (j - l) >= 0 && newBoard[i][j - l] != null
                  && newBoard[i][j - l].getVehicleName() == board[i][j].getVehicleName(); l++) {
                newBoard[i][k - l] = newBoard[i][j - l];
                newBoard[i][j - l] = null;
              }

              nextStates.add(new BoardState(newBoard));
            }
          }
        }
      }
    }

    return nextStates;
  }

  public void printBoard() {
    System.out.println("-------------------------------------");
    for (int i = 0; i < 6; i++) {
      System.out.print("| ");
      for (int j = 0; j < 6; j++) {
        if (board[i][j] == null) {
          System.out.print("   ");
        } else {
          System.out.print(board[i][j].getVehicleName().substring(0, 3));
        }
        System.out.print(" | ");
      }
      System.out.println("\n-------------------------------------");
    }
  }

  public boolean isSolved() {
    return board[2][5] != null && board[2][5].getVehicleName().equals("red");
  }

  public String getHash() {
    StringBuilder hash = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (board[i][j] == null) {
          hash.append("000");
        } else {
          hash.append(board[i][j].getVehicleName().substring(0, 3));
        }
      }
    }
    return hash.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof BoardState)) {
      return false;
    }
    BoardState other = (BoardState) obj;

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (board[i][j] == null && other.board[i][j] != null) {
          return false;
        }
        if (board[i][j] != null && other.board[i][j] == null) {
          return false;
        }
        if (board[i][j] != null && other.board[i][j] != null
            && !board[i][j].equals(other.board[i][j])) {
          return false;
        }
      }
    }

    return true;
  }
}
