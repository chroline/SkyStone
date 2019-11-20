package org.firstinspires.ftc.teamcode.opmodes.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.parts.AwesomeArm;
import org.firstinspires.ftc.teamcode.parts.ManyMotors;
import org.firstinspires.ftc.teamcode.parts.SeveralServos;
import org.firstinspires.ftc.teamcode.parts.WeirdWheels;
import org.firstinspires.ftc.teamcode.util.glob.SharedTelemetry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;



@Autonomous(name="minibot :)", group="Exercises")
public class minibot extends LinearOpMode
{
    private WeirdWheels wheels;
    WeirdWheels.Side side      = WeirdWheels.Side.LEFT;

    @Override
    public void runOpMode() throws InterruptedException
    {
        SharedTelemetry.telemetry = telemetry;

        telemetry.addData("Status", "Initialized");

        // wheels
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "br");
        DcMotor br = hardwareMap.get(DcMotor.class, "bl");
        List<DcMotor> wheelList = new ArrayList<>(Arrays.asList(fl, fr, bl, br));
        this.wheels = new WeirdWheels(wheelList);
        this.wheels.setSide(this.side);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        br.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setTargetPosition(0);
        fr.setTargetPosition(0);
        bl.setTargetPosition(0);
        br.setTargetPosition(0);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set left motor to run to 5000 encoder counts.

        wheels.moveTo(0,200);
        wheels.setPower(0.1);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && fl.isBusy())
        {
            telemetry.addData("encoder-fwd", fl.getCurrentPosition() + "  busy=" + fl.isBusy());
            telemetry.update();
            idle();
        }
    }
}