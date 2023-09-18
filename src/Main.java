
public class Main {

    public static void main(String[] args) {
        // �������� ����
        GameField gamefield;
        // ����������
        Cort cort;
        // �������
        View view=new View();
        // �������� ����
        gamefield= new GameField(view.getFieldSize());
        // ��������� ������ ��������
        char choice=view.getUserChoise();
        char computerchoise;
        if (choice=='X'){
            computerchoise='O';
        }
        else{
            computerchoise='X';
        }
        // ������ ���������� ���� ����������
        Controller contr=new Controller();
        // �������� ����
        while (true){
            cort=view.getHumanStep(gamefield.getFieldLength());
            while (true){
                if (gamefield.checkInput(cort.x,cort.y)==true){
                    System.out.println("��� ����� ��� ������");
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
                System.out.println("�����");
                System.exit(1);
            }
            else if(gamefield.checkWin()!='N'){
                System.out.println("�������"+gamefield.checkWin());
                break;
            }
        }
    }
}
