package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WeirdWheels;

@Autonomous(name="Right_Bridge", group="Game Controller")
public class Right_Bridge extends Autonomy
{
    public Right_Bridge() {
        parkBridgeSide = true;
        execCollectStones = true;
        execMoveFoundation = true;

        side = WeirdWheels.Side.RIGHT;
    }
}
