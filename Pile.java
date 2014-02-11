import java.util.ArrayList;
import java.util.Collections;
/**
 * A Pile is a collection of Cards.
 * 
 * @author Jeet Patel, Bridget Basan, and Mark Hallenbeck
 * @version 2/10/2014
 */
public class Pile
{
    /**
     * Constructs an empty Pile.
     */
    public Pile()
    {
        cards = new ArrayList<Card>();
    }
    
    /**
     * Clears the Pile, then stores a deck of cards in the Pile.
     */
    public void createADeck()
    {
        cards.clear();
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 13; j++)
            {
                switch(i)
                {
                    case 0: cards.add( new Card(j+1,"spades") ); break;
                    case 1: cards.add( new Card(j+1,"clubs") ); break;
                    case 2: cards.add( new Card(j+1,"hearts") ); break;
                    case 3: cards.add( new Card(j+1,"diamonds") ); break;
                    default: break;
                }
            }
        }
    }
    
    /**
     * Adds a Card to the Pile.
     * 
     * @param c the card to be added
     */
    public void addCard(Card c)
    {
        cards.add(c);
    }
    
    /**
     * Gets a Card at a specified index from the Pile.
     * 
     * @param index the specified index
     * @return the Card at the specified index
     */
    public Card getCard(int index)
    {
        return cards.get(index);
    }
    
    /**
     * Removes a Card at a specified index from the Pile.
     * 
     * @param index the specified index
     */
    public void removeCard( int index )
    {
        cards.remove(index);
    }
    
    /**
     * Shuffles the Pile.
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    
    /**
     * Sorts the Pile.
     */
    public void sort()
    {
        Collections.sort(cards);
    }
    
    /**
     * Prints all the Cards in the Pile.
     */
    public void printPile()
    {
        if( cards.size() == 0 )
            System.out.println("List is Empty");
        else
            System.out.println("There are " + cards.size() + " cards in this pile.");
        
        for(int i = 0; i < cards.size(); i++)
        {
            System.out.println( (i+1) + ". " + cards.get(i).getFullName() );
        }
    }
    
    private ArrayList<Card> cards;
}
