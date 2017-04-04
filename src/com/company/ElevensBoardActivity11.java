import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoardActivity11 extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoardActivity11() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		/* *** TO BE MODIFIED IN ACTIVITY 11 *** */
		if (selectedCards.size() == 2) {
			return findPairSum11(selectedCards).size() > 0;
		} else if (selectedCards.size() == 3) {
			return findJQK(selectedCards).size() > 0;
		} else {
			return false;
		}
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		/* *** TO BE MODIFIED IN ACTIVITY 11 *** */
		List<Integer> cIndexes = cardIndexes();
		return findPairSum11(cIndexes).size() > 0 || findJQK(cIndexes).size() > 0;
	}

	/**
	 * Look for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return a list of the indexes of an 11-pair, if an 11-pair was found;
	 *         an empty list, if an 11-pair was not found.
	 */
	private List<Integer> findPairSum11(List<Integer> selectedCards) {
		/* *** TO BE CHANGED INTO findPairSum11 IN ACTIVITY 11 *** */
		List<Integer> found = new ArrayList();
		for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
			int k1 = selectedCards.get(sk1);
			for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
				int k2 = selectedCards.get(sk2);
				if (cardAt(k1).pointValue() + cardAt(k2).pointValue() == 11) {
					found.add(k1);
					found.add(k2);
				}
			}
		}
		return found;
	}

	/**
	 * Look for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return a list of the indexes of a JQK, if a JQK was found;
	 *         an empty list, if a JQK was not found.
	 */
	private List<Integer> findJQK(List<Integer> selectedCards) {
		/* *** TO BE CHANGED INTO findJQK IN ACTIVITY 11 *** */
		List<Integer> found = new ArrayList();
		boolean foundJack = false;
		boolean foundQueen = false;
		boolean foundKing = false;
		int jack = 0;
		int queen = 0;
		int king = 0;
		for (Integer kObj : selectedCards) {
			int k = kObj;
			if (cardAt(k).rank().equals("jack")) {
				foundJack = true;
				jack = k;
			} else if (cardAt(k).rank().equals("queen")) {
				foundQueen = true;
				queen = k;
			} else if (cardAt(k).rank().equals("king")) {
				foundKing = true;
				king = k;
			}
		}
		if(foundJack && foundQueen && foundKing)
		{
			found.add(jack);
			found.add(queen);
			found.add(king);
		}
		return found;
	}

	/**
	 * Looks for a legal play on the board.  If one is found, it plays it.
	 * @return true if a legal play was found (and made); false otherwise.
	 */
	public boolean playIfPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 11 *** */
		if(playPairSum11IfPossible())
		{
			return true;
		}
		if(playJQKIfPossible())
		{
			return true;
		}
		return false;
	}

	/**
	 * Looks for a pair of non-face cards whose values sum to 11.
	 * If found, replace them with the next two cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if an 11-pair play was found (and made); false otherwise.
	 */
	private boolean playPairSum11IfPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 11 *** */
		List<Integer> cIndexes = cardIndexes();
		for(int i = 0; i < cIndexes.size(); i++)
		{
			int a = cIndexes.get(i);
			for(int x = i+1; x < cIndexes.size(); x++)
			{
				int b = cIndexes.get(x);
				if((cardAt(a).pointValue() + cardAt(b).pointValue()) == 11)
				{
					deal(a);
					deal(b);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Looks for a group of three face cards JQK.
	 * If found, replace them with the next three cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if a JQK play was found (and made); false otherwise.
	 */
	private boolean playJQKIfPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 11 *** */
		List<Integer> cIndexes = cardIndexes();
		boolean foundJack = false;
		boolean foundQueen = false;
		boolean foundKing = false;
		int jack = 0;
		int queen = 0;
		int king = 0;
		for (Integer kObj : cIndexes) {
			int k = kObj;
			if (cardAt(k).rank().equals("jack")) {
				foundJack = true;
				jack = k;
			} else if (cardAt(k).rank().equals("queen")) {
				foundQueen = true;
				queen = k;
			} else if (cardAt(k).rank().equals("king")) {
				foundKing = true;
				king = k;
			}
		}
		if(foundJack && foundQueen && foundKing)
		{
			deal(jack);
			deal(queen);
			deal(king);
			return true;
		}
		return false;
	}
}
