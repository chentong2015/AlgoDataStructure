package interview.edf;

import java.util.Arrays;

// 无法理解Refactoring重构代码之后的评判体系
// https://sammancoaching.org/kata_descriptions/yatzy.html
public class Yatzy {

    public static int countTargetNumber(int targetNumber, int... diceNums) {
        return countSpecifiedDiceNumber(targetNumber, diceNums);
    }

    private static int countSpecifiedDiceNumber(int targetNum, int... diceNums) {
        if (!isValidDiceNums(diceNums)) {
            return -1;
        }
        long count = Arrays.stream(diceNums).filter(dice -> dice == targetNum).count();
        return (int) (targetNum * count);
    }

    // Note: This function makes sure the input dice number is valid
    //       There are five numbers between 1 and 6(inclusive)
    public static boolean isValidDiceNums(int... diceNums) {
        if (diceNums.length != 5) {
            return false;
        }
        for (int dice : diceNums) {
            if (dice < 1 || dice > 6) return false;
        }
        return true;
    }

    public static int countChance(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        return Arrays.stream(diceNums).sum();
    }

    public static int countYatzy(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int markValue = -1;
        for (int dice : diceNums) {
            if (markValue == -1) {
                markValue = dice;
            } else if (markValue != dice) {
                return 0;
            }
        }
        return 50;
    }

    public static int countPairOrNumOfKinds(int numOfKinds, int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int diceFound = findHighDiceMoreThanSpecifiedTimes(2, diceNums);
        return diceFound * numOfKinds;
    }

    // Note: This function finds the highest matching dice with more than specified times
    private static int findHighDiceMoreThanSpecifiedTimes(int minTimes, int... diceNums) {
        int[] counts = new int[6];
        for (int dice : diceNums) counts[dice - 1]++;
        for (int index = 5; index >= 0; index--) {
            if (counts[index] >= minTimes) {
                return index + 1;
            }
        }
        return 0;
    }

    public static int countTwoPair(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int[] counts = new int[6];
        for (int dice : diceNums) counts[dice - 1]++;
        int lowDice = 0;
        int highDice = 0;
        for (int index = 0; index < 6; index++) {
            if (counts[index] >= 2) {
                if (lowDice == 0) lowDice = index + 1;
                else highDice = index + 1;
            }
        }
        if (lowDice > 0 && highDice > 0)
            return (lowDice + highDice) * 2;
        return 0;
    }

    public static int countSmallOrLargeStraight(int valueStraight, int... diceNums) {
        if (isValidStraightScores(true, diceNums))
            return valueStraight;
        return 0;
    }

    private static boolean isValidStraightScores(boolean isSmallStraight, int... diceNums) {
        if (!isValidDiceNums(diceNums)) return false;
        int tempDice = 0;
        int moduleScore = isSmallStraight ? 5 : 6;
        for (int dice : diceNums) {
            if (tempDice == 0) {
                tempDice = dice;
            } else {
                if (tempDice == moduleScore) {
                    tempDice = moduleScore - 4;
                } else {
                    tempDice = tempDice + 1;
                }
                if (tempDice != dice) return false;
            }
        }
        return true;
    }

    public static int countFullHouse(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int dice1 = -1;
        int countDice1 = 0;
        int dice2 = -1;
        int countDice2 = 0;
        for (int dice : diceNums) {
            if (dice1 == -1) {
                dice1 = dice;
                countDice1++;
            } else if (dice1 == dice) {
                if (++countDice1 > 3) return 0;
            } else if (dice2 == -1) {
                dice2 = dice;
                countDice2++;
            } else if (dice2 == dice) {
                if (++countDice2 > 3) return 0;
            } else {
                return 0;
            }
        }
        return dice1 * countDice1 + dice2 * countDice2;
    }
}
