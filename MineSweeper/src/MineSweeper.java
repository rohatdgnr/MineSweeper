import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    // Değerlendirme Formu #1  = > Değişkenler ve Fonksiyonlar
    // Evaluation Form #1 = > Variables and Functions"
    String [][] board;
    String [][] mineLocations;
    int rows;
    int cols;
    int remainingTiles;

    // Oyun Tahtası ve Mayınlar Bu Metot ile Yerleştiriliyor
    // Game Board and Mines are Placed with This Method
    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new String[rows][cols];
        remainingTiles = rows * cols;
        firstBoard();
        placeMines();
    }
    // Oyun Tahtasına "-" Karakterleri ile Yazdırılıyor
    // Characters "-" Printed on the Game Board
    public  void firstBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = "-";
            }
        }
    }
    // Mayınlar Rastgele Yerleştiriliyor
    // Mines Are Placed Randomly
    public  void placeMines() {
        Random rand = new Random();
        mineLocations = new String[rows][cols];
        int numMines = rows * cols / 4;
        while (numMines > 0) {
            int randRow = rand.nextInt(rows);
            int randCol = rand.nextInt(cols);
            if (mineLocations[randRow][randCol] != "*") {
                mineLocations[randRow][randCol] = "*";
                numMines--;

            }
        }
    }
    // Oyun Tahtasının Çıktısı Ekrana Yazdırılıyor
    // Print the Output of the Game Board to the Screen
    public void playBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    // Mayınları Rasgele Dağıtılmış Halini Konsola Bastırır
    // Prints Randomly Distributed Mines to the Console
    public void printMineLocations() {
        System.out.println("Mine Locations ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineLocations[i][j] == "*") {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("==============================================");
        System.out.println("Welcome to Minesweeper Game ! ");


    }
    //  Değerlendirme Formu # 6 Oyuncunun Oyun Durumunu Konrol Etme Alanı
    //  "The Evaluation Form #6: Player's Game State Checking Area"
    public void play() {
        Scanner scanner = new Scanner(System.in);
        printMineLocations(); // Mayınların rastgele yerleştirilmiş hâlini göster
        playBoard(); // Oyun tahtasını başlangıçta göster

        while (remainingTiles > 1) {

            System.out.print("Enter Row and Column (e.g., 1 1 for the first row and column): ");

            int row = scanner.nextInt()  -1;// Kullanıcının girdiği satır değerini indekse dönüştür
            int col = scanner.nextInt() - 1; // Kullanıcının girdiği sütun değerini indekse dönüştür
            System.out.println("==============================================");
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("Invalid coordinates! Please try again.");
                continue;
            }

            if (board[row][col] != "-") {
                System.out.println("This coordinate has already been selected. Please choose another one.");
                continue;
            }

            if (mineLocations[row][col] == "*") {
                System.out.println("Game over! You hit a mine!");
                return;
            }


            MineControl(row, col);
            playBoard(); // Oyun tahtasını güncel duruma göre yeniden göster
            // Kalan kutu sayısını kontrol et




        }
    }



    //Seçilen Kareyi Açar ve Eğer Seçilen Kare Etrafında Mayın Yoksa Ekrana 0 (SIFIR) Yazdırır
    //It Opens the Selected Square and Prints 0 (ZERO) On the PlayBoard if There Are No Mines Around the Selected Square
    public  void MineControl(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] != "-") {
            return;
        }

        int mines = nearbyMines(row, col);
        if (mines == 0) {
            board[row][col] = "0";
            remainingTiles--;

        } else {
            board[row][col] = Integer.toString(mines);
            remainingTiles--;
        }
    }

    // Girilen Koordinatın Çevresi Kontrol Eder ve Ne Kadar Mayın ile Temas Ediyorsa Onu Ekrana Yazdırır
    //It Checks the Surroundings of The Entered Coordinate and Prints One the Screen Howmany Mines it Comes İnto Contact With
    public int nearbyMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i < rows && col + j >= 0 && col + j < cols && mineLocations[row + i][col + j] == "*") {
                    count++;

                }
            }
        }
        return count;
    }

}

