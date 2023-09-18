/**
 * класс, отвечающий за взаимодействие с полем
 */
public class GameField {
    public final static int SIZE=3;
    private char field [][];
    // заполняет поле пробелами

    /** заполняет поле пробелами(на вход получает размер поля)
     *
     * @param size размер поля
     */
     GameField (int size){
        field=new char[size][size];
        for (int x=0;x<size;x++){
            for (int y=0;y<size;y++){
                field[x][y]=' ';
            }

        }

    }

    /**ставит фигурку на поле (на вход получает координаты по x и y и фигурку, которую надо ставить)
     *
     * @param x координаты по х
     * @param y координаты по у
     * @param choise фигурка
     */

    public void setPos(int x,int y,char choise){
         field[x][y]=choise;
    }

    /** выводит фигурку , которая стоит на определённых координатах(на вход получает коррдинаты по x и y)
     *
     * @param x координаты по х
     * @param y координаты по у
     * @return
     */
    public char getPos(int x, int y){
         char choise=field[x][y];
         return choise;
    }

    /** возвращает размер поля
     *
     * @return
     */
    public int getFieldLength(){
         return field.length;
    }
    // проверяет заполнено ли поле полностью

    /** проверяет заполнено ли поле полностью(на вход получает размер поля)
     *
     * @param mapsize размер поля
     * @return
     */
    public boolean isFull(int mapsize){
         boolean answer=true;
         for(int x=0;x<mapsize;x++){
             for (int y=0;y<mapsize;y++){
                 if (field[x][y]==' '){
                     answer=false;
                     break;
                 }
             }
         }
         return answer;
    }
    // проверяет победил ли кто то из пользователей( на вход получает поле , использует несколько функций)

    /**проверяет победил ли кто то из пользователей( на вход получает поле , использует несколько функций)
     *
     * @param field3x3 поле 3 на 3
     * @return
     */
    public char checkWin3x3(char field3x3[][]){
         int count=0;

         int count2=0;

        for(int x=0;x<field3x3.length;x++){
             char rightchoice='N';
             char rightchoicevert='V';

             for (int y=0;y<field3x3.length;y++){
                 if(rightchoice=='N'){
                     if (field3x3[x][y]!=' '){
                         rightchoice=field3x3[x][y];
                     }
                 }
                 if (rightchoicevert=='V'){
                     if (field3x3[y][x]!=' '){
                         rightchoicevert=field3x3[y][x];
                     }
                 }

                /* if (field3x3[x][y]!=rightchoice && field3x3[y][x]!=rightchoicevert){
                     break;
                 }*/
                 if(field3x3[x][y]==rightchoice){
                     count+=1;
                 }

                 if(field3x3[y][x]==rightchoicevert) {
                     count2 += 1;
                 }



             }

             if (count==SIZE){
                 return rightchoice;
             }
             if (count2==SIZE){
                 return rightchoicevert;
             }

             count2=0;

             count=0;
        }
/*
        char rightchoice='N';
        char rightchoicegor='G';
        count=0;
        count2=0;
        for (int x=0;x<SIZE;x++){
            if(rightchoice=='N'){
                if (field3x3[x][x]!=' '){
                    rightchoice=field3x3[x][x];
                }

            }
            if (rightchoicegor=='G'){
                if (field3x3[x][SIZE-1-x]!=' '){
                    rightchoicegor=field3x3[x][SIZE-1-x];
                }
            }
            if (field3x3[x][x]==rightchoice){
                count+=1;
            }
            if (field3x3[x][SIZE-1-x]==rightchoicegor){
                count2+=1;
            }

        }
        if (count==SIZE){
            return rightchoice;
        }
        if (count2==SIZE){
            return rightchoicegor;
        }*/
        return 'N';
     }

    /**проверяет победу на полном поле
     *
     * @return
     */
     public char checkWin(){
         for (int xbig=0;xbig<field.length-SIZE+1;xbig++){
             for (int ybig=0;ybig<field.length-SIZE+1;ybig++){
                 char smallmap[][]=new char[SIZE][SIZE];
                 for(int x=0;x<SIZE;x++){
                     for(int y=0;y<SIZE;y++){
                         smallmap[x][y]=field[xbig+x][y+ybig];
                     }
                 }
                 if (checkWin3x3(smallmap)!='N'){
                     return checkWin3x3(smallmap);
                 }


             }
         }
         return 'N';
     }

    /**проверяет занято ли место на поле(на вход получает координаты по x и y )
     *
     * @param x координаты по х
     * @param y координаты по у
     * @return
     */
     public boolean checkInput(int x,int y) {
         boolean answer = false;
         if (field[x][y] == 'X' || field[x][y] == 'O') {
             answer=true;
         }
         return answer;
     }


}
