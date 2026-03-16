package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class PatienceSortRealisation<T extends Comparable<T>> {

    private int iterations = 0;
    public int getIterations() {
        return iterations;
    }
    public List<T> sort(List<T> toSort) {
        iterations = 0;
        List<Stack<T>> piles = createPiles(toSort);
        return merge(piles);
    }

    // распределение элементов списка по стопкам
    private List<Stack<T>> createPiles(List<T> s) {
        List<Stack<T>> result = new ArrayList<>();

        for (int i = 0; i < s.size(); i++) {
            iterations++;
            int listIndex = binarySearch(result, s.get(i));

            if (listIndex == result.size()) { // подходящей стопки не нашлось
                Stack<T> newPile = new Stack<>();
                newPile.push(s.get(i));
                result.add(newPile);
            } else {
                result.get(listIndex).push(s.get(i));
            }
        }
        return result;
    }

    // бинарный поиск log(n), чтобы найти самую левую стопку, где верхний элемент >= текущего элемента
    private int binarySearch(List<Stack<T>> piles, T element ) {
        int left = 0;
        int right = piles.size() - 1;
        int result = piles.size(); // если не найдется подходящей стопки -> создаем новую стопку
        while (left <= right) {
            iterations++;
            int mid = left + (right - left) / 2;
            T topElement = piles.get(mid).peek();
            if (topElement.compareTo(element) >= 0) {
                result = mid;
                right = mid - 1; // проверяем наличие такой стопки до самой левой
            } else {
                left = mid + 1;
            }
        }
        return result;
    }


    private List<T> merge(List<Stack<T>> piles){
        List<T> result = new ArrayList<>();
        PriorityQueue<ElementOfPile<T>> minHeap = new PriorityQueue<>();

        // заполняем двоичную кучу верхними элементами со всех стопок
        for (int i = 0; i < piles.size(); i++) {
            iterations++;
            if (!piles.get(i).empty()) {

                minHeap.offer(new ElementOfPile<>(piles.get(i).pop(), i));
            }
        }

        while (!minHeap.isEmpty()) {
            iterations++;
            // удаляем минимальный элемент из кучи
            ElementOfPile<T> element = minHeap.poll();
            result.add(element.value);
            // если в стеке где был удаленный элемент ещё есть другие элементы,
            //  то добавляем в кучу минимальный элемент из стека (т.е. верхний)
            if (!piles.get(element.indexOfPile).isEmpty()) {
                minHeap.offer(new ElementOfPile<>(piles.get(element.indexOfPile).pop(), element.indexOfPile));
            }
        }
        return result;

    }

    // вспомогательный класс, хранящий индекс стопки, в которой элеемент находится
    private class ElementOfPile<T extends Comparable<T>> implements Comparable<ElementOfPile<T>> {
        T value;
        int indexOfPile;

        public ElementOfPile(T value, int indexOfPile) {
            this.value = value;
            this.indexOfPile = indexOfPile;
        }

        public int compareTo(ElementOfPile<T> e) {
            return this.value.compareTo(e.value);
        }
    }

}