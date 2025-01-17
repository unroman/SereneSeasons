/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import sereneseasons.config.ServerConfig;
import sereneseasons.handler.season.SeasonHandler;
import sereneseasons.season.SeasonSavedData;
import sereneseasons.season.SeasonTime;

import java.util.Locale;

public class CommandGetSeason
{
    static ArgumentBuilder<CommandSourceStack, ?> register()
    {
        return Commands.literal("get")
            .executes(ctx -> {
                Level world = ctx.getSource().getLevel();
                return getSeason(ctx.getSource(), world);
            });
    }

    private static int getSeason(CommandSourceStack cs, Level world) throws CommandRuntimeException
    {
        SeasonSavedData seasonData = SeasonHandler.getSeasonSavedData(world);
        SeasonTime time = new SeasonTime(seasonData.seasonCycleTicks);
        int subSeasonDuration = ServerConfig.subSeasonDuration.get();
        cs.sendSuccess(() -> Component.translatable("commands.sereneseasons.getseason.success", Component.translatable("desc.sereneseasons."+ time.getSubSeason().toString().toLowerCase(Locale.ROOT)), (time.getDay() % subSeasonDuration) + 1, subSeasonDuration, time.getSeasonCycleTicks() % time.getDayDuration(), time.getDayDuration()), true);

        return 1;
    }
}
