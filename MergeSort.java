import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSort {

  public static CardPile sort(CardPile unsorted) {
    return sort(unsorted, null);
  }

  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();

    while (!unsorted.isEmpty()) {
      CardPile single = new CardPile();
      single.add(unsorted.removeFirst());
      queue.addLast(single);
    }

    if (record != null) {
      for (CardPile pile : queue) {
        record.add(pile);
      }
    }

    if (queue.isEmpty()) {
      return new CardPile();
    }

    while (queue.size() > 1) {
      CardPile left = queue.removeFirst();
      CardPile right = queue.removeFirst();
      CardPile merged = merge(left, right);
      queue.addLast(merged);

      if (record != null) {
        record.next();
        for (CardPile pile : queue) {
          record.add(pile);
        }
      }
    }

    return queue.removeFirst();
  }

  private static CardPile merge(CardPile left, CardPile right) {
    CardPile merged = new CardPile();

    while (!left.isEmpty() && !right.isEmpty()) {
      if (left.peekFirst().compareTo(right.peekFirst()) <= 0) {
        merged.addLast(left.removeFirst());
      } else {
        merged.addLast(right.removeFirst());
      }
    }

    while (!left.isEmpty()) {
      merged.addLast(left.removeFirst());
    }

    while (!right.isEmpty()) {
      merged.addLast(right.removeFirst());
    }

    return merged;
  }

  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);

    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Collections.shuffle(cards);

    CardPile sorted = sort(cards, recorder);
    System.out.println(sorted);

    recorder.display("Card Sort Demo: MergeSort");
  }
}