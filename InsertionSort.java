import java.util.ListIterator;

public class InsertionSort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    if (record != null) {
      record.add(unsorted);
    }

    CardPile sorted = new CardPile();
  
    while (!unsorted.isEmpty()) {
      Card nextCard = unsorted.removeFirst();

      ListIterator<Card> it = sorted.listIterator();
      boolean inserted = false;

      while (it.hasNext()) {
        Card current = it.next();

        if (nextCard.compareTo(current) < 0) {
          it.previous();
          it.add(nextCard);
          inserted = true;
          break;
        }
      }

      if (!inserted) {
        sorted.addLast(nextCard);
      }

      if (record != null) {
        record.next();
        record.add(sorted);
        record.add(unsorted);
      }
    }

    return sorted;
  }
}
