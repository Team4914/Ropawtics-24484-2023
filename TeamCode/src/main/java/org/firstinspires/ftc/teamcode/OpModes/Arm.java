package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    DcMotor elbowMotor;
    Servo wristServo;
    Servo clawServo;

    OpMode opMode;
    public Arm(OpMode opMode) {
        this.opMode = opMode;

        elbowMotor = opMode.hardwareMap.get(DcMotor.class, "a");
        clawServo = opMode.hardwareMap.get(Servo.class, "b");
    }

    public void update() {
        if (opMode.gamepad1.left_stick_y > 0) {
            elbowMotor.setPower(0.5);
        } else if (opMode.gamepad1.left_stick_y < 0) {
            elbowMotor.setPower(-0.5);
        } else {
            elbowMotor.setPower(0);
        }

        if(opMode.gamepad1.y) {
            // move to 0 degrees.
            opMode.telemetry.addData("Key", "y");
            clawServo.setPosition(0);
        } else if (opMode.gamepad1.x || opMode.gamepad1.b) {
            // move to 90 degrees.
            opMode.telemetry.addData("Key", "x");
            clawServo.setPosition(0.5);
        } else if (opMode.gamepad1.a) {
            // move to 180 degrees.
            opMode.telemetry.addData("Key", "a");
            clawServo.setPosition(1);
        }
    }
}
