package hu.tokingame.elso;

import java.util.Vector;

public class Generator {
    
    public  enum Muvelet {
       szorzas, kivonas, osszeadas
    }

    private static int vel(int a, int b) {return (int)(Math.random()*(b-a+1)+a);}

    private static Vector<Integer> szamok = new Vector<Integer>();
    private static Vector<Muvelet> muveletek = new Vector<Muvelet>();
    private static int kezdoertek=0;
    private static int maxSz = 1;
    private static int minSz = 1;
    private static int maxM = 3;
    private static int minM = 1;
    private static int elemSzam = 6;
    private static Vector<Integer> SorozatElemek = new Vector<Integer>();
    private static Vector Fuggveny = new Vector();

    private static void generalas(){
        int mdb=vel(minM, maxM);
        kezdoertek = vel(1,9);
        Fuggveny.add(kezdoertek);
        szamok.add(0);
        for (int i = 0; i < mdb; i++) {
            /*int b=vel(1,3);
            if (muveletek.get(i) == Muvelet.kivonas){
                int a=szamok.get(i)-szamok.get(1);
                szamok.set(0, a);
            }*/
            int szdb = vel(minSz, maxSz);
            int sz = 0;
            for (int j = 0; j<szdb; j++){
                sz += vel(1*(int)Math.pow(10, j), 9*(int)Math.pow(10, j));
            }
            szamok.add(sz);


            switch (vel(1,3))
            {
                case 1: muveletek.add(Muvelet.szorzas); Fuggveny.add("*"); break;
                case 2: muveletek.add(Muvelet.kivonas); Fuggveny.add("-"); break;
                case 3: muveletek.add(Muvelet.osszeadas); Fuggveny.add("+"); break;
            }
            Fuggveny.add(sz);
        }
    }
    
    
    private static int szamit(int kezdoertek) {
        Vector<Muvelet> ujmuveletek = (Vector<Muvelet>)muveletek.clone();
        Vector<Integer> ujszamok = (Vector<Integer>)szamok.clone();
        ujszamok.set(0,kezdoertek);
        teszt(ujmuveletek, ujszamok);
        int i = 0;
        while (i<ujmuveletek.size()) {
            if (ujmuveletek.get(i)==Muvelet.szorzas) {
                ujszamok.set(i, ujszamok.get(i)*ujszamok.get(i+1));
                ujszamok.remove(i+1);
                ujmuveletek.remove(i);
            }
            else {
                i++;
            }
        }
        i = 0;
        while (i<ujmuveletek.size()) {
            if (ujmuveletek.get(i)==Muvelet.osszeadas)
            {
                ujszamok.set(i, ujszamok.get(i) + ujszamok.get(i+1));
            }
            if (ujmuveletek.get(i)==Muvelet.kivonas)
            {
                ujszamok.set(i, ujszamok.get(i) - ujszamok.get(i+1));
            }
            ujszamok.remove(i+1);
            ujmuveletek.remove(i);
        }
        return ujszamok.get(0);
    }
    
    private static void teszt(Vector<Muvelet> ujmuvelet, Vector<Integer> ujszam )
    {
        System.out.print(ujszam.get(0));
        for(int i=1; i<ujszam.size(); i++)
        {
            System.out.print(" " + ujmuvelet.get(i-1) + " " + ujszam.get(i));
        }
        System.out.println("");
    }
    
    public static void Generat(){ //Ezt kell meghívni amikor generálni akarunk.
        szamok.clear();
        muveletek.clear();
        SorozatElemek.clear();
        Fuggveny.clear();

        generalas();
        for(int i = 0; i< elemSzam; i++) {
            System.out.println(getSorozatElem(i));
            SorozatElemek.add(getSorozatElem(i));
        }
        System.out.println(Fuggveny.toString());
        System.out.println(getSorozatElemek());
    }

    
    public static int getSorozatElem(int elemszam) {

        if (elemszam == 0)
        {
            return kezdoertek;
        }
        else
        {
            int k = kezdoertek;
            for(int i = 0; i<elemszam; i++)
            {
                k = szamit(k);
            }
            return k;
        }
    }
    public static Vector getSorozatElemek(){
        return SorozatElemek;
    }
    public static Vector getFuggveny(){
        return Fuggveny;
    }
    public static void setMaxSz(int n){
        Generator.maxSz = n;
    }
    public static void setMaxM(int n){
        Generator.maxM = n;
    }
    public static void setMinSz(int n){
        Generator.minSz = n;
    }
    public static void setMinM(int n){
        Generator.minM = n;
    }
    public static int getMaxM() {
        return maxM;
    }
    public static int getMaxSz() {
        return maxSz;
    }
    public static int getMinM() {
        return minM;
    }
    public static int getMinSz() {
        return minSz;
    }

    public static int getElemSzam() {
        return elemSzam;
    }

    public static void setElemSzam(int elemSzam) {
        Generator.elemSzam = elemSzam;
    }

    public static void reset(){
        maxSz = 1;
        minSz = 1;
        maxM = 3;
        minM = 1;
        szamok.clear();
        muveletek.clear();
        SorozatElemek.clear();
        Fuggveny.clear();
    }


    public static void main(String[] args) {
        Generat();

    }

}
