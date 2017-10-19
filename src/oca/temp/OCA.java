package oca.temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package oca_se8_programmer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import oca.temp.anotherpackage.ClassWithStaticMethod;
import static oca.temp.anotherpackage.ClassWithStaticMethod.staticHello;

/**
 *
 * @author User
 * 
 * 
 */
class Chicken{}
interface HenHouse{
    public List<Chicken> getChicken(); 
}
class HenHouseClass implements HenHouse{
    public List<Chicken> chickList = new ArrayList<>();
    @Override
    public List<Chicken> getChicken() {
        return chickList;
    }


}
class ChickenSong{
    ChickenSong(){
        HenHouse house = new HenHouseClass();
        Chicken chicken = house.getChicken().get(0);
        for(int i = 0; i < house.getChicken().size(); chicken = house.getChicken().get(i++)){
            System.out.println("cluck");
        }
    }

}
public class OCA {
    class Assessment{
            public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 01: E");
            return errs;
        }
        public int question02(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 02: C");
            return errs;
        }
        public int question03(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 03: C  E");
            if(errs > 0)System.out.println("............fel...........fel.....felll");
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 04: C");
            return errs;
        }
        public int question05(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 05: D");
            return errs;
        }
        public int question06(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 06: B");
            return errs;
        }
        public int question07(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 07: B");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 08: A");
            return errs;
        }
        public int question09(){
            int errs = 0;
            try{
                //System.exit(0);
            }finally{
            
            }
            System.out.println("ASSESSMENT QUESTION 09: B C ");
            return errs;
        }
        public class Question11{
            public Question11(){
                int x = 5, j = 10;
                OUTER: for (int i = 0; i < 3;)
                    INNER: do{
                        i++; x++;
                        if( x > 10)break INNER;
                        x += 4;
                        j++;
                    }while( j <= 2);
                    System.out.println(x);
            }
        }
        public int question10(){
            int errs = 1;
            
            //garbage collection
            //language problem
            System.err.println("ASSESSMENT QUESTION 10: C F");
            return errs;
        }
        public int question11(){
            int errs = 0;
            //new Question11();
            System.out.println("ASSESSMENT QUESTION 11: B");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("ASSESSMENT QUESTION 12: B");
            return errs;
        }
        public class BearShark{
            public BearShark(){
                int luck = 10;
                if((luck > 10 ? luck++: --luck) < 10){
                    System.out.println("Bear");
                }
                if( luck < 10)System.out.println("shark");
            
            }
        
        }
        public int question13(){
            int errs = 1;
            new BearShark();
            System.out.println("fel fel fel fel");
            System.err.println("ASSESSMENT QUESTION 13: A");
            return errs;
        }
        
        public int question14(){
            int errs = 1;
            //it's a strange one this, assuming av valid object when HenHouse clearly is
            //an interface
            //new ChickenSong();
            System.err.println("ASSESSMENT QUESTION 14: B");
            System.exit(0);
            return errs;
        }
        public int question15(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 15:");
            return errs;
        }
        public int question16(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 16:");
            return errs;
        }
        public int question17(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 17:");
            return errs;
        }
        public int question18(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 18:");
            return errs;
        }
        public int question19(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 19:");
            return errs;
        }
        public int question20(){
            int errs = 1;
            System.out.println("ASSESSMENT QUESTION 20:");
            return errs;
        }
        public int run(){
            //int errs = 0;
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs += question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();
            int nQuestion = 20;
            System.out.println("errs: " + errs);
            System.out.println("%: " + ((nQuestion - errs) / (float)nQuestion) * 100);
            return errs;
        }
    }
    class Chapter01{
        public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 01: A  B  E");
            return errs;
        }
        public int question02(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 02: (A)... D");
            return errs;
        }
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 03: B  D  E");
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 04: A  B ");
            return errs;
        }
        public int question05(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 05: C  D");
            return errs;
        }
        public int question06(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 06: E ");
            return errs;
        }
        public int question07(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 07: A  B  C ");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 08: B,  ");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 09: A C D E  ");
            return errs;
        }
        public int question10(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 10: E ");
            return errs;
        }
        public int question11(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 11: C D  ");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 12: G ");
            return errs;
        }
        public int question13(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 13: A D ");
            return errs;
        }
        public int question14(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 14: B new answer D ");
            return errs;
        }
        public int question15(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 15: A E");
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 16: B C D ");
            return errs;
        }
        public int question17(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 17: A E ");
            return errs;
        }
        public int question18(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 18: C D E ");
            return errs;
        }
        public int question19(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 19: B D ");
            return errs;
        }
        public int question20(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 20: B E  ");
            return errs;
        }
        public int question21(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 21: A ");
            return errs;
        }
        public int question22(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 22: B E ");
            return errs;
        }
        public int question23(){
            int errs = 0;
            System.out.println("CHAPTER 01 QUESTION 23: C D");
            return errs;
        }
        public int run(){
            //int errs = 0;
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs += question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();
            errs += question21();
            errs += question22();
            errs += question23();
            int nQuestion = 23;
            System.out.println("errs: " + errs);
            System.out.println("%: " + ((nQuestion - errs) / (float)nQuestion) * 100);
            return errs;
        }
    }
    class Chapter02{
        public int errs = 0;
         public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 01: A D ");
            return errs;
        }
        public int question02(){
            int errs = 0;
            byte x = 5;
            byte y = 10;
            int i = x +y;
            long l = x + y;
            //boolean bool = x + y;
            double d = x + y;
            //short s = x + y;
            //byte b = x + y;
            
            System.out.println("CHAPTER 02 QUESTION 02: A B C");
            return errs;
        }
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 03: F");
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 04: B C D F");
            return errs;
        }
        public int question05(){
            int errs = 0;

            System.out.println("CHAPTER 02 QUESTION 05: C");
            return errs;
        }
        public int question06(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 06 F:");
            return errs;
        }
        public int question07(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 07: D");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 08: B ");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 09: (B... ) F");
            return errs;
        }
        public int question10(){
            int errs = 0;
            byte a = 40, b = 50;
            //byte c = (byte)a + b; casts a but not b
            byte c = (byte)(a + b);//cast the sum of a and b,
            System.out.println("CHAPTER 02 QUESTION 10: D");
            return errs;
        }
        public int question11(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 11: A");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 12: D");
            return errs;
        }
        public int question13(){
            int errs = 0;
            int x = 50, y = 75;
            boolean b = x >= y;
            System.out.println("CHAPTER 02 QUESTION 13: B");
            return errs;
        }
        public int question14(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 14: C");
            return errs;
        }
        public int question15(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 15: E ");
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 16: D ");
            return errs;
        }
        public int question17(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 17: D");
            return errs;
        }
        public int question18(){
            int errs = 0;
            int count = 0;
            ROW_LOOP: for( int row = 1; row <= 3; row++){
                        for(int col = 1; col <= 2; col++){
                            //System.out.format("row = %d, col = %d, %b \n", row, col, (col * row % 2 == 0));
                            if (col * row % 2 == 0)continue ROW_LOOP;
                            count++;
                        }
                    }
            //System.out.println("count: " + count);
            //System.out.println("");
            System.out.println("CHAPTER 02 QUESTION 18: B");
            return errs;
        }
        public int question19(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 19: B, new answer D");
            return errs;
        }
        public int question20(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 20: B");
            return errs;
        }
        public int question21(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 21");
            return errs;
        }
        public int question22(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 22:");
            return errs;
        }
        public int question23(){
            int errs = 0;
            System.out.println("CHAPTER 02 QUESTION 23:");
            return errs;
        }
        public int run(){
            System.out.println(".................CHAPTER 02...................");
            //int errs = 0;
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs += question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();  
            double nQuestion = 20.0;
            System.out.println("number of errors: chapter 02: " + errs);
            System.out.println("% " + ((nQuestion - errs) / nQuestion) * 100);
            return errs;
        }
    }
    class Chapter03{
        public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 01: G");;
            return errs;
        }
        public int question02() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 02: A,C,D ");
            return errs;
        }
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 03: B C E ");
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 04: B");
            return errs;
        }
        public int question05() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 05: F ");
            String s = "java";
            StringBuilder sb = new StringBuilder("java");
            //if ( s  == sb){
            
            //}
            //System.out.println("\ts.equals(sb)" + s.equals(sb));
            //System.out.println("D...no output");
            return errs;
        }
        public void roar(String str, StringBuilder sb){
            str.concat("!!!");
            sb.append("!!!");
        }
        public int question06(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 06: B");
            StringBuilder sb = new StringBuilder("roar");
            String str = new String("roar");
            roar(str , sb);
            //System.out.format(str + " " + sb);
            return errs;
        }
        public int question07(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 07: B, D, E");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 08: A E D ");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 09: C");
            return errs;
        }
        public int question10() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 10: F");;
            String s = "";
            s += 'c';
            s += false;
            s += 2;
            //System.out.println("\tString " + s);
            if ( s == "cfalse2"){
                //System.out.println("strange");
            }
            s = s.intern();
            if (s != "cfalse2"){
               // System.out.println("strange");
            }
            //System.out.println("and the answer is F");
            return errs;
        }
        public int question11(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 11: E");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 12: A ");
            return errs;
        }
        public int question13(){
            int errs = 0;
            //StringBuilder b ="rumble";
            StringBuilder b = new StringBuilder("rumble");
            b.append(4).deleteCharAt(3).delete(3,b.length() - 1);
            //System.out.println("\tb: " + b);
            
            System.out.println("CHAPTER 03 QUESTION 13: (A)...F");
            return errs;
        }
        public int question14(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 14: (A) ... A C ");
            return errs;
        }
        public int question15(){
            int errs = 0;
            int [][] scores = new int[5][];
            Object[][][] cubbies = new Object[3][0][5];
            java.util.Date[] date[] = new  java.util.Date[2][];
            System.out.println("CHAPTER 03 QUESTION 15: C E F");
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 16: C");
            return errs;
        }
        public int question17(){
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 17: F");
            return errs;
        }
        public int question18() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 18: (A C)... A C D E");
            return errs;
        }
        public int question19() {
            int errs = 1;
            ArrayList arrayList = new ArrayList();
            //arrayList.remove(0);
            System.err.println("CHAPTER 03 QUESTION 19: C");
            return errs;
        }
        
        public int question20() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 20: D");
            return errs;
        }
        public int question21() {
            int errs = 0;
            ArrayList<Integer> values = new ArrayList<>();
            values.add(4);
            values.add(5);
            values.set(1, 6);
            values.remove(0);
            for(Integer v: values){
                //System.out.println("v = " + v);
            }
            System.out.println("CHAPTER 03 QUESTION 21: (B) ... C");
            return errs;
        }
        public int question22() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 22: D");
            return errs;
        }
        
        public int question23() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 23: A");
            return errs;
        }
        
        public int question24() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 24: C ");
            String[] names = {"tom", "dick", "harry"};
            //List<String> list = names.asList();
            return errs;
        }
        
        public int question25() {
            int errs = 0;
            List<String> hex = Arrays.asList("30", "8", "3A", "FF");
            Collections.sort(hex);
            int x = Collections.binarySearch(hex, "8");
            int y = Collections.binarySearch(hex, "3A");
            int z = Collections.binarySearch(hex, "4F");
            System.out.println("CHAPTER 03 QUESTION 25: D");
            return errs;
        }
        public int question26() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 26: A B D ");
            return errs;
        }
        public int question27() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 27: B");;
            return errs;
        }
        
        public int question28() {
            int errs = 0;
            LocalDate date = LocalDate.of(2014, 6, 21);
            date = LocalDate.of(2014, Month.JUNE, 21);
            date = LocalDate.of(2014, Calendar.MARCH, 21);//needs an import so there you go
            System.out.println("CHAPTER 03 QUESTION 28: D F");
            return errs;
        }
        public int question29() {
            int errs = 0;
            LocalDate date = LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
            date.plusDays(2);
            //date.plusHours();
            //System.out.println(date.getYear() + " " + date.getMonth() + " " +  date.getDayOfMonth());
            System.out.println("CHAPTER 03 QUESTION 29: D");
            return errs;
        }
        public int question30() {
            int errs = 0;
            //LocalDate date = LocalDate.of(2018, Month.APRIL, 40);
            System.out.println("CHAPTER 03 QUESTION 30: F ");
            return errs;
        }
        public int question31() {
            int errs = 0;
            LocalDate date = LocalDate.of(2018,  Month.APRIL, 30);
            date.plusDays(2);
            date.plusYears(3);
            //System.out.println(date);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MMMM dd");
            //System.out.println(date.format(df));
            System.out.println("CHAPTER 03 QUESTION 31: (F)...B");
            return errs;
        }
        public int question32() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 32: a c ");
            LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
            Period p = Period.of(1, 2, 3);
            d = d.minus(p);
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            //System.out.println(d.format(f));
            //System.out.println("and the answer is A eller snarare C...FormatStyle.SHORT doesn't care about time");
            return errs;
        }
        public int question33() {
            int errs = 0;
            System.out.println("CHAPTER 03 QUESTION 32: B");
            LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
            Period p = Period.ofDays(1).ofYears(2);
            d = d.minus(p);
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            //System.out.println(f.format(d));
            return errs;
        }
        public int run(){
            System.out.println("..................CHAPTER 03..................");
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs += question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();
            errs += question21();
            errs += question22();
            errs += question23();
            errs += question24();
            errs += question25();
            errs += question26();
            errs += question27();
            errs += question28();
            errs += question29();
            errs += question30();
            errs += question31();
            errs += question32();
            errs += question33();
            int nQuestions = 33;
            System.out.println("chapter 03 errs " + errs);
            System.out.println("% " + (nQuestions - errs) /33.0 * 100);
            return errs;
        }

    }
    class Chapter04{
        public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 01: B C");
            return errs;
        }
        public int question02(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 02: A D");
            return errs;
        }
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 03: A C D");;
            return errs;
        }
        public class Question04{
            public Question04(){
            
            }
            public void moreA(int...nams){}
            public void moreB(String values, int...nums){}
            public void moreG(String[]values,int... nums){}
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 04: (A C D)...A B G");
            return errs;
        }
        public void question05(boolean b, boolean... b2){
            //System.out.println("CHAPTER 04 QUESTION 05:");
            //System.out.println("b2 " + b2.length);
        
        }
        public int question05(){
            int errs = 0;
             System.out.println("CHAPTER 04 QUESTION 05: D G");;
            question05(true, new boolean[2]);
            return errs;
        }
        public int question06(){
            int errs = 0;
            //language problem, my language that is...get a grip
            System.out.println("CHAPTER 04 QUESTION 06: (E)...D");;
            return errs;
        }
        public class School {
            public School() {
                //System.out.println(ClassRoom.globalKey);
                //ClassRoom classRoom = new ClassRoom(101, "Mrs. Anderson");
                //System.out.println(classRoom.roomNumber);
                //System.out.println(classRoom.floor);
                //System.out.println(classRoom.teacherName);
            }
        }
        public int question07(){
            int errs = 0;
            // line 5   B
            //line 6    C
            //line 7    D
            //line 9    F
            System.out.println("CHAPTER 04 QUESTION 07: (B D F)... B C D F");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 08: B C E");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 09: C E");
            return errs;
        }
        public int question10(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 10: C E");
            return errs;
        }

        public int question11(){
            int errs = 0;
            //A false
            //B true
            //C false
            //D false
            //E false
            //F true
            System.err.println("CHAPTER 04 QUESTION 11: (A)...(B  F)... B E");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 12: D");
            return errs;
        }
        public int question13(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 13: (D)...E");
            return errs;
        }
        public int question14(){
            int errs = 0;
            staticHello();
            System.out.println("CHAPTER 04 QUESTION 14: (A)... B");
            return errs;
        }
        public int question15(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 15: E");
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 16: C.......B");
            return errs;
        }
        public int question17(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 17: B D E");
            return errs;
        }
        public int question18(){
            int errs = 0;
            //OK, G is okey if the main method is in the same class as the private
            //constructor, which is not plainly stated in the question but ok
            System.out.println("CHAPTER 04 QUESTION 18: (C, F, G)... C G");
            return errs;
        }
        public int question19(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 19: (A, D)... A G");
            return errs;
        }
        public int question20(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 20: E ");
            return errs;
        }
        public int question21(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 21: C and nothing more ");
            return errs;
        }
        public int question22(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 21: E i would say");
            return errs;
        }
        public int question23(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 23:A");
            return errs;
        }
        public int question24(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 24: B, C, E... and my head hurts");
            return errs;
        }
        public int question25(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 25: A E");
            return errs;
        }
        public int question26(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 24: A E");
            return errs;
        }
        public int run(){
            System.out.println("here we go..chapter 04.................");
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs += question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();
            errs += question21();
            errs += question22();
            errs += question23();
            errs += question24();
            errs += question25();
            float nQuestions = 25f;
            System.out.println("errs : " + errs);
            System.out.println("% " + (nQuestions - errs)/nQuestions * 100);
            return errs;
        }
        
    }
    class Chapter05{
        public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 01: (B C E) ... B");
            return errs;
        }
        public int question02(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 02: ((B)...F)... E");
            return errs;
        }
        
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 03: A B D E");
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 04: C E");
            return errs;
        }
        public int question05(){
            int errs = 0;
            //this you may override
            //B protected instance methods
            //C public instance methods
            //and you may only hide these
            //A, D, E, F
            System.out.println("CHAPTER 02 QUESTION 05: (D E F)...A D E F");
            return errs;
        }
        public int question06(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 06: D");
            return errs;
        }
        public int question07(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 07: B C ");
            return errs;
        }
        public int question08(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 08: D, new answer F");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 09: (F...) A");
            return errs;
        }
        public int question10(){
            int errs = 1;
            System.err.println("CHAPTER 05 QUESTION 10: (A B C E F G)... B C E F G");
            return errs;
        }
        public int question11(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 11: A D E");
            return errs;
        }
        public int question12(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 12: B");
            return errs;
        }
        public int question13(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 13: A");
            return errs;
        }
        public int question14(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 14: C....D");
            return errs;
        }
        public int question15(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 15: B");
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 16: E");
            return errs;
        }
        public int question17(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 17: B");
            return errs;
        }
        public int question18(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 18: E");
            return errs;
        }
        public int question19(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION 19: A C F");
            return errs;
        }
        public int question20(){
            int errs = 0;
            System.out.println("CHAPTER 05 QUESTION: 20 A");
            return errs;
        }
        private int run() {
            System.out.println(".................CHAPTER 05...................");
            errs += question01();
            errs += question02();
            errs += question03();
            errs += question04();
            errs +=  question05();
            errs += question06();
            errs += question07();
            errs += question08();
            errs += question09();
            errs += question10();
            errs += question11();
            errs += question12();
            errs += question13();
            errs += question14();
            errs += question15();
            errs += question16();
            errs += question17();
            errs += question18();
            errs += question19();
            errs += question20();
            System.out.println("errs: " + errs);
            int correct = 20 - errs;
            System.out.println("percent" + correct/ 20.0 * 100);
            return errs;
        }
    
    
    }
    class Chapter06{
        public int errs = 0;
        public int question01(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 01 B:");
            return errs;
        }
        public int question02(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 02: B D");
            return errs;
        }
        public int question03(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 03: C");;
            return errs;
        }
        public int question04(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 04: B");
            return errs;
        }
        public int question05(){
            int errs = 0;
             System.out.println("CHAPTER 06 QUESTION 05: A B D");;
            return errs;
        }
        public int question06(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 06: C ");;
            return errs;
        }
        public class DoSomething{
            public void go(){
                System.out.println("A");
                try {
                    stop();
                } catch (ArithmeticException e) {
                    System.out.println("B");
                }finally{
                    System.out.println("C");
                }
                System.out.println("D");
            }
            public void stop(){
                System.out.println("E");
                Object x = null;
                x.toString();
                System.out.println("F");
            }
        }
        public int question07(){
            int errs = 0;
            //new DoSomething().go();
            System.out.println("CHAPTER 06 QUESTION 07 C:");
            return errs;
        }
        public int question08(int a, int b){
            try{
                return a / b;
            }catch(RuntimeException re){
                return -1;
            //}catch (ArithmeticException ae){
            //    return 0;
            }finally{
                //System.out.println("done");
            }

        }
        public int question08(){
            int errs = 0;
            question08(1, 1 );
            System.out.println("CHAPTER 06 QUESTION 08: E ");
            return errs;
        }
        public int question09(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 09: B ");
            return errs;
        }
        public int question10(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 10: E");
            return errs;
        }
        public int question11(){
            int errs = 0;
            //1, 2 
            System.out.println("CHAPTER 06 QUESTION 11: A");
            return errs;
        }
        public int question12(){
            int errs = 0;
            //1, 2, 4
            System.out.println("CHAPTER 06 QUESTION 12: A B D G");
            return errs;
        }
        public int question13(){
            int errs = 0;
            //a b c e
            System.out.println("CHAPTER 06 QUESTION 13: A B C E");;
            return errs;
        }
        public void question14(char c) throws IOException{
            switch (c){
                case 'A' :System.out.println("its okay");
                case 'B': break;   //throw new Exception();
                case 'C': throw new IllegalArgumentException();
                case 'D': throw new java.io.IOException();
                case 'E': throw new RuntimeException();
            }   
        }
        public int question14(){
            int errs = 0;
            try {
                question14('B');
            } catch (IOException ex) {
                System.out.println(ex);
            }
            System.out.println("CHAPTER 06 QUESTION 14: A C D E");
            return errs;
        }
        public int question15(){
            int errs = 0;
            //while(false){}
             System.out.println("CHAPTER 06 QUESTION 15: A B D E ");;
            return errs;
        }
        public int question16(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 16: B");;
            return errs;
        }
        public int question17(){
            int errs = 0;
            //A C 
            System.out.println("CHAPTER 06 QUESTION 17: A C D E");
            return errs;
        }
        public class Question18{
            public static final boolean DEBUG = false;
            public Question18(){

            
            }
            public void throwError() throws Error{
                    throw new Error();
            }
            public void throwRuntimeException() throws RuntimeException{
                throw new RuntimeException();
            }
            public void catchError(){
                try{
                    throw new Error();
                }catch(Error er){
                    if ( DEBUG) System.out.println("caught error" + er);
                }
            }
            public void catchRuntimeException(){
                try {
                    throwRuntimeException();
                } catch (RuntimeException e) {
                    if ( DEBUG)System.out.println("caught runtimeException" + e);
                }
            
            }

            private void run() {
                catchRuntimeException();
                catchError();
            }
        
        }
        public int question18(){
            int errs = 0;
            //A. checked exceptions are allowed to be handled or declared
            //B. checked exceptions are required to be handled or declared
            //C. errors are allowed to be handled or declared
            //D. errors are required to be handled or declared
            //E. runtime errors are allowed to be handled or declared
            //F. runtime errors are required to be handled or declared
            //more of language problem than anything else...except it's a real problem at the exam
            new Question18().run();
            System.out.println("CHAPTER 06 QUESTION 18: (B C E) .... A B C E");
            return errs;
        }
        public int question19(boolean debug){
            try{
                System.out.println("work real hard");
            }catch(StackOverflowError w){
            
            }catch(RuntimeException re){
            
            }
            return 1;
        }
        public int question19(){
            int errs = 0;
            System.out.println("CHAPTER 06 QUESTION 19: (B C E)... C E");
            return errs;
        }
        public int question20(){
            int errs = 0;
            System.out.println("CHAPTER 04 QUESTION 20: A E");
            return errs;
        }
        private int run() {
           errs += question01();
           errs += question02();
           errs += question03();
           errs += question04();
           errs += question05();
           errs += question06();
           errs += question07();
           errs += question08();
           errs += question09();
           errs += question10();
           errs += question11();
           errs += question12();
           errs += question13();
           errs += question14();
           errs += question15();
           errs += question16();
           errs += question17();
           errs += question18();
           errs += question19();
           errs += question20();
           double nQuestions = 20;
            System.out.println("percent " + ( nQuestions - errs )/ nQuestions *100);
            return errs;
        }
    
    }
    public void run(){
        int errs = 0;
        errs += new Assessment().run();
        errs += new Chapter01().run();
        errs += new Chapter02().run();
        errs += new Chapter03().run();
        errs += new Chapter04().run();
        errs += new Chapter05().run();
        errs += new Chapter06().run();
        System.out.println("TOTAL NUMBER OF ERRORS: " + errs);
    }
    public static void main(String[] args) {
        new OCA().run();
    }
}
