package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax; // Updated import paths
import com.revrobotics.spark.SparkLowLevel.MotorType; // Updated import paths
import com.revrobotics.AbsoluteEncoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class arm extends TimedRobot {
    private final SparkMax leftMotor = new SparkMax(9, MotorType.kBrushless); // Updated class name
    private final SparkMax rightMotor = new SparkMax(10, MotorType.kBrushless); // Updated class name
    private final XboxController controller = new XboxController(0);
    private final AbsoluteEncoder encoder = leftMotor.getAbsoluteEncoder(); // Initialize the absolute encoder

    // Define the target angles in degrees
    private static final double TARGET_ANGLE_1 = 45.0;
    private static final double TARGET_ANGLE_2 = 90.0;

    @Override
    public void teleopPeriodic() {
        double currentAngle = encoder.getPosition(); // Get the current position in degrees

        if (controller.getXButton()) { // X button
            if (currentAngle < TARGET_ANGLE_1) {
                leftMotor.set(1.0); // Forward
                rightMotor.set(1.0); // Forward
            } else {
                leftMotor.set(0.0); // Stop
                rightMotor.set(0.0); // Stop
            }
        } else if (controller.getBButton()) { // B button
            if (currentAngle > -TARGET_ANGLE_2) {
                leftMotor.set(-1.0); // Backward
                rightMotor.set(-1.0); // Backward
            } else {
                leftMotor.set(0.0); // Stop
                rightMotor.set(0.0); // Stop
            }
        } else {
            leftMotor.set(0.0); // Stop
            rightMotor.set(0.0); // Stop
        }
    }
}
