import java.util.Scanner;
import java.util.ArrayList;
/**
 * The User class extends the Player class, since a User is also a Player. A User requires input from the keyboard to perform operations.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck 
 * @version 2/10/2014
 */
public class User extends Player
{
    /**
     * Constructs a User Player that will use input from the keyboard.
     */
    public User()
    {
        super();
        kin = new Scanner( System.in );
    }
    
    /**
     * Prompts the User for how many opponents to play with.
     * 
     * @return the number of opponents
     */
    public int getNumberOfOpponents()
    {
        while( true )
        {
            System.out.print("Enter number of Opponents(Atleast 1 and no more than 5): ");
            if(kin.hasNextInt())
            {
                int x = kin.nextInt();
                if( x <= 5 && x >= 1 )
                {
                    kin.nextLine();
                    return x;
                }
                else
                    System.out.println("Error! TRY AGAIN.");
            }
            else
            {
                kin.next();
                System.out.println("Error! Please input a number.");
            }
        }
    }
    
    /**
     * Prompts the User which Cards it wants to discard, then discards those Cards from the User's hand.
     */
    public void discard()
    {
        System.out.println("\nYou have: ");
        printHand();
        
        boolean hasAce = (getCard(0).getRank()==1?true:false);
        
        if(hasAce)
            System.out.println("\nYou have an Ace! Which means you can remove remove up to 4 cards, except the Ace!");
        else
            System.out.println("\nYou're allowed to remove up to 3 cards!");
        
        boolean satisfied = false;
        ArrayList<Card> toRemove = new ArrayList<>();
        do
        {
            satisfied = true;
            System.out.print("Which cards would you like to remove?(Press ENTER if none.): ");
            toRemove = new ArrayList<>();
            Scanner sin = new Scanner( kin.nextLine() );
            while( sin.hasNext() )
            {
                if(sin.hasNextInt())
                {
                    int index = sin.nextInt() - 1;
                    if( (index >= (hasAce?1:0) && index <= 4) && (toRemove.size() < (hasAce?4:3) ))
                    {
                        toRemove.add( getCard(index) );
                        //System.out.println("SUCCESS");
                        satisfied = true;
                    }
                    else
                    {
                        System.out.println("\nYou did not enter a valid input. Please try again.");
                        satisfied = false;
                        break;
                    }
                }
                else
                {
                    System.out.println("\nYou did not enter a valid input. Please try again.");
                    sin.next();
                    satisfied = false;
                    break;
                }
            }
            sin.close();
        }while(!satisfied);
        
        removeAll(toRemove);
        
        System.out.println();
    }
    
    private Scanner kin;
}
