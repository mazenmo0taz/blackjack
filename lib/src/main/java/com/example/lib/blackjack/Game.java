package com.example.lib.blackjack;
import java.util.Random;
import java.util.Scanner;
public class Game {
    Player[] playersArr = new Player[4];
    Card[] cardDeckArr = new Card[52];
    Random rand = new Random();
    private static int numbersOfCards = 52;
    private int highScore = 0;
    public void cardDeckGenerator(){ // generating 52 cards with all suits and values into the cardDeck Array
        for (int i = 0; i< cardDeckArr.length; i++){
            if(i%13 < 10)
                cardDeckArr[i] = new Card(i/13,i%13,(i%13)+1);
            else {
                cardDeckArr[i] = new Card(i/13,i%13,10);
            }
        }

    }
    public void shuffleCardsDeck(){ // shuffles the cards deck to draw a random card every time

        for(int first = 0;first<cardDeckArr.length;first++){
            int second = rand.nextInt(numbersOfCards);
            Card tmpCard = cardDeckArr[first];
            cardDeckArr[first] = cardDeckArr[second];
            cardDeckArr[second] = tmpCard;
        }
        for(int first = 0;first<cardDeckArr.length;first++){
            int second = rand.nextInt(numbersOfCards);
            Card tmpCard = cardDeckArr[first];
            cardDeckArr[first] = cardDeckArr[second];
            cardDeckArr[second] = tmpCard;
        }
    }
    public Card drawCard(){ // draw a random card after shuffling and delete it from the card deck
        int x = 0;
        Card cardObject = cardDeckArr[0];
        for(int i = 1;i<numbersOfCards;i++){
            cardDeckArr[i-1] = cardDeckArr[i];
        }
        this.cardDeckArr[numbersOfCards-1] = null;
        numbersOfCards--;
        return cardObject;
    }

    public void setPlayerInfo(){ // set the 3 players name and gives him 2 cards and calculate his score
        for (int i = 0 ;i < 3;i++){

            System.out.println("enter player number " + (i+1) + " name:");
            Scanner scan = new Scanner(System.in);
            String playerName  = scan.nextLine();
            playersArr[i] = new Player(playerName);
            playersArr[i].index++;
            playersArr[i].cardsArr[0] = drawCard();

            playersArr[i].cardsArr[1] = drawCard();

            int playerScore = playersArr[i].cardsArr[0].getValue() + playersArr[i].cardsArr[1].getValue();
            playersArr[i].setScore(playerScore);
        }
    }

    public void updateMaxScore(){ // updates the high score to the score of the highest player if there is at least 1 winner
        for(int i=0;i<3;i++){
            if(playersArr[i].getScore() > highScore && playersArr[i].getScore() <= 21)
                highScore = playersArr[i].getScore();
        }
    }

    public int getHighScore() {
        return highScore;
    }
}
