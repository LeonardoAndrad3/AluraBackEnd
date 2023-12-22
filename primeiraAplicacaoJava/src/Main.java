import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Format view string
        String str = "Realiznado um teste com strings para saber se ele pula linhas";

        System.out.println(str);

        int average;
        average = (int) (8.2+5.6+7.6+8.0+8.5)/2;
        System.out.println(average);


        Scanner sc = new Scanner(System.in);

        System.out.println("What's film? ");
        System.out.print("--");
        String resp = sc.nextLine();
        Double ava = 0.0;
        int count = 0;

        for(int i = 0; i < 3; i++){
            System.out.println("what's you available?");
             ava += sc.nextDouble();
             count++;
        }


        double aux = 0;
        do{

            System.out.println("what's you available?");
            aux = sc.nextDouble();

            if(aux != -1 ) {
                ava += aux;
                count++;
            }

        } while(aux != -1);

        System.out.println(ava);
        System.out.println(count);

        ava /= count;
        System.out.printf("Film: %s%nAvailable: %.1f", resp, ava);





    }
}