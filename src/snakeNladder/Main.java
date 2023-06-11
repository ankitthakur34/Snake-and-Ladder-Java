package snakeNladder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static void printBoard(int[][] board, HashMap<Integer, Integer> map, HashMap<Integer, Integer> lad){
        int r=board.length;
        for(int i=0;i<r;i++){
            for(int j=0;j<board[i].length;j++){
                if (i == 0 && j<r-1 ) {
                    if(map.containsKey(board[i][j])){
                        System.out.print("0"+ board[i][j] + "X"+" ");
                    } else if(lad.containsKey(board[i][j])){
                        System.out.print("0"+ board[i][j] + "#"+" ");
                    }
                    else {
                        System.out.print("0" + board[i][j] + " ");
                    }
                }
                else if(map.containsKey(board[i][j])){
                    System.out.print(board[i][j] + "X"+" ");
                }
                else if(lad.containsKey(board[i][j])){
                    System.out.print(board[i][j] + "#"+" ");
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();

        }
    }

    static void fillBoard(int[][] board){
        int r= board.length;
        int k=1;
        for(int i=0;i<r;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=k;
                k++;
            }
        }
    }

    static int throwDice(char player){
        int dice = (int) Math.abs(Math.random() * 10);
        // System.out.println(dice);
        // %7 means 1-6 then 0,1,2 rem.
        dice = (dice % 7);
        // if %7==0 0 is not in dice make it 3.
        if (dice == 0) {
            dice = 3;
        }
        //  System.out.println(dice);
        return dice;
    }

    public static void main(String[] args) {
        int[][] board = new int[10][10];
        // fill the board with 1-100
       fillBoard(board);
        System.out.println("start");
       // now we need to create a list of snakes and where the player will drop.
        // so we will create a hashmap containing key as snake and value as dropValue
        HashMap<Integer,Integer> map = new HashMap<>();
        snakes(map);
        HashMap<Integer,Integer> lad = new HashMap<>();
        ladder(lad);


        // create player 1
        char player='A';
        char player1 ='A';
        char player2='B';
        int pos1=1;
        int pos2=1;
        int pos=1;
        boolean haveWom=false;
        while (!haveWom) {

            Scanner sc = new Scanner(System.in);
            System.out.println(player + " please press Y");
        //    String s= sc.next();
            // dice random value btw 1-6
            int dice=0;
//            if (s.equals("Y")) {
//                System.out.println(player +"is throwing dice");
//                 dice= throwDice(player);
//            }
            dice= throwDice(player);
            System.out.println( "dice "+ dice);

           if(player==player1) {
               if (pos1+dice <= 100) {
                   pos1 = pos1 + dice;
               }
           } else{
               if (pos2+dice <= 100) {
                   pos2 = pos2 + dice;
               }
           }
            System.out.println("pos of A"+ pos1);
            System.out.println("pos of B"+pos2);
           pos=(player==player1)?pos1:pos2;

           // after changing position we have to check for snakes and ladder.
          pos=  check(board,map,lad,pos);
            if(player==player1){
                pos1=pos;
            } else{
                pos2=pos;
            }
            System.out.println(pos);


            //break;
           if(pos1==100 || pos2==100 ){
               haveWom=true;
               System.out.println(player +" has won");
           } else if(dice==6){
               // extra chance
                   System.out.println(player+ "congo for extra chance");
           }else{
               player = (player == player1) ? player2 : player1;
           }

        }


       // simply print the board and for 1-9 add 0 before it.
        printBoard(board,map,lad);

    }

    private static void ladder(HashMap<Integer, Integer> lad) {
        lad.put(3,12);
        lad.put(22,34);
        lad.put(44,56);
        lad.put(52,61);
        lad.put(73,83);
        lad.put(76,89);
        lad.put(83,90);
        lad.put(85,98);
    }

    private static int check(int[][] board, HashMap<Integer, Integer> map,HashMap<Integer, Integer> lad, int pos1) {

            if(map.containsKey(pos1)){
                System.out.println("Sorry Bro you have been dissed");
                pos1=map.get(pos1);
            }
        if(lad.containsKey(pos1)){
            System.out.println("Yaa Bro you have jump");
            pos1=lad.get(pos1);
        }

        return pos1;
    }

    private static void snakes(HashMap<Integer, Integer> map) {

        map.put(8,1);
        map.put(13,6);
        map.put(17,9);
        map.put(35,22);
        map.put(41,34);
        map.put(47,39);
        map.put(61,53);
        map.put(76,60);
        map.put(85,73);
        map.put(91,89);
        map.put(97,75);
        map.put(99,88);
    }
}
