package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.parts.WackyWheels;
import org.firstinspires.ftc.teamcode.util.glob.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TeleOp(name="drivetrain", group="Game Controller")
public class drivetrain extends OpMode
{
    private WackyWheels wheels;

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
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        List<DcMotor> wheelList = new ArrayList<>(Arrays.asList(fl, fr, bl, br));
        this.wheels = new WackyWheels(wheelList);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        wheelControl();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        wheels.stop();
    }

    private void wheelControl() {
        double strafe   = gamepad1.right_stick_y;
        double vertical = gamepad1.left_stick_x;
        double rotate   = gamepad1.left_stick_x;

        wheels.setVelocityXYR(strafe,vertical,rotate);
        wheels.moveMecanum();

    }
}