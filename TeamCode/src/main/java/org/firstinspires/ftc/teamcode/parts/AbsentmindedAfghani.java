package org.firstinspires.ftc.teamcode.parts;

import java.util.Arrays;
import java.util.List;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.glob.StupidStateful;

public class AbsentmindedAfghani {

    // parts
    private DcMotor actuator, slides;
    private SillyServos pushers, grippers;
    private Servo extender;

    //states
    private StupidStateful extenderState = new StupidStateful<>(false);
    private boolean        extenderOn    = false;
    private StupidStateful grippersState = new StupidStateful<>(false);
    private boolean        grippersOn    = false;

    public AbsentmindedAfghani(DcMotor actuator, DcMotor slides, SillyServos pushers, SillyServos grippers, Servo extender) {
        this.actuator  = actuator;
        this.slides    = slides;
        this.pushers   = pushers;
        this.grippers  = grippers;
        this.extender  = extender;
    }

    public void actuatorCtrl(double pow) {
        actuator.setPower(pow*.4);
    }

    public void slidesCtrl(double pow) {
        slides.setPower(pow);
    }

    public void pushCtrl(List<Boolean> vals) {
        for(int i = 0; i < pushers.servos.size(); i++) {
            Servo pusher = pushers.servos.get(i);
            if(vals.get(i) == Boolean.TRUE) pusher.setPosition(0.5);
            else pusher.setPosition(0.5);
        }
    }

    public void extenderCtrl(boolean on) {
        extenderState.set(on);

        if(on && extenderState.getState() == StupidStateful.State.Edited) {
            extenderState.accept();
            extenderOn = !extenderOn;
        }

        if(extenderOn) extender.setPosition(0.5);
        else extender.setPosition(0);
    }

    public void grippersCtrl(boolean on) {
        grippersState.set(on);

        if(on && grippersState.getState() == StupidStateful.State.Edited) {
            grippersState.accept();
            grippersOn = !grippersOn;
        }

        if(grippersOn) grippers.setPosition(Arrays.asList(0.6,0.6));
        else grippers.setPosition(Arrays.asList(1.0,1.0));
    }
}
