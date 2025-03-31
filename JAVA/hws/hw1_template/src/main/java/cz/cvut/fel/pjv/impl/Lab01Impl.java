package cz.cvut.fel.pjv.impl;
import cz.cvut.fel.pjv.Lab01;
import java.util.Scanner;

public class Lab01Impl implements Lab01 {
    @Override
    public void homework() {

        System.out.println("Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):");
        Scanner scanner = new Scanner (System.in);
        int option;
        int desetinna;
        double num1= 0;
        double num2= 0;
        double result = 0;
        char operand = 0;

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        }
        else {
            System.out.println("Chybna volba!");
            return;
        }

        if (option <1 || option >4){
            System.out.println("Chybna volba!");
            return;
        }
        switch (option) {
            case 1:
                operand = '+';
                System.out.println("Zadej scitanec: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num1 =  scanner.nextDouble();
                System.out.println("Zadej scitanec: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num2 =  scanner.nextDouble();
                result = num1 + num2;
                break;
            case 2:
                operand = '-';
                System.out.println("Zadej mensenec: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num1 =  scanner.nextDouble();
                System.out.println("Zadej mensitel: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num2 =  scanner.nextDouble();
                result = num1 - num2;
                break;
            case 3:
                operand = '*';
                System.out.println("Zadej cinitel: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num1 =  scanner.nextDouble();
                System.out.println("Zadej cinitel: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num2 =  scanner.nextDouble();
                result = num1 * num2;
                break;
            case 4:
                operand = '/';
                System.out.println("Zadej delenec: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num1 =  scanner.nextDouble();
                System.out.println("Zadej delitel: ");
                if (!scanner.hasNextDouble()){
                    System.out.println("Neni cislo");
                    return;
                }
                num2 =  scanner.nextDouble();
                if (num2 == 0){
                    System.out.println("Pokus o deleni nulou!");
                    return;
                }
                result = num1 / num2;
       }

        System.out.println("Zadej pocet desetinnych mist: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }
        desetinna = scanner.nextInt();
        if (desetinna < 0){
            System.out.println("Chyba - musi byt zadane kladne cislo!");
            return;
        }

        System.out.printf("%." + desetinna + "f " + operand + " %." + desetinna + "f = %." + desetinna + "f\n" , num1, num2, result);
        scanner.close();
    }
}
