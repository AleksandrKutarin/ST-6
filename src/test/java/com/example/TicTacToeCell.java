package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Font;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeCellTest {

    private TicTacToeCell cell;

    @BeforeEach
    void setUp() {
        // Создаем ячейку перед каждым тестом
        cell = new TicTacToeCell(0, 1, 2);  // num=0, row=2, col=1
    }

    // Тест на правильную инициализацию ячейки
    @Test
    void testCellInitialization() {
        assertNotNull(cell);  // Проверяем, что объект ячейки создан
        assertEquals(0, cell.getNum());  // Проверяем, что номер ячейки правильный
        assertEquals(2, cell.getRow());  // Проверяем, что строка правильная
        assertEquals(1, cell.getCol());  // Проверяем, что столбец правильный
        assertEquals(' ', cell.getMarker());  // Проверяем, что ячейка инициализирована пустым маркером

        // Проверяем, что текст на кнопке соответствует маркеру
        assertEquals(" ", cell.getText());

        // Проверяем, что шрифт установлен правильно
        Font font = cell.getFont();
        assertNotNull(font);
        assertEquals("Arial", font.getName());
        assertEquals(Font.PLAIN, font.getStyle());
        assertEquals(40, font.getSize());
    }

    // Тест на установку маркера в ячейку
    @Test
    void testSetMarker() {
        // Устанавливаем маркер 'X' в ячейку
        cell.setMarker("X");

        // Проверяем, что маркер изменился
        assertEquals('X', cell.getMarker());

        // Проверяем, что текст на кнопке обновился
        assertEquals("X", cell.getText());

        // Проверяем, что ячейка стала неактивной
        assertFalse(cell.isEnabled());
    }

    // Тест на получение маркера
    @Test
    void testGetMarker() {
        // Изначально маркер должен быть пустым
        assertEquals(' ', cell.getMarker());

        // Устанавливаем маркер 'O'
        cell.setMarker("O");

        // Проверяем, что маркер стал 'O'
        assertEquals('O', cell.getMarker());
    }

    // Тест на корректность возвращаемых координат ячейки (строка и столбец)
    @Test
    void testGetRowAndCol() {
        // Проверяем, что строка и столбец ячейки инициализированы правильно
        assertEquals(2, cell.getRow());  // row = 2
        assertEquals(1, cell.getCol());  // col = 1
    }

    // Тест на корректность номера ячейки
    @Test
    void testGetNum() {
        // Проверяем, что номер ячейки правильный
        assertEquals(0, cell.getNum());
    }
}
