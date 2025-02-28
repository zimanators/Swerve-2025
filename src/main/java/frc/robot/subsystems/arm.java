package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder; // Import the encoder library
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;

public class arm extends TimedRobot {
    private final CANSparkMax leftMotor = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax rightMotor = new CANSparkMax(2, MotorType.kBrushless);
    private final XboxController controller = new XboxController(0);
    private final AbsoluteEncoder encoder = leftMotor.getAbsoluteEncoder(); // Initialize the absolute encoder

    // Define the target angles in degrees
    private static final double TARGET_ANGLE_1 = 45.0;
    private static final double TARGET_ANGLE_2 = 90.0;

    @Override
    public void teleopPeriodic() {
        double currentAngle = encoder.getPosition(); // Get the current position in degrees

        if (controller.getPOV() == 0) { // D-pad up
            if (currentAngle < TARGET_ANGLE_1) {
                leftMotor.set(1.0); // Forward
                rightMotor.set(1.0); // Forward
            } else {
                leftMotor.set(0.0); // Stop
                rightMotor.set(0.0); // Stop
            }
        } else if (controller.getPOV() == 180) { // D-pad down
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