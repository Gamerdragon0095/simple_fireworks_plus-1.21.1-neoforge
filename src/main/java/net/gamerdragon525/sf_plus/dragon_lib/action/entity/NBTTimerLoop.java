package net.gamerdragon525.sf_plus.dragon_lib.action.entity;

import net.minecraft.world.entity.Entity;

import java.util.Calendar;

public class NBTTimerLoop {
    public static void createNewTimerTag(Entity entity, String name, int ticks, Boolean paused, Boolean doseCountDown) {
        if (entity == null)
            return;

        String timerTag = "ticks:[" + ticks + "]" + " ,isPaused:[" + paused + "], doseCountDown:[" + doseCountDown + "]";

        entity.getPersistentData().putString("" + name, timerTag);

        if(entity.getPersistentData().getString("TimerLoops").length() >= 1) {
            if (!entity.getPersistentData().getString("TimerLoops").substring(14).contains(name)) {
                String amountOfTimers = entity.getPersistentData().getString("TimerLoops").substring(12, entity.getPersistentData().getString("TimerLoops").indexOf(")"));

                int numberOfTimers = new Object() {
                    int convert(String s) {
                        try {
                            return Integer.parseInt(s.trim());
                        } catch (Exception e) {

                        }
                        return 0;
                    }
                }.convert(amountOfTimers);

                entity.getPersistentData().putString("TimerLoops", "totalLoops:(" + (numberOfTimers + 1) + ")" + entity.getPersistentData().getString("TimerLoops").substring(14) + " ," + (numberOfTimers + 1) + ":[" + name + "])");
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: timerTag with name: '" + name + "' for entity: '" + entity.getStringUUID() + "' already exists, please use 'setTicksOfTimerTag' or 'setIsPausedOfTimerTag' to modify existing timerTags");
            }

        } else {
            entity.getPersistentData().putString("TimerLoops", "totalLoops:(1) ,Timers:( 1:[" + name + "])");
        }

    }

    public static void setTicksOfTimerTag(Entity entity, String name, int ticks) {

        if(entity.getPersistentData().getString("" + name).length() >= 1) {

            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops");
            String oldTag = entity.getPersistentData().getString("" + name);

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = oldTag.indexOf("isPaused:[", 0) + 10;
                double index2 = oldTag.indexOf("]", (int) index1);

                double index3 = oldTag.indexOf("doseCountDown:[", 0) + 15;
                double index4 = oldTag.indexOf("]", (int) index3);

                String newTag = "ticks:[" + ticks + "]" + " ,isPaused:[" + (oldTag.substring((int) index1, (int) index2)) + "], doseCountDown:[" + (oldTag.substring((int) index3, (int) index4)) + "]";

                entity.getPersistentData().putString("" + name, newTag);
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be modified by this action");
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to modify it");
        }

    }

    public static void setIsPausedOfTimerTag(Entity entity, String name, boolean paused) {

        if(entity.getPersistentData().getString("" + name).length() >= 1) {
            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops");
            String oldTag = entity.getPersistentData().getString("" + name);

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = oldTag.indexOf("ticks:[", 0) + 7;
                double index2 = oldTag.indexOf("]", (int) index1);

                double index3 = oldTag.indexOf("doseCountDown:[", 0) + 15;
                double index4 = oldTag.indexOf("]", (int) index3);

                String newTag = "ticks:[" + (oldTag.substring((int) index1, (int) index2)) + "]" + " ,isPaused:[" + paused + "], doseCountDown:[" + (oldTag.substring((int) index3, (int) index4)) + "]";

                entity.getPersistentData().putString("" + name, newTag);
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be modified by this action");
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to modify it");
        }

    }

    public static void setDoseCountDownOfTimerTag(Entity entity, String name, boolean doseCountDown) {

        if(entity.getPersistentData().getString("" + name).length() >= 1) {
            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops");
            String oldTag = entity.getPersistentData().getString("" + name);

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = oldTag.indexOf("ticks:[", 0) + 7;
                double index2 = oldTag.indexOf("]", (int) index1);

                double index3 = oldTag.indexOf("isPaused:[", 0) + 10;
                double index4 = oldTag.indexOf("]", (int) index3);

                String newTag = "ticks:[" + (oldTag.substring((int) index1, (int) index2)) + "]" + " ,isPaused:[" + (oldTag.substring((int) index3, (int) index4)) + "], doseCountDown:[" + doseCountDown + "]";

                entity.getPersistentData().putString("" + name, newTag);
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be modified by this action");
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to modify it");
        }

    }

}

