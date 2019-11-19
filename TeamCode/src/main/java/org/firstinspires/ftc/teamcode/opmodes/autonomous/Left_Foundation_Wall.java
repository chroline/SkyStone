package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WeirdWheels;

@Autonomous(name="Left_Wall", group="Game Controller")
public class Left_Wall extends Autonomy
{
    public Left_Wall() {
        parkBridgeSide = false;
        execCollectStones = true;
        execMoveFoundation = true;

        side = WeirdWheels.Side.LEFT;
    }
}
