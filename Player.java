import java.util.ArrayList;
import java.util.Collections;
/**
 * A Player has a hand of Cards. A Player plays the game amd performs operations as the game moves along.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck
 * @version 2/10/2014
 */
public abstract class Player implements Comparable<Player>
{
    /**
     * Constructs a new Player, with an empty hand.
     */
    public Player()
    {
        hand = new ArrayList<Card>();
    }
    
    /**
     * Returns the amount of Cards in the Player's hand.
     * 
     * @return amount of cards the player has
     */
    public int size()
    {
        return hand.size();
    }
    
    /**
     * Adds a Card to the Player's Hand.
     */
    public void addCard(Card c)
    {
        hand.add(c);
        Collections.sort(hand);
    }
    
    /**
     * Gets the Card at a specified index.
     * 
     * @param index the index of the card
     * @return the card at the specified index
     */
    public Card getCard(int index)
    {
        return hand.get(index);
    }
    
    /**
     * Removes a Card at a specified index from the Player's hand.
     * 
     * @param index the specified index
     */
    public void removeCard(int index)
    {
        hand.remove(index);
    }
    
    /**
     * Removes a specified ArrayList of Cards from this Player's hand.
     * 
     * @param cards the specified ArrayList containing all the Cards to be removed
     */
    public void removeAll(ArrayList<Card> cards)
    {
        hand.removeAll(cards);
    }
    
    /**
     * Prints all the Cards in the Player's hand.
     */
    public void printHand()
    {
        for( int i = 0; i < hand.size(); i++)
            System.out.println( (i+1) + ". " + hand.get(i).getFullName() );
    }
    
    /**
     * Prints a short form of all the Cards in the Player's hand. For example, it would print an Ace of Spades as "AS", or a Six of Hearts as "6H".
     */
    public void printHandShort()
    {
        for( int i = 0; i < hand.size(); i++)
            System.out.print((i+1) + ") " + hand.get(i).getShortName() + " " );
        
        System.out.println();
    }
    
    /**
     * Abstract method with no definite definition in the Player class. 
     * Classes that extend this Player class are required to provide a definition for a discard method. General rule for the discard method is that it must not remove more than 3 Cards, unless it has an Ace.
     */
    public abstract void discard();
    
    /**
     * Draws a Card from a specified Pile of Cards and puts them in the Player's hand.
     * 
     * @param deck the specified Pile of Cards to draw the Card from
     */
    public void drawCard(Pile deck)
    {
        addCard( deck.getCard(0) );
        deck.removeCard(0);
    }
    
    /**
     * Follows an AI algorithim, and discards cards accordingly, then draws Cards from a specified Pile of Cards to get a full hand again.
     * 
     * @deck the specified Pile of Cards to draw from after discarding
     */
    public void discardAndDraw(Pile deck)
    {
        discard();
        while(size() < 5)
        {
            drawCard( deck );
        }
    }
    
    private int bestHandPoints()
    {
        if( hasRoyalFlush() ) return 10;
        if( hasStraightFlush() ) return 9;
        if( hasFourOfAKind() ) return 8;
        if( hasFullHouse() ) return 7;
        if( hasFlush() ) return 6;
        if( hasStraight() ) return 5;
        if( hasThreeOfAKind() ) return 4;
        if( hasTwoPair() ) return 3;
        if( hasOnePair() ) return 2;
        else return 1;
    }
    
    /**
     * Returns the name of the best hand this Player has.
     * 
     * @return a string representation of the name of the best hand this Player has
     */
    public String bestHand()
    {
        if( hasRoyalFlush() ) return "Royal Flush";
        if( hasStraightFlush() ) return "Straight Flush";
        if( hasFourOfAKind() ) return "Four Of A Kind";
        if( hasFullHouse() ) return "Full House";
        if( hasFlush() ) return "Flush";
        if( hasStraight() ) return "Straight";
        if( hasThreeOfAKind() ) return "Three Of A Kind";
        if( hasTwoPair() ) return "Two Pair";
        if( hasOnePair() ) return "One Pair";
        else return "High Card";
    }
    
    private int tieBreaker(Player other)
    {
        if( this.hasRoyalFlush() ) return 0;
        if( this.hasTwoPair() || this.hasFullHouse() )
        {
            Card x[];
            Card y[];
            if( this.hasTwoPair() ) 
            {
                x = this.checkTwoPair();
                y = other.checkTwoPair();
            }
            else 
            {
                x = this.checkFullHouse();
                y = other.checkFullHouse();
            }
            
            for(int i = 0; i < 2; i++)
            {
                if( x[i].compareTo(y[i]) != 0 )
                {
                    return x[i].compareTo(y[i]);
                }
            }
            return 0;
        }
        else if(this.hasFlush() || this.hasStraight() || this.bestHand().equals("High Card") )
        {
            for(int i = 0; i < 5; i++)
            {
                Card x = this.hand.get(i);
                Card y = other.hand.get(i);
                if( x.compareTo(y) != 0 )
                {
                    return x.compareTo(y);
                }
            }
            return 0;
        }
        if( this.hasThreeOfAKind() || this.hasFourOfAKind() )
        {
            if( this.hasThreeOfAKind() )
                return this.checkThreeOfAKind().compareTo( other.checkThreeOfAKind() );
            else
                return this.checkFourOfAKind().compareTo( other.checkFourOfAKind() );
        }
        else //Assumes it's One Pair, since it's the only one remaining
        {
            if( this.checkOnePair() == other.checkOnePair() )
            {
                for(int i = 0; i < 5; i++)
                {
                    Card x = this.hand.get(i);
                    Card y = other.hand.get(i);
                    if( x.compareTo(y) != 0 ) return x.compareTo(y);
                }
                return 0;
            }
            else return this.checkOnePair().compareTo( other.checkOnePair() );
        }
    }
    
    /**
     * Compares this Player to another player, and returns an integer that represents whether this Player has a better, worse, or an equal hand compared to the other Player.
     * 
     * @param other the other Player to compare to
     * @return a negative number if this Player has a better, and a positive number if not. Returns 0 if both hands are of equal value
     */
    public int compareTo(Player other)
    {
        if( (this.bestHandPoints() - other.bestHandPoints()) == 0) return this.tieBreaker(other);
        return other.bestHandPoints() - this.bestHandPoints();
    }
    
    private Card checkOnePair()
    {
        for(int i = 0; i < 4; i++)
        {
            if( hand.get(i).getRank() == hand.get(i+1).getRank() )
                return hand.get(i);
        }
        return null;
    }
    /**
     * Checks whether this Player has a One Pair in his Hand.
     * 
     * @return true if this Player has One Pair, false if not.
     */
    public boolean hasOnePair()
    {
        if( this.checkOnePair() == null ) return false;
        return true;
    }
    
    private Card[] checkTwoPair()
    {
        Card pairsOf[] = { null , null };
        for(int i = 0; i < 4; i++)
        {
            if( hand.get(i).getRank() == hand.get(i+1).getRank() )
            {
                if( pairsOf[0] == null ) pairsOf[0] = hand.get(i);
                else pairsOf[1] = hand.get(i);
                i++;
            }
        }
        return pairsOf[1]!=null?pairsOf:null;
    }
    /**
     * Checks whether this Player has a Two Pair in his Hand.
     * 
     * @return true if this Player has Two Pair, false if not.
     */
    public boolean hasTwoPair()
    {
        if( this.checkTwoPair() == null ) return false;
        return true;
    }
    
    private Card checkThreeOfAKind()
    {
        for(int i = 0; i < 3; i++)
        {
            Card check = hand.get(i);
            if( check.getRank() == hand.get(i+1).getRank() && check.getRank() == hand.get(i+2).getRank())
                return check;
        }
        return null;
    }
    /**
     * Checks whether this Player has a Three of a Kind in his Hand.
     * 
     * @return true if this Player has Three of a Kind, false if not.
     */
    public boolean hasThreeOfAKind()
    {
        if( this.checkThreeOfAKind() == null ) return false;
        return true;
    }
    
    private Card checkStraight()
    {
        int c = 0;
        if(hand.get(0).getRank() == 1 && hand.get(1).getRank() == 13)
            c = 1;
        for(int i = c; i < 4; i++)
        {
            if( hand.get(i).getRank() != (hand.get(i+1).getRank()+1) )
                return null;
        }
        return hand.get(0);
    }
    /**
     * Checks whether this Player has a Straight in his Hand.
     * 
     * @return true if this Player has Straight, false if not.
     */
    public boolean hasStraight()
    {
        if( this.checkStraight() == null ) return false;
        return true;
    }
    
    private Card checkFlush()
    {
        String s = hand.get(0).getSuit();
        for(int i = 1; i < 5; i++)
        {
            if( !s.equals(hand.get(i).getSuit()) )
                return null;
        }
        return hand.get(0);
    }
    /**
     * Checks whether this Player has a Flush in his Hand.
     * 
     * @return true if this Player has Flush, false if not.
     */
    public boolean hasFlush()
    {
        if( this.checkFlush() == null ) return false;
        return true;
    }
    
    private Card[] checkFullHouse()
    {
        if(!this.hasThreeOfAKind()) return null;
        
        Card check = hand.get(2);
        Card left = hand.get(1);
        Card right = hand.get(3);
        
        if( check.getRank() == left.getRank() && check.getRank() == right.getRank() )
            return null;
        if( check.getRank() == right.getRank() )
        {
            if( left.getRank() == hand.get(0).getRank() )
            {
                return new Card[] {check, left };
            }
        }
        if( check.getRank() == left.getRank() )
        {
            if( right.getRank() == hand.get(4).getRank() )
            {
                return new Card[] {check, right };
            }
        }
        return null;
    }
    /**
     * Checks whether this Player has a Full House in his Hand.
     * 
     * @return true if this Player has Full House, false if not.
     */
    public boolean hasFullHouse()
    {
        if( this.checkFullHouse() == null ) return false;
        return true;
    }
    
    private Card checkFourOfAKind()
    {
        for(int i = 0; i < 2; i++)
        {
            Card check = hand.get(i);
            if( check.getRank() == hand.get(i+1).getRank() && check.getRank() == hand.get(i+2).getRank() && check.getRank() == hand.get(i+3).getRank())
                return check;
        }
        return null;
    }
    /**
     * Checks whether this Player has a Four of a Kind in his Hand.
     * 
     * @return true if this Player has Four of a Kind, false if not.
     */
    public boolean hasFourOfAKind()
    {
        if(this.checkFourOfAKind() == null) return false;
        return true;
    }
    
    /**
     * Checks whether this Player has a Straight Flush in his Hand.
     * 
     * @return true if this Player has Straight Flush, false if not.
     */
    public boolean hasStraightFlush()
    {
        if(this.hasStraight() && this.hasFlush() ) return true;
        return false;
    }
    
    /**
     * Checks whether this Player has a Royal Flush in his Hand.
     * 
     * @return true if this Player has Royal Flush, false if not.
     */
    public boolean hasRoyalFlush()
    {
        if(this.hasStraightFlush() && hand.get(0).getRank() == 1 ) return true;
        return false;
    }
    
    private ArrayList<Card> hand;
}
