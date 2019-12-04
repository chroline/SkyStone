package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WackyWheels;

@Autonomous(name="Right_Park_Wall", group="Game Controller")
public class Right_Park_Wall extends Autonomy
{
    public Right_Park_Wall() {
        parkBridgeSide = false;
        execCollectStones = true;
        execMoveFoundation = false;
        side = WackyWheels.Side.RIGHT;
    }
}
