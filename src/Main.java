
public class Main {

    public static void main(String[] args) {
        // основное поле
        GameField gamefield;
        // координаты
        Cort cort;
        // человек
        View view=new View();
        // создание поля
        gamefield= new GameField(view.getFieldSize());
        // получение выбора человека
        char choice=view.getUserChoise();
        char computerchoise;
        if (choice=='X'){
            computerchoise='O';
        }
        else{
            computerchoise='X';
        }
        // создаём контроллер хода компьютера
        Controller contr=new Controller();
        // основной цикл
        while (true){
            cort=view.getHumanStep(gamefield.getFieldLength());
            while (true){
                if (gamefield.checkInput(cort.x,cort.y)==true){
                    System.out.println("Это место уже занято");
                    cort=view.getHumanStep(gamefield.getFieldLength());
                }
                else{
                    break;
                }
            }
            gamefield.setPos(cort.x,cort.y,choice);
            view.printField(gamefield);
            if (contr.getHumanWinPos(gamefield,choice,computerchoise)!=null){
                gamefield.setPos(contr.getHumanWinPos(gamefield,choice,computerchoise).x,contr.getHumanWinPos(gamefield,choice,computerchoise).y,computerchoise);
            }
            else if (contr.optimalCompStep(gamefield,choice,computerchoise) != null){
                gamefield.setPos(contr.optimalCompStep(gamefield,choice,computerchoise).x,contr.optimalCompStep(gamefield,choice,computerchoise).y,computerchoise);
            }
            view.printField(gamefield);
            if (gamefield.isFull(gamefield.getFieldLength())==true){
                System.out.println("Ничья");
                System.exit(1);
            }
            else if(gamefield.checkWin()!='N'){
                System.out.println("Победил"+gamefield.checkWin());
                break;
            }
        }
    }
}
