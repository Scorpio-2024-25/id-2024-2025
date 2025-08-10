package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    private DcMotor leftlift = null;
    private DcMotor rightlift = null;
    private DcMotor raisemotor = null;

    Servo claw;
    Servo leftwrist;
    Servo rightwrist;
    Servo leftlock;
    Servo rightlock;

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

        leftlift = hardwareMap.get(DcMotor.class, "leftlift");
        rightlift = hardwareMap.get(DcMotor.class, "rightlift");
        raisemotor = hardwareMap.get(DcMotor.class, "raisemotor");

        claw = hardwareMap.get(Servo.class, "claw");
        leftwrist = hardwareMap.get(Servo.class, "leftwrist");
        rightwrist = hardwareMap.get(Servo.class, "rightwrist");
        leftlock = hardwareMap.get(Servo.class, "leftlock");
        rightlock = hardwareMap.get(Servo.class, "rightlock");


        RobotHardware robot = new RobotHardware(fl, fr, bl, br, leftlift, rightlift, raisemotor, claw, leftwrist, rightwrist, leftlock, rightlock);
        robot.innitHardwareMap();

        leftlift.setTargetPosition(0);
        rightlift.setTargetPosition(0);

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {

            telemetry.addLine("Entering Solo Mode. Gamepad 1 is the ONLY user");
//            telemetry.addLine(" ");
//            telemetry.addData("Left Arm Position", robot.getLeftArmPosition());
//            telemetry.addData("Right Arm Position", robot.getRightArmPosition());
//            telemetry.addData("Right stick Val:", gamepad2.right_stick_y);

            telemetry.update();

            // --- Drive ---
            // Use Left Stick and Right Stick x
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            // Speed Modifier for Base
            double modifier = 0.9;
            if (gamepad1.left_bumper) {
                modifier = 0.3;
            } else if (gamepad1.right_bumper) {
                modifier = 1;
            } else modifier = 0.8;

            // --- Arms and Whatnot ---
            double armmodifier = 0.45;
            double liftmodifier = 1;

            // Lifts --> Right Stick Y
            leftlift.setPower(gamepad1.right_stick_y * armmodifier);
            rightlift.setPower(-gamepad1.right_stick_y * armmodifier);

            // Move arm from Horizontal to Vertical --> Triggers
            if (gamepad1.right_trigger > 0) {
                raisemotor.setPower(0.2 + (0.65 * gamepad1.right_trigger));
            } else if (gamepad1.left_trigger > 0) {
                raisemotor.setPower(-(0.2 + (0.65 * gamepad1.left_trigger)));
            } else raisemotor.setPower(0);

            // Claw Control --> a and b
            if (gamepad1.b) {claw.setPosition(0.55);}
            if (gamepad1.a) {claw.setPosition(0.44);}

            // Wrist Control --> DPAD
            if (gamepad1.dpad_up) {leftwrist.setPosition(0.0); rightwrist.setPosition(0.0);}
            if (gamepad1.dpad_down) {leftwrist.setPosition(0.7); rightwrist.setPosition(0.7);}
            if (gamepad1.dpad_left) {leftwrist.setPosition(0.3); rightwrist.setPosition(0.3);}
            if (gamepad1.dpad_right) {leftwrist.setPosition(0.45); rightwrist.setPosition(0.45);}

            // Arm Locks --> x and y
            if (gamepad1.x) {leftlock.setPosition(0.7); rightlock.setPosition(0.2);}
            if (gamepad1.y) {leftlock.setPosition(1); rightlock.setPosition(0.5);}

//            if (gamepad2.x) {wrist.setPosition(0.72);}
//            if (gamepad2.y) {wrist.setPosition(0.06);}

            // Set Power to Motors
            fl.setPower(modifier * (y + x + turn));
            fr.setPower(modifier * (y - x - turn));
            bl.setPower(modifier * (y - x + turn));
            br.setPower(modifier * (y + x - turn));
        }

    }
}