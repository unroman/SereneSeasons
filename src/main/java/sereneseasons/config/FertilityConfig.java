package sereneseasons.config;

import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.ForgeConfigSpec;

public class FertilityConfig
{
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	// General config options
	public static ForgeConfigSpec.BooleanValue seasonalCrops;
	public static ForgeConfigSpec.BooleanValue cropTooltips;
	public static ForgeConfigSpec.IntValue outOfSeasonCropBehavior;
	public static ForgeConfigSpec.IntValue undergroundFertilityLevel;

	static
	{
		BUILDER.push("general");
		seasonalCrops = BUILDER.comment("Whether crops are affected by seasons.").define("seasonal_crops", true);
		cropTooltips = BUILDER.comment("Whether to include tooltips on crops listing which seasons they're fertile in. Note: This only applies to listed crops.").define("crop_tooltips", true);
		outOfSeasonCropBehavior = BUILDER.comment("How crops behave when out of season.\n0 = Grow slowly\n1 = Can't grow\n2 = Break when trying to grow").defineInRange("out_of_season_crop_behavior", 0, 0, 2);
		undergroundFertilityLevel = BUILDER.comment("Maximum height level for out of season crops to have fertility underground.").defineInRange("underground_fertility_level", 48, DimensionType.MIN_Y, Integer.MAX_VALUE);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
