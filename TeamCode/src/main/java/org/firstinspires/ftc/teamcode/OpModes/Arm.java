package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    final static double CLAW_OPEN = 0.8;
    final static double CLAW_CLOSED = 0.2;

    OpMode opMode;

    DcMotor elbowMotor;
    Servo wristServo;
    Servo clawServo;

    public Arm(OpMode opMode) {
        this.opMode = opMode;

        elbowMotor = opMode.hardwareMap.get(DcMotor.class, "a");
        clawServo = opMode.hardwareMap.get(Servo.class, "b");

        opMode.telemetry.addData("Arm: ", "Initialized");
    }

    public void update() {
        // rotate whole arm (elbow joint motor)
        if (opMode.gamepad1.a) { // a and b buttons for now
            elbowMotor.setPower(0.5);
        } else if (opMode.gamepad1.b) {
            elbowMotor.setPower(-0.5);
        } else {
            elbowMotor.setPower(0);
        }

        // open and close claw; x and y buttons for now
        if(opMode.gamepad1.y) {
            // move to 0 degrees.
            opMode.telemetry.addData("Key", "y");
            clawServo.setPosition(CLAW_OPEN);
        } else if (opMode.gamepad1.x) {
            // move to 90 degrees.
            opMode.telemetry.addData("Key", "x");
            clawServo.setPosition(CLAW_CLOSED);
        }
    }
}
