package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOPT", group="Master")
public class TrialTeleOP extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime sleeptime = new ElapsedTime();
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor ramp;
    DcMotor intake;
    Servo servo1;
    @Override
    public void init()
    {
        leftFront = hwMotor("leftFront");
        leftBack = hwMotor("leftBack");
        rightFront = hwMotor("rightFront");
        rightBack = hwMotor("rightBack");
        ramp = hwMotor("ramp");
        intake = hwMotor("intake");
        servo1 = hardwareMap.servo.get("servo");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        // Encoder tick for 40's motors = 1120

        //AUSTIN WAS HERE
    }

    @Override
    public void loop()
    {
        // Tank drive
        /**
        leftFront.setPower(gamepad1.left_stick_y);
        rightFront.setPower(gamepad1.right_stick_y);
        leftBack.setPower(gamepad1.left_stick_y);
        rightBack.setPower(gamepad1.right_stick_y);
        strafe();
         */
        double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        leftFront.setPower(-v1);
        rightFront.setPower(-v2);
        leftBack.setPower(-v3);
        rightBack.setPower(-v4);

        //Ramp controls
        ramp.setPower(gamepad2.right_stick_y);
        //intake.setPower(gamepad1.right_trigger);
        intake.setPower(gamepad2.left_stick_y);
        //intake.setPower(-gamepad1.left_trigger);

        /**

        if(gamepad2.right_bumper) {
            ramp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            int newTarget = ramp.getTargetPosition() + 616;
            ramp.setTargetPosition(newTarget);
            ramp.setPower(0.75);
            ramp.setMode((DcMotor.RunMode.RUN_TO_POSITION));
            if(ramp.getCurrentPosition() == ramp.getTargetPosition())
            {
                ramp.setPower(0);
            }

            ramp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
            else if(gamepad2.left_bumper)
        {
            ramp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            int newTarget = ramp.getTargetPosition() + 1000;
            ramp.setTargetPosition(newTarget);
            ramp.setPower(-0.75);
            ramp.setMode((DcMotor.RunMode.RUN_TO_POSITION));
            if(ramp.getCurrentPosition() == ramp.getTargetPosition())
            {
                ramp.setPower(0);
            }

            ramp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

         */

        // 0.5 too far
        if(gamepad2.y) // middle
        {
            servo1.setPosition(0.25);
        }

        else if(gamepad2.right_bumper)
        {
            servo1.setPosition(0.0);
        }
        else if(gamepad2.left_bumper)
        {
            servo1.setPosition(0.35);
        }
    }

    public DcMotor hwMotor(String text)
    {
        return hardwareMap.dcMotor.get(text);
    }
    public void strafe()
    {
        leftFront.setPower(-gamepad1.right_stick_x);
        rightFront.setPower(gamepad1.left_stick_x);
        leftBack.setPower(gamepad1.left_stick_x);
        rightBack.setPower(-gamepad1.right_stick_x);
    }

}




