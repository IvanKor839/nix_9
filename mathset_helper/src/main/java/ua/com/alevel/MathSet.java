package ua.com.alevel;

import java.math.BigDecimal;
import java.util.Arrays;

public class MathSet implements IMathSet<Number> {

    private int size = 0;
    private int capacity;
    private Number[] numbers;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    MathSet(int c) {
        capacity = c;
        numbers = new Number[capacity];
        size = 0;
    }

    MathSet() {
        this(10);
    }

    MathSet(Number[] numbersArray) {
        this.capacity = Math.max(numbersArray.length, 10);
        numbers = new Number[capacity];
        add(numbersArray);
    }


    MathSet(Number[]... numbersArray) {
        this(numbersArray[0]);
        for (int i = 1; i < numbersArray.length; i++) {
            this.setCapacity(this.getCapacity() + numbersArray[i].length);
            for (Number number : numbersArray[i]) {
                add(number);
            }
        }
        this.setCapacity(this.getSize());
    }


    MathSet(MathSet n) {
        this.capacity = n.capacity;
        this.size = n.size;
        numbers = new Number[capacity];
        System.arraycopy(n.numbers, 0, this.numbers, 0, size);

    }


    MathSet(MathSet... mathSets) {
        this(mathSets[0]);
        for (int i = 1; i < mathSets.length; i++) {
            this.setCapacity(this.getCapacity() + mathSets[i].getCapacity());
            join(mathSets[i]);
        }
        this.setCapacity(this.getSize());
    }

    public boolean unique(Number[] numbers, Number number) {
        BigDecimal numberInput = BigDecimal.valueOf(number.doubleValue());
        for (int i = 0; i < size; i++) {
            if (numbers[i] == null) return false;
            BigDecimal numberFromCollection = BigDecimal.valueOf(numbers[i].doubleValue());
            if (numberFromCollection.compareTo(numberInput) == 0) {
                return true;
            }
        }
        return false;
    }

    public void resize(int capacity) {
        if (capacity < this.capacity) {
            cut(0, capacity);
        } else {
            Number[] num = new Number[capacity];
            System.arraycopy(this.numbers, 0, num, 0, this.size);
            this.numbers = num;
        }
        this.capacity = capacity;
    }

    @Override
    public void add(Number n) {
        if (unique(numbers, n)) {
            return;
        }
        if (size == capacity) {
            return;
        }
        numbers[size] = n;
        size++;
    }

    @Override
    public void add(Number... n) {
        for (Number number : n) {
            add(number);
        }
    }

    @Override
    public void join(MathSet mathSet) {
        Number[] array = mathSet.toArray();
        for (Number number : array) {
            add(number);
        }
    }

    @Override
    public void join(MathSet... ms) {
        for (MathSet mathSet : ms) {
            join(mathSet);
        }
    }

    @Override
    public void intersection(MathSet ms) {
        Number[] array = ms.toArray();
        MathSet resNumbers = new MathSet(this.capacity);
        for (Number number : array) {
            if (unique(numbers, number)) {
                resNumbers.add(number);
            }
        }
        this.size = resNumbers.size;
        this.numbers = resNumbers.toArray();
        resize(resNumbers.capacity);
    }

    @Override
    public void intersection(MathSet... ms) {
        for (MathSet mathset : ms) {
            intersection(mathset);
        }
    }

    @Override
    public void sortDesc() {
        sort(numbers, 0, size - 1, false);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        sort(numbers, firstIndex, lastIndex, false);
    }

    @Override
    public void sortDesc(Number value) {
        for (int i = 0; i < size; i++) {
            if (numbers[i].equals(value)) {
                sort(numbers, i, size - 1, false);
                break;
            }
        }
    }

    @Override
    public void sortAsc() {
        sort(numbers, 0, size - 1, true);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        sort(numbers, firstIndex, lastIndex, true);
    }

    public void sort(Number[] numbers, int firstIndex, int lastIndex, boolean isAsc) {
        try {
            if (firstIndex > lastIndex) throw new IllegalAccessException("Last index must be more than first");
            else if (firstIndex < 0) throw new IllegalAccessException("First index must be more than 0");
            else if (lastIndex >= size) throw new IllegalAccessException("Last index must be lower than size of set");
            for (int i = lastIndex; i >= firstIndex; i--) {
                for (int j = firstIndex; j < i; j++) {
                    boolean hasToSwap = isAsc ? (numbers[j].doubleValue() > numbers[j + 1].doubleValue())
                            : (numbers[j].doubleValue() < numbers[j + 1].doubleValue());
                    if (hasToSwap) {
                        Number temp = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = temp;
                    }
                }
            }
        } catch (NullPointerException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sortAsc(Number value) {
        for (int i = 0; i < size; i++) {
            if (numbers[i].equals(value)) {
                sort(numbers, i, size - 1, true);
                break;
            }
        }
    }

    @Override
    public Number get(int index) {
        return numbers[index];
    }

    @Override
    public Number getMax() {
        Number max = numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i].doubleValue() > max.doubleValue()) max = numbers[i];
        }
        return max;
    }

    @Override
    public Number getMin() {
        Number min = numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i].doubleValue() < min.doubleValue()) min = numbers[i];
        }
        return min;
    }

    @Override
    public Number getAverage() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += numbers[i].doubleValue();
        }
        return sum / size;
    }

    @Override
    public Number getMedian() {
        Number[] numbersTemp = new Number[capacity];
        System.arraycopy(numbers, 0, numbersTemp, 0, capacity);
        sort(numbersTemp, 0, size - 1, true);
        int medianIndex = size / 2;
        if (size % 2 == 0) {
            return (numbersTemp[medianIndex].doubleValue() + numbersTemp[medianIndex - 1].doubleValue()) / 2;
        }
        return numbersTemp[medianIndex];
    }

    @Override
    public Number[] toArray() {
        Number[] numbers = new Number[getSize()];
        Object[] objects = getArray();
        for (int i = 0; i < numbers.length; i++) {
            if (objects[i] instanceof Number) {
                numbers[i] = (Number) objects[i];
            }
        }
        return numbers;
    }

    public Number[] getArray() {
        Number[] returnable = new Number[size];
        System.arraycopy(numbers, 0, returnable, 0, size);
        return returnable;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex];
        Object[] objects = getArray();
        for (int i = firstIndex, j = 0; i < lastIndex; i++) {
            if (objects[i] instanceof Number) {
                numbers[j] = (Number) objects[i];
                ++j;
            }
        }
        return numbers;
    }

    @Override
    public MathSet cut(int firstIndex, int lastIndex) {
        Number[] resNumber = new Number[lastIndex - firstIndex];
        if (lastIndex - firstIndex >= 0) {
            System.arraycopy(numbers, firstIndex, resNumber, 0, lastIndex - firstIndex);
        }
        return new MathSet(resNumber);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            numbers[i] = null;
        }
        size = 0;
    }

    @Override
    public void clear(Number[] n) {
        int[] indexesForDelete = new int[n.length];

        for (int i = 0; i < n.length; i++) {
            boolean isWrittenIndex = false;
            for (int j = 0; j < size; j++) {
                if (n[i].equals(this.numbers[j])) {
                    indexesForDelete[i] = j;
                    isWrittenIndex = true;
                    break;
                }
            }

            if (!isWrittenIndex) {
                indexesForDelete[i] = -1;
            }
        }

        Number[] numbersDuplicate = new Number[capacity];

        for (int i = 0, j = 0; i < size; i++) {
            boolean mustBeDeleted = false;
            for (int index : indexesForDelete) {
                if (index == i) {
                    mustBeDeleted = true;
                    break;
                }
            }

            if (!mustBeDeleted) {
                numbersDuplicate[j] = this.numbers[i];
                ++j;
            }
        }
        this.size = size - indexesForDelete.length;
        System.arraycopy(numbersDuplicate, 0, this.numbers, 0, capacity);
    }

    @Override
    public String toString() {
        return "MathSet{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", numbers=" + Arrays.toString(numbers) +
                '}';
    }
}
