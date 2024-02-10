import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener.*;

public class tic {

    static Boolean flag = true;
    static JButton[] arr = new JButton[9];

    public static void main(String[] args) {
        JFrame f = new JFrame("Tic Tac Toe");
        f.setSize(500, 400);
        f.getContentPane().setBackground(new Color(0, 0, 255, 20));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel msg = new JLabel("Copyright Reserved @ arnav mehta");
        f.add(msg, BorderLayout.SOUTH);

        JTextField value = new JTextField(20);
        f.add(value, BorderLayout.NORTH);

        // Creating a JPanel for holding the Buttons
        GridLayout gl = new GridLayout(3, 3);
        JPanel p1 = new JPanel(gl);

        for (int i = 0; i < 9; i++) {
            JButton a = new JButton();
            a.setFont(new Font("Arial", 1, 60));
            a.setEnabled(true);
            arr[i] = a;
            p1.add(a);
        }

        f.add(p1);

        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                JButton b = (JButton) ae.getSource();
                if (flag) {
                    System.out.println("Turn of X");
                    b.setText("X");
                    flag = false;
                } else {
                    System.out.println("Turn of O");
                    b.setText("O");
                    flag = true;
                }
                b.setEnabled(false);
                checkWinner();
            }
        }

        MyListener ml = new MyListener();
        for (JButton jb : arr) {
            jb.addActionListener(ml);
        }

        f.setVisible(true);
    }

    static void checkWinner() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = arr[i].getText();
        }

        // Check rows, columns, and diagonals
        String[] lines = {
                board[0] + board[1] + board[2],
                board[3] + board[4] + board[5],
                board[6] + board[7] + board[8],
                board[0] + board[3] + board[6],
                board[1] + board[4] + board[7],
                board[2] + board[5] + board[8],
                board[0] + board[4] + board[8],
                board[2] + board[4] + board[6]
        };

        for (String line : lines) {
            if (line.equals("XXX")) {
                JOptionPane.showMessageDialog(null, "X wins!");
                resetGame();
            } else if (line.equals("OOO")) {
                JOptionPane.showMessageDialog(null, "O wins!");
                resetGame();
            }
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (board[i].isEmpty()) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        }
    }

    static void resetGame() {
        for (int i = 0; i < 9; i++) {
            arr[i].setText("");
            arr[i].setEnabled(true);
        }
        flag = true;
    }
}
