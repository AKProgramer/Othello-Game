package reversi;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    Color borderColor;
    Color circleColor;
   public CustomButton(Color borderColor, Color circleColor) {
       this.borderColor=borderColor;
       this.circleColor=circleColor;
       setContentAreaFilled(false); // Make button background transparent
       setPreferredSize(new Dimension(100, 100)); // Set preferred size
   }

   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);

       // Draw green background
       g.setColor(Color.GREEN);
       g.fillRect(0, 0, getWidth(), getHeight());

       // Draw white circle with black border
       int circleDiameter = Math.min(getWidth(), getHeight()) - 10; // Diameter of circle
       int x = (getWidth() - circleDiameter) / 2;
       int y = (getHeight() - circleDiameter) / 2;

       // Draw black border
       g.setColor(borderColor);
       g.drawOval(x, y, circleDiameter, circleDiameter);

       // Draw white circle
       g.setColor(circleColor);
       g.fillOval(x, y, circleDiameter, circleDiameter);
   }
}