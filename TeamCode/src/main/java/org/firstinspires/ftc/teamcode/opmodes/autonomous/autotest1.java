package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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

@Autonomous(name="autotest1 yay", group="Game Controller")
public class autotest1 extends LinearOpMode {
    boolean parkBridgeSide     = false;
    WeirdWheels.Side side      = WeirdWheels.Side.LEFT;
    boolean execCollectStones  = false;
    boolean execMoveFoundation = false;

    private WeirdWheels wheels;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.2;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException
    {
        registerMotors();

        telemetry.addData("Robob says","I'm Ready!");
        telemetry.update();

        // After we are done initializing our code, we wait for Start button.
        waitForStart();

        /*wheels.moveTo(0,3000);

        while (opModeIsActive() && wheelsBusy())
        {
            telemetry.addData("Robob says","I am moving");
            telemetry.update();
            idle();
        }*/
        //encoderDrive(DRIVE_SPEED, 0, 2, 4.0);

        wheels.setVelocityY(1);
        wheels.moveMecanum();
        Thread.sleep(2000);
        wheels.stop();
    }
    public void registerMotors() {
        SharedTelemetry.telemetry = telemetry;

        telemetry.addData("Status", "Initialized");

        // wheels
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");
        List<DcMotor> wheelList = new ArrayList<>(Arrays.asList(fl, fr, bl, br));

        this.wheels = new WeirdWheels(wheelList);
        this.wheels.setSide(this.side);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(double speed,
                             double xInches, double yInches,
                             double timeoutS) {
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            wheels.moveTo((int)(xInches * COUNTS_PER_INCH), (int)(yInches * COUNTS_PER_INCH));

            // Turn On RUN_TO_POSITION
            wheels.motors.get(0).setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wheels.motors.get(1).setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wheels.motors.get(2).setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wheels.motors.get(3).setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            wheels.motors.get(0).setPower(Math.abs(speed));
            wheels.motors.get(1).setPower(Math.abs(speed));
            wheels.motors.get(2).setPower(Math.abs(speed));
            wheels.motors.get(3).setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() && wheelsBusy()) {

                // Display it for the driver.
                telemetry.addData("Robob says",  "I am moving");
                telemetry.update();
            }


            wheels.motors.get(0).setPower(0);
            wheels.motors.get(1).setPower(0);
            wheels.motors.get(2).setPower(0);
            wheels.motors.get(3).setPower(0);

            // Turn off RUN_TO_POSITION
            /*wheels.motors.get(0).setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheels.motors.get(1).setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheels.motors.get(2).setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheels.motors.get(3).setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/
        }
    }

    private boolean wheelsBusy() {
        telemetry.addData("motor0",wheels.motors.get(0).getCurrentPosition());
        telemetry.addData("motor1",wheels.motors.get(1).getCurrentPosition());
        telemetry.addData("motor2",wheels.motors.get(2).getCurrentPosition());
        telemetry.addData("motor3",wheels.motors.get(3).getCurrentPosition());
        telemetry.update();
        return wheels.motors.get(0).isBusy()||wheels.motors.get(1).isBusy();
    }
}