package org.dqw4w9wgxcq.api.position;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.List;

public class Areas {
    public static final Area EVERYWHERE = new Area(0) {
        @Override
        public List<Position> getTiles() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean contains(Positionable positionable) {
            return true;
        }
    };

    public static Area floor(int floor){
        return new Area(floor) {
            @Override
            public List<Position> getTiles() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean contains(Positionable positionable) {
                return positionable.getFloorLevel() == floor;
            }
        };
    }
}
