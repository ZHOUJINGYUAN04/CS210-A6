import java.util.Collections;

public class LinearSearch {

  public static boolean search(CardPile cards, Card target) {
    return search(cards, target, null);
  }

  public static boolean search(CardPile cards, Card target, SortRecorder record) {
    if (record != null) {
      record.add(cards);
    }

    for (Card card : cards) {
      if (record != null) {
        record.next();
        record.add(cards);
        record.add(card);   // currently inspected card
        record.add(target); // target card
      }

      if (card.compareTo(target) == 0) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);

    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Collections.shuffle(cards);

    Card target = cards.peekLast();
    boolean found = search(cards, target, recorder);

    System.out.println("Searching for: " + target);
    System.out.println("Found? " + found);

    recorder.display("Card Search Demo: LinearSearch");
  }
}