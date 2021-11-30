package com.example.lib.blackjack;

import java.util.Scanner;
public class BlackJack {

    static Game game = new Game();
    public static void main(String[] args){
        GUI gui = new GUI();
        game.cardDeckGenerator();
        game.shuffleCardsDeck();
        game.shuffleCardsDeck();
        game.setPlayerInfo();
        AskPlayer();
        gui.runGUI( game.cardDeckArr, game.playersArr[0].cardsArr, game.playersArr[1].cardsArr, game.playersArr[2].cardsArr, game.playersArr[3].cardsArr);

        whoWon();
    }



    static void whoWon(){
        int winners = 0;
        for (int i = 0 ; i<3;i++){

            if(game.playersArr[i].getScore() < game.getHighScore() || game.playersArr[i].getScore() > 21){
                game.playersArr[i].setLost(true);
            }else if(game.playersArr[i].getScore() == game.getHighScore()) {
                winners++;
            }

            if(game.playersArr[i].isLost()){
                game.playersArr[i].setLost(true);
            }else if(winners > 1){
                System.out.println("push game");
            }else if(game.playersArr[3].getScore() > game.getHighScore() && game.playersArr[3].getScore() < 21){
                System.out.println("dealer won and his score is: " + game.playersArr[3].getScore());
            } else {
                System.out.println("the winner is: " + game.playersArr[i].getName());
            }


        }
    }

    public static void AskPlayer(){// asks the player whether to hit or stand and print his result after taking decision
        GUI gui = new GUI();
        int x = 2;
        String decision = null;
        for(int i = 0; i < 3; i++) {
            do {
                System.out.println(game.playersArr[i].getName() + "'s turn to play now! (your score now is: " + game.playersArr[i].getScore() + ")");
                System.out.println("decide whether to hit or stand(Press H to hit and S to Stand):");
                Scanner scan = new Scanner(System.in);
                decision = scan.next();
                if(decision.equalsIgnoreCase("H")) {
                    game.playersArr[i].cardsArr[x] = game.drawCard();
                    gui.updatePlayerHand(game.playersArr[i].cardsArr[x],game.playersArr[i].index);
                    if(game.playersArr[i].cardsArr[x] == null) // testing only
                        System.out.println("fu!!!!");
                    System.out.println(game.playersArr[i].cardsArr[x].getValue() + " -- ADDED CARD VALUE");
                    int currentScore = game.playersArr[i].getScore() + game.playersArr[i].cardsArr[x].getValue();
                    game.playersArr[i].setScore(currentScore);
                    if(game.playersArr[i].getScore() > 21){
                        System.out.println("Busted");
                        game.playersArr[i].setLost(true);
                        break;
                    }else if(game.playersArr[i].getScore() == 21){
                        System.out.println("BlackJack");
                        break;
                    }else {
                        continue;
                    }
                }
            }while(!decision.equalsIgnoreCase("S"));
            x++;


        }
        game.updateMaxScore();
        dealerStart();
    }

    static void dealerStart(){
        GUI gui = new GUI();
        game.playersArr[3] = new Player("Dealer");
        game.playersArr[3].cardsArr[0] = game.drawCard();
        game.playersArr[3].cardsArr[1] = game.drawCard();
        int dealerScore = game.playersArr[3].cardsArr[0].getValue() + game.playersArr[3].cardsArr[1].getValue();
        game.playersArr[3].setScore(dealerScore);
        System.out.println("dealer score is: " + game.playersArr[3].getScore() + " -- current highscore: :" + game.getHighScore());
        if(dealerScore < game.getHighScore()){
            for(int i = 2;i<11;i++){
                game.playersArr[3].cardsArr[i] = game.drawCard();
                gui.updateDealerHand(game.playersArr[3].cardsArr[i],game.cardDeckArr);
                int dealerNewScore = game.playersArr[3].cardsArr[i].getValue();
                dealerScore += dealerNewScore;
                game.playersArr[3].setScore(dealerScore);
                System.out.println(game.playersArr[3].getScore() + " is the new dealer score -- current high score: " + game.getHighScore());
                if(game.playersArr[3].getScore() > 21) {
                    System.out.println("dealer busted");
                    game.playersArr[3].setLost(true);

                }else {
                    continue;
                }
                break;
            }
        }
    }

}