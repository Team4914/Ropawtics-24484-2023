package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumDriveTest extends OpMode {
    DcMotor RFMotor;
    DcMotor LFMotor;
    DcMotor RBMotor;
    DcMotor LBMotor;

    public void moveDriveTrain() {
        double vertical = 0; // Front-back motion
        double horizontal = 0; // Left-right motion
        double pivot = 0; // Turn angle

        /*Depending on what motors are flipped, vertical must be negative or positive.
        * Test with robot, and change to positive if necessary*/
        vertical = -gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        // Diagonally Alternate Motors must be the same
        /* If robot does not work properly, set the vertical values for RB and LF to positive*/
        RFMotor.setPower(-pivot + (vertical - horizontal));
        RBMotor.setPower(-pivot + (vertical + horizontal));
        LFMotor.setPower(pivot + (vertical + horizontal));
        LBMotor.setPower(pivot + (vertical - horizontal));
    }

    @Override
    public void init() {
        RFMotor = hardwareMap.get(DcMotor.class, "right_front_drive");
        LFMotor = hardwareMap.get(DcMotor.class, "left_front_drive");
        RBMotor = hardwareMap.get(DcMotor.class, "right_back_drive");
        LBMotor = hardwareMap.get(DcMotor.class, "left_back_drive");
        telemetry.addData("Hardware: ", "Initialized");

        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        moveDriveTrain();
    }
}