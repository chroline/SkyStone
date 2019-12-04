package org.firstinspires.ftc.teamcode.opmodes.teleop;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.parts.AbsentmindedAfghani;
import org.firstinspires.ftc.teamcode.parts.MoldovanMotors;
import org.firstinspires.ftc.teamcode.parts.SillyServos;
import org.firstinspires.ftc.teamcode.parts.WackyWheels;
import org.firstinspires.ftc.teamcode.util.glob.Shared;
import org.firstinspires.ftc.teamcode.util.glob.StupidStateful;

@TeleOp(name="Robob", group="Game Controller")
public class Robob extends OpMode
{
    private WackyWheels wheels;

    private MoldovanMotors intake;
    private StupidStateful intakeState = new StupidStateful<>(false);
    private boolean        intakeOn    = false;

    private AbsentmindedAfghani arm;


    private Servo          bd;
    private StupidStateful bdState = new StupidStateful<>(false);
    private boolean        bdOn    = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        Shared.telemetry = telemetry;

        telemetry.addData("Status", "Initialized");

        // wheels
        DcMotor fl              = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr              = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl              = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br              = hardwareMap.get(DcMotor.class, "br");
        List<DcMotor> wheelList = new ArrayList<>(Arrays.asList(fl, fr, bl, br));
        wheels = new WackyWheels(wheelList);

        DcMotor i1               = hardwareMap.get(DcMotor.class, "i1");
        i1.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotor i2               = hardwareMap.get(DcMotor.class, "i2");
        List<DcMotor> intakeList = new ArrayList<>(Arrays.asList(i1, i2));
        intake                   = new MoldovanMotors(intakeList);

        DcMotor actuator     = hardwareMap.get(DcMotor.class, "actuator");
        DcMotor slides       = hardwareMap.get(DcMotor.class, "slides");
        Servo push1          = hardwareMap.get(Servo.class, "push1");
        Servo push2          = hardwareMap.get(Servo.class, "push2");
        SillyServos pushers  = new SillyServos(Arrays.asList(push1, push2));
        Servo grip1          = hardwareMap.get(Servo.class, "grip1");
        Servo grip2          = hardwareMap.get(Servo.class, "grip2");
        SillyServos grippers = new SillyServos(Arrays.asList(grip1, grip2));
        Servo extender       = hardwareMap.get(Servo.class, "extender");
        arm = new AbsentmindedAfghani(actuator, slides, pushers, grippers, extender);

        //bd = hardwareMap.get(Servo.class,"bd");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        wheelControl();
        intakeControl();
        //bdControl();
        armControl();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        wheels.stop();
    }

    private void wheelControl() {
        double strafe   = gamepad1.left_stick_x;
        double vertical = gamepad1.left_stick_y;
        double rotate   = gamepad1.right_stick_x;

        wheels.setVelocityXYR(strafe, vertical, rotate);
        wheels.moveMecanum();
    }

    private void intakeControl() {
        boolean toggle = gamepad1.a;
        intakeState.set(toggle);

        if(toggle && intakeState.getState() == StupidStateful.State.Edited) {
            intakeState.accept();
            intakeOn = !intakeOn;
        }

        if(intakeOn) intake.setPower(1);
        else intake.stop();
    }

    private void bdControl() {
        boolean toggle = gamepad1.x;
        bdState.set(toggle);

        if(toggle && bdState.getState() == StupidStateful.State.Edited) {
            bdState.accept();
            bdOn = !bdOn;
        }

        if(bdOn) bd.setPosition(0.5);
        else bd.setPosition(0);
    }

    private void armControl() {
        double actuatorPow = gamepad2.left_stick_y;
        double slidesPow   = gamepad2.left_trigger - gamepad2.right_trigger;

        arm.actuatorCtrl(actuatorPow);
        arm.slidesCtrl(slidesPow);
        arm.pushCtrl(new ArrayList<>(Arrays.asList(gamepad2.left_bumper, gamepad2.right_bumper)));
        arm.extenderCtrl(gamepad2.b);
        arm.grippersCtrl(gamepad2.a);
    }
}