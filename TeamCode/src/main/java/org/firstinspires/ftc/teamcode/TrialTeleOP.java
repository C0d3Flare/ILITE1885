package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOP", group="Master")
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

    @Override
    public void init()
    {
        leftFront = hwMotor("leftFront");
        leftBack = hwMotor("leftBack");
        rightFront = hwMotor("rightFront");
        rightBack = hwMotor("rightBack");
        ramp = hwMotor("ramp");
        intake = hwMotor("intake");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void loop()
    {
        // Tank drive
        leftFront.setPower(-gamepad1.left_stick_y);
        rightFront.setPower(-gamepad1.right_stick_y);
        leftBack.setPower(-gamepad1.left_stick_y);
        rightBack.setPower(-gamepad1.right_stick_y);
        strafe();

        //Ramp controls
        ramp.setPower(-gamepad2.right_stick_y);
        intake.setPower(gamepad1.right_trigger);
        intake.setPower(gamepad2.right_trigger);
        intake.setPower(-gamepad1.left_trigger);
        intake.setPower(-gamepad2.left_trigger);

        if(gamepad1.right_bumper || gamepad2.right_bumper)
        {
            intake.setPower(1);
        }
        else if(gamepad1.left_bumper || gamepad2.left_bumper)
        {
            intake.setPower(-1);
        }
    }

    public DcMotor hwMotor(String text)
    {
        return hardwareMap.dcMotor.get(text);
    }
    public void strafe()
    {
        leftFront.setPower(gamepad1.right_stick_x);
        rightFront.setPower(-gamepad1.left_stick_x);
        leftBack.setPower(-gamepad1.left_stick_x);
        rightBack.setPower(gamepad1.right_stick_x);
    }

}




