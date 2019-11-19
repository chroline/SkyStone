package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Left_Foundation_Bridge", group="Game Controller")
public class Left_Foundation_Bridge extends Autonomy
{
    public Left_Foundation_Bridge() {
        parkBridgeSide = true;
        execCollectStones = true;
        execMoveFoundation = true;
    }
}
