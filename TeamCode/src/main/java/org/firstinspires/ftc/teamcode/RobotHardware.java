package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    DcMotor fl; DcMotor fr; DcMotor bl; DcMotor br;
    DcMotor leftlift; DcMotor rightlift; DcMotor raisemotor;
    /*DcMotor backlift; DcMotor intake;
    Servo leftintakearm; Servo rightintakearm;
    Servo mainrelease; Servo auxrelease;
    Servo leftboxarm; Servo rightboxarm;
    Servo launcher; */
    Servo claw;
    Servo leftwrist; Servo rightwrist;
    Servo leftlock; Servo rightlock;

    public RobotHardware(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, DcMotor leftarm, DcMotor rightarm, DcMotor raisemotor, Servo claw, Servo leftwrist, Servo rightwrist, Servo leftlock, Servo rightlock) {
        this.fl = fl; this.fr = fr; this.bl = bl; this.br = br; this.leftlift = leftarm; this.rightlift = rightarm; this.raisemotor = raisemotor; this.claw = claw; this.leftwrist = leftwrist; this.rightwrist = rightwrist; this.leftlock = leftlock; this.rightlock = rightlock;
    }

    public void innitHardwareMap() {
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftlift.setDirection(DcMotor.Direction.REVERSE);
        rightlift.setDirection(DcMotor.Direction.REVERSE);

        leftlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftlift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightlift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        raisemotor.setDirection(DcMotor.Direction.REVERSE);
        raisemotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        raisemotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        leftarm.setDirection(DcMotor.Direction.FORWARD);
//        rightarm.setDirection(DcMotor.Direction.REVERSE);
//        leftarm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightarm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftarm.setTargetPosition(0);
//        rightarm.setTargetPosition(0);
//        leftarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftarm.setPower(0.5);
//        rightarm.setPower(0.5);

        claw.setDirection(Servo.Direction.REVERSE);
        leftwrist.setDirection(Servo.Direction.FORWARD);
        rightwrist.setDirection(Servo.Direction.REVERSE);

        leftlock.setDirection(Servo.Direction.FORWARD);
        rightlock.setDirection(Servo.Direction.REVERSE);
    }


    public void openClaw() {
        claw.setPosition(0);
    }

//    public void resetWrist() {
//        wrist.setPosition(0);
//    }
    public int getLeftArmPosition() {
        return leftlift.getCurrentPosition();
    }

    public int getRightArmPosition() {
        return rightlift.getCurrentPosition();
    }











}
