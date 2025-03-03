package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax; // Updated import paths
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType; // Updated import paths
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class claw extends TimedRobot {

    private final SparkMax leftMotor = new SparkMax(11, MotorType.kBrushless); // Updated class name
    private final SparkMax rightMotor = new SparkMax(12, MotorType.kBrushless); // Updated class name

    // Declare the Xbox controller
    private final XboxController controller;

    // Initialize the Xbox controller (port 0 is usually the default)
    public claw() {
        controller = new XboxController(0);

        // Configuring the motors
        SparkMaxConfig config = new SparkMaxConfig();
        config.inverted(false);
        config.idleMode(IdleMode.kBrake);
    }

    @Override
    public void teleopPeriodic() {
        // Get the trigger values (range from 0.0 to 1.0)
        double leftTrigger = controller.getLeftTriggerAxis();
        double rightTrigger = controller.getRightTriggerAxis();

        // Set the motor speeds based on the trigger inputs
        if (leftTrigger > 0.1) {  // If the left trigger is pressed (value > 0)
            leftMotor.set(leftTrigger);  // Drive forward with speed proportional to the trigger
            rightMotor.set(leftTrigger); // Drive forward with the same speed
        } else if (rightTrigger > 0.1) {  // If the right trigger is pressed (value > 0)
            leftMotor.set(-rightTrigger);  // Drive backward with speed proportional to the trigger
            rightMotor.set(-rightTrigger); // Drive backward with the same speed
        } else {
            // If no trigger is pressed, stop the motors
            leftMotor.set(0);
            rightMotor.set(0);
        }
    }
}
    
