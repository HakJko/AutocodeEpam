package com.epam.rd.autocode.concurrenttictactoe;

public interface Player extends Runnable
{
    static Player createPlayer(final TicTacToe board, final char mark, PlayerStrategy strategy) {
        return new PlayerImpl(board, mark, strategy);
    }
}
