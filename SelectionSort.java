import java.util.Collections;
import java.util.ListIterator;

public class SelectionSort {

  public static CardPile sort(CardPile unsorted) {
    return sort(unsorted, null);
  }

  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    if (record != null) {
      record.add(unsorted);
    }

    CardPile sorted = new CardPile();

    while (!unsorted.isEmpty()) {
      Card smallest = removeSmallest(unsorted);
      sorted.addLast(smallest);

      if (record != null) {
        record.next();
        record.add(sorted);
        record.add(unsorted);
      }
    }

    return sorted;
  }

  private static Card removeSmallest(CardPile pile) {
    ListIterator<Card> it = pile.listIterator();

    Card smallest = it.next();
    int smallestIndex = 0;
    int currentIndex = 0;

    while (it.hasNext()) {
      currentIndex++;
      Card current = it.next();
      if (current.compareTo(smallest) < 0) {
        smallest = current;
        smallestIndex = currentIndex;
      }
    }

    ListIterator<Card> removeIt = pile.listIterator();
    for (int i = 0; i <= smallestIndex; i++) {
      removeIt.next();
    }
    removeIt.remove();

    return smallest;
  }

  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);

    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Collections.shuffle(cards);

    CardPile sorted = sort(cards, recorder);
    System.out.println(sorted);

    recorder.display("Card Sort Demo: SelectionSort");
  }
}