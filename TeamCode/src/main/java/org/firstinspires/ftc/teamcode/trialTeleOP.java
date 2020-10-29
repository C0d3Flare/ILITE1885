package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Trial TeleOP", group="Trial")
public class trialTeleOP extends OpMode {
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Servo armServo;


    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("left_front");
        rightFront = hardwareMap.dcMotor.get("right_front");
        leftBack = hardwareMap.dcMotor.get("left_back");
        rightBack = hardwareMap.dcMotor.get("right_back");
        armServo = hardwareMap.servo.get("armServo");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);

    }

    @Override
    public void loop() {

        // POV Drive
        // Tank Drive // left stick controls left wheels on robot, right stick controls right wheels on robot

        leftFront.setPower(-gamepad1.left_stick_y);
        leftBack.setPower(-gamepad1.left_stick_y);
        rightFront.setPower(-gamepad1.right_stick_y);
        rightBack.setPower(-gamepad1.right_stick_y);

        // left up, right down // left wheel forward, right wheel back - turn right
        // left down, right up // left wheels backward, right wheel forward - turn left


        if(gamepad2.a) {
            // 0 = 0
            // 90 = 0.5
            // 180 = 1

            armServo.setPosition(0.43);

        }

    }
}
