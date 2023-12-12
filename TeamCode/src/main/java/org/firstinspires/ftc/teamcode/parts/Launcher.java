package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {

    OpMode opMode;

    Servo launchServo;
    int launchPosition = 0;

    public Launcher(OpMode opMode) {
        this.opMode = opMode;

        launchServo = opMode.hardwareMap.get(Servo.class, "launchServo");
    }
    public void update() {
        if (opMode.gamepad1.a) {
            launchPosition = (launchPosition + 1) % 2;
            if (launchPosition == 0)
                launchServo.setPosition(0.4);
            else
                launchServo.setPosition(0.9);
        }
        opMode.telemetry.addData("LaunchPos", launchPosition);
    }
}
