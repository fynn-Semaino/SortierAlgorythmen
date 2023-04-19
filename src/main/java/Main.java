package main.java;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static int[] genRandArray(int length,int min,int max){
        int[] randArray = new int[length];
        for (int i = 0; i < randArray.length; i++) {
            //randArray[i] = (int) Math.ceil(Math.random()*100);
            randArray[i] = ThreadLocalRandom.current().nextInt(min,max + 1);
        }
        return randArray;
    }
    public static void initialisierung(int[] array){
        return;
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
    public static int[] QuickSort(int[] array){
        if (Sorted == null){
            Sorted = new int[array.length];
            QuickSort(array);
        }else{
            if (array.length <= 5){
                int[] temp = InsertionSort(array);
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
                QuickSort(Left);
                sorted.addAll(middle);
                QuickSort(Right);
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
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]){
                max = array[i];
            }
        }
        for (int i = 0; i < Integer.toString(max).length(); i++) {
            ArrayList<Integer> n0 = new ArrayList<>();
            ArrayList<Integer> n1 = new ArrayList<>();
            ArrayList<Integer> n2 = new ArrayList<>();
            ArrayList<Integer> n3 = new ArrayList<>();
            ArrayList<Integer> n4 = new ArrayList<>();
            ArrayList<Integer> n5 = new ArrayList<>();
            ArrayList<Integer> n6 = new ArrayList<>();
            ArrayList<Integer> n7 = new ArrayList<>();
            ArrayList<Integer> n8 = new ArrayList<>();
            ArrayList<Integer> n9 = new ArrayList<>();
            ArrayList<Integer> all = new ArrayList<>();
            for (int j = 0; j < array.length; j++) {
                int ni =(int) Math.floor(array[j] / Math.pow(10,i) % 10);
                if (ni == 0){n0.add(array[j]);}
                else if (ni == 1) {n1.add(array[j]);}
                else if (ni == 2) {n2.add(array[j]);}
                else if (ni == 3) {n3.add(array[j]);}
                else if (ni == 4) {n4.add(array[j]);}
                else if (ni == 5) {n5.add(array[j]);}
                else if (ni == 6) {n6.add(array[j]);}
                else if (ni == 7) {n7.add(array[j]);}
                else if (ni == 8) {n8.add(array[j]);}
                else {n9.add(array[j]);}
            }
            all.addAll(n0);
            all.addAll(n1);
            all.addAll(n2);
            all.addAll(n3);
            all.addAll(n4);
            all.addAll(n5);
            all.addAll(n6);
            all.addAll(n7);
            all.addAll(n8);
            all.addAll(n9);
            for (int j = 0; j < array.length; j++) {
                array[j] = all.get(j);
            }
        }
        return array;
    }
    public static int[] BucketSort(int[] array){
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]){
                max = array[i];
            }
        }
        for (int i = Integer.toString(max).length() - 1; i >= 0 ; i--) {
            ArrayList<Integer> n0 = new ArrayList<>();
            ArrayList<Integer> n1 = new ArrayList<>();
            ArrayList<Integer> n2 = new ArrayList<>();
            ArrayList<Integer> n3 = new ArrayList<>();
            ArrayList<Integer> n4 = new ArrayList<>();
            ArrayList<Integer> n5 = new ArrayList<>();
            ArrayList<Integer> n6 = new ArrayList<>();
            ArrayList<Integer> n7 = new ArrayList<>();
            ArrayList<Integer> n8 = new ArrayList<>();
            ArrayList<Integer> n9 = new ArrayList<>();
            ArrayList<Integer> all = new ArrayList<>();
            for (int j = 0; j < array.length; j++) {
                int ni = array[j] / (int) Math.pow(10,i) % 10;
                if (ni == 0){n0.add(array[j]);}
                else if (ni == 1) {n1.add(array[j]);}
                else if (ni == 2) {n2.add(array[j]);}
                else if (ni == 3) {n3.add(array[j]);}
                else if (ni == 4) {n4.add(array[j]);}
                else if (ni == 5) {n5.add(array[j]);}
                else if (ni == 6) {n6.add(array[j]);}
                else if (ni == 7) {n7.add(array[j]);}
                else if (ni == 8) {n8.add(array[j]);}
                else {n9.add(array[j]);}
            }
            all.addAll(n0);
            all.addAll(n1);
            all.addAll(n2);
            all.addAll(n3);
            all.addAll(n4);
            all.addAll(n5);
            all.addAll(n6);
            all.addAll(n7);
            all.addAll(n8);
            all.addAll(n9);
            for (int j = 0; j < array.length; j++) {
                array[j] = all.get(j);
            }
        }
        return array;
    }
    public static int[] NaturalMergeSort(int[] array){
        int i = array.length / 2 - 1;
        boolean split = false;
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
        int gap = array.length;
        boolean done = false;
        while (!done){
            done = true;
            gap = (int) Math.floor((double) (gap / 1.3));
            if (gap < 1){
                gap = 1;
            }
            for (int i = 0;  i + gap < array.length; i++) {
                if (array[i] > array[i+ gap ]){
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    done = false;
                }
            }
        }
        return array;
    }
    public static int[] javaSort(int[] array){
        Arrays.sort(array);
        return array;
    }
    public static String timer(String name, String funktion, int[] array) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        System.out.print("Sorting with " + name + "...");
        int[] reset = Arrays.copyOf(array,array.length);
        long start;
        long finish;
        long time;
        double seconds;
        String sTime;
        Method method= Main.class.getMethod(funktion,int[].class);
        int[] solution;
        start = System.nanoTime();
        solution = (int[]) method.invoke(null, (Object) reset);
        finish = System.nanoTime();
        time = (finish - start);
        seconds = (double) time / 1000000000;
        sTime = String.format("%,08.5f",seconds);
//        System.out.print("\r");
//        System.out.print("\r Time:" + sTime + " Length: " + array.length);
//        System.out.println("Ergebnis: " + Arrays.toString(solution));
        return sTime;
    }
    static ArrayList<ArrayList<String>> places = new ArrayList<>();
    public static void sort(String name, String method, int[] array) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        places.add( new ArrayList<>(Arrays.asList(timer(name,method,array),name )));
        if (name == "initialisierung"){
            places.clear();
        }
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        for (int i = 0; i < i + 1; i++) {
//            timer("Counting Sort","CountingSort",array);
//        }
        int[] array = genRandArray(10000, 0, 10);
//        int[] array = new int[]{9,8,7,6,5,4,3,2,1};
//        System.out.println(Arrays.toString(array));

        /*Vergleicgbasierte Sortier Algorythmen*/
//        Block Sort
//        sort("Bogo Sort","BogoSort",array);
//        sort("Bubble Sort","Bubblesort",array);
        sort("Cocktail Shaker Sort","CocktailShackerSort",array);
        sort("Comb Sort","CombSort",array);
//        Cube Sort
//        sort("Cycle Sort","CycleSort",array);
//        sort("Exchange Sort","ExchangeSort",array);
//        sort("Gnome Sort","GnomeSort",array);
//        sort("Heap Sort","HeapSort",array);
//        sort("\"I can´t belive it can sort\" Sort","IcantbeliveitcansortSort",array);
//        sort("Insertion Sort","InsertionSort",array);
//        sort("Java","javaSort",array);
//        Libary Sort
//        sort("Merge Sort","MergeSort",array);
//        sort("Natural Merge Sort","NaturalMergeSort",array);
//        odd Even Sort
//        sort("Quick Sort","QuickSort",array);
//        sort("Selection Sort","SelectionSort",array);
//        sort("Shell Sort","ShellSort",array);
//        Smooth Sort
//        Strand Sort

        /*Nicht Vergleichbasierte Sortier Alorythmen*/
//        sort("Bucket Sort" ,"BucketSort",array);
//        sort("Counting Sort" ,"CountingSort",array);

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
            System.out.println("Platz " + String.format("%02d",i+1) + " mit " +  places.get(i).get(0) + " Sekunden" + " geht an " + places.get(i).get(1) + "." );
        }
    }
}