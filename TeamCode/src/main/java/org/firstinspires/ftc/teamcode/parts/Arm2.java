package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm2 {
    //final static double WRIST_OPEN = 0.8;
    //final static double WRIST_CLOSED = 0.2;
    final static double WRIST_SPEED = 0.005;
    int armPosition = -1;
    int clawPosition = 0;

    OpMode opMode;

    DcMotor elbowMotorLeft, elbowMotorRight;
    Servo wristServo;
    Servo clawServo;


    public Arm2(OpMode opMode) {
        this.opMode = opMode;

        elbowMotorLeft = opMode.hardwareMap.get(DcMotor.class, "elbowLeft");
        elbowMotorRight = opMode.hardwareMap.get(DcMotor.class, "elbowRight");

        wristServo = opMode.hardwareMap.get(Servo.class, "wrist");
        clawServo = opMode.hardwareMap.get(Servo.class, "claw");

        elbowMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        opMode.telemetry.addData("Arm: ", "Initialized");
    }

    // To be called at the start of the game to put the arm in game position
    public void initialize() throws InterruptedException {
        final int ELBOW_TIME = 3001;
        final double WRIST_DOWN = 1;
        final double CLAW_DOWN = 0.2;

        elbowMotorLeft.setPower(-1);
        elbowMotorRight.setPower(-1);
        Thread.sleep(ELBOW_TIME);
        wristServo.setPosition(WRIST_DOWN);
    }

    /* To be called to reset the bot. I think we might have to have the claw open and the arm
    * touching the ground in a position that can be easily reached, and then we call reset. */
    public void reset() throws InterruptedException{
        final int ELBOW_TIME = 3000;
        final double WRIST_UP = 0;
        final double CLAW_UP = 0.8;

        elbowMotorLeft.setPower(1);
        elbowMotorRight.setPower(1);
        Thread.sleep(ELBOW_TIME);
        wristServo.setPosition(WRIST_UP);
    }

    // Function for updating the wrist and elbow. Cycles between 2 positions
    public void updateArm() throws InterruptedException {
        // I think for the time being we might have to use hardcoding.
        final double WRIST_DOWN = 1;
        final double WRIST_UP = 0;
        // Needs to be tested later, time it takes to move elbow up and down.
        final int ELBOW_TIME = 2000;

        if (opMode.gamepad1.b) {
            armPosition = (armPosition + 1) % 2;

            // Updating Position
            // Moving Arm downwards
            if (armPosition == 0) {
                elbowMotorLeft.setPower(-1);
                elbowMotorRight.setPower(-1);
                Thread.sleep(ELBOW_TIME);
                wristServo.setPosition(WRIST_DOWN);
            }
            // Moving Arm Upwards
            else {
                elbowMotorLeft.setPower(1);
                elbowMotorRight.setPower(1);
                Thread.sleep(ELBOW_TIME);
                wristServo.setPosition(WRIST_UP);
            }
        }
    }

    // Function for updating the claw. Cycles through 3 different clawPositions
    public void updateClaw() {
        final double CLAW_OPEN = 0.2;
        final double CLAW_TRAP = 0.4;
        final double CLAW_CLOSE = 0.8;

        if (opMode.gamepad1.a) {
            clawPosition = (clawPosition + 1) % 3;

            if (clawPosition == 0) {
                clawServo.setPosition(CLAW_OPEN);
                opMode.telemetry.addData("Claw Position", "OPEN");
            }
            else if (clawPosition == 1) {
                clawServo.setPosition(CLAW_TRAP);
                opMode.telemetry.addData("Claw Position", "TRAP");
            }
            else {
                clawServo.setPosition(CLAW_CLOSE);
                opMode.telemetry.addData("Claw Position", "CLOSE");
            }
        }
    }
    public void update() throws InterruptedException {
        updateArm();
        updateClaw();
    }
}
