package main.java;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    public static int[] genRandArray(int length,int min,int max){
        int[] randArray = new int[length];
        for (int i = 0; i < randArray.length; i++) {
            //randArray[i] = (int) Math.ceil(Math.random()*100);
            randArray[i] = ThreadLocalRandom.current().nextInt(min,max + 1);
        }
        return randArray;
    }
    public static int[] Bubblesort(int[] array){
        while (true){
            boolean used = false;
            for (int i = 0; i < array.length-1; i++) {
                int n1 = array[i];
                int n2 = array[i + 1];
                if (n1 > n2) {
                    array[i] = n2;
                    array[i + 1] = n1;
                    used = true;
                }
            }
            if (!used){
                return array;
            }
        }
    }
    public static int[] InsertionSort(int[] array){
        for (int i = 1; i < array.length;) {
            int n = i;
            while (n > 0){
                int n1 = array[n];
                int n2 = array[n - 1];
                if (n2 > n1){
                    array[n] = n2;
                    array[n - 1] = n1;
                    n--;
                }else {
                    i++;
                    break;
                }
            }
        }
        return array;
    }

    static int[] Sorted;
    static ArrayList <Integer> sorted = new ArrayList<>();
    public static int[] QuickSort1(int[] array){
        if (Sorted == null){
            Sorted = new int[array.length];
            QuickSort1(array);
        }else{
            if (array.length <= 5){
                int[] temp = CountingSort(array);
                for (int i = 0; i < array.length; i++) {
                    sorted.add(temp[i]);
                }
            } else {
                int pivit = array[array.length - 1];
                ArrayList <Integer> left = new ArrayList<>();
                ArrayList <Integer> right = new ArrayList<>();
                ArrayList <Integer> middle = new ArrayList<>();
                right.add(pivit);
                for (int i = 0; i < array.length-1; i++) {
                    if (array[i] < pivit){
                        left.add(array[i]);
                    }else if( array[i] > pivit){
                        right.add(array[i]);
                    } else {
                        middle.add(array[i]);
                    }
                }
                int[] Left = new int[left.size()];
                int[] Right = new int[right.size()];
                for (int i = 0; i < Left.length; i++) {
                    Left[i] = left.get(i);
                }
                for (int i = 0; i < Right.length; i++) {
                    Right[i] = right.get(i);
                }
                QuickSort1(Left);
                sorted.addAll(middle);
                QuickSort1(Right);
            }
        }
        if (sorted.size() == Sorted.length){
            for (int i = 0; i < Sorted.length; i++) {
                Sorted[i] = sorted.get(i);
            }
        }
        return Sorted;
    }

    public static int[] MergeSort(int[] array){
        int[] merged = new int[array.length];
        if (array.length == 1){
            return array;
        }
        if (array.length > 2){
            int[] Left = new int[(int) Math.floor((double)array.length / 2)];
            int[] Right = new int[(int) Math.ceil((double)array.length / 2)];
            int middle =(int) Math.floor(array.length / 2);
            for (int i = 0; i < array.length; i++) {
                if (i < middle){
                    Left[i] = array[i];
                }else{
                    Right[i - middle] = array[i];
                }
            }
            int[] mleft = MergeSort(Left);
            int[] mright = MergeSort(Right);
            int li = 0;
            int ri = 0;
            for (int i = 0; i < merged.length; i++) {
                if (li == mleft.length){
                    merged[i] = mright[ri];
                    ri++;
                } else if( ri  == mright.length){
                    merged[i] = mleft[li];
                    li++;
                } else {
                    if (mleft[li] < mright[ri]){
                        merged[i] = mleft[li];
                        li++;
                    } else {
                        merged[i] = mright[ri];
                        ri++;
                    }
                }
            }
            return merged;
        }else if (array.length > 1){
            int n1 = array[0];
            int n2 = array[1];
            if (n1 > n2) {
                array[0] = n2;
                array[1] = n1;
            }
            return array;
        }
        return merged;
    }
    public static int[] SelectionSort(int[] array){
        int n1;
        int n2;
        int i2 = 0;
        for (int i = 0; i < array.length; i++) {
            n1 = array[i];
            n2 = array[i];
            for (int j = i; j < array.length; j++) {
              if (n2 >= array[j]){
                  n2 = array[j];
                  i2 = j;
              }
            }
            array[i] = n2;
            array[i2] = n1;
        }
        return array;
    }
    public static int[] ShellSort(int[] array){
         int length = (int) Math.floor(array.length / 2);
         int p = 0;
         int f = 0;
         while(true){
            if (f < length) {
                p++;
                f = (int) (Math.pow(2, p) - 1);
            }else if (f == length){
                break;
            }else {
                p--;
                break;
            }
         }
         while (p > 0){
             f = (int) (Math.pow(2, p) - 1);
             for (int i = 1; i <= array.length / f; i++) {
                 int n = i * f;
                 while (n >= 1 && n < array.length){
                     int n1 = array[n];
                     int n2 = array[n - f];
                     if (n2 > n1){
                         array[n] = n2;
                         array[n - f] = n1;
                         n = n - f;
                     }else {
                         break;
                     }
                 }
             }
             p--;
         }
        return array;
    }
    public static int[] CountingSort(int[] array){
        int[] sorted = new int[array.length];
        int b = 0;
        int n = 0;
        for (int j : array) {
            if (b < j) {
                b = j;
            }
        }
        int[] addres = new int[b + 1];
        for (int j : array) {
            addres[j]++;
        }
        for (int i = 0; i < addres.length; i++) {
            n = n + addres[i];
            addres[i] = n;
        }
        for (int j : array) {
            sorted[addres[j] - 1] = j;
            addres[j]--;
        }
        return sorted;
    }
    public static int[] ExchangeSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }
    public static int[] CycleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            int e = array[i];
            boolean loop = true;
            while(loop){
                int tie = 0;
                for (int j = 0; j < array.length; j++) {
                    if (array[j] <= e && j != i){
                        tie++;
                    }
                }
                while(true){
                    if (tie != i){
                        if (tie >= array.length){
                            tie--;
                        }
                        if (array[tie] != e){
                            int etemp = array[tie];
                            array[tie] = e;
                            e = etemp;
                            break;
                        }else {
                            tie--;
                        }
                    }else {
                        array[i] = e;
                        loop = false;
                        break;
                    }
                }
            }
        }
        return array;
    }
    public static int[] GnomeSort(int[] array){
        for (int i = 0; i < array.length - 1;) {
            int n1 = array[i];
            int n2 = array[i + 1];
            if (n1 > n2){
                array[i] = n2;
                array[i + 1] = n1;
                if (i != 0){
                    i--;
                }
            }else {
                i++;
            }
        }
        return array;
    }
    public static int[] BogoSort(int[] array){
        ArrayList <Integer> rand = new ArrayList<>();
        for (int j : array) {
            rand.add(j);
        }
        boolean sorted;
        while (true) {
            sorted = true;
            for (int i = 0; i < rand.size() - 1; i++) {
                if (rand.get(i) > rand.get(i + 1)) {
                    sorted = false;
                    break;
                }
            }
            if (sorted){
                break;
            }
            Collections.shuffle(rand);
        }
        for (int i = 0; i < rand.size(); i++) {
            array[i] = rand.get(i);
        }
        return array;
    }
    public static int[] SpaghettiSort(int[] array){
        int[] sorted = new int[array.length];
        int sai = 0;
        for (int i = 0;sai < array.length; i++) {
            for (int j : array) {
                if (i == j) {
                    sorted[sai] = j;
                    sai++;
                }
            }
        }
        return sorted;
    }
    public static int[] IcantbeliveitcansortSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]){
                    int n1 = array[i];
                    array[i] = array[j];
                    array[j] = n1;
                }
            }
        }
        return array;
    }
    public static int[] HeapSort(int[] array){
        int max = array.length;
        int i;
        if (max % 2 == 0){
            i = max / 2;
        } else {
            i = (max - 1) / 2;
        }
        i--;
        while(max > 1) {
            if (i < 0 ){
                int temp = array[max - 1];
                array[max - 1] = array[0];
                array[0] = temp;
                i = 0;
                max--;
            }else {
                int il = i * 2 + 1;
                int ir = 0;
                boolean lib = true;
                if ( il + 1 < max){
                    ir = il + 1;
                    if (array[il] < array[ir]){
                        lib = false;
                    }
                }
                if (array[i] < array[il] || array[i] < array[ir]){
                    int temp = array[i];
                    if (lib){
                        array[i] = array[il];
                        array[il] = temp;
                        if (il * 2 + 1 < max){
                            i = il;
                        } else {
                            i--;
                        }
                    } else {
                        array[i] = array[ir];
                        array[ir] = temp;
                        if(ir * 2 + 2 < max){
                            i = ir;
                        } else {
                            i--;
                        }
                    }
                } else {
                    i--;
                }
            }
        }
        return array;
    }
    public static int[] RadixSort(int[] array){
        //System.out.println(Integer.toString(array[i]).length() + "" + array[i] % 10);
        int max = 0;
        for (int i : array) {
            if (max < i) {
                max = i;
            }
        }
        for (int i = 0; i < Integer.toString(max).length(); i++) {
            ArrayList<Integer> container = new ArrayList<>();
            for (int n = 0; n < 10; n++) {
                for (int j: array) {
                    int ni = (int) Math.floor(j / Math.pow(10, i) % 10);
                    if (n == ni) {
                        container.add(j);
                    }
                }
            }
            ArrayList<Integer> all = new ArrayList<>(container);
            for (int j = 0; j < array.length; j++) {
                array[j] = all.get(j);
            }
        }
        return array;
    }
    public static int[] BucketSort(int[] array){
        int max = 0;
        for (int i : array) {
            if (max < i) {
                max = i;
            }
        }
        ArrayList<Integer> bucket = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        int width = Integer.toString(max).length();
        for (int j = 0; j <= 9; j++) {
            for (int i : array) {
                if (i / (10 * width) == j) {
                    bucket.add(i);
                }
            }
            int sai = 0;
            for (int i = 0;sai < bucket.size(); i++) {
                for (int k : bucket) {
                    if (i == k) {
                        sorted.add(k);
                        sai++;
                    }
                }
            }
            bucket.clear();
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = sorted.get(i);
        }
        return array;
    }
    public static int[] NaturalMergeSort(int[] array){
        int i = array.length / 2 - 1;
        boolean asc = false;
        if (array.length > 1) {
            while (i + 1 != array.length){
                if (array[i] <= array[i + 1]){
                    if (i == 0){
                        asc = true;
                    }
                    if (asc){
                        i++;
                    } else {
                        i--;
                    }
                } else {
                    if (array[i] > array[i + 1]) {
                        int[] start = Arrays.copyOfRange(array, 0, i + 1);
                        int[] end = Arrays.copyOfRange(array, i + 1, array.length);
//                        System.out.println("Start: " + Arrays.toString(start));
//                        System.out.println("end: " + Arrays.toString(end));
                        int[] mleft = NaturalMergeSort(start);
                        int[] mright = NaturalMergeSort(end);
                        int li = 0;
                        int ri = 0;
                        for (int j = 0; j < array.length; j++) {
                            if (li == mleft.length) {
                                array[j] = mright[ri];
                                ri++;
                            } else if (ri == mright.length) {
                                array[j] = mleft[li];
                                li++;
                            } else {
                                if (mleft[li] < mright[ri]) {
                                    array[j] = mleft[li];
                                    li++;
                                } else {
                                    array[j] = mright[ri];
                                    ri++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return array;
    }
    public static int[] CocktailShackerSort(int[] array){
        int mini = 0;
        int maxi = array.length - 2;
        boolean asc = true;
        for(int i = 0;mini != maxi;){
            if (array[i] > array[i + 1]){
                int temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
            if (asc){
                i++;
            } else {
                i--;
            }
            if (i == maxi && asc){
                asc = false;
                maxi--;
            } else if (i == mini && !asc) {
                asc = true;
                mini++;
            }
        }
        return array;
    }
    public static int[] CombSort(int[] array){
        int gap = array.length - 1;
        boolean done = false;
        while (gap > 1 || !done){
            done = true;
            gap = (int) Math.floor(gap / 1.3);
            if (gap < 1){
                gap = 1;
            }
            for (int i = 0;  i + gap < array.length; i++) {
                if (array[i] > array[i+ gap]){
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    done = false;
                }
            }
        }
        return array;
    }
    public static int[] StoogeSort(int[] array){
        if (array[0] > array[array.length - 1]){
            int temp = array[0];
            array[0] = array[array.length - 1];
            array[array.length - 1] = temp;
        }
        if (array.length <= 2){
            return array;
        } else {
            int length = array.length / 3;
            int length2 =(int) ((double) array.length / 3 * 2);
            int[] temp = Arrays.copyOfRange(array,0,length2);
            StoogeSort(temp);
            System.arraycopy(temp, 0, array, 0, temp.length);
            temp = Arrays.copyOfRange(array,length, array.length);
            StoogeSort(temp);
            System.arraycopy(temp, 0, array, length, temp.length);
            temp = Arrays.copyOfRange(array,0,length2);
            StoogeSort(temp);
            System.arraycopy(temp, 0, array, 0, temp.length);
            return array;
        }
    }
    public static int[] StrandSort(int[] array){
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        while (list.size() > 0){
            ArrayList<Integer> chosen = new ArrayList<>();
            int n = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) >= n){
                    n = list.get(i);
                    chosen.add(n);
                    list.remove(i);
                    i--;
                }
            }
            if (sorted.size() == 0){
                sorted.addAll(chosen);
            }else {
                for (int i = 0; 0 < chosen.size(); i++) {
                    if (chosen.get(0) <= sorted.get(i)){
                        sorted.add(i,chosen.get(0));
                        chosen.remove(0);
                    }
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = sorted.get(i);
        }
        return array;
    }
    public static int[] OddEvenSort(int[] array){
        boolean even = true;
        boolean sorted = false;
        while(even || !sorted){
            sorted = true;
            int i;
            if (even){
                i = 0;
            } else {
                i = 1;
            }
            even = !even;
            for (; i < array.length - 1; i = i + 2) {
                if (array[i] > array[i + 1]){
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return array;
    }
    public static int[] LibrarySort(int[] array){
        ArrayList<Integer> gappedArray = new ArrayList<>();
            gappedArray.add(array[0]);
        int goal = 1;
        for (int i = 1; i <= array.length;) {
            if (i < goal && i < array.length){
                int n = array[i];
                int mini = 0;
                int maxi = gappedArray.size() - 1;
                boolean inserted = false;
                while (gappedArray.get(maxi) == null){
                    maxi--;
                }
                int middle = (maxi - mini) / 2 + mini;
                System.out.println("Inserting: " + gappedArray.toString() + " Element: " + n + " Mitte: " + middle);
//                mitte ist leer:
                if (gappedArray.get(middle) == null){
                    int tmiddle = middle + 1;
//                  finde das nächste element was größer ist als n ist:
                    while((gappedArray.get(tmiddle) == null || gappedArray.get(tmiddle) <= n) && tmiddle < gappedArray.size() - 1){
                        tmiddle++;
                    }
                    if (tmiddle != gappedArray.size() - 1){
                        while ((gappedArray.get(tmiddle) == null || gappedArray.get(tmiddle) > n) && tmiddle > 0){
                            tmiddle--;
                        }
                        middle = tmiddle;
                    } else {
                        if (gappedArray.get(tmiddle) == null){
                            middle = tmiddle;
                        } else {
                            if (gappedArray.get(tmiddle) < n){
                                middle = tmiddle;
                            } else{
                                middle = tmiddle - 1;
                            }
                        }
                    }
                }  else {
                    while(mini != maxi){
                        if (gappedArray.get(middle) >= n){
                            maxi--;
                        } else if (gappedArray.get(middle) < n) {
                            mini++;
                        }
                        middle =(int) Math.round((double) (maxi - mini) / 2 + mini);
                        if (gappedArray.get(middle) == null){
                            gappedArray.set(middle,n);
                            inserted = true;
                            break;
                        }
                    }
                }
                if (!inserted){
                    Integer temp = gappedArray.get(middle);
                    Integer e = n;
                    int index = middle;
                    boolean OutOfBounce = false;
                    if (temp == null){
                        gappedArray.set(index,e);
                    }
                    while (temp != null){
                        temp = gappedArray.get(index);
                        System.out.println("shifting: " + gappedArray.toString() + "Temp: " + temp  + " index: " + index);
//                        if (OutOfBounce && temp < gappedArray.get(index - 1)){
//
//                        }
                        gappedArray.set(index,e);
                        e = temp;
                        if (index + 1 >= gappedArray.size() && !OutOfBounce){
                            OutOfBounce = true;
                            index++;
                        }
                        if (OutOfBounce){
                            index--;
                        } else {
                            index++;
                        }
                    }
                }
                i++;
            } else if (i < array.length){
                goal = goal * 2;
                for (int j = 0; j < gappedArray.size(); j++) {
                    if (gappedArray.get(j) == null){
                        gappedArray.remove(j);
                        j--;
                    }
                    System.out.println("rebalanceing: " + gappedArray.toString());
                }
                int length = gappedArray.size();
                for (int j = 0; j < length; j++) {
                    gappedArray.add(j * 2 + 1,null);
//                    gappedArray.add(j * 3 + 2,null);
                    System.out.println("adding gaps: " + gappedArray.toString());
                }
            } else {
                int arrayi = 0;
                for (int j = 0; j < gappedArray.size(); j++) {
                    if (gappedArray.get(j) != null){
                        array[arrayi] = gappedArray.get(j);
                        arrayi++;
                    }
                }
                System.out.println(gappedArray.toString());
                i++;
            }
        }
        System.out.println(gappedArray.size());
        return array;
    }
    public static int[] javaSort(int[] array){
        Arrays.sort(array);
        return array;
    }
    public static String timer(String funktion, int[] array) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        int[] reset = Arrays.copyOf(array,array.length);
        long start = 0;
        long finish = 0;
        long time = 0;
        double seconds = 0;
        String sTime;
        Method method= Main.class.getMethod(funktion,int[].class);
        int[] solution;
        start = System.currentTimeMillis();
        solution = (int[]) method.invoke(null, (Object) reset);
        finish = System.currentTimeMillis();
        time = (finish - start);
        seconds = (double) time / 1000;
        sTime = String.format("%,06.3f",seconds);
        System.out.println("Ergebnis: " + Arrays.toString(solution));
        return sTime;
    }
    static ArrayList<ArrayList<String>> places = new ArrayList<>();
    public static void sort(String name, String method, int[] array) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        places.add( new ArrayList<>(Arrays.asList(timer(method,array),name )));
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InterruptedException {

//        int[] array = genRandArray(10, 0, 10);
        int[] array = new int[]{5, 1, 6, 1, 10, 8, 3, 0, 6, 4};
//        int[] array = new int[]{9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(array));

        /*Vergleicgbasierte Sortier Algorythmen*/
//        Block Sort
//        sort("Bogo Sort","BogoSort",array);
//        sort("Bubble Sort","Bubblesort",array);
//        sort("Cocktail Shaker Sort","CocktailShackerSort",array);
//        sort("Comb Sort","CombSort",array);
//        Cube Sort
//        sort("Cycle Sort","CycleSort",array);
//        sort("Exchange Sort","ExchangeSort",array);
//        sort("Gnome Sort","GnomeSort",array);
//        sort("Heap Sort","HeapSort",array);
//        sort("\"I can´t belive it can sort\" Sort","IcantbeliveitcansortSort",array);
//        sort("Insertion Sort","InsertionSort",array);
//        sort("Java","javaSort",array);
        sort("Libary Sort","LibrarySort",array);
//        sort("Merge Sort","MergeSort",array);
//        sort("Natural Merge Sort","NaturalMergeSort",array);
//        sort("Odd Even Sort","OddEvenSort",array);
//        sort("Quick Sort1","QuickSort1",array);
//        sort("Selection Sort","SelectionSort",array);
//        sort("Shell Sort","ShellSort",array);
//        Smooth Sort
//        sort("Strand Sort","StrandSort",array);
//        sort("Stooge Sort","StoogeSort",array);
//        ArrayList<Integer> test = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//            test.add(null);
//        }

//        System.out.println((int) Math.round((double)9 / 2));
//        System.out.println(test.toString());
        /*Nicht Vergleichbasierte Sortier Alorythmen*/
//        sort("Bucket Sort" ,"BucketSort",array);
        sort("Counting Sort" ,"CountingSort",array);
//        sort("Radix Sort","RadixSort",array);
//        sort("Spaghetti Sort","SpaghettiSort",array);

        ArrayList<String> n1;
        ArrayList<String> n2;
        int i2 = 0;
        for (int j = 0; j < places.size(); j++) {
            n1 = places.get(j);
            n2 = places.get(j);
            for (int k = j; k < places.size(); k++) {
                if (n2.get(0).compareTo(places.get(k).get(0)) >= 0){
                    n2 = places.get(k);
                    i2 = k;
                }
            }
            places.set(j,n2);
            places.set(i2,n1);
        }
        for (int i = 0; i < places.size(); i++) {
            System.out.println("\rPlatz " + String.format("%02d",i+1) + " mit " +  places.get(i).get(0) + " Sekunden" + " geht an " + places.get(i).get(1) + "." );
        }
    }
}
/*
import io.swagger.client.BsddApis;
        import io.swagger.client.api.DomainApi;
        import io.swagger.client.model.ClassificationContractV3;
        import io.swagger.client.model.LanguageContractV1;
        import io.swagger.client.model.ReferenceDocumentContractV1;

        import java.util.List;

    public class test {
    public static void main(String[] args){
        double dm = 15;
        double n = dm+1;
        n = Math.sqrt(n);
        n--;
        System.out.println(n);
        StringBuilder sphere = new StringBuilder();
        for (int i = 0; i < dm; i++) {
            for (int j = 0; j < dm; j++) {
                if ( (Math.sqrt(i) + n) + (Math.sqrt(j) + n) >= dm ){
                    System.out.print("[]");
                } else {
                    System.out.print("##");
                }
            }
            System.out.print("\n");
        }
//        System.out.println(sphere);
    }
//        System.out.println(
//                "          [][][]\n" +
//                "      [][][][][][][]\n" +
//                "    [][][][][][][][][]\n" +
//                "  [][][][][][][][][][][]\n" +
//                "  [][][][][][][][][][][]\n" +
//                "[][][][][][][][][][][][][]\n" +
//                "[][][][][][][][][][][][][]\n" +
//                "[][][][][][][][][][][][][]\n" +
//                "  [][][][][][][][][][][]\n" +
//                "  [][][][][][][][][][][]\n" +
//                "    [][][][][][][][][]\n" +
//                "      [][][][][][][]\n" +
//                "          [][][]");
//    }
}*/
