import java.util.Scanner ;

/**
 * класс, отвечающий за вырисовывание и создание поля
 */
public class View {

    /**считывает размер поля с экрана
     *
     * @return
     */
    public int getFieldSize(){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Ведите размер поля: ");
            int num = in.nextInt();
            if (2 <= num && num <= 10) {
                return num;
            }
            else {
                System.out.println("Введите число от 2 до 10");
            }
        }
    }


    /**считывает выбор человека фигурку
     *
     * @return
     */
    public char getUserChoise(){
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Ведите фигурку: ");
            String choice = in.nextLine();
            char newchoice=choice.charAt(0);
            if ((!choice.equalsIgnoreCase("X")) && (!choice.equalsIgnoreCase( "O"))) {
                System.out.println("Введите Х или О");
            }
            else{
                return newchoice;
            }
        }


    }

    /**считывает координаты хода человека(на вход получает размер поля)
     *
     * @param mapsize размер поля
     * @return
     */
    public Cort getHumanStep(int mapsize){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите ход по х: ");
            int num = in.nextInt();
            System.out.print("Введите ход по у: ");
            int num2 = in.nextInt();
            if (0<=num && num<mapsize && 0<=num2 && num2<mapsize){
                Cort cort=new Cort(num,num2);
                return cort;
            }
            else{
                System.out.println("Введите число от 0 до"+' '+mapsize);
            }
        }
    }

    /**выводит поле на экран(на вход получает поле)
     *
     * @param field поле
     */
    public void printField(GameField field){
        String strheader=" ";
        strheader=strheader+' ';
        for (int e=0;e<field.getFieldLength();e++){
            strheader=strheader+e+' ';
        }
        strheader = "\u001B[42m" + "\u001B[30m"+strheader+'Y' + "\u001B[30m";
        System.out.println(strheader);
        for (int x=0;x<field.getFieldLength();x++){
            String str2="";
            for (int y=0;y<field.getFieldLength();y++){
                if (field.getPos(x,y)=='X'){
                    str2=str2+"\u001B[31m"+'X'+"\u001B[30m";
                }
                else if (field.getPos(x,y)=='O'){
                    str2=str2+"\u001B[31m"+'O'+"\u001B[30m";
                }
                else{
                    str2=str2+' ';
                }
                str2=str2+"\u001B[37m"+'|'+"\u001B[0m";
            }
            str2="\u001B[42m"+"\u001B[37m"+"\u001B[30m"+x+"\u001B[30m"+"\u001B[0m"+"\u001B[37m"+'|' +"\u001B[0m"+str2+"\u001B[42m"+' '+"\u001B[30m";
            System.out.println(str2);
        }
        String strfooter="";
        for (int c=0;c<field.getFieldLength()*2;c++){
            strfooter = strfooter + ' ';
        }
        strfooter = "\u001B[42m" +"\u001B[30m"+'X' + strfooter + ' '+' ' + "\u001B[30m"+"\u001B[0m";
        System.out.println(strfooter);
    }
}
