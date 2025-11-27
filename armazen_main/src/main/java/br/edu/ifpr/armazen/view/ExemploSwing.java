package br.edu.ifpr.armazen.view;

import javax.swing.*;  // Importa as classes do Swing
import java.awt.event.*; // Importa para tratar eventos

public class ExemploSwing {
    public static void main(String[] args) {
        // Cria a janela principal (JFrame)
        JFrame frame = new JFrame("Exemplo Swing");
        
        // Cria um botão
        JButton button = new JButton("Clique em mim!");
        
        // Cria um painel e adiciona o botão a ele
        JPanel panel = new JPanel();
        panel.add(button);
        
        // Define o comportamento do fechamento da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adiciona o painel à janela
        frame.add(panel);
        
        // Ajusta o tamanho da janela
        frame.setSize(300, 200);
        
        // Exibe a janela
        frame.setVisible(true);
        
        // Adiciona um listener ao botão
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra uma mensagem quando o botão for clicado
                JOptionPane.showMessageDialog(frame, "Você clicou no botão!");
            }
        });
    }
}
