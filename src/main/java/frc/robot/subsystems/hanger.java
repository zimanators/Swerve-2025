package frc.robot.subsystems;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.XboxController;

public class hanger extends TimedRobot {
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);
    private final XboxController controller = new XboxController(0);

    @Override
    public void teleopPeriodic() {
        if (controller.getYButton()) { // Y button
            leftMotor.set(1.0); // Forward
            rightMotor.set(1.0); // Forward
        } else if (controller.getAButton()) { // A button
            leftMotor.set(-1.0); // Backward
            rightMotor.set(-1.0); // Backward
        } else {
            leftMotor.set(0.0); // Stop
            rightMotor.set(0.0); // Stop
        }
    }
}
    