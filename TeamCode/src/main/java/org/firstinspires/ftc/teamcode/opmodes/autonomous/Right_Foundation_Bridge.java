package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.parts.WackyWheels;

@Autonomous(name="Right_Foundation_Bridge", group="Game Controller")
public class Right_Foundation_Bridge extends Autonomy
{
    public Right_Foundation_Bridge() {
        parkBridgeSide = true;
        execCollectStones = true;
        execMoveFoundation = true;

        side = WackyWheels.Side.RIGHT;
    }
}
