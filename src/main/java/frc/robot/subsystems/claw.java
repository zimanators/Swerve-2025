package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class claw extends SubsystemBase {
    // Declare two Spark Max motors for the left and right drive motors
    private final SparkMax leftMotor;
    private final SparkMax rightMotor;

    // Declare the Xbox controller
    private final XboxController controller;

    public arm() {
        // Initialize motors (replace with your actual CAN IDs)
        leftMotor = new CANSparkMax(1, MotorType.kBrushless);  // Left motor on CAN ID 1
        rightMotor = new CANSparkMax(2, MotorType.kBrushless); // Right motor on CAN ID 2

        // Initialize the Xbox controller (port 0 is usually the default)
        controller = new XboxController(0);
    }

    @Override
    public void periodic() {
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
