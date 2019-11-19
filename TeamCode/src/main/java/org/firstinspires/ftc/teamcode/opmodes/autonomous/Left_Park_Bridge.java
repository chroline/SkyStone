package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Left_Park_Bridge", group="Game Controller")
public class Left_Park_Bridge extends Autonomy
{
    public Left_Park_Bridge() {
        parkBridgeSide = true;
        execCollectStones = true;
        execMoveFoundation = false;
    }
}
