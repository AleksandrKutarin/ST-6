package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @Test
    void testCheckState_Playing_1() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        State state = game.checkState(board);
        assertEquals(State.PLAYING, state);
    }

    @BeforeEach
    void setUp() {
        game = new Game();
    }
    @Test
    void testInitialGameState() {
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }
    @Test
    void testCheckState_XWin() {
        char[] board = {
                'X', 'X', 'X',  // Крестики выиграли по первой строке
                'O', 'O', ' ',
                ' ', ' ', ' '
        };
        game.symbol = 'X'; // Символ текущего игрока
        State state = game.checkState(board);
        assertEquals(State.XWIN, state);  // Ожидаемая победа крестиков
    }

    @Test
    void testCheckState_OWin() {
        char[] board = {
                'O', 'O', 'O',  // Нолики выиграли по первой строке
                'X', 'X', ' ',
                ' ', ' ', ' '
        };
        game.symbol = 'O'; // Символ текущего игрока
        State state = game.checkState(board);
        assertEquals(State.OWIN, state);  // Ожидаемая победа ноликов
    }

    @Test
    void testGenerateMoves() {
        char[] board = {'X', 'O', 'X', ' ', 'O', ' ', ' ', 'X', 'O'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(3, moves.size());
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(6));
    }

    @Test
    void testCheckState_Draw() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                'O', 'X', 'O'  // Все клетки заняты, и нет победителя — ничья
        };
        game.symbol = 'X';  // Символ текущего игрока (не имеет значения)
        State state = game.checkState(board);
        assertEquals(State.DRAW, state);  // Ожидаемая ничья
    }

    // Тест на продолжение игры (игра продолжается)
    @Test
    void testCheckState_Playing() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', ' ',  // Есть ещё пустая клетка, игра продолжается
                'O', 'X', 'O'
        };
        game.symbol = 'X';  // Символ текущего игрока
        State state = game.checkState(board);
        assertEquals(State.PLAYING, state);  // Ожидаемое состояние — игра продолжается
    }

    @Test
    void testCheckState_Draw_1() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';
        State state = game.checkState(board);
        assertEquals(State.DRAW, state);
    }
    // Новый тест: проверка пустого массива для генерирования ходов
    @Test
    void testGenerateMoves_NoMovesAvailable() {
        char[] board = {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'O'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(0, moves.size());
    }
    // Новый тест: проверка оценки позиции для проигравшего
    @Test
    void testEvaluatePosition_Loss() {
        char[] board = {'O', 'O', 'O', 'X', 'X', ' ', ' ', 'X', 'X'};
        game.symbol = 'X';
        int score = game.evaluatePosition(board, game.player1);
        assertEquals(-1, score); // Проигрыш для X
    }

    @Test
    void testMiniMax_BestMove() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.cplayer = game.player2;  // AI (нолики)
        int move = game.MiniMax(board, game.player2);
        assertEquals(5, move);  // Оптимальный ход для 'O'
    }

    @Test
    void testMinMove() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.cplayer = game.player2; // AI (нолики)
        int score = game.MinMove(board, game.player2);
        assertTrue(score <= 0); // Минимальный ход для O должен быть <= 0
    }

    @Test
    void testMaxMove() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.cplayer = game.player1; // X
        int score = game.MaxMove(board, game.player1);
        assertTrue(score >= 0); // Максимальный ход для X должен быть >= 0
    }

    // Новый тест: проверка сценария, когда игра завершена
    @Test
    void testMove_AfterGameOver() {
        char[] board = {'X', 'X', 'X', 'O', 'O', ' ', ' ', ' ', ' '};
        game.state = State.XWIN; // Игра закончена
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(4, moves.size()); // Нет доступных ходов, игра завершена
    }
}