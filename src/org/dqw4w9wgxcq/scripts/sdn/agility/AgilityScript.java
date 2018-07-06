package org.dqw4w9wgxcq.scripts.sdn.agility;

import org.dqw4w9wgxcq.api.fw.task.Task;
import org.dqw4w9wgxcq.api.identifiable.Preds;
import org.dqw4w9wgxcq.scripts.sdn.agility.data.Course;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ScriptMeta(name = "Rickk's AIO Progressive AgilityScript", desc = "", developer = "Rickk")
public class AgilityScript extends Script implements RenderListener {
    public static final Set<String> OBSTACLE_NAMES = new HashSet<>(Arrays.asList("Obstacle net", "Tightrope"));
    public static final Set<String> OBSTACLE_ACTIONS = new HashSet<>(Arrays.asList("Walk-across", "Climb-over", "Climb", "Cross", "Jump"));

    public static final Set<Integer> CAST_ANIMS = new HashSet<>(Arrays.asList(712));
    public static final Set<Integer> IDLE_STANCES = new HashSet<>(Arrays.asList(808, 813));

    private Course course;
    private Task traverse = null;
    private Player local;
    private int lvl;
    @Override
    public int loop() {
        try {
            Log.info("loop");
            if(traverse != null && traverse.running()){
                Log.info("traversing");
                return traverse.execute();
            }

            local = Players.getLocal();
            if(!IDLE_STANCES.contains(local.getStance()) || local.getAnimation() != -1){
                return 200;
            }

            if(Skills.getCurrentLevel(Skill.HITPOINTS) <= 3){
                Log.info("hp too low");
                return 1000;
            }

            lvl = Skills.getLevel(Skill.AGILITY);

            if(!checkCourse()){
                return 100;
            }

            Pickable mark = Pickables.getNearest(Preds.name("Mark of grace").and(identifiable -> Movement.isReachable((Positionable) identifiable)));
            if(mark != null){
                mark.interact("Take");
                return 1000;
            }

            Arrays.stream(course.getAreas())
                    .filter(agilArea1 -> agilArea1.contains(local))
                    .findFirst()
                    .ifPresent(agilArea1 -> {
                        SceneObject obstacle = SceneObjects.getNearest(agilArea1.getObstaclePred());
                        Log.info(obstacle.getName());
                        obstacle.interact(a-> true);
                    });
            return 2000;
        }catch (Exception thanksMad){
            StackTraceElement[] stackTraceElements = thanksMad.getStackTrace();
            StringBuilder stackTrace = new StringBuilder(thanksMad.toString() + (thanksMad.getMessage() != null ? thanksMad.getMessage() : "null"));
            for (StackTraceElement element : stackTraceElements) {
                stackTrace.append("\n").append(element.toString());
            }

            Log.severe("thanks mad\n" + stackTrace);
        }

        return 1000;
    }

    private boolean checkCourse(){
        if(course == null || local.getFloorLevel() == 0){
            if(lvl < 1){
                return false;
            }

            Course old = course;
            course = Course.getForLevel(lvl);
            if (course == null) {
                Log.info("cant get course");
                setStopping(true);
                return false;
            }

            if (old != course) {
                Log.info("new course: " + course.name());
                if(!course.isNear()){
                    Log.info("not near");
                    traverse = course.getTraverseTask();
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();
        Player local = Players.getLocal();
        int x = 20;
        int y = 20;
        g.drawString("aio agil ", x, y += 15);
        g.drawString("animation " + local.getAnimation(), x, y += 15);
        g.drawString("moving " + local.isMoving(), x, y += 15);
        g.drawString("animation delay " + local.getAnimationDelay(), x, y += 15);
        g.drawString("animation frame " + local.getAnimationFrame(), x, y += 15);
        g.drawString("stance frame " + local.getStanceFrame(), x, y += 15);
        g.drawString("stance " + local.getStance(), x, y+=15);
        //g.drawString("pose " + local.getPoseAnimation(), x, y+=15);
        //g.drawString("idle pose" + local.getIdlePoseAnimation(), x, y+=15);
    }
}
