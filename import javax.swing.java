import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomGame extends Jframe {
    private int targetNumber;
    private int attempts;
    private JTextField guessField;
    private JLabel resultLabel;
    private JButton submitButton;
    private JButton restartButton;

    public ZoomGame() {
        setTitle("Zoom Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        targetNumber = generateRandomNumber();
        attempts = 0;

        JLabel titleLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel();
        restartButton = new JButton("Restart");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        add(titleLabel);
        add(guessField);
        add(submitButton);
        add(resultLabel);
        add(restartButton);

        setVisible(true);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == targetNumber) {
                resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                submitButton.setEnabled(false);
            } else if (guess < targetNumber) {
                resultLabel.setText("Too low! Try again.");
            } else {
                resultLabel.setText("Too high! Try again.");
            }
        } catch (NumberFormatException e) {
        resultLabel.setText("Invalid input! Please enter a valid number.");
        }

        guessField.setText("");
    }

    private void restartGame() {
        targetNumber = generateRandomNumber();
        attempts = 0;
        resultLabel.setText("");
        submitButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZoomGame();
            }
        });
    }
}
