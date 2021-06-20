package com.epam.rd.autocode.concurrenttictactoe;

public interface TicTacToe {

    /**
     * Sets a mark in cell with specified coordinates.
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @param mark - mark to set.
     *
     * Задает отметку в ячейке с заданными координатами.
     * * @param x - координата x.
     * * @param y - y координата.
     * * @param mark - отметка для установки.
     */
    void setMark(int x, int y, char mark);

    /**
     * Returns a COPY of current table with marks.
     * Note, edit of that copy should not affect the source TicTacToe object.
     * @return a copy of current table.
     *
     * Возвращает КОПИЮ текущей таблицы с метками.
     * * Обратите внимание, что редактирование этой копии не должно влиять на исходный объект TicTacToe.
     * * @возвращает копию текущей таблицы.
     */
    char[][] table();

    /**
     * Returns last mark that was set in a table.
     * @return last mark that was set in a table.
     *
     * Возвращает последнюю отметку, заданную в таблице.
     * * @возвращает последнюю отметку, установленную в таблице.
     */
    char lastMark();

    static TicTacToe buildGame() {
        return new TicTacToeImpl();
    }
}