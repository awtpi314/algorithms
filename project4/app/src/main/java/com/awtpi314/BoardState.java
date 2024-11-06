package com.awtpi314;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardState {
  private String[][] board = new String[6][6];
  private String lastMove;

  public BoardState(BoardState boardState) {
    for (int i = 0; i < board.length; i++) {
      this.board[i] = Arrays.copyOf(boardState.board[i], 6);
    }
    this.lastMove = boardState.lastMove;
  }

  public BoardState(String[][] board) {
    this(board, "");
  }

  public BoardState(String[][] board, String lastMove) {
    this.board = board;
    this.lastMove = lastMove;
  }

  public ArrayList<BoardState> getNextMoves() {
    ArrayList<BoardState> moves = new ArrayList<>();

    // find all vehicles
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != null && !board[i][j].equals(board[i + 1][j]) && board[i][j + 1].equals(board[i][j])) {
          String name = board[i][j];

          // check verticality
          int horizontal = 0;
          if (board[i][j + 1] == name) {
            horizontal = 1;
          }
          int vertical = (horizontal + 1) % 2;

          // find size
          int size = 1;
          while (board[i + size * vertical][j + size * horizontal] == name) {
            size++;
          }

          // find moves
          boolean aheadFound = true;
          boolean behindFound = true;
          int k = 1;

          while (aheadFound) {
            if (board[i - k * vertical][j - k * horizontal] == null) {
              String dir = horizontal == 1 ? "left" : "up";
              // insert board
              BoardState nboard = new BoardState(this);
              
              for (int l = 0; l < size; l++) {
                nboard.board[i - (k - l) * vertical][j - (k - l) * horizontal] = name;
                nboard.board[i + (size - l - 1) * vertical][j + (size - l - 1) * horizontal] = null;
              }
              
              nboard.lastMove = String.format("%s %i %s", name, k, dir);
              moves.add(nboard);
              k++;
            } else {
              aheadFound = false;
            }
          }

          k = 1;
          while (behindFound) {
            if (i + (k + size) * vertical < board.length
                && board[i + (k + size) * vertical][j + (k + size) * horizontal] == null) {
              // insert board
              String dir = horizontal == 1 ? "right" : "down";
              // insert board
              BoardState nboard = new BoardState(this);

              for (int l = 0; l < size - k; l++) {
                nboard.board[i + (k - l) * vertical][j + (k - l) * horizontal] = null;
                nboard.board[i + (size - l - 1) * vertical][j + (size - l - 1) * horizontal] = name;
              }

              nboard.board[i + k * vertical][j + k * horizontal] = name;
              nboard.board[i - vertical][j - horizontal] = null;
              nboard.lastMove = String.format("%s %i %s", name, k, dir);
              moves.add(nboard);
              k++;
            } else {
              behindFound = false;
            }
          }
        }
      }
    }
    return moves;
  }

  public String getLastMove() {
    return lastMove;
  }

  public boolean isSolved() {
    return board[2][5] != null && board[2][5].equals("red");
  }

  public void print() {
    System.out.println("-------------------------------------");
    for (int i = 0; i < 6; i++) {
      System.out.print("| ");
      for (int j = 0; j < 6; j++) {
        if (board[i][j] == null) {
          System.out.print("   ");
        } else {
          System.out.print(board[i][j].substring(0, 3));
        }
        System.out.print(" | ");
      }
      System.out.println("\n-------------------------------------\n");
    }
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(board);
  }
}
