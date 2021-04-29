import java.util.Scanner;

public class solution {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        while(true){
            int num = input.nextInt();
            int index=31;
            while(((num>>index)&1)==0) index--;
            while(index>=0){
                if(((num>>index)&1)==1)
                    System.out.print("1");
                else
                    System.out.print("0");
                index--;
            }
        }
    }
}
