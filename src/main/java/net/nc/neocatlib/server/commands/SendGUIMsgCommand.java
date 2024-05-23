package net.nc.neocatlib.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.server.level.ServerPlayer;
import net.nc.neocatlib.util.NeoCatLibUtils;

import java.util.Collection;

public class SendGUIMsgCommand {

    public SendGUIMsgCommand(CommandDispatcher<CommandSourceStack> dp)
    {
        dp.register(Commands.literal("sendguimsg").requires((stack) -> {
            return stack.hasPermission(3);
        }).then(Commands.argument("targets", EntityArgument.players()).executes((ctx) -> {
            return SendGuiMsgFunc(ctx.getSource(), EntityArgument.getPlayers(ctx, "targets"), "Hello!");
        }).then(Commands.argument("msg", MessageArgument.message()).executes((ctx) -> {
            return SendGuiMsgFunc(ctx.getSource(), EntityArgument.getPlayers(ctx, "targets"), MessageArgument.getMessage(ctx, "msg").getString());
        }))));
    }

    private int SendGuiMsgFunc(CommandSourceStack src, Collection<ServerPlayer> pPlayers, String msg) throws CommandSyntaxException {

        try {
            String m = msg.toString();

            for (ServerPlayer serverplayer : pPlayers) {
                NeoCatLibUtils.sendGUIMessage(m, serverplayer);
            }
        } catch(Exception err)
        {
            return -1;
        }
        return 1;

    }
}
