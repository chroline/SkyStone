package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WeirdWheels;

@Autonomous(name="Right_Foundation_Wall", group="Game Controller")
public class Right_Foundation_Wall extends Autonomy
{
    public Right_Foundation_Wall() {
        parkBridgeSide = false;
        execCollectStones = true;
        execMoveFoundation = true;

        side = WeirdWheels.Side.RIGHT;
    }
}
