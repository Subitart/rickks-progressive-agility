package org.dqw4w9wgxcq.scripts.sdn.agility.data;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.List;
import java.util.function.Predicate;

public class AgilArea extends Area {
    private final Area inner;
    private final Predicate<? super SceneObject> obstaclePred;

    public AgilArea(Area inner, Predicate<? super SceneObject> obstaclePred){
        super(inner.getFloorLevel());
        this.inner = inner;
        this.obstaclePred = obstaclePred;
    }

    public AgilArea(int x1, int y1, int x2, int y2, int floorLevel, String name){
        this(Area.rectangular(x1, y1, x2, y2, floorLevel), sceneObject -> sceneObject.getName().equalsIgnoreCase(name));
    }

    @Override
    public List<Position> getTiles() {
        return inner.getTiles();
    }

    @Override
    public boolean contains(Positionable positionable) {
        return inner.contains(positionable);
    }

    public Predicate<? super SceneObject> getObstaclePred(){
        return obstaclePred;
    }
}
