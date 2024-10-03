import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncuestaApp {
    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;

    // Preguntas de la encuesta
    private String[] preguntas = {
            "¿Cuál es tu nombre?",
            "¿Cuál es tu color favorito?",
            "¿Cuál es tu comida favorita?",
            "¿Cuál es tu hobby favorito?"
    };

    // Respuestas temporales
    private String[] respuestas;

    public EncuestaApp() {
        respuestas = new String[preguntas.length];
        frame = new JFrame("Encuesta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        // Añadir preguntas al panel
        for (int i = 0; i < preguntas.length; i++) {
            addPreguntaPanel(i);
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centra la ventana
    }

    private void addPreguntaPanel(int index) {
        JPanel preguntaPanel = new JPanel();
        preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));

        JLabel preguntaLabel = new JLabel(preguntas[index]);
        JTextField respuestaField = new JTextField(20);
        JButton siguienteButton = new JButton("Siguiente");

        preguntaPanel.add(preguntaLabel);
        preguntaPanel.add(respuestaField);
        preguntaPanel.add(siguienteButton);

        // Acción del botón "Siguiente"
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestas[index] = respuestaField.getText();
                if (index < preguntas.length - 1) {
                    cardLayout.show(panel, "Pregunta " + (index + 1));
                } else {
                    mostrarMensajeAgradecimiento();
                }
            }
        });

        panel.add(preguntaPanel, "Pregunta " + index);
    }

    private void mostrarMensajeAgradecimiento() {
        JPanel agradecimientoPanel = new JPanel();
        JLabel agradecimientoLabel = new JLabel("¡Gracias por completar la encuesta!");
        agradecimientoPanel.add(agradecimientoLabel);
        panel.add(agradecimientoPanel, "Agradecimiento");
        cardLayout.show(panel, "Agradecimiento");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncuestaApp());
    }
}