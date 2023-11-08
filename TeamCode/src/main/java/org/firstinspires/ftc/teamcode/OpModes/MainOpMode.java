package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MainOpMode extends OpMode {
    MecanumDrive mecanumDrive;
    Arm arm;

    @Override
    public void init() {
        mecanumDrive = new MecanumDrive(this);
        arm = new Arm(this);

        telemetry.addData("Hardware: ", "Initialized");
    }

    @Override
    public void loop() {
        mecanumDrive.update();
        arm.update();
    }
}