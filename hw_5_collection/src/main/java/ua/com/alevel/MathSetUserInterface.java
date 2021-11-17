package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MathSetUserInterface {

    private final Map<String, MathSet> mapOfMathSets = new HashMap<>();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----------------");
        System.out.println("All MathSets will be saved and you can use them for testing methods");
        System.out.println("Select type of operation:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                mainOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("----------------");
        System.out.println("If you want to create MathSet, please enter 1");
        System.out.println("If you get all MathSets which was created , please enter 2");
        System.out.println("If you want to test methods , please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void mainOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                runInterfaceForCreate(reader);
                break;
            case "2":
                showAllMathSets();
                break;
            case "3":
                runInterfaceForMethods(reader);
                break;
        }
        runNavigation();
    }

    public void runInterfaceForMethods(BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("Test Methods");
        System.out.println("Select type of operation:");
        String position;
        try {
            runMethodsNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                methodOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    public void runInterfaceForCreate(BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("Create MathSets");
        System.out.println("Select type of operation:");
        String position;
        try {
            runCreateNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                createOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runCreateNavigation() {
        System.out.println("to create with constructor MathSet(), please enter 1");
        System.out.println("to create with constructor MathSet(int capacity), please enter 2");
        System.out.println("to create with constructor MathSet(Number[] numbers), please enter 3");
        System.out.println("to create with constructor MathSet(Number[] ... numbers), please enter 4");
        System.out.println("to create with constructor MathSet(MathSet numbers), please enter 5");
        System.out.println("to create with constructor MathSet(MathSet ... numbers), please enter 6");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void runMethodsNavigation() {
        System.out.println("to use method add(Number n), please enter 1");
        System.out.println("to use method add(Number ... n), please enter 2");
        System.out.println("to use method join(MathSet ms), please enter 3");
        System.out.println("to use method join(MathSet ... ms), please enter 4");
        System.out.println("to use method intersection(MathSet ms), please enter 5");
        System.out.println("to use method intersection(MathSet ... ms), please enter 6");
        System.out.println("to use method sortDesc(), please enter 7");
        System.out.println("to use method sortDesc(int firstIndex, int lastIndex), please enter 8");
        System.out.println("to use method sortDesc(Number value), please enter 9");
        System.out.println("to use method sortAsc(), please enter 10");
        System.out.println("to use method sortAsc(int firstIndex, int lastIndex), please enter 11");
        System.out.println("to use method sortAsc(Number value), please enter 12");
        System.out.println("to use method get(int index), please enter 13");
        System.out.println("to use method getMax(), please enter 14");
        System.out.println("to use method getMin(), please enter 15");
        System.out.println("to use method getAverage(), please enter 16");
        System.out.println("to use method getMedian(), please enter 17");
        System.out.println("to use method toArray(), please enter 18");
        System.out.println("to use method toArray(int firstIndex, int lastIndex), please enter 19");
        System.out.println("to use method cut(), please enter 20");
        System.out.println("to use method clear(), please enter 21");
        System.out.println("to use method clear(Number[] numbers), please enter 22");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void showAllMathSets() {
        for (Map.Entry<String, MathSet> entry : mapOfMathSets.entrySet()) {
            System.out.println("id:" + entry.getKey() + ", capacity:" + entry.getValue().getCapacity() + ", mathSet:" + Arrays.toString(entry.getValue().toArray()));
        }
    }

    private void createOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                createMathSet();
                break;
            case "2":
                createMathSetWithCapacity(reader);
                break;
            case "3":
                createMathSetFromArray(reader);
                break;
            case "4":
                createMathSetFromVarArgsOfArray(reader);
                break;
            case "5":
                createMathSetFromMathSet(reader);
                break;
            case "6":
                createMathSetFromVarArgsOfMathSet(reader);
                break;
        }
        runCreateNavigation();
    }

    private void createMathSet() {
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet();
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetWithCapacity(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter capacity:");
        String inputRow;
        int capacity = 0;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    return;
                }
                try {
                    capacity = Integer.parseInt(inputRow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("input max capacity is not valid, try again please");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet(capacity);
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetFromArray(BufferedReader reader) {
        Number[] inputArray;
        try {
            inputArray = inputArrayOfNumbers(reader);
        } catch (RuntimeException e) {
            return;
        }
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet(inputArray);
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetFromVarArgsOfArray(BufferedReader reader) {
        String position;
        List<Number[]> arrayOfNumbers = new ArrayList<Number[]>();
        try {
            createArraysNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1":
                        Number[] inputArray;
                        try {
                            inputArray = inputArrayOfNumbers(reader);
                            arrayOfNumbers.add(inputArray);
                        } catch (RuntimeException e) {
                            return;
                        }
                        createArraysNavigation();
                        break;
                    case "2":
                        String id = UUID.randomUUID().toString();
                        ///
                        MathSet mathSet = new MathSet(arrayOfNumbers.toArray(Number[][]::new));
                        mapOfMathSets.put(id, mathSet);
                        System.out.println("MathSet created:");
                        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
                        System.out.println("---------------");
                        return;
                    case "0":
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void createMathSetFromMathSet(BufferedReader reader) {
        MathSet mathSetById;
        try {
            mathSetById = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet(mathSetById);
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }


    private void createMathSetFromVarArgsOfMathSet(BufferedReader reader) {
        String position;
        List<MathSet> arrayOfMathSet = new ArrayList<MathSet>();
        try {
            createMathSetsNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1":
                        MathSet mathSetById = inputMathSetById(reader);
                        arrayOfMathSet.add(mathSetById);
                        break;
                    case "2":
                        String id = UUID.randomUUID().toString();
                        MathSet mathSet = new MathSet(arrayOfMathSet.stream().toArray(MathSet[]::new));
                        mapOfMathSets.put(id, mathSet);
                        System.out.println("MathSet created:");
                        System.out.println("id:" + id + ", capacity:" + mathSet.getCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
                        System.out.println("---------------");
                        return;
                    case "0":
                        break;
                }
                createMathSetsNavigation();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void methodOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                addNumberToMathSet(reader);
                break;
            case "2":
                addVarArgsOfNumberToMathSet(reader);
                break;
            case "3":
                joinMathSetToMathSet(reader);
                break;
            case "4":
                joinVarArgsOfMathSetToMathSet(reader);
                break;
            case "5":
                intersectionMathSetToMathSet(reader);
                break;
            case "6":
                intersectionVarArgsOfMathSetToMathSet(reader);
                break;
            case "7":
                sortDesc(reader);
                break;
            case "8":
                sortDescByIndexes(reader);
                break;
            case "9":
                sortDescByNumber(reader);
                break;
            case "10":
                sortAsc(reader);
                break;
            case "11":
                sortAscByIndexes(reader);
                break;
            case "12":
                sortAscByNumber(reader);
                break;
            case "13":
                get(reader);
                break;
            case "14":
                getMax(reader);
                break;
            case "15":
                getMin(reader);
                break;
            case "16":
                getAverage(reader);
                break;
            case "17":
                getMedian(reader);
                break;
            case "18":
                toArray(reader);
                break;
            case "19":
                toArrayByIndexes(reader);
                break;
            case "20":
                cut(reader);
                break;
            case "21":
                clear(reader);
                break;
            case "22":
                clearByArray(reader);
                break;
        }
        runMethodsNavigation();
    }

    private void clearByArray(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        Number[] inputArray;
        try {
            inputArray = inputArrayOfNumbers(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.clear(inputArray);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void clear(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.clear();
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void cut(BufferedReader reader) {
        MathSet currentMathSet;
        int firstIndex;
        int lastIndex;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("First index");
            firstIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("Last index");
            lastIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        MathSet newMathSet = currentMathSet.cut(firstIndex, lastIndex);
        System.out.println("new MathSet:");
        String id = UUID.randomUUID().toString();
        mapOfMathSets.put(id, newMathSet);
        System.out.println("id:" + id + ", capacity:" + newMathSet.getCapacity() + ", mathSet:" + Arrays.toString(newMathSet.toArray()));
        System.out.println("---------------");
    }

    private void toArrayByIndexes(BufferedReader reader) {
        MathSet currentMathSet;
        int firstIndex;
        int lastIndex;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("First index");
            firstIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("Last index");
            lastIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Array of numbers:" + Arrays.toString(currentMathSet.toArray(firstIndex, lastIndex)));
        System.out.println("---------------");
    }

    private void toArray(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Array of numbers:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void getMedian(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Min number:" + currentMathSet.getMedian());
        System.out.println("---------------");
    }

    private void getAverage(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Average number:" + currentMathSet.getAverage());
        System.out.println("---------------");
    }

    private void getMin(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Min number:" + currentMathSet.getMin());
        System.out.println("---------------");
    }

    private void getMax(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("Max number:" + currentMathSet.getMax());
        System.out.println("---------------");
    }

    private void get(BufferedReader reader) {
        MathSet currentMathSet;
        int index;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            index = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        Number number = currentMathSet.get(index);
        System.out.println("number:" + number);
        System.out.println("---------------");
    }

    private void sortAscByNumber(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }

        System.out.println("-----------");
        System.out.println("if you want exit, please enter 'esc'");
        System.out.println("you can use next types: byte, short, int, long, float, double");
        System.out.println("enter number (example:(long)12):");
        String inputRow;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("esc")) {
                    System.out.println("--------------------");
                    break;
                }
                try {
                    Number number = Integer.parseInt(inputRow);
                    currentMathSet.sortAsc(number);
                    System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
                    System.out.println("----------------");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("problem with pars number: " + inputRow);
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void sortAscByIndexes(BufferedReader reader) {
        MathSet currentMathSet;
        int firstIndex;
        int lastIndex;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("First index");
            firstIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("Last index");
            lastIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.sortAsc(firstIndex, lastIndex);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void sortAsc(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.sortAsc();
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void sortDescByNumber(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }

        System.out.println("-----------");
        System.out.println("if you want exit, please enter 'esc'");
        System.out.println("you can use next types: byte, short, int, long, float, double");
        System.out.println("enter number (example:(long)12):");
        String inputRow;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("esc")) {
                    System.out.println("--------------------");
                    break;
                }
                try {
                    Number number = Integer.parseInt(inputRow);
                    currentMathSet.sortDesc(number);
                    System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
                    System.out.println("----------------");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("problem with pars number: " + inputRow);
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void sortDescByIndexes(BufferedReader reader) {
        MathSet currentMathSet;
        int firstIndex;
        int lastIndex;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("First index");
            firstIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        try {
            System.out.println("Last index");
            lastIndex = inputIndex(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.sortDesc(firstIndex, lastIndex);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void sortDesc(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.sortDesc();
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("---------------");
    }

    private void intersectionVarArgsOfMathSetToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        String position;
        List<MathSet> arrayOfMathSet = new ArrayList<MathSet>();
        try {
            addVarArgsOfMathSetsNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1":
                        MathSet mathSetById = inputMathSetById(reader);
                        arrayOfMathSet.add(mathSetById);
                        break;
                    case "2":
                        currentMathSet.intersection(arrayOfMathSet.stream().toArray(MathSet[]::new));
                        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
                        System.out.println("---------------");
                        return;
                    case "0":
                        return;
                }
                addVarArgsOfMathSetsNavigation();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void intersectionMathSetToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        MathSet joiningMathSet;
        try {
            System.out.println("MathSet which you want intersection");
            joiningMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.intersection(joiningMathSet);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("----------------");
    }

    private void joinVarArgsOfMathSetToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        String position;
        List<MathSet> arrayOfMathSet = new ArrayList<MathSet>();
        try {
            addVarArgsOfMathSetsNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1":
                        MathSet mathSetById = inputMathSetById(reader);
                        arrayOfMathSet.add(mathSetById);
                        break;
                    case "2":
                        currentMathSet.join(arrayOfMathSet.stream().toArray(MathSet[]::new));
                        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
                        System.out.println("---------------");
                        return;
                    case "0":
                        return;
                }
                addVarArgsOfMathSetsNavigation();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void joinMathSetToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            System.out.println("current MathSet");
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        MathSet joiningMathSet;
        try {
            System.out.println("MathSet which you want to join");
            joiningMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        currentMathSet.join(joiningMathSet);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("----------------");
    }

    private void addVarArgsOfNumberToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        Number[] numbers = inputArrayOfNumbers(reader);
        currentMathSet.add(numbers);
        System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
        System.out.println("----------------");
    }

    private void addNumberToMathSet(BufferedReader reader) {
        MathSet currentMathSet;
        try {
            currentMathSet = inputMathSetById(reader);
        } catch (RuntimeException e) {
            return;
        }
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 'esc'");
        System.out.println("in number you can use next types: byte, short, int, long, float, double");
        System.out.println("enter number separated by SPACE");
        System.out.println("enter number(example: 1, 2b, 3.4, 5L, 6.0f):");
        String inputRow;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("esc")) {
                    System.out.println("--------------------");
                    break;
                }
                try {
                    Number number = getNumberFromString(inputRow);
                    currentMathSet.add(number);
                    System.out.println("capacity:" + currentMathSet.getCapacity() + ", mathSet:" + Arrays.toString(currentMathSet.toArray()));
                    System.out.println("----------------");
                } catch (NumberFormatException e) {
                    System.out.println("problem with pars number: " + inputRow);
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private Number[] inputArrayOfNumbers(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("in number you can use next types: byte, short, int, long, float, double");
        System.out.println("enter number separated by SPACE");
        System.out.println("enter row of number(example: 1 2b 3.4 5L 6.0f):");
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    throw new RuntimeException("Enter 0 for exit");
                }
                break;
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        String[] stringArray = inputRow.split(" ");
        Number[] returnArray = new Number[stringArray.length];
        int countNumbersWithMistake = 0;
        for (int i = 0; i < stringArray.length; i++) {
            try {
                returnArray[i - countNumbersWithMistake] = getNumberFromString(stringArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("problem with pars number: " + stringArray[i]);
                countNumbersWithMistake++;
            }
        }
        return returnArray;
    }

    private int inputIndex(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter esc");
        System.out.println("enter index:");
        String inputRow;
        int index = 0;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("esc")) {
                    System.out.println("--------------------");
                    throw new RuntimeException();
                }
                try {
                    index = Integer.parseInt(inputRow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("input index is not valid, try again please");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return index;
    }

    private MathSet inputMathSetById(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter id of MathSet:");
        String inputRow;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    break;
                }
                MathSet baseMathSet = mapOfMathSets.get(inputRow);
                if (baseMathSet == null) {
                    System.out.println("input id isn't exist, try again please");
                } else {
                    return baseMathSet;
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        throw new RuntimeException("Math set is not found");
    }

    private Number getNumberFromString(String stringNumber) {
        if (stringNumber.contains("b")) {
            return Byte.parseByte(stringNumber.replace("b", ""));
        } else if (stringNumber.contains("B")) {
            return Byte.parseByte(stringNumber.replace("B", ""));
        } else if (stringNumber.contains("s")) {
            return Short.parseShort(stringNumber.replace("s", ""));
        } else if (stringNumber.contains("S")) {
            return Short.parseShort(stringNumber.replace("S", ""));
        } else if (stringNumber.contains("l")) {
            return Long.parseLong(stringNumber.replace("l", ""));
        } else if (stringNumber.contains("L")) {
            return Long.parseLong(stringNumber.replace("L", ""));
        } else if (stringNumber.contains(".")) {
            if (stringNumber.contains("f")) {
                return Float.parseFloat(stringNumber.replace("f", ""));
            } else if (stringNumber.contains("F")) {
                return Float.parseFloat(stringNumber.replace("F", ""));
            } else return Double.parseDouble(stringNumber);
        }
        return Integer.parseInt(stringNumber);
    }

    private void createArraysNavigation() {
        System.out.println("-----------");
        System.out.println("if you want to create array, please enter 1");
        System.out.println("if you want to create MathSet from the entered arrays, please enter 2");
        System.out.println("if you want exit, please enter 0");
    }

    private void createMathSetsNavigation() {
        System.out.println("-----------");
        System.out.println("if you want to enter id of MathSet, please enter 1");
        System.out.println("if you want to create MathSet from the entered id, please enter 2");
        System.out.println("if you want exit, please enter 0");
    }

    private void addVarArgsOfMathSetsNavigation() {
        System.out.println("-----------");
        System.out.println("if you want to enter id of MathSet, please enter 1");
        System.out.println("if you want to run method, please enter 2");
        System.out.println("if you want exit, please enter 0");
    }
}
