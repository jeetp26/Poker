import java.util.Random;
/**
 * A Card represents a playing card, which has a suit and a rank. For example, an Ace of Spades.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck
 * @version 2/10/2014
 */
public class Card implements Comparable<Card>
{
    /**
     * Constructs a random Card.
     */
    public Card()
    {
        Random rand = new Random();
        
        rank = rand.nextInt(13) + 1;
        
        switch( rand.nextInt(4) )
        {
            case 0: suit = "Clubs"; break;
            case 1: suit = "Spades"; break;
            case 2: suit = "Hearts"; break;
            case 3: suit = "Diamonds"; break;
            default: suit = "Spades"; break;
        }
    }
    
    /**
     * Constructs a Card with a specified rank and a specified suit.
     * 
     * @param rank the specified rank
     * @param suit the specified suit
     */
    public Card(int rank, String suit)
    {
        this.rank = rank;
        
        switch(suit.toLowerCase())
        {
            case "clubs": this.suit = "Clubs"; break;
            case "spades": this.suit = "Spades"; break;
            case "hearts": this.suit = "Hearts"; break;
            case "diamonds": this.suit = "Diamonds"; break;
            default: break;
        }
    }
    
    /**
     * Compares this Card with another Card based on their ranks. Suit doesn't matter.
     * 
     * @param other the Card to be compared to
     * @return 0 if they have the same rank; 
     *         -1 if this Card has a higher rank than the other Card; 1 
     *         if this Card has a lower rank thank the other Card
     */
    public int compareTo(Card other)
    {
        if(rank == 1) return -1;
        if(other.rank == 1) return 1;
        return other.rank - rank;
    }
    
    /**
     * Returns a two character representation of the Card. For example, for the Ace Of Spades, it would return "AS", for Six of Hearts it would return "6H".
     * 
     * @return a two character representation of the Card. The First character represents the rank, as a number; unless, it's an Ace, King, Queen, Jack, or Ten; 
     *         then the first character is represented by 'A', 'K', 'Q', 'J', or 'T', respectively. The second character represents the first letter of the suit.
     */
    public String getShortName()
    {
        switch( rank )
        {
            case 1: return ( "A" + suit.charAt(0) );
            case 10: return ( "T" + suit.charAt(0) );
            case 11: return ( "J" + suit.charAt(0) );
            case 12: return ( "Q" + suit.charAt(0) );
            case 13: return ( "K" + suit.charAt(0) );
            default: return ( "" + rank + suit.charAt(0) );
        }
    }
    
    /**
     * Returns the full name of the Card as a String. For example, for the Ace of Spades, it returns "Ace of Spades", or for the Six of Hearts, it returns "Six of Hearts".
     * 
     * @return the string representation of the full name of the Card, in the format "<Rank> of <Suit>", where <Rank> and <Suit> are the rank and suit of the Card, respectively.
     */
    public String getFullName()
    {
        switch( rank )
        {
            case 1: return ( "Ace of " + suit );
            case 2: return ( "Two of " + suit );
            case 3: return ( "Three of " + suit );
            case 4: return ( "Four of " + suit );
            case 5: return ( "Five of " + suit );
            case 6: return ( "Six of " + suit );
            case 7: return ( "Seven of " + suit );
            case 8: return ( "Eight of " + suit );
            case 9: return ( "Nine of " + suit );
            case 10: return ( "Ten of " + suit );
            case 11: return ( "Jack of " + suit );
            case 12: return ( "Queen of " + suit );
            case 13: return ( "King of " + suit );
            default: return ( "N/O" );
        }
    }
    
    /**
     * Returns the rank of the Card, as an integer value.
     * 
     * @return integer representation of the rank of the Card, where 11, 12, and 13 represent the Jack, Queen, and the King, respectively.
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Returns the suit of the Card.
     * 
     * @return the suit of the Card
     */
    public String getSuit()
    {
        return suit;
    }

    private int rank;
    private String suit;
}
