import java.util.Collections;

public class Quicksort {

  public static CardPile sort(CardPile unsorted) {
    return sort(unsorted, null);
  }

  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    if (unsorted.size() <= 1) {
      return new CardPile(unsorted);
    }

    if (record != null) {
      record.add(unsorted);
      record.next();
    }

    Card pivot = unsorted.removeFirst();
    CardPile smaller = new CardPile();
    CardPile bigger = new CardPile();

    while (!unsorted.isEmpty()) {
      Card current = unsorted.removeFirst();
      if (current.compareTo(pivot) < 0) {
        smaller.addLast(current);
      } else {
        bigger.addLast(current);
      }
    }

    if (record != null) {
      record.add(smaller);
      record.add(pivot);
      record.add(bigger);
      record.next();
    }

    CardPile sortedSmaller = sort(smaller, record);
    CardPile sortedBigger = sort(bigger, record);

    CardPile result = new CardPile();
    result.addAll(sortedSmaller);
    result.addLast(pivot);
    result.addAll(sortedBigger);

    if (record != null) {
      record.add(result);
      record.next();
    }

    return result;
  }

  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);

    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Collections.shuffle(cards);

    CardPile sorted = sort(cards, recorder);
    System.out.println(sorted);

    recorder.display("Card Sort Demo: Quicksort");
  }
}