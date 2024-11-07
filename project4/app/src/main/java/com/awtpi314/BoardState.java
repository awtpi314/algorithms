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
    for (int i = 0; i < board.length; i++) {
      this.board[i] = Arrays.copyOf(board[i], board.length);
    }
    this.lastMove = lastMove;
  }

  public ArrayList<BoardState> getNextMoves() {
    ArrayList<BoardState> moves = new ArrayList<>();

    // find all vehicles
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != null && (i == 0 || !board[i][j].equals(board[i - 1][j]))
            && (j == 0 || !board[i][j].equals(board[i][j - 1]))) {
          String name = board[i][j];

          // check verticality
          int horizontal = 0;
          if (j + 1 < 6 && board[i][j + 1] == name) {
            horizontal = 1;
          }
          int vertical = (horizontal + 1) % 2;

          // find size
          int size = 0;
          while (i + size * vertical < board.length && j + size * horizontal < board.length && board[i + size * vertical][j + size * horizontal] == name) {
            size++;
          }

          size -= 1; //acount for 0 index

          // find moves
          boolean aheadFound = true;
          boolean behindFound = true;
          int k = 1;

          while (aheadFound && i - k * vertical >= 0 && j - k * horizontal >= 0) {
            if (board[i - k * vertical][j - k * horizontal] == null) {
              String dir = horizontal == 1 ? "left" : "up";
              // insert board
              BoardState nboard = new BoardState(this);

              for (int l = 0; l < k; l++) {
                nboard.board[i + (size - l) * vertical][j + (size - l) * horizontal] = null;
                nboard.board[i - (l + 1) * vertical][j - (l + 1) * horizontal] = name;
              }

              nboard.lastMove = String.format("%s %d %s", name, k, dir);
              moves.add(nboard);
              k++;
            } else {
              aheadFound = false;
            }
          }

          k = 1;
          while (behindFound && i + (k + size) * vertical < board.length && j + (k + size) * horizontal < board.length) {
            if (board[i + (k + size) * vertical][j + (k + size) * horizontal] == null) {
              // insert board
              String dir = horizontal == 1 ? "right" : "down";
              // insert board
              BoardState nboard = new BoardState(this);

              for (int l = 0; l < k; l++) {
                nboard.board[i + l * vertical][j + l * horizontal] = null;
                nboard.board[i + (size + l + 1) * vertical][j + (size + 1 + l) * horizontal] = name;
              }

              nboard.lastMove = String.format("%s %d %s", name, k, dir);
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    BoardState board = (BoardState) obj;
    return Arrays.deepEquals(board.board, this.board);
  }
}