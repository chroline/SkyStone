package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Left_Park_Wall", group="Game Controller")
public class Left_Park_Wall extends Autonomy
{
    public Left_Park_Wall() {
        parkBridgeSide = false;
        execCollectStones = true;
        execMoveFoundation = false;
    }
}
