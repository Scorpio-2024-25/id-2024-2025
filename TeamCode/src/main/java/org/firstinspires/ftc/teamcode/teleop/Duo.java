package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.odometry;


@TeleOp(name="Solo", group="Linear Opmode")

public class Solo extends LinearOpMode {


    public static double p = 0.15, i = 0.5, d = 0.00000001; //0.15, 0.5, 8 0s 8
    private static double maxpowerstay = 0.6;
    private static double maxpowerturn = 0.5;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor fl = null;
    private DcMotor fr = null;
    private DcMotor bl = null;
    private DcMotor br = null;
    private DcMotor leftarm = null;
    private DcMotor rightarm = null;
    private DcMotor backlift = null;
    private DcMotor intake = null;

    Servo  claw;
    Servo wrist;
    Servo mainrelease;
    Servo auxrelease;
    Servo leftboxarm;
    Servo rightboxarm;
    Servo launcher;

    DcMotor verticalLeft, verticalRight, horizontal;
    final double COUNTS_PER_INCH = 336.877963;
    odometry update;

    @Override
    public void runOpMode() {


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        leftarm = hardwareMap.get(DcMotor.class, "leftarm");
        rightarm = hardwareMap.get(DcMotor.class, "rightarm");

        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");


        RobotHardware robot = new RobotHardware(fl, fr, bl, br, leftarm, rightarm, claw, wrist);
        robot.innitHardwareMap();

        leftarm.setTargetPosition(0);
        rightarm.setTargetPosition(0);

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {

            telemetry.addData("Left Arm Position", robot.getLeftArmPosition());
            telemetry.addData("Right Arm Position", robot.getRightArmPosition());
            telemetry.addData("Right stick Val:", gamepad2.right_stick_y);

            telemetry.update();

            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double modifier = 1;
            double armmodifier = 0.25;
            leftarm.setPower(gamepad1.right_stick_y * armmodifier);
            rightarm.setPower(gamepad1.right_stick_y * armmodifier);

            if (gamepad1.b) {claw.setPosition(0.7);}
            if (gamepad1.a) {claw.setPosition(0.2);}

            if (gamepad1.x) {wrist.setPosition(0.72);}
            if (gamepad1.y) {wrist.setPosition(0.06);}

            fl.setPower(modifier * (y + x + turn));
            fr.setPower(modifier * (y - x - turn));
            bl.setPower(modifier * (y - x + turn));
            br.setPower(modifier * (y + x - turn));
        }

    }
}