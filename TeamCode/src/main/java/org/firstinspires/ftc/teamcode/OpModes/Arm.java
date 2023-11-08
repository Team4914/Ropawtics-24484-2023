package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class
Arm {
    final static double WRIST_OPEN = 0.8;
    final static double WRIST_CLOSED = 0.2;
    final static double CLAW_OPEN = 0.8;
    final static double CLAW_CLOSED = 0.2;

    OpMode opMode;

    DcMotor elbowMotorLeft, elbowMotorRight;
    Servo wristServo;
    Servo clawServo;

    public Arm(OpMode opMode) {
        this.opMode = opMode;

        elbowMotorLeft = opMode.hardwareMap.get(DcMotor.class, "elbowLeft");
        elbowMotorRight = opMode.hardwareMap.get(DcMotor.class, "elbowRight");

        wristServo = opMode.hardwareMap.get(Servo.class, "wrist");
        clawServo = opMode.hardwareMap.get(Servo.class, "claw");

        elbowMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        opMode.telemetry.addData("Arm: ", "Initialized");
    }

    public void update() {
        // rotate whole arm (elbow joint motor)
        elbowMotorLeft.setPower(opMode.gamepad1.left_stick_y);
        elbowMotorRight.setPower(opMode.gamepad1.left_stick_y);

        // Wrist
        if(opMode.gamepad1.dpad_up) {
            // move to 0 degrees.
            clawServo.setPosition(WRIST_OPEN);
        } else if (opMode.gamepad1.dpad_down) {
            // move to 90 degrees.
            clawServo.setPosition(WRIST_CLOSED);
        }

        // Claw
        if (opMode.gamepad1.left_trigger > 0) {
            wristServo.setPosition(CLAW_CLOSED);
        }
        else if (opMode.gamepad1.right_trigger > 0) {
            wristServo.setPosition(CLAW_OPEN);
        }
    }
}
