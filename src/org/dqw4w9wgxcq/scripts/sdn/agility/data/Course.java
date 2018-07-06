package org.dqw4w9wgxcq.scripts.sdn.agility.data;

import org.dqw4w9wgxcq.api.delay.Delay;
import org.dqw4w9wgxcq.api.fw.task.AbstractTask;
import org.dqw4w9wgxcq.api.fw.task.Task;
import org.dqw4w9wgxcq.api.fw.task.Tasks;
import org.dqw4w9wgxcq.api.identifiable.Preds;
import org.dqw4w9wgxcq.api.position.Areas;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;

public enum Course {
    GNOME(
            1,
            new Position(2470, 3435, 0),
            new AbstractTask() {
                @Override
                public boolean validate() {
                    return Inventory.contains(Preds.prefix("Necklace of passage("));
                }

                @Override
                public int execute() {
                    if (GNOME.getPosition().distance() > 150) {
                        if (Interfaces.isViewingChatOptions()) {
                            Interfaces.processDialog(s -> s.contains("utpost"));
                        } else {
                            Inventory.getFirst(Preds.prefix("Necklace of passage(")).interact("Rub");
                        }
                    } else if (Players.getLocal().getY() < 3385) {
                        if (Interfaces.canContinue()) {
                            Interfaces.processContinue();
                        } else if (Interfaces.isViewingChatOptions()) {
                            Interfaces.processDialog(1);
                        } else if (!Interfaces.isDialogProcessing()) {
                            SceneObjects.getNearest(190).interact(a -> true);
                        }
                    } else if (GNOME.position.distance() > 20) {
                        Movement.walkTo(GNOME.getPosition());
                    } else {
                        stopRunning();
                    }
                    return Delay.standard();
                }
            },
            new AgilArea(Areas.floor(0), Preds.name("Log balance")),
            new AgilArea(2470, 3430, 2479, 3424, 0, "Obstacle net"),
            new AgilArea(2470, 3424, 2476, 3422, 1, "Tree branch"),
            new AgilArea(2472, 3421, 2477, 3418, 2, "Balancing rope"),
            new AgilArea(2483, 3421, 3488, 3418, 2, "Tree branch"),
            new AgilArea(2482, 3426, 2489, 3418, 0, "Obstacle net"),
            new AgilArea(2481, 3433, 2490, 3427, 0, "Obstacle pipe")
    ),
    DRAYNOR(
            10,
            new Position(3092, 3261, 0),
            new AbstractTask() {
                @Override
                public boolean validate() {
                    return Inventory.contains(Preds.prefix("Amulet of glory("));
                }

                @Override
                public int execute() {
                    if (DRAYNOR.position.distance() > 30) {
                        if (Interfaces.isViewingChatOptions()) {
                            Interfaces.processDialog(s -> s.contains("raynor"));
                        } else {
                            Inventory.getFirst(Preds.prefix("Amulet of glory(")).interact("Rub");
                        }
                    }
                    return Delay.standard();
                }
            },
            new AgilArea(Areas.floor(0), Preds.name("Rough wall")),
            new AgilArea(3097, 3281, 3102, 3277, 3, "Tightrope"),
            new AgilArea(3088, 3276, 3091, 3273, 3, "Tightrope"),
            new AgilArea(3089, 3267, 3094, 3265, 3, "Narrow wall"),
            new AgilArea(3088, 3261, 3088, 3257, 3, "Wall"),
            new AgilArea(3087, 3255, 3094, 3255, 3, "Gap"),
            new AgilArea(3096, 3261, 3101, 3256, 3, "Crate")
    ),
    AL_KHARID(
            20,
            new Position(3294, 3180, 0),
            Tasks.never()
    ),
    VARROCK(
            30,
            new Position(3207, 3407, 0),
            new AbstractTask() {
                @Override
                public boolean validate() {
                    return Inventory.contains("Varrock teleport");
                }

                @Override
                public int execute() {
                    if (VARROCK.position.distance() > 30) {
                        if (Players.getLocal().getAnimation() == -1) {
                            if (Inventory.getFirst("Varrock teleport").interact(a -> true)) {
                                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 250, 3000);
                            }
                        }
                    } else {
                        stopRunning();
                    }
                    return Delay.standard();
                }
            },
            new AgilArea(Areas.floor(0), Preds.name("Rough wall")),
            new AgilArea(3214, 3419, 3219, 3410, 3, "Clothes line"),
            new AgilArea(3201, 3417, 3208, 3413, 3, "Gap"),
            new AgilArea(3194, 3416, 3197, 3416, 1, "Wall"),
            new AgilArea(3192, 3406, 3198, 3402, 3, "Gap"),
            new AgilArea(Area.rectangular(3182, 3403, 3208, 3382, 3), Preds.id(10779)),
            new AgilArea(Area.rectangular(3218, 3403, 3232, 3393, 3), Preds.id(10780)),
            new AgilArea(3236, 3408, 3240, 3403, 3, "Ledge"),
            new AgilArea(3236, 3415, 3240, 3410, 3, "Edge")
    ),
    CANFIS(
            40,
            new Position(3496, 3489, 0),
            Tasks.never()
    ),
    FALADOR(
            50,
            new Position(3036, 3353, 0),
            new AbstractTask() {
                @Override
                public boolean validate() {
                    return Inventory.contains("Falador teleport");
                }

                @Override
                public int execute() {
                    if (FALADOR.position.distance() > 100) {
                        if (Players.getLocal().getAnimation() == -1) {
                            if (Inventory.getFirst("Falador teleport").interact(a -> true)) {
                                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 250, 3000);
                            }
                        }
                    } else if (SceneObjects.getNearest("Rough wall") == null) {
                        Movement.walkTo(new Position(3007, 3362, 0));
                    } else {
                        stopRunning();
                    }
                    return Delay.standard();
                }
            },
            new AgilArea(Areas.floor(0), Preds.name("Rough wall")),
            new AgilArea(3036, 3343, 3040, 3342, 3, "Tightrope"),
            new AgilArea(3044, 3349, 3051, 3341, 3, "Hand holds"),
            new AgilArea(3048, 3358, 3050, 3357, 3, "Gap"),
            new AgilArea(Area.rectangular(3045, 3367, 3048, 3361, 3), Preds.id(11360)),
            new AgilArea(3034, 3364, 3041, 3361, 3, "Tightrope"),
            new AgilArea(3026, 3354, 3029, 3352, 3, "Tightrope"),
            new AgilArea(3009, 3358, 3021, 3353, 3, "Gap"),
            new AgilArea(3016, 3349, 3022, 3343, 3, "Ledge"),
            new AgilArea(Area.rectangular(3011, 3346, 3014, 3344, 3), Preds.id(11367)),
            new AgilArea(Area.rectangular(3009, 3342, 3013, 3335, 3), Preds.id(11369)),
            new AgilArea(Area.rectangular(3012, 3334, 3017, 3331, 3), Preds.id(11370)),
            new AgilArea(3019, 3335, 3024, 3332, 3, "Edge")
    ),
    SEERS(
            60,
            new Position(2717, 3479, 0),
            new AbstractTask() {
                @Override
                public boolean validate() {
                    return Inventory.contains("Camelot teleport");
                }

                @Override
                public int execute() {
                    if (SEERS.position.distance() > 50) {
                        if (Players.getLocal().getAnimation() == -1) {
                            if (Inventory.getFirst("Camelot teleport").interact(a -> true)) {
                                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 250, 3000);
                            }
                        }
                    } else if (SceneObjects.getNearest("Wall") == null) {
                        Movement.walkTo(new Position(2730, 3485, 0));
                    } else {
                        stopRunning();
                    }
                    return Delay.standard();
                }
            },
            new AgilArea(Areas.floor(0), Preds.name("Wall")),
            new AgilArea(2721, 3497, 2730, 3490, 3, "Gap"),
            new AgilArea(2705, 3495, 2713, 3493, 2, "Tightrope"),
            new AgilArea(2710, 3481, 2715, 3477, 2, "Gap"),
            new AgilArea(Area.rectangular(2700, 3475, 2715, 3470, 3), Preds.id(11376)),
            new AgilArea(2698, 3465, 2702, 3460, 2, "Edge")
    ),
    POLLNIVNEACH(
            70,
            new Position(3357, 2973, 0),
            Tasks.never()
    ),
    RELLEKKA(
            80,
            new Position(2653, 3677, 0),
            Tasks.never()
    ),
    ARDOUGNE(
            90,
            new Position(2663, 3306, 0),
            Tasks.never()
    ),;

    private static final List<Course> reversed;

    static {
        reversed = Arrays.asList(values());
        Collections.reverse(reversed);
    }

    private final int lvl;
    private final Position position;
    private BooleanSupplier skip;
    private final Task traverseTask;
    private final AgilArea[] areas;

    Course(int lvl, Position position, Task traverseTask, BooleanSupplier skip, AgilArea... areas) {
        this.lvl = lvl;
        this.position = position;
        this.traverseTask = traverseTask;
        this.skip = skip;
        this.areas = areas;
        Collections.reverse(Arrays.asList(areas));
    }

    Course(int lvl, Position position, Task traverseTask, AgilArea... areas) {
        this(lvl, position, traverseTask, () -> false, areas);
    }

    public static Course getForLevel(int currLvl) {
        for (Course course : reversed) {
            if (currLvl >= course.lvl
                    && !course.skip.getAsBoolean()
                    && (course.isNear() || course.getTraverseTask().validate())
                    ) {
                return course;
            }
        }
        return null;
    }

    public boolean isNear() {
        return position.distance(new Position(Players.getLocal().getX(), Players.getLocal().getY(), position.getFloorLevel())) < 35;
    }

    public Position getPosition() {
        return position;
    }

    public AgilArea[] getAreas() {
        return areas;
    }

    public Task getTraverseTask() {
        return traverseTask;
    }
}
