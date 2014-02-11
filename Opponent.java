import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * The Opponent class extens the Player class, since an Opponent is also a Player. The Opponent uses AI(Artificial Intelligence) to perform its operations.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck
 * @version 2/10/2014
 */
public class Opponent extends Player
{
    /**
     * Constructs a new Opponent with a randmon name, like Mark, or Jeet, or Bridget.
     */
    public Opponent()
    {
        super();
        int x = (new Random()).nextInt( names.size() );
        name = names.get( x );
        names.remove(x);
    }
    
    /**
     * Returns the Opponent's name.
     * 
     * @return the String representation of the Opponent's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Follows an AI algorithim and removes Cards from its hand accordingly.
     */
    public void discard()
    {
        if( hasFourOfAKind() || 
            hasFullHouse() || 
            hasFlush() ||
            hasStraight() ) return;
        
        if( hasThreeOfAKind() )
        {
            discardForThreeOfAKind();
            return;
        }
        if( hasTwoPair() )
        {
            discardForTwoPair();
            return;
        }
        if( hasOnePair() )
        {
            discardForOnePair();
            return;
        }
        discardForHighCard();
    }
    
    private void discardForThreeOfAKind()
    {
        Card check = getCard(2);
        Card left = getCard(1);
        Card right = getCard(3);
        
        if( check.getRank() == left.getRank() && check.getRank() == right.getRank() )
        {
            removeCard(4);
            removeCard(0);
        }
        else if( check.getRank() == left.getRank() )
        {
            removeCard(4);
            removeCard(3);
        }
        else
        {
            removeCard(1);
            removeCard(0);
        }
    }
    
    private void discardForTwoPair()
    {
        if( getCard(0).getRank() != getCard(1).getRank() )
            removeCard(0);
        else if( getCard(4).getRank() != getCard(3).getRank() )
            removeCard(4);
        else
            removeCard(2);
    }
    
    private void discardForOnePair()
    {
        for(int i = 0; i < 4; i++)
        {
            if( getCard(i).getRank() == getCard(i+1).getRank() )
            {
                for(int j = i+2; j < 5; j++)
                {
                    removeCard(size()-1);
                }
                for(int j = i-1; j > -1; j--)
                {
                    removeCard(0);
                }
                return;
            }
        }
    }
    
    private void discardForHighCard()
    {
        int s = 0, c = 0, h = 0, d = 0;
        
        for(int i = 0; i < 5; i++)
        {
            switch( getCard(i).getSuit() )
            {
                case "Clubs": c++; break;
                case "Spades": s++; break;
                case "Hearts": h++; break;
                case "Diamonds": d++; break;
                default: break;
            }
        }
        
        if( s == 4 )
        {
            removeAllBut("Spades");
            return;
        }
        if( c == 4 ) 
        {
            removeAllBut("Clubs");
            return;
        }
        if( h == 4 ) 
        {
            removeAllBut("Hearts");
            return;
        }
        if( d == 4 ) 
        {
            removeAllBut("Diamonds");
            return;
        }
        
        for(int i = 0; i < 2; i++)
        {
            if( getCard(i).getRank() == getCard(i+1).getRank()+1 &&
                getCard(i).getRank() == getCard(i+2).getRank()+2 &&
                getCard(i).getRank() == getCard(i+3).getRank()+3 )
            {
                if(i == 0)
                    removeCard(4);
                else
                    removeCard(0);
                return;
            }
        }
        
        if(getCard(0).getRank() == 1)
        {
            if( getCard(1).getRank() == 13 && 
                getCard(2).getRank() == 12 && 
                getCard(3).getRank() == 11 )
            {
                removeCard(4);
                return;
            }
            else
            {
                for(int i = 0; i < 4; i++)
                {
                    removeCard(1);
                }
            }
        }
        else
        {
            for(int i = 0; i < 3; i++)
            {
                removeCard(2);
            }
        }
    }
    
    private void removeAllBut(String suit)
    {
        for(int i = 0; i < size(); i++)
        {
            if(!getCard(i).getSuit().equalsIgnoreCase(suit))
            {
                removeCard(i);
                return;
            }
        }
    }
    
    private static ArrayList<String> names = new ArrayList<String>( Arrays.asList( new String[]{"Bob", "Bridget", "Mark", "Anna", "Lucas", "Mike", "Jeff", "Jeet", "Patrick", "Emily"}) );
    private String name;
}
