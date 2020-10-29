package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Trial Auto", group="Trial")
public class trialAuto extends LinearOpMode {
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Servo armServo;

    @Override
    public void runOpMode() throws InterruptedException {
        // Run every before waitForStart() when init button is pressed
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        armServo.setPosition(0);

        waitForStart();

        // Run after play button

        // Drive Forward
        leftFront.setPower(1);
        rightFront.setPower(1);
        leftBack.setPower(1);
        rightBack.setPower(1);
        sleep(1300);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        armServo.setPosition(1);
        /**
         * Drive forward for 1.3 and stop moving. Then set armServo to 180 degrees
         *
         */

        driveForward(1, 5000);
        stopDriving();
        driveBackward(1, 5000);
        stopDriving();
        /**
         * Drive Forward at full power for 5 seconds, stop moving, then drive backward at full power for 5 seconds then stop moving
         */



    }

    public void driveForward(double power, int time){
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);
        sleep(time);
    }

    public void driveBackward(double power, int time) {
        leftFront.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        rightBack.setPower(-power);
        sleep(time);
    }
    public void stopDriving(){
        driveForward(0, 0);
    }
}
