package interview.edf;

import java.util.Arrays;

// https://sammancoaching.org/kata_descriptions/yatzy.html
public class Yatzy {

    public static int countOne(int... diceNums) {
        return countSpecifiedDiceNumber(1, diceNums);
    }

    public static int countTwo(int... diceNums) {
        return countSpecifiedDiceNumber(2, diceNums);
    }

    public static int countThree(int... diceNums) {
        return countSpecifiedDiceNumber(3, diceNums);
    }

    public static int countFour(int... diceNums) {
        return countSpecifiedDiceNumber(4, diceNums);
    }

    public static int countFive(int... diceNums) {
        return countSpecifiedDiceNumber(5, diceNums);
    }

    public static int countSix(int... diceNums) {
        return countSpecifiedDiceNumber(6, diceNums);
    }

    private static int countSpecifiedDiceNumber(int targetNum, int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        long count = Arrays.stream(diceNums).filter(dice -> dice == targetNum).count();
        return (int) (targetNum * count);
    }

    // Note: This function makes sure the input dice number is valid
    //       There are five numbers between 1 and 6(inclusive)
    public static boolean isValidDiceNums(int... diceNums) {
        if (diceNums.length != 5) return false;
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

    public static int countPair(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int pairDiceFound = findHighDiceMoreThanSpecifiedTimes(2, diceNums);
        return pairDiceFound * 2;
    }

    public static int countThreeOfKind(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int threeKindDiceFound = findHighDiceMoreThanSpecifiedTimes(3, diceNums);
        return threeKindDiceFound * 3;
    }

    public static int countFourOfKind(int... diceNums) {
        if (!isValidDiceNums(diceNums)) return -1;
        int fourKindDiceFound = findHighDiceMoreThanSpecifiedTimes(4, diceNums);
        return fourKindDiceFound * 4;
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

    public static int countSmallStraight(int... diceNums) {
        if (isValidStraightScores(true, diceNums))
            return 15;
        return 0;
    }

    public static int countLargeStraight(int... diceNums) {
        if (isValidStraightScores(false, diceNums))
            return 20;
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
