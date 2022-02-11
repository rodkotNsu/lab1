import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
/**
*Class {@code SetWordCounter} implements the storage of a set of words with its own counters(objects of {@code WordCounter})
 {@param wordsCount} is count all words in set
 * */
public class SetWordCounter extends HashSet<WordCounter> {

    private int wordsCount;

    public SetWordCounter() {
        super();
        this.wordsCount = 0;
    }

    @Override
    public boolean add(WordCounter wordCounter) {
        if (super.add(wordCounter)) {
            wordsCount += wordCounter.getCount();
            return true;
        } else {
            for (WordCounter s : this) {
                if (s.isWord(wordCounter.getWord())) {
                    s.setCount(wordCounter.getCount() + s.getCount());
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            wordsCount -= ((SetWordCounter) o).wordsCount;
            return true;
        } else
            return false;
    }

    @Override
    public void clear() {
        wordsCount = 0;
        super.clear();
    }

    public void insert(String word) {
        for (WordCounter s : this) {
            if (s.isWord(word)) {
                s.click();
                wordsCount++;
                return;
            }
        }
        this.add(new WordCounter(word));
    }

    public int getCountWord() {
        return wordsCount;
    }

    public List<WordCounter> getList() {
        return new ArrayList<>(this);
    }

    public List<WordCounter> getSortedList() {
        List<WordCounter> list = getList();
        list.sort(new SortAlphabet());
        return list;
    }

    public String wordCounterInCsv(WordCounter wordCounter) {
        return new String("\"" + wordCounter.getWord() + "\"," + wordCounter.getCount() + "," + (double) (wordCounter.getCount()) / wordsCount);
    }

     static class SortAlphabet implements Comparator<WordCounter> {
        @Override
        public int compare(WordCounter o1, WordCounter o2) {
            if (o1.getCount().equals(o2.getCount()))
                return o1.getWord().compareTo(o2.getWord());
            else
                return o2.getCount().compareTo(o1.getCount());
        }
    }
}
