package MemoryGame;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    /**
     * The width of the window of this game.
     */
    private int width;
    /**
     * The height of the window of this game.
     */
    private int height;
    /**
     * The current round the user is on.
     */
    private int round;
    /**
     * The Random object used to randomly generate Strings.
     */
    private Random rand;
    /**
     * Whether or not the game is over.
     */
    private boolean gameOver;
    /**
     * Whether or not it is the player's turn. Used in the last section of the
     * spec, 'Helpful UI'.
     */
    private boolean playerTurn;
    /**
     * The characters we generate random Strings from.
     */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /**
     * Encouraging phrases. Used in the last section of the spec, 'Helpful UI'.
     */
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String result = "";
        for (int i = 0; i < n; i++) {
            result = result + CHARACTERS[this.rand.nextInt(26)];
        }
        return result;
    }

    public void drawFrame(String s) {
        /* Take the input string S and display it at the center of the screen,
         * with the pen settings given below. */
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, this.height / 2, s);

        //TODO: If the game is not over, display encouragement, and let the user know if they
        // should be typing their answer or watching for the next round.

        if (!gameOver) {
            Font fontsmall = new Font("Monaco", Font.BOLD, 15);
            StdDraw.setFont(fontsmall);
            StdDraw.line(0, height - 2, width, height - 2);
            StdDraw.textLeft(0, height - 1, "Round: " + this.round);
            if (playerTurn) {
                StdDraw.text(width/2, height - 1, "Type!");
            } else {
                StdDraw.text(width / 2, height - 1, "Watch!");
            }
            String encouragement = ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)];
            StdDraw.textRight(width, height - 1, encouragement);
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(Character.toString(letters.charAt(i)));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String result = "";
        while (n > 0) {
            if (StdDraw.hasNextKeyTyped()) {
                result = result + StdDraw.nextKeyTyped();
                drawFrame(result);
                n -= 1;
            }
        }
//        for (int i = 0; i < n; i++) {
//            if (StdDraw.hasNextKeyTyped()) {
//                result = result + StdDraw.nextKeyTyped();
//            }
//        }
        return result;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        this.gameOver = false;
        this.round = 1;
        //TODO: Establish Engine loop
        while (!gameOver) {
            playerTurn = false;
            StdDraw.pause(1000);
            drawFrame("Round: " + this.round);
            StdDraw.pause(1000);
            String random = generateRandomString(this.round);
//            System.out.println(random);
            flashSequence(random);
            playerTurn = true;
            drawFrame("");
            String user = solicitNCharsInput(this.round);
            if (user.equals(random)) {
                this.round += 1;
            } else {
                this.gameOver = true;
                break;
            }
        }
        this.drawFrame("Game Over! You made it to round: " + this.round);
        }
    }

