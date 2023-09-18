/**
 * класс, отвечающий за ход компьютера
 */
public class Controller {

    /**рассчитывает сколько ходов человека и компьютера на поле(на вход получает поле,фигурку человека,фигурку компьютера)
     *
     * @param gf поле
     * @param choise фигурка человека
     * @param computerchoise фигурка компьютера
     * @return
     */
    public Cort amountOfXO(GameField gf, char choise, char computerchoise) {
        int countO = 0;
        int countX = 0;
        for (int ix = 0; ix < gf.getFieldLength(); ix++) {
            for (int iy = 0; iy < gf.getFieldLength(); iy++) {
                if (gf.getPos(ix, iy) == choise) {
                    countX += 1;
                } else if (gf.getPos(ix, iy) == computerchoise) {
                    countO += 1;
                }
            }
        }
        return new Cort(countX, countO);
    }


    /**ищет оптимальный ход для компьютера, если человека не надо блокировать и нет выигрышной комбинации( на вход получает поле, фигурку человека, фигурку компьютера)
     *
     * @param gf поле
     * @param choise фигурка человека
     * @param computerchoise фигурка компьютера
     * @return
     */
    public Cort optimalCompStep(GameField gf, char choise, char computerchoise) {
        int iCntChoise = amountOfXO(gf, choise, computerchoise).x;
        if (iCntChoise == 1) {
            int center = gf.getFieldLength() / 2;
            if (gf.getPos(center, center) != ' ') {
                return new Cort(center - 1, center);
            } else {
                return new Cort(center, center);
            }
        }
        else if (iCntChoise > 1) {
            int xpos = 0;
            int ypos = 0;
            for (int x = 0; x < gf.getFieldLength(); x++) {
                for (int y = 0; y < gf.getFieldLength(); y++) {
                    if (gf.getPos(x, y) == computerchoise) {
                        xpos = x;
                        ypos = y;
                    }
                }
            }
            for (int x = 0; x < gf.getFieldLength(); x++) {
                for (int y = 0; y < gf.getFieldLength(); y++) {
                    if (xpos - 1 <= x && x <= xpos + 1 && ypos - 1 <= y && y <= ypos + 1) {
                        if (gf.getPos(x, y) == ' ') {
                            return new Cort(x, y);
                        }
                    }

                }
            }
        }
        return null;
    }


    /** не даёт человеку победить( на вход получает поле, фигурку человека, фигурку компьютера)
     *
     * @param field3x3 поле
     * @param choice вы
     * @return
     */
    public Cort findHumanWinPos3x3block(char field3x3[][], char choice) {

        View view = new View();
        GameField gamefield = new GameField(GameField.SIZE);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (field3x3[x][y] == ' ') {
                    field3x3[x][y] = choice;
                    if (gamefield.checkWin3x3(field3x3) == choice) {
                        field3x3[x][y] = ' ';
                        Cort cort = new Cort(x, y);
                        return cort;
                    } else {
                        field3x3[x][y] = ' ';
                    }
                }
            }
        }
        return null;
    }


    /**ищет победный ход для компьютера( на вход получает поле, фигурку человека, фигурку компьютера)
     *
     * @param field3x3 поле
     * @param choice фигурка человека
     * @param computerchoice фигурка компьютера
     * @return
     */
    public Cort findCompWinPosition3x3(char field3x3[][], char choice, char computerchoice) {
        View view = new View();
        GameField gamefield = new GameField(GameField.SIZE);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (field3x3[x][y] !=choice && field3x3[x][y]!=computerchoice) {
                    field3x3[x][y] = computerchoice;
                    if (gamefield.checkWin3x3(field3x3) == computerchoice) {
                        field3x3[x][y] = ' ';
                        Cort cort = new Cort(x, y);
                        return cort;
                    } else {
                        field3x3[x][y] = ' ';
                    }
                }
            }
        }
        return null;
    }


    /**совмещает две функции, ищущие победный ход человека и компьютера( на вход получает поле, размер поля,  фигурку человека, фигурку компьютера)
     *
     * @param gf поле
     * @param choice фигурка человека
     * @param computerchoice фигурка компьютера
     * @return
     */
    public Cort getHumanWinPos(GameField gf, char choice, char computerchoice) {
        for (int xbig = 0; xbig < gf.getFieldLength() - 3 + 1; xbig++) {
            for (int ybig = 0; ybig < gf.getFieldLength() - 3 + 1; ybig++) {
                char smallmap[][] = new char[3][3];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        smallmap[x][y] = gf.getPos(xbig + x, y + ybig);
                    }
                }
                if (findCompWinPosition3x3(smallmap, choice, computerchoice) != null) {
                    Cort cort = new Cort(findCompWinPosition3x3(smallmap, choice, computerchoice).x, findCompWinPosition3x3(smallmap, choice, computerchoice).y);
                    return cort;
                }
            }
        }
        for (int xbig = 0; xbig < gf.getFieldLength() - 3 + 1; xbig++) {
            for (int ybig = 0; ybig < gf.getFieldLength() - 3 + 1; ybig++) {
                char smallmap[][] = new char[3][3];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        smallmap[x][y] = gf.getPos(xbig + x, y + ybig);
                    }
                }
                if (findHumanWinPos3x3block(smallmap, choice) != null) {
                    Cort cort = new Cort(findHumanWinPos3x3block(smallmap, choice).x, findHumanWinPos3x3block(smallmap, choice).y);
                    return cort;
                }
            }
        }
        return null;
    }
}

