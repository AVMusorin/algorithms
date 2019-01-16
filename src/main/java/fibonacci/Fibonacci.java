package fibonacci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fibonacci {
    /**
     * Находит остаток от деления n-го числа числа Фибоначчи по m
     * @param fibNum - номер числа Фибоначчи
     * @param modValue - модуль
     * @return остаток от деленияыв
     */
    private Long bigFibMod(Long fibNum, Long modValue) {
        List<Long> values = getPeriodicity(fibNum, modValue);
        if (values.isEmpty()) {
            return fibNum % modValue;
        } else {
            // Период Пизано
            Long period = (long) values.size();
            return values.get((int) (fibNum % period));
        }
    }

    private Long nextFib(Long first, Long second) {
        return first + second;
    }

    /**
     * Считается периодичность для последовательности фибоначи
     * @param modValue модуль
     * @return
     */
    private List<Long> getPeriodicity(Long fibNum, Long modValue) {
        // Считаем числа Фибоначчи пока не найдем последовательность
        ArrayList<Long> modValues = new ArrayList<Long>();
        modValues.add(0L);
        modValues.add(1L);
        Long firstFib = 0L;
        Long secondFib = 1L;
        for (Long i = 0L; i < fibNum; i++) {
            Long tmp = nextFib(firstFib, secondFib) % modValue;
            if (tmp.equals(modValues.get(0))) {
                Long nextMod = nextFib(secondFib, tmp) % modValue;
                if (nextMod.equals(modValues.get(1))) {
                    return modValues;
                }
            }
            firstFib = secondFib;
            secondFib = tmp;
            modValues.add(tmp);
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        Long modResult = fib.bigFibMod(999999999999999999L, 99999L);
        System.out.print(modResult);
    }
}

