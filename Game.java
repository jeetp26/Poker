import java.util.Collections;
import java.util.ArrayList;
/**
 * Plays the game of Poker - 5 Card Draw.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck
 * @version 2/10/2014
 */
public class Game
{
    private static void welcomeUser()
    {
        System.out.println("Hello! Welcome to the Poker-5 Card Draw game. GOOD LUCK!\n");
    }
    private static void thankUser()
    {
        System.out.println("\nThanks for playing! Hope you had fun! GOODBYE.");
    }
    /**
     * Starts the game of Poker - 5 Card Draw.
     */
    public static void main(String[] args)
    {
        User user = new User();
        ArrayList<Opponent> opps = new ArrayList<Opponent>();
        
        Pile deck = new Pile();
        
        welcomeUser();
        initializeOpponents(opps, user.getNumberOfOpponents() );
        initializeDeck(deck);
        dealCards(deck, user, opps);
        discardAndDraw(deck, user, opps);
        showAllHands(user, opps);
        declareWinner(user, opps);
        thankUser();
    }
    
    private static void initializeOpponents(ArrayList<Opponent> opps, int n)
    {
        System.out.println("\nYour " + n + " opponents are: ");
        for(int i = 0; i < n; i++)
        {
            opps.add( new Opponent() );
            System.out.println(opps.get(i).getName() );
        }
        System.out.println();
    }
    
    private static void initializeDeck(Pile deck)
    {
        deck.createADeck();
        System.out.print("Deck is being shuffled......");
        deck.shuffle();
        System.out.println("Done");
    }
    
    private static void dealCards(Pile deck, User user, ArrayList<Opponent> opps)
    {
        System.out.print("Dealing cards to " + (opps.size()+1) + " players......");
        for(int i = 0; i < 5; i++)
        {
            user.drawCard(deck);
            for(int j = 0; j < opps.size(); j++)
                opps.get(j).drawCard(deck);
        }
        System.out.println("Done");
    }
    
    private static void discardAndDraw(Pile deck, User user, ArrayList<Opponent> opps)
    {
        user.discardAndDraw(deck);
        for(int i = 0; i < opps.size(); i++)
        {
            opps.get(i).discardAndDraw(deck);
        }
    }
    
    private static void showAllHands(User user, ArrayList<Opponent> opps)
    {
        for(int i = 0; i < opps.size(); i++)
        {
            System.out.println(opps.get(i).getName() + " has: " + opps.get(i).bestHand());
            opps.get(i).printHand();
            System.out.println();
        }
        
        System.out.println("You have: " + user.bestHand());
        user.printHand();
        System.out.println();
    }
    
    private static void declareWinner(User user, ArrayList<Opponent> opps)
    {
        Collections.sort(opps);
        int check = user.compareTo(opps.get(0));
        if( check < 0 )
        {
            System.out.println("CONGRATULATIONS! You had the best hand. You win the game with a " + user.bestHand() + "!");
        }
        else if( check == 0 )
        {
            System.out.print("There was a TIE between you ");
            for(int i = 0; i < opps.size(); i++)
            {
                if(user.compareTo(opps.get(i)) == 0)
                {
                    System.out.print("and " + opps.get(i).getName() );
                }
                else break;
            }
            System.out.println(".");
        }
        else
        {
            if(opps.size() == 1)
                System.out.println(opps.get(0).getName() + " wins the game with a " + opps.get(0).bestHand() + "!");
            else
            {
                if( opps.get(0).compareTo(opps.get(1)) == 0 )
                {
                    System.out.print("There was a TIE between " + opps.get(0).getName() +" ");
                    for(int i = 1; i < opps.size(); i++)
                    {
                        if(user.compareTo(opps.get(i)) == 0)
                        {
                            System.out.print("and " + opps.get(i).getName() );
                        }
                        else break;
                    }
                    System.out.println(". They each had a " + opps.get(0).bestHand() + "!");
                }
                else
                    System.out.println(opps.get(0).getName() + " wins the game with a " + opps.get(0).bestHand() + "!");
            }
        }
    }
}
