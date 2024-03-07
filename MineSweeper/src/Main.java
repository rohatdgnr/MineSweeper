import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Değerlendirme Formu # 7 Oyuncudan Dizi Boyutu (Matris) Alma Alanı
        // The Evaluation Form #7: Player's Array Size (Matrix) Acquisition Area
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter Number of Rows : ");
            row = scanner.nextInt();
            System.out.print("Please Enter Number of Columns : ");
            col = scanner.nextInt();
            if (row < 2 || col < 2) {
                System.out.println("Please enter a value greater than 2!");
            } else {
                break;
            }
        }
        MineSweeper game = new MineSweeper(row, col);
        game.play();
    }
}