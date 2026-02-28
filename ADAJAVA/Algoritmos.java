import java.util.Arrays;
import java.util.Random;

public class Algoritmos {
    public static int[] generarArreglo(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 2);
        return arr;
    }

    public static int[] ordenamiento_burbuja(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] ord_mezcla(int[] arr) {
        if (arr.length <= 1) return arr;
        int mitad = arr.length / 2;
        int[] izq = ord_mezcla(Arrays.copyOfRange(arr, 0, mitad));
        int[] der = ord_mezcla(Arrays.copyOfRange(arr, mitad, arr.length));
        return mezclar(izq, der);
    }

    private static int[] mezclar(int[] izq, int[] der) {
        int[] res = new int[izq.length + der.length];
        int i = 0, j = 0, k = 0;
        while (i < izq.length && j < der.length) {
            if (izq[i] < der[j]) res[k++] = izq[i++];
            else res[k++] = der[j++];
        }
        while (i < izq.length) res[k++] = izq[i++];
        while (j < der.length) res[k++] = der[j++];
        return res;
    }

    public static int[] ord_quick(int[] arr) {
        int[] copia = Arrays.copyOf(arr, arr.length);
        quick(copia, 0, copia.length - 1);
        return copia;
    }

    private static void quick(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int p = particion(arr, inicio, fin);
            quick(arr, inicio, p - 1);
            quick(arr, p + 1, fin);
        }
    }

    private static int particion(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (arr[j] < pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;
        return i + 1;
    }

    public static int busqueda_binaria(int[] arr, int obj) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            if (arr[medio] == obj) return medio;
            if (arr[medio] < obj) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }

    public static int busqueda_jump(int[] arr, int obj) {
        int n = arr.length;
        int salto = (int) Math.sqrt(n);
        int prev = 0;
        while (arr[Math.min(salto, n) - 1] < obj) {
            prev = salto;
            salto += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }
        while (arr[prev] < obj) {
            prev++;
            if (prev == Math.min(salto, n)) return -1;
        }
        return arr[prev] == obj ? prev : -1;
    }

    public static int buscar_lineal(int[] arr, int obj) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == obj) return i;
        return -1;
    }
}