//Created by NikaStark on 28.07.2015.

import engine.controllers.calculator.Calculator;
import engine.models.Card;
import engine.models.Distribution;
import engine.models.Game;
import inputer.Scanner;
import inputer.Translator;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        long t1=System.nanoTime();
        final String path = "C:\\Users\\Alex\\Desktop\\primalCalculation2.txt";
        final Calculator calculator = new Calculator();
        try(PrintStream outputFile = new PrintStream(new FileOutputStream(path))) {
            for (int i = 0; i < Game.DECK_OF_CARDS.length - 1; i++) {
                for (int j = i + 1; j < Game.DECK_OF_CARDS.length; j++) {
                    Distribution distribution = new Distribution(new Card[]{Game.DECK_OF_CARDS[i], Game.DECK_OF_CARDS[j]}, null);
                    outputFile.print(Game.DECK_OF_CARDS[i] + " " + Game.DECK_OF_CARDS[j] + " ");
                    for (int number : calculator.getTableChances(distribution)) {
                        outputFile.print(number + "  ");
                    }
                    outputFile.println();
                }
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        long t2 = System.nanoTime();
        long elapsedTime = t2-t1;
        System.out.println("Elapsed time was "+elapsedTime+" ns");
    }

//    public static void main(String[] args) throws Exception {
//
//        final String pathInputOrigin = "C:\\Users\\Alex\\AppData\\Local\\PokerStars\\PokerStars.log.0";
//        final File fileInput = new File(pathInputOrigin);
//        Scanner scanner = new Scanner();
//        List<String> tempStorage = scanner.scanner(fileInput);
//        Translator translator = new Translator();
//        translator.translator(tempStorage);
//    }


//    public static void main(String[] args) throws AbstractPokerBotException {
//        long t1=System.nanoTime();
//        final Calculator calculator = new Calculator();
//        final Card[] inputCardsOfPlayer = new Card[]{
//                new Card(6, Suits.SPADES),
//                new Card(5, Suits.SPADES)
//        };
//        final Card[] inputFlopCards = new Card[]{
//                new Card(7, Suits.HERTZ),
//                new Card(2, Suits.CLUBS),
//                new Card(HighCard.King, Suits.DIAMONDS)
//        };
//        final Distribution distribution = new Distribution(inputCardsOfPlayer);
//        distribution.setFlopCards(inputFlopCards);
//        for (int number : calculator.getTableChances(distribution)) {
//            System.out.print(number);
//            System.out.printf(" %.4f%n", ((float) number * 100 / 1081));
//        }
//        long t2 = System.nanoTime();
//        long elapsedTime = t2-t1;
//        System.out.println("Elapsed time was "+elapsedTime+" ns");
//    }

}
