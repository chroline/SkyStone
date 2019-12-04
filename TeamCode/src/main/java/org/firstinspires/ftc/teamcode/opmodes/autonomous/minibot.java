package org.firstinspires.ftc.teamcode.opmodes.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.parts.WackyWheels;
import org.firstinspires.ftc.teamcode.util.glob.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Autonomous(name="minibot :)", group="Exercises")
public class minibot extends LinearOpMode
{
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    private WackyWheels wheels;
    WackyWheels.Side side = WackyWheels.Side.LEFT;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Shared.telemetry = telemetry;

        initMotors();

        waitForStart();

        wheels.moveTo(0,2000);
        wheels.setPower(0.1);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && fl.isBusy())
        {
            telemetry.addData("fl", fl.getCurrentPosition());
            telemetry.addData("fr", fr.getCurrentPosition());
            telemetry.addData("bl", bl.getCurrentPosition());
            telemetry.addData("br", br.getCurrentPosition());
            telemetry.update();
            idle();
        }

        Thread.sleep(10000);
    }

    public void initMotors()
    {
        // wheels
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        List<DcMotor> wheelList = new ArrayList<>(Arrays.asList(fl, fr, bl, br));
        this.wheels = new WackyWheels(wheelList);
        this.wheels.setSide(this.side);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setTargetPosition(0);
        fr.setTargetPosition(0);
        bl.setTargetPosition(0);
        br.setTargetPosition(0);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}