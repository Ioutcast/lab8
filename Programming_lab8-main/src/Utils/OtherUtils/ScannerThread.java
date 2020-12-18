package Utils.OtherUtils;

import java.util.Scanner;

public class ScannerThread extends Thread {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        String str = scanner.nextLine();

        if(str.equals("exit")){
            System.exit(-1);
        }


    }
}
