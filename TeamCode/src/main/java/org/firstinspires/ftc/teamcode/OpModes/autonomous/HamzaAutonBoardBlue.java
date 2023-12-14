package org.firstinspires.ftc.teamcode.OpModes.autonomous;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.parts.ObjectDetector;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class HamzaAutonBoardBlue extends LinearOpMode{
    DcMotor RFMotor;
    DcMotor LFMotor;
    DcMotor RBMotor;
    DcMotor LBMotor;

    final static int TICKS_PER_ROTATION = 1440;
    final static int TIRE_DIAMETER_INCHES = 4;

    static String PROP_POSITION;
    boolean aprilTagFound = false;
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag;
    private VisionPortal builder;
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Testing", "Success");

        RFMotor = hardwareMap.get(DcMotor.class, "right_front_drive");
        LFMotor = hardwareMap.get(DcMotor.class, "left_front_drive");
        RBMotor = hardwareMap.get(DcMotor.class, "right_back_drive");
        LBMotor = hardwareMap.get(DcMotor.class, "left_back_drive");

        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Auto: ", "Initialized");

        waitForStart();
        initAprilTag();
        RFMotor.setPower(1);
        RBMotor.setPower(-1);
        LFMotor.setPower(-1);
        LBMotor.setPower(1);
        Thread.sleep(1500);
        resetMotors();
    }

    void resetMotors() {
        RFMotor.setPower(0);
        RBMotor.setPower(0);
        LFMotor.setPower(0);
        LBMotor.setPower(0);
    }

    private static void getPropPosition(){
        if(ObjectDetector.getPropPosition() != null)
            PROP_POSITION = ObjectDetector.getPropPosition();
    }
    private void initAprilTag() {

        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                .setDrawAxes(false)
                .setDrawCubeProjection(false)
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics()
                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Adjust Image Decimation to trade-off detection-range for detection-rate.
        // eg: Some typical detection data using a Logitech C920 WebCam
        // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
        // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
        // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
        // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
        // Note: Decimation can be changed on-the-fly to adapt during a match.
        aprilTag.setDecimation(3);

        // Create the vision portal by using a builder.
        builder = new VisionPortal.Builder()
                .addProcessor(aprilTag)
                .setCamera(hardwareMap.get(WebcamName.class, "Zain"))
                .setCameraResolution(new Size(640,480))
                .enableLiveView(true)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .setAutoStopLiveView(false)
                .build();

        builder.setProcessorEnabled(aprilTag, true);

    }   // end method initAprilTag()

    private void decideAprilTagStop(){
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                int aprilTag = detection.id;
                if((aprilTag == 1 && PROP_POSITION.equals("LEFT")) || (aprilTag == 2 && PROP_POSITION.equals("CENTER")) || (aprilTag == 3 && PROP_POSITION.equals("RIGHT")))
                    aprilTagFound = true;
            }
        }
    }
}
