/**
 * �����, ���������� �� �������������� � �����
 */
public class GameField {
    public final static int SIZE=3;
    private char field [][];
    // ��������� ���� ���������

    /** ��������� ���� ���������(�� ���� �������� ������ ����)
     *
     * @param size ������ ����
     */
     GameField (int size){
        field=new char[size][size];
        for (int x=0;x<size;x++){
            for (int y=0;y<size;y++){
                field[x][y]=' ';
            }

        }

    }

    /**������ ������� �� ���� (�� ���� �������� ���������� �� x � y � �������, ������� ���� �������)
     *
     * @param x ���������� �� �
     * @param y ���������� �� �
     * @param choise �������
     */

    public void setPos(int x,int y,char choise){
         field[x][y]=choise;
    }

    /** ������� ������� , ������� ����� �� ����������� �����������(�� ���� �������� ���������� �� x � y)
     *
     * @param x ���������� �� �
     * @param y ���������� �� �
     * @return
     */
    public char getPos(int x, int y){
         char choise=field[x][y];
         return choise;
    }

    /** ���������� ������ ����
     *
     * @return
     */
    public int getFieldLength(){
         return field.length;
    }
    // ��������� ��������� �� ���� ���������

    /** ��������� ��������� �� ���� ���������(�� ���� �������� ������ ����)
     *
     * @param mapsize ������ ����
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
    // ��������� ������� �� ��� �� �� �������������( �� ���� �������� ���� , ���������� ��������� �������)

    /**��������� ������� �� ��� �� �� �������������( �� ���� �������� ���� , ���������� ��������� �������)
     *
     * @param field3x3 ���� 3 �� 3
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

    /**��������� ������ �� ������ ����
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

    /**��������� ������ �� ����� �� ����(�� ���� �������� ���������� �� x � y )
     *
     * @param x ���������� �� �
     * @param y ���������� �� �
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
