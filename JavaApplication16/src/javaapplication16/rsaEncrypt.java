/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication16;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author husam
 */
public class rsaEncrypt {
    
     private static String message;
    private static BigInteger nBig;
    private static BigInteger asciiBig;
    private static BigInteger eBig;
    private static BigInteger dBig;
    private static char[] myArray;
    private static char[] cryptoText;

    @SuppressWarnings("static-access")

    public rsaEncrypt(String message) {

        this.message = message;

        myArray = message.toCharArray();

        int p = 59;
        int q = 61;
        int n = p * q;
        int totientN = (p - 1) * (q - 1);
        int e;
        Random random = new Random();
        do {

            e = random.nextInt(totientN);

        } while (!isPrime(e));

        int d = dHesaplama(totientN, e);
        System.out.println("e:"+e);
        System.out.println("d:"+d);
        nBig = BigInteger.valueOf(n);
        eBig = BigInteger.valueOf(e);
        dBig = BigInteger.valueOf(d);
    }

    public rsaEncrypt(String message, int ps, int qs, int es) {

        this.message = message;

        myArray = message.toCharArray();

        int p = ps;
        int q = qs;
        int n = p * q;
        int totientN = (p - 1) * (q - 1);
        int e = es;
        //Random random = new Random();

        int d = dHesaplama(totientN, e);

        nBig = BigInteger.valueOf(n);
        eBig = BigInteger.valueOf(e);
        dBig = BigInteger.valueOf(d);
    }

    private static boolean isPrime(int e) {

        for (int i = 2; i < e; i++) {

            if (e % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int dHesaplama(int totient, int e) {

        for (int k = 1; k < totient; k++) {

            if (((totient * k + 1) % e) == 0) {

                return (((totient * k) + 1) / e);
            }
        }

        return 0;
    }

    private static BigInteger dhesaplama2(BigInteger nBig, BigInteger ascii, BigInteger e) {

        BigInteger us = BigInteger.valueOf(1);

        for (int i = 1; i <= e.intValue(); i++) {

            us = ascii.multiply(us);//us alma işlemleri
        }

        BigInteger c = BigInteger.valueOf(1);

        c = us.mod(nBig);

        return c;
    }

    public char[] siferele() {

        cryptoText = new char[myArray.length];

        int cryptoIndex = 0;

        for (char letter : myArray) {

            int ascii = (int) letter;

            asciiBig = BigInteger.valueOf(ascii);

            BigInteger c = dhesaplama2(nBig, asciiBig, eBig);

            cryptoText[cryptoIndex++] = (char) c.intValue();
        }

        return cryptoText;

    }

    public char[] sifresizHali() {

        char[] decryptoText = new char[myArray.length];

        int decryptoIndex = 0;

        for (char letter : cryptoText) {

            BigInteger c = BigInteger.valueOf((int) letter);//asci değerini bigİnte ceevirdi

            BigInteger de = dhesaplama2(nBig, c, dBig);

            decryptoText[decryptoIndex++] = (char) de.intValue();//karraktere cevirdi

        }

        return decryptoText;

    }

}
