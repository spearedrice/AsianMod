package org.spearedrice.asianmod.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import org.spearedrice.asianmod.AsianMod;

public class ModCriteria {
    public static final UseToolCriterion USE_TOOL = new UseToolCriterion();
    public static final ParameterizedUseToolCriterion PARAMETERIZED_USE_TOOL = new ParameterizedUseToolCriterion();

    public static void register() {
        CriteriaTriggers.register(AsianMod.MOD_ID + ":use_tool", USE_TOOL);
        CriteriaTriggers.register(AsianMod.MOD_ID + ":parameterized_use_tool", PARAMETERIZED_USE_TOOL);
    }
}