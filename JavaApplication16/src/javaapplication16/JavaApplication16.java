/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication16;

import java.util.Scanner;

/**
 *
 * @author husam
 */
public class JavaApplication16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         Scanner input = new Scanner(System.in);

        System.out.print("Enter the text you want to encrypt : ");

        String myText = input.next();

        input.close();

      rsaEncrypt rsa = new rsaEncrypt(myText);
       //rsaEncrypt rsa = new rsaEncrypt(myText,5,7,7);
        System.out.println("\"Sifreli\"+");
        System.out.println(rsa.siferele());
        System.out.println("\"Sifresiz\"+");
        System.out.println(rsa.sifresizHali());

    }
    
}
