package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WeirdWheels;

@Autonomous(name="Right_Park_Bridge", group="Game Controller")
public class Right_Park_Bridge extends Autonomy
{
    public Right_Park_Bridge() {
        parkBridgeSide = true;
        execCollectStones = true;
        execMoveFoundation = false;
        side = WeirdWheels.Side.RIGHT;
    }
}
