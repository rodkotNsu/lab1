/**
 * Class {@code WordCounter} implements the storage of the word and its quantity in the text
 * {@see #click} is method increases the number of words in the text
 */
public class WordCounter {
    private final String word;
    private Integer count;

    public WordCounter(String word) {
        this.word = word.toLowerCase();
        this.count = 1;
    }

    public boolean isWord(String word) {
        return this.word.equals(word);
    }

    public void click() {
        this.count++;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return ((WordCounter) other).word.equals(this.word)
        && ((WordCounter) other).count.equals(this.count);
    }
}
