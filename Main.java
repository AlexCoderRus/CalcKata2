import java.util.Scanner;

import static java.lang.String.valueOf;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введдите выражение");
        Scanner scan = new Scanner(System.in);
        String ups = scan.nextLine();
        String result1 = calc(ups);
        System.out.println(result1);

        }
    public static String calc (String input) {
        String[] romanStart = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            return "Cтрока не является математической операцией";
        }
        int[] variables = new int[2];
        int[] variablesStart = new int[2];
        String[] massive = input.split(regexActions[actionIndex]);
        if (massive.length != 2) {
            return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор";
        }

        for (int i = 0; i < 2; i++)
            massive[i] = massive[i].trim();
        int rN = 0;
        for (int i = 0; i < 2; i++) {   //проверяем есть ли римские цифры в выражении и переводим их в арабские
            for (int v = 0; v < 10; v++) {
                if (massive[i].equals(romanStart[v])) {
                    variables[i] = arabic[v];
                    rN = rN + 1;
                    break;
                }
            }
        }

        boolean a = variablesStart[0] == variables[0];
        boolean b = variablesStart[1] == variables[1];
        if ((a && b) || (a != b)) {
            if (a != b) {
                return "Вы используете разные системы счисления или ввели числа меньше 1 или больше 10";
            } else {
                variables[0] = Integer.parseInt(massive[0]);
                variables[1] = Integer.parseInt(massive[1]);
                if (variables[0] < 1 || variables[0] > 10 || variables[1] < 1 || variables[1] > 10) {
                    return "Вы ввели числа меньше 1 или больше 10";
                }
            }
        }
        int resultM = switch (actions[actionIndex]) {
            case "+" -> variables[0] + variables[1];
            case "-" -> variables[0] - variables[1];
            case "*" -> variables[0] * variables[1];
            default -> variables[0] / variables[1];
        };

        if ((rN == 2 && resultM<=0)) {
            return "В римской системе нет отрицательных чисел";
        }

            String result;
            if (rN == 2) {
                result = IntegerToRomanNumeral(resultM);
            } else {
                result = valueOf(resultM);
            }
        return result;
    }



    public static String IntegerToRomanNumeral(int input) {

        StringBuilder s = new StringBuilder();

        while (input == 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input == 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input == 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }
    }