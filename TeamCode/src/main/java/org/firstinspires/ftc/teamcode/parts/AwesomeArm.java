package org.firstinspires.ftc.teamcode.parts;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.glob.SharedTelemetry;

public class AwesomeArm {
    public final DcMotor shoulder;
    public final DcMotor actuator;
    public final CRServo elbow;
    public final List<Servo> grippers;

    public boolean grippersOpen = false;
    public int shoulderPos = 0;
    public int actuatorPos = 0;
    public int elbowPos    = 0;

    public AwesomeArm(DcMotor shoulder, DcMotor actuator, CRServo elbow, List<Servo> grippers) {
        this.shoulder = shoulder;
        this.actuator = actuator;
        this.elbow    = elbow;
        this.grippers = grippers;
        shoulderPos = shoulder.getCurrentPosition();
        actuatorPos = actuator.getCurrentPosition();
    }

    public void moveShoulder(double amnt) {
        int movet = 50;
        shoulderPos += (int)(movet * amnt);

        DcMotor i = shoulder;
        if(i.getCurrentPosition() != shoulderPos) {
            i.setTargetPosition(shoulderPos);
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            i.setPower(1);
        } else {
            i.setPower(0);
        }
    }

    public void moveActuator(double amnt) {
        /*int movet = 20;
        actuatorPos += (int)(movet * amnt);
        //actuatorPos = Range.clip(actuatorPos,-13000,-200);

        DcMotor i = actuator;
        if(i.getCurrentPosition() != actuatorPos) {
            i.setTargetPosition(actuatorPos);
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            i.setPower(0.5);
        } else {
            i.setPower(0);
        }

        SharedTelemetry.telemetry.addData("t1",i.getCurrentPosition());
        SharedTelemetry.telemetry.addData("t2",actuatorPos);*/

        actuator.setPower(amnt*0.75);
    }

    public void moveElbow(double amnt) {
        amnt = amnt*0.8;
        int movet = 50;
        elbowPos += (int)(movet * amnt);

        CRServo i = elbow;
        elbow.setPower(amnt);
    }

    public void toggleGrippers() {

        Servo lg = grippers.get(0);
        Servo rg = grippers.get(1);

        grippersOpen = !grippersOpen;
        if(grippersOpen) {
            lg.setPosition(0.3);
            rg.setPosition(0.4);
        } else {
            lg.setPosition(0);
            rg.setPosition(0);
        }
    }
}
