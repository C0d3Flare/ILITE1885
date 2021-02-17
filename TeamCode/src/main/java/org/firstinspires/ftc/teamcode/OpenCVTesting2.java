package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
@Autonomous(name="OpenCVTesting2", group="Test")
public class OpenCVTesting2 extends LinearOpMode {
    /**
     * Declare variables here
     */
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor ramp;
    Servo arm;
    EasyOpenCVExample.SkystoneDeterminationPipeline pipeline;
    OpenCvWebcam webcam;
    int encoderRev = 1120;
    int sectionSelect = 0;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        /**
         * Init variables here
         */
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        ramp = hardwareMap.dcMotor.get("ramp");
        arm = hardwareMap.servo.get("arm");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        /**
         int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
         phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new EasyOpenCVExample.SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);



        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320,240, OpenCvCameraRotation.UPSIDE_DOWN);
            }
        });
        while(!isStarted() && !isStopRequested())
        {
            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.addData("Position", pipeline.position);
            telemetry.update();
            arm.setPosition(0.8);

            if(gamepad1.a){
                sectionSelect = 1;
            }
            else if(gamepad1.b){
                sectionSelect = 2;
            }
            else if(gamepad1.x){
                sectionSelect = 3;
            }
        }

        waitForStart();

        /**
         * Place all robot movement code in here.
         */

        // 4 rings
        if (pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR)
        {
            //strafeRight(1.0);
        }
        // One ring
        else if(pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.ONE)
        {
            // code code code
        }
        // No rings
        else
        {
            // code code code
        }

        // All other autonomous code goes down here


        //section a
        /**
         ramp.setPower(.3);
         encoderStrafeRight(1.0, (int)(encoderRev*1));
         encoderDriveForward(1.0, (int)(encoderRev*4.7));
         ramp.setPower(-0.1);
         arm.setPosition(0.25);
         ramp.setPower(0);
         */

        //section b
        /**
        ramp.setPower(.3);
        encoderStrafeRight(1.0, (int)(encoderRev*1));
        encoderDriveForward(1.0, (int)(encoderRev*5.3));
        encoderStrafeLeft(1.0, (int)(encoderRev*1.5));
        encoderDriveForward(0.5, (int)(encoderRev*3));

        //section c
        /**
         ramp.setPower(.3);
         encoderStrafeRight(1.0, (int)(encoderRev*1));
         encoderDriveForward(1.0, (int)(encoderRev*6.7));
         ramp.setPower(-0.1);
         arm.setPosition(0.25);
         ramp.setPower(0);
         */



        if(sectionSelect == 1){
            ramp.setPower(.3);
            encoderStrafeRight(1.0, (int)(encoderRev*1));
            encoderDriveForward(1.0, (int)(encoderRev*4.7));
            encoderTurnRight(0.75, (int)(encoderRev*0.4));
            encoderDriveForward(0.5, (int)(encoderRev*0.4));
        }

        else if(sectionSelect == 2)
        {
            ramp.setPower(.3);
            encoderStrafeRight(1.0, (int)(encoderRev*1));
            encoderDriveForward(1.0, (int)(encoderRev*5.3));
            encoderStrafeLeft(1.0, (int)(encoderRev*1.5));
            encoderDriveForward(0.5, (int)(encoderRev*1.7));
        }

        else if(sectionSelect == 3)
        {
            ramp.setPower(.3);
            encoderStrafeRight(1.0, (int)(encoderRev*1));
            encoderDriveForward(1.0, (int)(encoderRev*8.7));
            ramp.setPower(-0.1);
            arm.setPosition(0.25);
            ramp.setPower(0);
        }
        ramp.setPower(-0.5);
        arm.setPosition(0.25);
        ramp.setPower(0);


    }
    public void encoderDriveForward(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(encoderAmount);
        rightFront.setTargetPosition(encoderAmount);
        leftBack.setTargetPosition(encoderAmount);
        rightBack.setTargetPosition(encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveForward(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderTurnLeft(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(encoderAmount);
        rightFront.setTargetPosition(-encoderAmount);
        leftBack.setTargetPosition(encoderAmount);
        rightBack.setTargetPosition(-encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turnLeft(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderTurnRight(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-encoderAmount);
        rightFront.setTargetPosition(encoderAmount);
        leftBack.setTargetPosition(-encoderAmount);
        rightBack.setTargetPosition(encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turnRight(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderDriveBackward(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-encoderAmount);
        rightFront.setTargetPosition(-encoderAmount);
        leftBack.setTargetPosition(-encoderAmount);
        rightBack.setTargetPosition(-encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveBackward(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderStrafeRight(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(encoderAmount);
        rightFront.setTargetPosition(-encoderAmount);
        leftBack.setTargetPosition(-encoderAmount);
        rightBack.setTargetPosition(encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafeRight(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderStrafeLeft(double power, int encoderAmount)
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-encoderAmount);
        rightFront.setTargetPosition(encoderAmount);
        leftBack.setTargetPosition(encoderAmount);
        rightBack.setTargetPosition(-encoderAmount);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafeLeft(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {


        }

        stopDriving();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void driveForward(double power)
    {
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);
    }
    public void turnLeft(double power)
    {
        leftFront.setPower(power);
        rightFront.setPower(-power);
        leftBack.setPower(power);
        rightBack.setPower(-power);
    }
    public void turnRight(double power)
    {
        turnLeft(-power);
    }
    public void driveBackward(double power)
    {
        driveForward(-power);
    }
    public void stopDriving()
    {
        driveForward(0);
    }

    public void strafeRight(double power)
    {
        leftFront.setPower(power);
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        rightBack.setPower(power);
    }
    public void strafeLeft(double power)
    {
        strafeRight(-power);
    }
}
