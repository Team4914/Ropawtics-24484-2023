package org.firstinspires.ftc.teamcode.Airagan.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class Testing2 extends OpMode {
    DcMotor motor;
    DcMotor motor1;
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "a");
        motor1 = hardwareMap.get(DcMotor.class, "b");

        telemetry.addData("Hardware: ", "Initialized");
    }

    @Override
    public void loop() {

        if(gamepad1.x) {
            motor1.setPower(1);
        } else if(gamepad1.x) {
            motor1.setPower(-1);
        }
        else {
            motor1.setPower(0);
        }

        if(gamepad1.a) {
            motor.setPower(1);
        } else if(gamepad1.b) {
            motor.setPower(-1);
        }
        else {
            motor.setPower(0);
        }

    }
}
