import java.util.Scanner;

public class Strassen {

    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
        } else {
            // Divide step
            int[][] A11 = divideMatrix(A, 0, 0, n / 2);
            int[][] A12 = divideMatrix(A, 0, n / 2, n / 2);
            int[][] A21 = divideMatrix(A, n / 2, 0, n / 2);
            int[][] A22 = divideMatrix(A, n / 2, n / 2, n / 2);

            int[][] B11 = divideMatrix(B, 0, 0, n / 2);
            int[][] B12 = divideMatrix(B, 0, n / 2, n / 2);
            int[][] B21 = divideMatrix(B, n / 2, 0, n / 2);
            int[][] B22 = divideMatrix(B, n / 2, n / 2, n / 2);

            // Conquer step
            int[][] M1 = multiply(add(A11, A22), add(B11, B22));
            int[][] M2 = multiply(add(A21, A22), B11);
            int[][] M3 = multiply(A11, subtract(B12, B22));
            int[][] M4 = multiply(A22, subtract(B21, B11));
            int[][] M5 = multiply(add(A11, A12), B22);
            int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
            int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

            // Combine step
            int[][] C11 = add(subtract(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(subtract(add(M1, M3), M2), M6);

            // Populate result matrix
            joinMatrices(result, C11, 0, 0);
            joinMatrices(result, C12, 0, n / 2);
            joinMatrices(result, C21, n / 2, 0);
            joinMatrices(result, C22, n / 2, n / 2);
        }
        return result;
    }

    private static int[][] divideMatrix(int[][] matrix, int row, int col, int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrix[row + i][col + j];
            }
        }
        return result;
    }

    private static void joinMatrices(int[][] C, int[][] subMatrix, int row, int col) {
        int size = subMatrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[row + i][col + j] = subMatrix[i][j];
            }
        }
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of square matrices (n x n):");
        int n = scanner.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter the elements of the first matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the elements of the second matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        int[][] result = multiply(A, B);

        // Print result matrix
        System.out.println("Resultant Matrix:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}