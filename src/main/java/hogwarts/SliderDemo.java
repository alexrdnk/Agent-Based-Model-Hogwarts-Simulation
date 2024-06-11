package hogwarts;

import javax.swing.*;
import java.awt.*;

public class SliderDemo extends JPanel {
    private final JSlider slider;

    public SliderDemo(String labelText, int min, int max, int value) {
        // Initialize the label and slider
        JLabel label = new JLabel(labelText);
        slider = new JSlider(min, max, value);

        // Set major tick spacing, ticks, and labels visibility based on the range
        if (max > 10) {
            slider.setMajorTickSpacing(10);
        } else {
            slider.setMajorTickSpacing(1);
        }
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
    }

    // Getter for the slider if needed elsewhere
    public JSlider getSlider() {
        return slider;
    }
}
